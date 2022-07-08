//
//  ChampionshipController.swift
//  iosApp
//
//  Created by Denis Shikunets on 2/21/22.
//  Copyright © 2022 Netcosports. All rights reserved.
//

import UIKit
import HBSSDK

final class ChampionshipController: UIViewController {

  private let widget = HBSSDK.Championship.widget()
  

  override func viewDidLoad() {
    super.viewDidLoad()
    changeBackground()

    self.view.addSubviews(widget)
  }

  override func viewDidLayoutSubviews() {
    super.viewDidLayoutSubviews()
    widget.pin.marginTop(self.view.safeAreaInsets.top)
      .all()
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
