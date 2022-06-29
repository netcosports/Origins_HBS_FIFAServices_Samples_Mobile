//
//  AVPlayerDemo.swift
//  Demo
//
//  Created by Sergei Mikhan on 2/23/20.
//

import AVKit
import Dioptra
import OnRewindSDK
import UIKit

import RxCocoa
import RxSwift

class AVPlayerDemo: UIView, OnRewindSDK.PlayerWrapper {

  // MARK: - Internal
  fileprivate var progressClosure: OnRewindSDK.WrapperProgressClosure?
  fileprivate var playerStateClosure: OnRewindSDK.WrapperPlayerStateClosure?
  fileprivate var playing = false
  fileprivate var disposeBag: DisposeBag?
  fileprivate let avItemDisposeBag = DisposeBag()

  // MARK: - UIView
  public init(item: Observable<AVPlayerItem>) {
    super.init(frame: .zero)
    backgroundColor = .black
    playerLayer?.videoGravity = .resizeAspect
    playerLayer?.player = player
    item.observeOn(MainScheduler.instance).subscribe(onNext: { [weak self] item in
      guard let player = self?.player else { return }
      self?.bind(item: item, into: player)
      self?.player.replaceCurrentItem(with: item)
    }).disposed(by: avItemDisposeBag)
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
    let disposeBag = DisposeBag()
    let periodicTimeUpdateInterval = CMTime(value: 1, timescale: 1)
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
      .map { ranges -> LoadedTimeRange in
        return ranges.map {
          let bounds = (lower: CMTimeGetSeconds($0.start), upper: CMTimeGetSeconds($0.end))
          guard bounds.lower.isFinite && bounds.upper.isFinite else {
            return TimeInSecondsRange(uncheckedBounds: (0, 0))
          }
          return TimeInSecondsRange(uncheckedBounds: bounds)
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
    .map { $0 == 0.0 ? PlayerState.active(state: .paused) : PlayerState.active(state: .playing) }
    .subscribe(onNext: { [weak self] state in
      self?.playerStateClosure?(state)
    }).disposed(by: disposeBag)

    item.rx.playbackLikelyToKeepUp
    .observeOn(MainScheduler.asyncInstance)
    .map { [weak player] in
      return $0 ? PlayerState.active(state: player?.rate == 0.0 ? .paused : .playing ) : PlayerState.stuck
    }.subscribe(onNext: { [weak self] state in
      self?.playerStateClosure?(state)
    }).disposed(by: disposeBag)

    item.rx.didPlayToEnd
      .map { _ in PlayerState.finished }
      .subscribe(onNext: { [weak self] state in
      self?.playerStateClosure?(state)
    }).disposed(by: disposeBag)

    item.rx.status.filter { $0 == .readyToPlay }.take(1).map { _ in
      return PlayerState.ready
    }.subscribe(onNext: { [weak self] state in
      self?.playerStateClosure?(state)
    }).disposed(by: disposeBag)

    item.rx.error.flatMap { error -> Observable<Error> in
      guard let error = error else { return .empty() }
      return .just(error)
    }.map { error in PlayerState.error(error: .playback(error: error)) }
    .subscribe(onNext: { [weak self] state in
      self?.playerStateClosure?(state)
    }).disposed(by: disposeBag)

    self.disposeBag = disposeBag
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

  func setPlaybackState(state: OnRewindSDK.PlaybackState) {
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

