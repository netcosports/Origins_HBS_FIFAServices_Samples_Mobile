//
//  TeamMatchesStatsController.swift
//  iosApp
//
//  Created by Denis Shikunets on 2/25/22.
//  Copyright © 2022 Netcosports. All rights reserved.
//

import UIKit
import HBSSDK

class TeamMatchesStatsController: UIViewController {

  private var teamId: String?

  func setupParams(teamId: String) {
    teamMatchesStatsWidget.setupWidgetParams(teamId: teamId)
    teamMatchesStatsTransparentWidget.setupWidgetParams(teamId: teamId)
  }

  private let teamMatchesStatsWidget = HBSSDK.Stats.teamMatchesWidget()
  private let teamMatchesStatsTransparentWidget = HBSSDK.Stats.teamMatchesWidget()

  private let bottomView = UIView {
    $0.backgroundColor = .blue
  }

  override func viewDidLoad() {
    super.viewDidLoad()
    changeBackground()
    self.view.addSubviews(teamMatchesStatsWidget, bottomView)
    bottomView.addSubviews(teamMatchesStatsTransparentWidget)
  }

  override func viewDidLayoutSubviews() {
    super.viewDidLayoutSubviews()
    teamMatchesStatsWidget.pin.top(self.view.safeAreaInsets.top)
      .start().size(HBSSDK.Stats.teamMatchesSize(for: self.view.bounds.size))

    bottomView.pin.below(of: teamMatchesStatsWidget).marginTop(30.ui).all()

    teamMatchesStatsTransparentWidget.pin.top(self.view.safeAreaInsets.top)
      .start().size(HBSSDK.Stats.teamMatchesSize(for: self.view.bounds.size))
  }

  override func traitCollectionDidChange(_ previousTraitCollection: UITraitCollection?) {
    changeBackground()
  }

  private func changeBackground() {
    if self.traitCollection.userInterfaceStyle == .dark {
      self.view.backgroundColor = .black
    } else {
      self.view.backgroundColor = .white
    }
  }
}
