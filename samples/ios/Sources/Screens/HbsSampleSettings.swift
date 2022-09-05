//
//  HbsSampleSettings.swift
//  iosApp
//
//  Created by Denis Shikunets on 9/5/22.
//  Copyright © 2022 Netcosports. All rights reserved.
//

import Foundation
import HBSSDK
import hbsshared

class HbsSampleSettings {

  private let userDefaults = UserDefaults(suiteName: "hbs")!

  func setHbsLayoutDirection(direction: HbsLayoutDirection) {
    userDefaults.set(direction.rawValue, forKey: "direction")
  }

  func getHbsLayoutDirection() -> HbsLayoutDirection {
    let direction = userDefaults.integer(forKey: "direction")
    return HbsLayoutDirection.init(rawValue: direction) ?? .auto
  }

}
