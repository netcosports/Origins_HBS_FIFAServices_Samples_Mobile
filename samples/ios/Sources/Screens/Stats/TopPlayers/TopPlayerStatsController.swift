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

class TopPlayerStatsController: UIViewController {

  private let disposeBag = DisposeBag()
  private var isTransparent = false


  func setupViewParams(isTransparent: Bool) {
    self.isTransparent = isTransparent
    goalsStatsWidget.setupWidgetParams(statsType: .goals, isTransparent: true)
    assistsStatsWidget.setupWidgetParams(statsType: .assists, isTransparent: false)
  }

  private let goalsStatsWidget = HBSSDK.stats().topPlayersWidget()
  private let assistsStatsWidget = HBSSDK.stats().topPlayersWidget()

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
    self.view.addSubviews(goalsStatsWidget, assistsStatsWidget)

  }

  override func viewDidLayoutSubviews() {
    super.viewDidLayoutSubviews()
    goalsStatsWidget.pin.top(view.safeAreaInsets.top).horizontally()
      .height(HBSSDK.stats().topPlayersSize(for: view.bounds.size).height)

    assistsStatsWidget.pin.below(of: goalsStatsWidget).marginTop(20.ui).horizontally()
      .height(HBSSDK.stats().topPlayersSize(for: view.bounds.size).height)

  }
}
