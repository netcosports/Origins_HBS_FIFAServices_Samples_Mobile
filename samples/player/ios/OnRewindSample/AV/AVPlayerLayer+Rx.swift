//
//  AVPlayerLayer+Rx.swift
//  RxAVPlayer
//
//  Created by Patrick Mick on 4/4/16.
//  Copyright © 2016 YayNext. All rights reserved.
//

import Foundation
import RxSwift
import AVFoundation

extension Reactive where Base: AVPlayerLayer {
    var readyForDisplay: Observable<Bool> {
      return self.observe(Bool.self, #keyPath(AVPlayerLayer.isReadyForDisplay))
            .map { $0 ?? false }
    }
}
