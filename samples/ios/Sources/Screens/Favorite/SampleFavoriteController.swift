//
//  SampleFavoriteController.swift
//  HBSSDK
//
//  Created by Denis Shikunets on 3/30/22.
//  Copyright © 2022 Netcosports. All rights reserved.
//

import UIKit
import HBSSDK

class SampleFavoriteController: SampleBaseController {

  private let favoriteWidget = HBSSDK.Favorites.widget()

  override func viewDidLoad() {
    super.viewDidLoad()
    self.view.addSubviews(favoriteWidget)
  }

  override func viewDidLayoutSubviews() {
    super.viewDidLayoutSubviews()
    favoriteWidget.pin.top().size(HBSSDK.Favorites.size(for: self.view.frame.size))
  }

}
