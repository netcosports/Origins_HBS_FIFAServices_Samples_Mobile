//
//  SampleVideoController.swift
//  HBSSDK
//
//  Created by Denis Shikunets on 3/23/22.
//  Copyright © 2022 Netcosports. All rights reserved.
//

import UIKit
import HBSSDK

class SampleVideoController: SampleBaseController {

  private let videoWidget = HBSSDK.Videos.widget()
  private let logo = UIImageView()

  override func viewDidLoad() {
    super.viewDidLoad()
    logo.image = getLogoImage()
    self.view.addSubviews(logo, videoWidget)
  }

  override func viewDidLayoutSubviews() {
    super.viewDidLayoutSubviews()
    logo.pin.top(self.view.getStatusBarHeight()).size(78.ui).hCenter()
    videoWidget.pin.below(of: logo).horizontally()
      .height(HBSSDK.Videos.size(for: self.view.bounds.size).height)
  }
}
