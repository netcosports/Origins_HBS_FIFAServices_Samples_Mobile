//
//  HbsSampleSettings.swift
//  iosApp
//
//  Created by Denis Shikunets on 9/5/22.
//  Copyright © 2022 Netcosports. All rights reserved.
//

import Foundation
import HBSSDK

class HbsSampleSettings {

  private let userDefaults = UserDefaults(suiteName: "hbs")!

  func setHbsLayoutDirection(direction: HbsLayoutDirection) {
    userDefaults.set(direction.rawValue, forKey: "direction")
    userDefaults.synchronize()
  }

  func getHbsLayoutDirection() -> HbsLayoutDirection {
    let direction = userDefaults.integer(forKey: "direction")
    return HbsLayoutDirection.init(rawValue: direction) ?? .auto
  }

  func setDispayActions(display: Bool) {
    userDefaults.set(display, forKey: "display_actions")
    userDefaults.synchronize()
  }

  func isDisplayActions() -> Bool {
    return userDefaults.bool(forKey: "display_actions")
  }

}
