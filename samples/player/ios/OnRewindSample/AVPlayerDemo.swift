//
//  AVPlayerDemo.swift
//  Demo
//
//  Created by Sergei Mikhan on 2/23/20.
//

import AVKit
import OnRewindSDK
import UIKit

import RxSwift
import RxCocoa


enum TestError: OnRewindErrorWithMessage {
  var isRecoverableError: Bool {
    return true
  }

  var message: String {
    "Some error text"
  }

  case test
}

class AVPlayerDemo: UIView, OnRewindSDK.PlayerWrapper {

  func startPlayback(with streamUrl: URL, at timestamp: TimeInSeconds?) {
    let headers: [String: String] = [
      "referer": "https://sdk.onrewind.tv"
    ]
    let asset = AVURLAsset(url: streamUrl, options:
                            ["AVURLAssetHTTPHeaderFieldsKey": headers]
    )
    let itemToPlay = AVPlayerItem(asset: asset, automaticallyLoadedAssetKeys: nil)
    bind(item: itemToPlay, into: player)
    player.replaceCurrentItem(with: itemToPlay)
  }

//  func setOverlayControlsVisible(closure: @escaping WrapperOverlayControlsVisibleClosure) {
//  }
//
//  func setExternalPlaybackActive(closure: @escaping WrapperExternalPlaybackClosure) {
//    // FIXME:
//  }


  // MARK: - Internal
  fileprivate var progressClosure: OnRewindSDK.WrapperProgressClosure?
  fileprivate var playerStateClosure: OnRewindSDK.WrapperPlayerStateClosure?
  fileprivate var playing = false
  fileprivate var disposeBag = DisposeBag()

  // MARK: - UIView
  public init() {
    super.init(frame: .zero)
    backgroundColor = .black
    playerLayer?.videoGravity = .resizeAspect
    playerLayer?.player = player
  }

  public required init?(coder aDecoder: NSCoder) {
    fatalError("init(coder:) has not been implemented")
  }

  open override class var layerClass: AnyClass {
    return AVPlayerLayer.self
  }

  fileprivate var playerLayer: AVPlayerLayer? {
    return layer as? AVPlayerLayer
  }

  override open class var requiresConstraintBasedLayout: Bool {
    return false
  }

  private var player = AVPlayer()

  private func bind(item: AVPlayerItem, into player: AVPlayer) {
    disposeBag = DisposeBag()

    let periodicTimeUpdateInterval = CMTime(value: 1, timescale: 10)
    player.rx.periodicTimeObserver(interval: periodicTimeUpdateInterval)
      .filter { [weak self] progress in
        guard let timeRange = self?.player.currentItem?.seekableTimeRanges.last?.timeRangeValue else {
          return false
        }
        return timeRange.containsTime(progress)
      }
      .map {
        return CMTimeGetSeconds($0)
      }
      .filter { $0.isFinite }
      .subscribe(onNext: { [weak self] progress in
        self?.progressClosure?(ProgressEvent.progress(progress))
      }).disposed(by: disposeBag)

    item.rx.seekableRange.map {
      guard let lastRange = $0.last else { return 0.0 }
      let seconds = CMTimeGetSeconds(lastRange.end)
      guard seconds.isFinite else { return 0.0 }
      return seconds
    }.distinctUntilChanged()
     .filter { $0 > 0.0 }
    .subscribe(onNext: { [weak self] duration in
      self?.progressClosure?(ProgressEvent.duration(duration))
    }).disposed(by: disposeBag)

    item.rx.loadedTimeRanges
      .map { ranges -> [ClosedRange<TimeInSeconds>] in
        return ranges.map { range -> ClosedRange<TimeInSeconds> in
          let bounds = (lower: CMTimeGetSeconds(range.start), upper: CMTimeGetSeconds(range.end))
          guard bounds.lower.isFinite && bounds.upper.isFinite else {
            return ClosedRange<TimeInSeconds>(uncheckedBounds: (0, 0))
          }
          return ClosedRange<TimeInSeconds>(uncheckedBounds: bounds)
        }
    }.subscribe(onNext: { [weak self] buffers in
      guard let buffer = buffers.last?.upperBound else { return }
      self?.progressClosure?(ProgressEvent.buffer(buffer))
    }).disposed(by: disposeBag)

    player.rx.rate.filter { [weak self] rate in
      guard let item = self?.player.currentItem else {
        return false
      }
      // NOTE: we need this check to avoid unnecessary event when video finished
      return item.seekableTimeRanges.last?.timeRangeValue.end != item.currentTime()
    }
    .distinctUntilChanged()
    .map { $0 == 0.0 ? PlayerStateDeprecated.active(state: .paused) : PlayerStateDeprecated.active(state: .playing) }
    .subscribe(onNext: { [weak self] state in
      self?.playerStateClosure?(state)
    }).disposed(by: disposeBag)

    item.rx.playbackLikelyToKeepUp
    .observeOn(MainScheduler.asyncInstance)
    .map { [weak player] in
      return $0 ? PlayerStateDeprecated.active(state: player?.rate == 0.0 ? .paused : .playing ) : PlayerStateDeprecated.loading
    }.subscribe(onNext: { [weak self] state in
      self?.playerStateClosure?(state)
    }).disposed(by: disposeBag)

    item.rx.didPlayToEnd
      .map { _ in PlayerStateDeprecated.finished }
      .subscribe(onNext: { [weak self] state in
      self?.playerStateClosure?(state)
    }).disposed(by: disposeBag)

    item.rx.status.filter { $0 == .readyToPlay }.take(1).map { _ in
      return PlayerStateDeprecated.ready
    }.subscribe(onNext: { [weak self] state in
      self?.playerStateClosure?(state)
    }).disposed(by: disposeBag)

    item.rx.error.flatMap { error -> Observable<Error> in
      guard let error = error else { return .empty() }
      return .just(error)
    }.map { error in PlayerStateDeprecated.error(error: .playback(error: TestError.test)) }
    .subscribe(onNext: { [weak self] state in
      self?.playerStateClosure?(state)
    }).disposed(by: disposeBag)
  }

  // MARK: - PlayerWrapper
  var playerView: UIView { self }

  public var isMuted: Bool {
    set {
      player.isMuted = newValue
      player.currentItem?.audioMix = nil
    }

    get {
      player.isMuted
    }
  }
  var isPlaybackSpeedSupported: Bool { false }
  var playbackSpeed: Double = 1

  // MARK: - Dioptra - PlayerWrapper
  func seek(progress: TimeInSeconds, completion: @escaping WrapperVoidClosure) {
    if let timeRange = player.currentItem?.seekableTimeRanges.last?.timeRangeValue {
      let duration = timeRange.end
      let fullProgress = progress / CMTimeGetSeconds(duration)
      let time = CMTime(value: CMTimeValue(Double(duration.value) * fullProgress), timescale: duration.timescale)
      let tolerance = CMTime(seconds: 0.5, preferredTimescale: 1)
      player.currentItem?.cancelPendingSeeks()
      player.currentItem?.seek(to: time, toleranceBefore: tolerance, toleranceAfter: tolerance, completionHandler: { finished in
        if finished {
          completion()
        }
      })
    }
  }

  func setPlaybackState(state: OnRewindSDK.PlaybackStateDeprected) {
    switch state {
    case .paused:
      self.player.pause()
    case .playing:
      self.player.play()
    }
  }

  func setDidChangeProgress(closure: @escaping OnRewindSDK.WrapperProgressClosure) {
    progressClosure = closure
  }

  func setDidChangePlayerState(closure: @escaping OnRewindSDK.WrapperPlayerStateClosure) {
    playerStateClosure = closure
  }

  // MARK: - Not supported
  func selectVideoQuality(videoQuality: OnRewindSDK.VideoQuality) {
    // Not Supported
  }
  func setDidChangeAvailableVideoQualities(closure: @escaping OnRewindSDK.WrapperQualitiesClosure) {
    // Not Supported
  }
}
