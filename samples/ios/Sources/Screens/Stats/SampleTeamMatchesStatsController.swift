//
//  SampleTeamMatchesStatsController.swift
//  HBSSDK
//
//  Created by Denis Shikunets on 3/22/22.
//  Copyright © 2022 Netcosports. All rights reserved.
//

import UIKit
import HBSSDK

class SampleTeamMatchesStatsController: SampleBaseController {

  private let logo = UIImageView()

  private var teamId: String?

  func setupParams(teamId: String) {
    teamMatchesStatsWidget.setupWidgetParams(teamId: teamId)
  }

  private let teamMatchesStatsWidget = HBSSDK.Stats.teamMatchesWidget()


  override func viewDidLoad() {
    super.viewDidLoad()
    logo.image = getLogoImage()
    self.view.addSubviews(logo, teamMatchesStatsWidget)
  }

  override func viewDidLayoutSubviews() {
    super.viewDidLayoutSubviews()
    logo.pin.hCenter().size(78.ui).top(self.view.safeAreaInsets.top)
    teamMatchesStatsWidget.pin.below(of: logo).marginTop(30.ui)
      .start().size(HBSSDK.Stats.teamMatchesSize(for: self.view.bounds.size))
  }
}
