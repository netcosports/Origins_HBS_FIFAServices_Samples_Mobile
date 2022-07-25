//
//  SampleTopPlayersStatsController.swift
//  HBSSDK
//
//  Created by Denis Shikunets on 3/23/22.
//  Copyright © 2022 Netcosports. All rights reserved.
//

import UIKit
import HBSSDK
import PinLayout

class SampleTopPlayersStatsController: SampleBaseController {
  private let logo = UIImageView()

  private let goalsStatsWidget = HBSSDK.Stats.topPlayersWidget()
  private let assistsStatsWidget = HBSSDK.Stats.topPlayersWidget()

  override func viewDidLoad() {
    super.viewDidLoad()
    logo.image = getLogoImage()
    self.view.addSubviews(logo, goalsStatsWidget, assistsStatsWidget)

    goalsStatsWidget.setupWidgetParams(statsType: .goals)
    assistsStatsWidget.setupWidgetParams(statsType: .assists)
  }

  override func viewDidLayoutSubviews() {
    super.viewDidLayoutSubviews()
    logo.pin.top(self.view.safeAreaInsets).hCenter().size(78.ui)
    goalsStatsWidget.pin.below(of: logo).marginTop(20.ui).horizontally()
      .height(HBSSDK.Stats.topPlayersSize(for: view.bounds.size).height)

    assistsStatsWidget.pin.below(of: goalsStatsWidget).marginTop(20.ui).horizontally()
      .height(HBSSDK.Stats.topPlayersSize(for: view.bounds.size).height)
    
  }
}
