//
//  SampleWatchController.swift
//  HBSSDK
//
//  Created by Denis Shikunets on 6/29/22.
//  Copyright © 2022 Netcosports. All rights reserved.
//

import UIKit
import HBSSDK

class SampleWatchController: SampleBaseController {


  private let watchWidget = HBSSDK.Watch.widget()
  private let logo = UIImageView()

  override func viewDidLoad() {
    super.viewDidLoad()
    logo.image = getLogoImage()
    self.view.addSubviews(logo, watchWidget)

    watchWidget.setupTeamId(teamId: "275075")
  }

  override func viewDidLayoutSubviews() {
    super.viewDidLayoutSubviews()
    logo.pin.top(self.view.getStatusBarHeight()).size(78.ui).hCenter()
    watchWidget.pin.below(of: logo).horizontally()
      .height(HBSSDK.Watch.size(for: self.view.bounds.size).height)
  }
}
