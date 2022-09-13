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
    self.view.addSubviews(scrollView)
    scrollView.addSubviews(logo, smallMatchesWidget, matchesMediumWidget, largeMatchWidget, expandedMatchWidget)
  }

  override func viewDidLayoutSubviews() {
    super.viewDidLayoutSubviews()

    scrollView.pin.all().marginTop(self.view.safeAreaInsets.top)

    logo.pin.top().size(78.ui).hCenter()
    smallMatchesWidget.pin.horizontally().below(of: logo)
      .height(HBSSDK.Matches.smallSize(for: view.bounds.size).height)

    matchesMediumWidget.pin.below(of: smallMatchesWidget).marginTop(20.ui)
      .horizontally()
      .height(HBSSDK.Matches.mediumSize(for: view.bounds.size).height)

    largeMatchWidget.pin.below(of: matchesMediumWidget).marginTop(20.ui)
      .start()
      .size(HBSSDK.Matches.largeSize(for: view.bounds.size))

    expandedMatchWidget.pin.below(of: largeMatchWidget).marginTop(20.ui)
      .start()
      .size(HBSSDK.Matches.expandedSize(for: view.bounds.size))

    scrollView.contentSize = .init(width: self.view.width, height: expandedMatchWidget.frame.maxY + 20.ui)
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
      largeMatchWidget.setGroupId(groupId: groupId)
      expandedMatchWidget.setGroupId(groupId: groupId)
      break
    case .team:
      smallMatchesWidget.setTeamId(teamId: teamId)
      matchesMediumWidget.setTeamId(teamId: teamId)
      largeMatchWidget.setTeamId(teamId: teamId)
      expandedMatchWidget.setTeamId(teamId: teamId)
      break
    case .round:
      smallMatchesWidget.setRoundId(roundId: roundId)
      matchesMediumWidget.setRoundId(roundId: roundId)
      largeMatchWidget.setRoundId(roundId: roundId)
      expandedMatchWidget.setRoundId(roundId: roundId)
      break
    case .match:
      smallMatchesWidget.setMatchId(matchId: matchId)
      matchesMediumWidget.setMatchId(matchId: secondMatchId)
      largeMatchWidget.setMatchId(matchId: secondMatchId)
      expandedMatchWidget.setMatchId(matchId: secondMatchId)
      break
    }

    if useLocalMatchClickListener {
      [smallMatchesWidget, matchesMediumWidget, largeMatchWidget, expandedMatchWidget].forEach { widget in
        widget.openMatchDetailsBlock = { [weak self] matchId in
          self?.openLocalMatchCenter(matchId: matchId)
        }
      }
    }
  }

  private let scrollView = UIScrollView {
    $0.showsVerticalScrollIndicator = false
  }
  private let smallMatchesWidget = HBSSDK.Matches.smallWidget()
  private let matchesMediumWidget = HBSSDK.Matches.mediumWidget()
  private let largeMatchWidget = HBSSDK.Matches.largeWidget()
  private let expandedMatchWidget = HBSSDK.Matches.expandedWidget()

  enum MatchType {
    case group
    case team
    case round
    case match
  }
}
