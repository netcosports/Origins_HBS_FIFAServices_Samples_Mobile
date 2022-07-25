//
//  SampleCarouselStandingsController.swift
//  HBSSDK
//
//  Created by Denis Shikunets on 3/22/22.
//  Copyright © 2022 Netcosports. All rights reserved.
//

import UIKit
import HBSSDK
import Alidade

class SampleCarouselStandingsController: SampleBaseController {

  private let logo = UIImageView()

  func setupParams(isExpanded: Bool = false) {
    groupStandingWidget.setupAllGroupsWidgetParams(
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
