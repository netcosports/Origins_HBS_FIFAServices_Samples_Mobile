//
//  SampleActionController.swift
//  HBSSDK
//
//  Created by Denis Shikunets on 4/22/22.
//  Copyright © 2022 Netcosports. All rights reserved.
//

import UIKit
import HBSSDK

class SampleActionController: SampleBaseController {

  private let actionsWidget = HBSSDK.MatchCenter.actionsWidget()

  override func viewDidLoad() {
    super.viewDidLoad()
    self.view.addSubviews(actionsWidget)
    actionsWidget.setupMatchId(matchId: "84864")
  }

  override func viewDidLayoutSubviews() {
    super.viewDidLayoutSubviews()
    actionsWidget.pin.top(self.view.safeAreaInsets.top).horizontally().height(HBSSDK.MatchCenter.actionsWidgetSize(for: self.view.bounds.size).height)
  }
}
