//
//  GroupStandingsSelectTypeController.swift
//  iosApp
//
//  Created by Denis Shikunets on 2/7/22.
//  Copyright © 2022 Netcosports. All rights reserved.
//

import UIKit
import PinLayout
import Alidade
import RxSwift
import RxCocoa

class GroupStandingsSelectTypeController: UIViewController {


  private let disposeBag = DisposeBag()

  private let compact: UIButton = {
    let button = UIButton()
    button.setTitle("Compact", for: .normal)
    button.setTitleColor(.white, for: .normal)
    button.backgroundColor = .darkGray
    return button
  }()
  private let expanded: UIButton = {
    let button = UIButton()
    button.setTitle("Expanded", for: .normal)
    button.setTitleColor(.white, for: .normal)
    button.backgroundColor = .darkGray
    return button
  }()


  private let singleGroup: UIButton = {
    let button = UIButton()
    button.setTitle("Single Group Expanded", for: .normal)
    button.setTitleColor(.white, for: .normal)
    button.backgroundColor = .darkGray
    return button
  }()

  override func viewDidLoad() {
    super.viewDidLoad()
    self.view.addSubviews(compact, expanded, singleGroup)

    Observable.merge(
      compact.rx.tap.map { _ in WidgetType.compact},
      expanded.rx.tap.map { _ in WidgetType.expaned}
    ).observeOn(MainScheduler.asyncInstance)
      .subscribe(onNext: {[weak self] type in
        self?.openWidgets(type: type)
      })
      .disposed(by: disposeBag)

    Observable.merge(
      singleGroup.rx.tap.map { _ in WidgetType.expaned}
    ).observeOn(MainScheduler.asyncInstance)
      .subscribe(onNext: {[weak self] type in
        self?.openSingleWidgets(type: type)
      })
      .disposed(by: disposeBag)

    changeBackground()
  }

  private func openSingleWidgets(type: WidgetType) {
    let widgetController = SingleGroupStandingsViewController()
    switch type {
    case .compact:
      widgetController.setupParams(isExpanded: false)
    case .expaned:
      widgetController.setupParams(isExpanded: true)
    }

    navigationController?.pushViewController(widgetController, animated: true)
  }

  private func openWidgets(type: WidgetType) {
    let widgetController = GroupStandingsViewController()
    switch type {
    case .compact:
      widgetController.setupParams(isExpanded: false)
    case .expaned:
      widgetController.setupParams(isExpanded: true)
    }

    navigationController?.pushViewController(widgetController, animated: true)
  }

  override func traitCollectionDidChange(_ previousTraitCollection: UITraitCollection?) {
    super.traitCollectionDidChange(previousTraitCollection)
    changeBackground()
  }

  private func changeBackground() {
    if self.traitCollection.userInterfaceStyle == .dark {
      self.view.backgroundColor = .black
    } else {
      self.view.backgroundColor = .white
    }
  }

  override func viewDidLayoutSubviews() {
    super.viewDidLayoutSubviews()
    compact.pin.top(view.safeAreaInsets.top).horizontally(20.ui).height(100.ui)
    expanded.pin.below(of: compact).marginTop(20.ui).horizontally(20.ui).height(100.ui)

    singleGroup.pin.below(of: expanded).marginTop(20.ui).horizontally(20.ui).height(100.ui)
  }

  private enum WidgetType {
    case compact
    case expaned
  }
  
}
