//
//  GroupStandingsController.swift
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

class GroupStandingsViewController: UIViewController {

  private let disposeBag = DisposeBag()

  private var isTransparent = false
  func setupParams(isExpanded: Bool = false, isTransparent: Bool = false) {
    self.isTransparent = isTransparent
    groupStandingWidget.setupAllGroupsWidgetParams(isExpanded: isExpanded, isTransparent: isTransparent)
  }


  private let groupStandingWidget = HBSSDK.Standings.widget()

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

    self.view.addSubviews(groupStandingWidget)

  }

  override func viewDidLayoutSubviews() {
    super.viewDidLayoutSubviews()
    groupStandingWidget.pin.top(view.safeAreaInsets.top)
      .marginTop(20.ui)
      .horizontally()
      .height(HBSSDK.Standings.size(for: view.bounds.size).height)
  }

  override func viewWillAppear(_ animated: Bool) {
    super.viewWillAppear(animated)
//    setupNavigationBar()
  }

  private func setupNavigationBar() {
    guard let navigationController = navigationController else {
      return
    }
    navigationController.navigationBar.barTintColor = .black
    navigationController.navigationItem.hidesBackButton = false
  }
}
