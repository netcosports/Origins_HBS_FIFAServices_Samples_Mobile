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


  }

  func setupParams(watchType: WatchType) {
    switch watchType {
    case .group:
      watchWidget.setupGroupId(groupId: "255937")
      break
    case .team:
      watchWidget.setupTeamId(teamId: "43960")
      break
    case .round:
      watchWidget.setupRoundId(roundId: "255951")
      break
    case .match:
      watchWidget.setupMatchId(matchId: "134080")
      break
    }
  }

  override func viewDidLayoutSubviews() {
    super.viewDidLayoutSubviews()
    logo.pin.top(self.view.getStatusBarHeight()).size(78.ui).hCenter()
    watchWidget.pin.below(of: logo).horizontally()
      .height(HBSSDK.Watch.size(for: self.view.bounds.size).height)
  }

  enum WatchType {
    case group
    case team
    case round
    case match
  }
}
