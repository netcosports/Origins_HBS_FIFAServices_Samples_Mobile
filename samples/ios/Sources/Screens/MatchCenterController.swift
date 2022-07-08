//
//  MatchCenterController.swift
//  iosApp
//
//  Created by Denis Shikunets on 3/16/22.
//  Copyright © 2022 Netcosports. All rights reserved.
//

import UIKit
import HBSSDK


class MatchCenterControllerSample: UIViewController {

  private let matchCenterWidget = HBSSDK.MatchCenter.widget()
  override func viewDidLoad() {
    super.viewDidLoad()
    changeBackground()
    self.view.addSubviews(matchCenterWidget)
    matchCenterWidget.setupMatch(with: "108549")
  }

  override func viewDidLayoutSubviews() {
    super.viewDidLayoutSubviews()
    matchCenterWidget.pin.top().start()
      .size(HBSSDK.MatchCenter.widgetSize(for: view.bounds.size))

    
  }

  override func traitCollectionDidChange(_ previousTraitCollection: UITraitCollection?) {
    changeBackground()
  }

  private func changeBackground() {
    if self.traitCollection.userInterfaceStyle == .dark {
      self.view.backgroundColor = .black

    } else {
//      if (isTransparent) {
//        self.view.backgroundColor = UIColor(red: 0.6, green: 0.05, blue: 0.1, alpha: 1.0)
//      } else {
//        self.view.backgroundColor = .white
//      }

      self.view.backgroundColor = .white
    }
  }
  
}
