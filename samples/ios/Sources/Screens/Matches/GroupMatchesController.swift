//
//  MatchesController.swift
//  HBSSDK
//
//  Created by Denis Shikunets on 2/7/22.
//  Copyright © 2022 Netcosports. All rights reserved.
//

import UIKit
import RxSwift
import RxCocoa
import HBSSDK

final class GroupMatchesController: UIViewController {

  private let disposeBag = DisposeBag()

  private var isTransparent: Bool = false

  func setupParams(dataSource: MatchWidgetDataSource, isTransparent: Bool = false) {
    self.isTransparent = isTransparent
    smallMatchesWidget.setupWidgetParams(dataSource: dataSource, isTransparent: isTransparent)
    matchesMediumWidget.setupWidgetParams(dataSource: dataSource, isTransparent: isTransparent)
  }

  private let smallMatchesWidget = HBSSDK.matches().smallWidget()
  private let matchesMediumWidget = HBSSDK.matches().mediumWidget()

  private let smallLabel = UILabel {
    $0.text = "Small carousel widget"
  }

  private let mediumLabel = UILabel {
    $0.text = "Medium carousel widget"
  }

  override func viewDidLoad() {
    super.viewDidLoad()
    changeBackground()
    self.view.addSubviews(smallMatchesWidget, matchesMediumWidget, smallLabel, mediumLabel)
  }

  override func viewDidLayoutSubviews() {
    super.viewDidLayoutSubviews()
    smallLabel.pin.top(view.safeAreaInsets.top).marginTop(20.ui).sizeToFit().marginStart(10.ui)
    smallMatchesWidget.pin.below(of: smallLabel).marginTop(20.ui)
      .horizontally()
      .height(HBSSDK.matches().smallSize(for: view.bounds.size).height)

    mediumLabel.pin.below(of: smallMatchesWidget).marginTop(20.ui).sizeToFit().marginStart(10.ui)

    matchesMediumWidget.pin.below(of: mediumLabel).marginTop(20.ui)
      .horizontally()
      .height(HBSSDK.matches().mediumSize(for: view.bounds.size).height)
  }

  override func traitCollectionDidChange(_ previousTraitCollection: UITraitCollection?) {
    changeBackground()
  }

  private func changeBackground() {
    if self.traitCollection.userInterfaceStyle == .dark {
      self.view.backgroundColor = .black
      smallLabel.textColor = .white
      mediumLabel.textColor = .white
    } else {
      if (isTransparent) {
        self.view.backgroundColor = UIColor(red: 0.6, green: 0.05, blue: 0.1, alpha: 1.0)
      } else {
        self.view.backgroundColor = .white
      }

      smallLabel.textColor = .blue
      mediumLabel.textColor = .blue
    }
  }

}
