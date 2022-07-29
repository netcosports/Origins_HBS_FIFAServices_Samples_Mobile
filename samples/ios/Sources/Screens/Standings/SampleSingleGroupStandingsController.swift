//
//  SampleSingleGroupStandingsViewController.swift
//  HBSSDK
//
//  Created by Denis Shikunets on 3/22/22.
//  Copyright © 2022 Netcosports. All rights reserved.
//

import UIKit
import Alidade
import HBSSDK

class SampleSingleGroupStandingsController: SampleBaseController {

  private let logo = UIImageView()

  func setupParams(isExpanded: Bool = false) {
    groupStandingWidget.setupSingleGroupWidgetParams(
      groupId: "255945",
      isExpanded: isExpanded
    )
  }

  private let groupStandingWidget = HBSSDK.Standings.widget()

  override func viewDidLoad() {
    super.viewDidLoad()
    logo.image = getLogoImage()
    self.view.addSubviews(logo, groupStandingWidget)
  }

  override func viewDidLayoutSubviews() {
    super.viewDidLayoutSubviews()
    logo.pin.top(view.safeAreaInsets.top).hCenter().size(78.ui)
    groupStandingWidget.pin.below(of: logo)
      .marginTop(20.ui)
      .horizontally()
      .height(HBSSDK.Standings.size(for: view.bounds.size).height)
  }
}
