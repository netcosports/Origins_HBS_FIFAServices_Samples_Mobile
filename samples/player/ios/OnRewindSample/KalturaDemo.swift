//
//  KalturaDemo.swift
//  OnRewindSample
//
//  Created by Sergei Mikhan on 9.07.22.
//  Copyright © 2022 Sergei Mikhan. All rights reserved.
//


import AVKit
import Dioptra
import OnRewindSDK
import UIKit

import PinLayout

import RxCocoa
import RxSwift

import PlayKit

class KalturaPlayerDemo: UIView, OnRewindSDK.PlayerWrapper {

  // MARK: - Internal
  fileprivate let player: Player = {
    return PlayKitManager.shared.loadPlayer(pluginConfig: nil)
  } ()
  fileprivate let playerContainer = PlayerView()
  fileprivate var progressClosure: OnRewindSDK.WrapperProgressClosure?
  fileprivate var playerStateClosure: OnRewindSDK.WrapperPlayerStateClosure?
  fileprivate var seekedClosure: OnRewindSDK.WrapperVoidClosure?

  fileprivate var playing = true
  fileprivate var disposeBag: DisposeBag?

  // MARK: - UIView
  public init() {
    super.init(frame: .zero)
    backgroundColor = .black

    addSubview(playerContainer)

    player.addObserver(self, event: PlayerEvent.durationChanged, block: { [weak self] (event) in
      guard let self = self else { return }
      if let playerEvent = event as? PlayerEvent, let duration = playerEvent.duration {
        self.progressClosure?(.duration(duration.doubleValue))
      }
    })

    player.addObserver(self, event: PlayerEvent.playheadUpdate, block: { [weak self] (event) in
      guard let self = self else { return }
      if let playerEvent = event as? PlayerEvent, let currentTime = playerEvent.currentTime {
        self.progressClosure?(.progress(currentTime.doubleValue))
      }
    })

    player.addObserver(self, event: PlayerEvent.canPlay, block: { [weak self] (event) in
      guard let self = self else { return }
      self.playerStateClosure?(.ready)
    })

    player.addObserver(self, event: PlayerEvent.seeked, block: { [weak self] (event) in
      guard let self = self else { return }
      self.seekedClosure?()
      self.seekedClosure = nil

      if self.playing {
        self.playerStateClosure?(.active(state: .playing))
      } else {
        self.playerStateClosure?(.active(state: .paused))
      }
    })

    player.addObserver(self, event: PlayerEvent.playing, block: { [weak self] (event) in
      guard let self = self else { return }
      self.playing = true
      self.playerStateClosure?(.active(state: .playing))
    })

    player.addObserver(self, event: PlayerEvent.pause, block: { [weak self] (event) in
      guard let self = self else { return }
      self.playing = false
      self.playerStateClosure?(.active(state: .paused))
    })

    player.addObserver(self, event: PlayerEvent.playbackStalled, block: { [weak self] (event) in
      guard let self = self else { return }
      self.playerStateClosure?(.stuck)
    })

    player.addObserver(self, event: PlayerEvent.stateChanged, block: { [weak self] (event) in
      guard let self = self else { return }
      if let state = event as? PlayerEvent.StateChanged {
        switch state.newState {
          case .buffering:
            self.playerStateClosure?(.stuck)
          case .ended:
            self.playerStateClosure?(.finished)
          case .error:
            self.playerStateClosure?(.error(error: .playback(error: state.error)))
          default: break
        }
      }
    })

    preparePlayer()
  }

  override func layoutSubviews() {
    super.layoutSubviews()
    playerContainer.pin.vertically().aspectRatio(16.0 / 9.0).center()
  }

  deinit {
    player.removeObserver(self, event: PlayerEvent.durationChanged)
    player.removeObserver(self, event: PlayerEvent.playheadUpdate)
    player.removeObserver(self, event: PlayerEvent.canPlay)
    player.removeObserver(self, event: PlayerEvent.seeked)
    player.removeObserver(self, event: PlayerEvent.playing)
    player.removeObserver(self, event: PlayerEvent.pause)
    player.removeObserver(self, event: PlayerEvent.playbackStalled)
    player.removeObserver(self, event: PlayerEvent.stateChanged)
  }

  public required init?(coder aDecoder: NSCoder) {
    fatalError("init(coder:) has not been implemented")
  }

  override open class var requiresConstraintBasedLayout: Bool {
    return false
  }

  // MARK: - PlayerWrapper
  var playerView: UIView { self }

  public var isMuted: Bool = true {
    didSet {
      player.volume = isMuted ? 0.0 : 1.0
    }
  }
  var isPlaybackSpeedSupported: Bool { false }
  var playbackSpeed: Double = 1

  // MARK: - Dioptra - PlayerWrapper
  func seek(progress: TimeInSeconds, completion: @escaping WrapperVoidClosure) {
    player.seek(to: progress)
    seekedClosure = completion
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

extension KalturaPlayerDemo {

  func preparePlayer() {
      self.player.view = playerContainer
      let contentURL = "https://cdnapisec.kaltura.com/p/2215841/playManifest/entryId/1_w9zx2eti/format/applehttp/protocol/https/a.m3u8"
      let entryId = "sintel"
      let source = PKMediaSource(entryId, contentUrl: URL(string: contentURL), drmData: nil, mediaFormat: .hls)
      let mediaEntry = PKMediaEntry(entryId, sources: [source], duration: -1)
      let mediaConfig = MediaConfig(mediaEntry: mediaEntry)
      self.player.prepare(mediaConfig)
  }
}



