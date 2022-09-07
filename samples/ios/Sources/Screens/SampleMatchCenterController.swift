//
//  SampleMatchCenterController.swift
//  iosApp
//
//  Created by administrator on 9/6/22.
//  Copyright © 2022 Netcosports. All rights reserved.
//

import UIKit
import HBSSDK

class SampleMatchCenterController: SampleBaseController {

  private let titleLabel = UILabel {
    $0.textColor = .black
  }

  private let lineupWidget = HBSSDK.MatchCenter.lineupWidget()

  override func viewDidLoad() {
    super.viewDidLoad()
    self.view.backgroundColor = .white
    self.view.addSubviews(titleLabel, lineupWidget)

  }

  override func viewDidLayoutSubviews() {
    super.viewDidLayoutSubviews()
    titleLabel.pin.top(20.ui).hCenter().sizeToFit()
    lineupWidget.pin.below(of: titleLabel).marginTop(10.ui).start()
      .size(HBSSDK.MatchCenter.lineupWidgetSize(for: self.view.bounds.size))
  }

  func setupMatchId(matchId: String, isLocal: Bool) {
    titleLabel.text = isLocal ? "Local matchcenter" : "Global matchcenter"
    lineupWidget.setupMatchId(matchId: matchId)
    self.view.setNeedsLayout()
  }
}
