//
//  AVPlayerItem+Rx.swift
//  RxAVPlayer
//
//  Created by Patrick Mick on 4/1/16.
//  Copyright © 2016 YayNext. All rights reserved.
//

import Foundation
import RxSwift
import AVFoundation

extension Reactive where Base: AVPlayerItem {
  var status: Observable<AVPlayerItem.Status> {
    return self.observe(AVPlayerItem.Status.self, #keyPath(AVPlayerItem.status))
            .map { $0 ?? .unknown }
    }
    
    var error: Observable<NSError?> {
        return self.observe(NSError.self, #keyPath(AVPlayerItem.error))
    }
    
    var duration: Observable<CMTime> {
        return self.observe(CMTime.self, #keyPath(AVPlayerItem.duration))
            .map { $0 ?? .zero }
    }

  var seekableRange: Observable<[CMTimeRange]> {
    return self.observe([NSValue].self, #keyPath(AVPlayerItem.seekableTimeRanges))
      .map { ranges in
        return (ranges ?? []).map { $0.timeRangeValue }
      }
  }
    
    var playbackLikelyToKeepUp: Observable<Bool> {
        return self.observe(Bool.self, #keyPath(AVPlayerItem.isPlaybackLikelyToKeepUp))
            .map { $0 ?? false }
    }
    
    var playbackBufferFull: Observable<Bool> {
        return self.observe(Bool.self, #keyPath(AVPlayerItem.isPlaybackBufferFull))
            .map { $0 ?? false }
    }
    
    var playbackBufferEmpty: Observable<Bool> {
        return self.observe(Bool.self, #keyPath(AVPlayerItem.isPlaybackBufferEmpty))
            .map { $0 ?? false }
    }
    
    var didPlayToEnd: Observable<Notification> {
        let ns = NotificationCenter.default
        return ns.rx.notification(.AVPlayerItemDidPlayToEndTime, object: base)
    }
    
    var loadedTimeRanges: Observable<[CMTimeRange]> {
        return self.observe([NSValue].self, #keyPath(AVPlayerItem.loadedTimeRanges))
            .map { $0 ?? [] }
            .map { values in values.map { $0.timeRangeValue } }
    }
}
