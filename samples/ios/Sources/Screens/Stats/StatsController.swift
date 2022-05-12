//
//  HomeController.swift
//  iosApp
//
//  Created by Denis Shikunets on 1/21/22.
//  Copyright © 2022 Netcosports. All rights reserved.
//

import UIKit
import PinLayout
import Alidade
import RxSwift
import RxCocoa
import HBSSDK

class StatsController: UIViewController {

  private let disposeBag = DisposeBag()
  private var isTransparent = false


  private let topPlayers: UIButton = {
    let button = UIButton()
    button.setTitle("Top Players", for: .normal)
    button.setTitleColor(.white, for: .normal)
    button.backgroundColor = .darkGray
    return button
  }()

  private let teamMatches: UIButton = {
    let button = UIButton()
    button.setTitle("Team Matches", for: .normal)
    button.setTitleColor(.white, for: .normal)
    button.backgroundColor = .darkGray
    return button
  }()

  override func traitCollectionDidChange(_ previousTraitCollection: UITraitCollection?) {
    changeBackground()
  }

  private func changeBackground() {
    if self.traitCollection.userInterfaceStyle == .dark {
      self.view.backgroundColor = .black
    } else {
      if (isTransparent) {
        self.view.backgroundColor = UIColor(red: 0.6, green: 0.05, blue: 0.1, alpha: 1.0)
      } else {
        self.view.backgroundColor = .white
      }
    }
  }

  override func viewDidLoad() {
    super.viewDidLoad()
    changeBackground()
    self.view.addSubviews(topPlayers, teamMatches)

    topPlayers.rx.tap.subscribe(onNext: { [weak self] _ in
      let controller = TopPlayerStatsTypeController()
      self?.navigationController?.pushViewController(controller, animated: true)

    })
      .disposed(by: disposeBag)

    teamMatches.rx.tap.subscribe(onNext: { [weak self] _ in
      let controller = TeamMatchesStatsController()
      controller.setupParams(teamId: "275075")
      self?.navigationController?.pushViewController(controller, animated: true)

    })
      .disposed(by: disposeBag)

  }

  override func viewDidLayoutSubviews() {
    super.viewDidLayoutSubviews()
    
    topPlayers.pin.horizontally(20.ui).height(100.ui).top(self.view.safeAreaInsets.top)
    teamMatches.pin.horizontally(20.ui).height(100.ui).below(of: topPlayers)
      .marginTop(30.ui)
  }
}
