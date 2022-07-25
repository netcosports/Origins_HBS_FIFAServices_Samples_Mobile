//
//  SampleStatsController.swift
//  HBSSDK
//
//  Created by Denis Shikunets on 3/22/22.
//  Copyright © 2022 Netcosports. All rights reserved.
//

import UIKit
import HBSSDK
import Alidade
import PinLayout

class SampleStatsController: SampleBaseController {

  private var isTransparent = false

  private let logo = UIImageView()

  private let topPlayers = UIButton {
    $0.setupHbsButton("Top players")
  }

  private let teamMatches = UIButton {
    $0.setupHbsButton("Team matches")
  }

  override func viewDidLoad() {
    super.viewDidLoad()
    logo.image = getLogoImage()
    self.view.addSubviews(logo, topPlayers, teamMatches)

    teamMatches.rx.tap
      .subscribe(onNext: {[weak self] _ in
        let controller = SampleTeamMatchesStatsController()
        controller.setupParams(teamId: "275075")
        self?.openController(controller)
      })
      .disposed(by: disposeBag)

    topPlayers.rx.tap
      .subscribe(onNext: {[weak self] _ in
        let controller = SampleTopPlayersStatsController()
        self?.openController(controller)
      })
      .disposed(by: disposeBag)


  }

  private func openController(_ controller: UIViewController) {
    self.navigationController?.pushViewController(controller, animated: true)
  }

  override func viewDidLayoutSubviews() {
    super.viewDidLayoutSubviews()
    logo.pin.top(self.view.safeAreaInsets.top).size(78.ui).hCenter()
    topPlayers.pin.horizontally(20.ui).height(100.ui).below(of: logo).marginTop(10.ui)
    teamMatches.pin.horizontally(20.ui).height(100.ui).below(of: topPlayers)
      .marginTop(10.ui)
  }

  
}
