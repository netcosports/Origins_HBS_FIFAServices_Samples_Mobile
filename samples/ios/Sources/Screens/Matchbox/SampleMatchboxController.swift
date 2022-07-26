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

  func setupParams(matchType: MatchType) {

    let groupId = "255937"
    let roundId = "255951"
    let teamId = "43960"
    let matchId = "84872"
    let secondMatchId = "84864"

    switch matchType {
    case .group:
      smallMatchesWidget.setGroupId(groupId: groupId)
      matchesMediumWidget.setGroupId(groupId: groupId)
      break
    case .team:
      smallMatchesWidget.setTeamId(teamId: teamId)
      matchesMediumWidget.setTeamId(teamId: teamId)
      break
    case .round:
      smallMatchesWidget.setRoundId(roundId: roundId)
      matchesMediumWidget.setRoundId(roundId: roundId)
      break
    case .match:
      smallMatchesWidget.setMatchId(matchId: matchId)
      matchesMediumWidget.setMatchId(matchId: secondMatchId)
      break
    }
  }

  private let smallMatchesWidget = HBSSDK.Matches.smallWidget()
  private let matchesMediumWidget = HBSSDK.Matches.mediumWidget()

  enum MatchType {
    case group
    case team
    case round
    case match
  }
  
}
