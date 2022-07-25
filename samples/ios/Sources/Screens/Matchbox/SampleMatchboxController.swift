//
//  SampleMatchboxController.swift
//  HBSSDK
//
//  Created by Denis Shikunets on 3/23/22.
//  Copyright © 2022 Netcosports. All rights reserved.
//

import UIKit
import HBSSDK
import PinLayout
import Alidade

class SampleMatchboxController: SampleBaseController {

  private let logo = UIImageView()

  override func viewDidLoad() {
    super.viewDidLoad()
    logo.image = getLogoImage()
    self.view.addSubviews(logo, smallMatchesWidget, matchesMediumWidget)

    smallMatchesWidget.openMatchDetailsBlock = { [weak self] matchId in
      self?.testOpenMatch(matchId: matchId)
    }
  }

  override func viewDidLayoutSubviews() {
    super.viewDidLayoutSubviews()
    logo.pin.top(self.view.safeAreaInsets.top).size(78.ui).hCenter()
    smallMatchesWidget.pin.horizontally().below(of: logo)
      .height(HBSSDK.Matches.smallSize(for: view.bounds.size).height)

    matchesMediumWidget.pin.below(of: smallMatchesWidget).marginTop(20.ui)
      .horizontally()
      .height(HBSSDK.Matches.mediumSize(for: view.bounds.size).height)
  }

  private var isTransparent = false

  func setupParams(dataSource: MatchWidgetDataSource, isTransparent: Bool = false) {
    self.isTransparent = isTransparent

    switch dataSource {
      case .group(let groupId):
        smallMatchesWidget.setupGroupWidgetParams(groupId: groupId)
        matchesMediumWidget.setupGroupWidgetParams(groupId: groupId)
      case .team(let teamId):
        smallMatchesWidget.setupTeamWidgetParams(teamId: teamId)
        matchesMediumWidget.setupTeamWidgetParams(teamId: teamId)
      case .round(let roundId):
        smallMatchesWidget.setupRoundWidgetParams(roundId: roundId)
        matchesMediumWidget.setupRoundWidgetParams(roundId: roundId)
    }
  }

  private let smallMatchesWidget = HBSSDK.Matches.smallWidget()
  private let matchesMediumWidget = HBSSDK.Matches.mediumWidget()

  
}
