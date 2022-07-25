//
//  SampleHeadToHeadController.swift
//  HBSSDK
//
//  Created by Denis Shikunets on 4/14/22.
//  Copyright © 2022 Netcosports. All rights reserved.
//

import UIKit
import HBSSDK

class SampleHeadToHeadController: SampleBaseController {

  private let headToHeadWidget = HBSSDK.HeadToHead.widget()
  private let scrollView = UIScrollView()

  override func viewDidLoad() {
    super.viewDidLoad()
    self.view.addSubviews(scrollView)
    scrollView.addSubviews(headToHeadWidget)
    scrollView.showsVerticalScrollIndicator = false
    headToHeadWidget.setupNoTeams()
//    headToHeadWidget.setupOneTeamId(teamId: "43963")
//    headToHeadWidget.setupTwoTeamIds(team1Id: "43938", team2Id: "43925")
  }

  override func viewDidLayoutSubviews() {
    super.viewDidLayoutSubviews()
    scrollView.pin.all().marginTop(self.view.safeAreaInsets.top)
    headToHeadWidget.pin.top().size(HBSSDK.HeadToHead.size(for: scrollView.bounds.size))

    scrollView.contentSize = .init(width: scrollView.width, height: headToHeadWidget.frame.maxY + 12.ui)
  }

}
