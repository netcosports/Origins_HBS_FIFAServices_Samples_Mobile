//
//  SampleTeamBoardController.swift
//  HBSSDK
//
//  Created by Denis Shikunets on 6/30/22.
//  Copyright © 2022 Netcosports. All rights reserved.
//

import UIKit
import HBSSDK
import hbsshared

class SampleTeamBoardController: SampleBaseController {

  private let teamBoardWidget = HBSSDK.TeamBoard.widget()
  private let scrollView = UIScrollView()

  override func viewDidLoad() {
    super.viewDidLoad()
    self.view.addSubviews(scrollView)
    scrollView.addSubviews(teamBoardWidget)
    scrollView.showsVerticalScrollIndicator = false
    title = "Team Board"
    teamBoardWidget.setupTeamId(teamId: HBSSDK.Integration.getFavoriteTeamId() ?? "43946")
    if useLocalMatchClickListener {
      teamBoardWidget.openMatchDetailsBlock = { [weak self] matchId in
        self?.openLocalMatchCenter(matchId: matchId)
      }
    }
  }


  override func viewDidLayoutSubviews() {
    super.viewDidLayoutSubviews()
    scrollView.pin.all().marginTop(self.view.safeAreaInsets.top)
    teamBoardWidget.pin.top()
      .size(HBSSDK.TeamBoard.size(for: scrollView.bounds.size))
    scrollView.contentSize = CGSize(
      width: scrollView.width,
      height: teamBoardWidget.frame.maxY + 16.ui
    )
  }

}
