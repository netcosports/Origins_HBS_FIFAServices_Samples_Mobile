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

  private let compactSolid: UIButton = {
    let button = UIButton()
    button.setTitle("Compact Solid", for: .normal)
    button.setTitleColor(.white, for: .normal)
    button.backgroundColor = .darkGray
    return button
  }()

  private let compactTransparent: UIButton = {
    let button = UIButton()
    button.setTitle("Compact Transparent", for: .normal)
    button.setTitleColor(.white, for: .normal)
    button.backgroundColor = .darkGray
    return button
  }()

  private let expandedSolid: UIButton = {
    let button = UIButton()
    button.setTitle("Expanded Solid", for: .normal)
    button.setTitleColor(.white, for: .normal)
    button.backgroundColor = .darkGray
    return button
  }()

  private let expandedTransparent: UIButton = {
    let button = UIButton()
    button.setTitle("Expanded Transparent", for: .normal)
    button.setTitleColor(.white, for: .normal)
    button.backgroundColor = .darkGray
    return button
  }()

  private let singleSolidCompact: UIButton = {
    let button = UIButton()
    button.setTitle("Single Solid Compact", for: .normal)
    button.setTitleColor(.white, for: .normal)
    button.backgroundColor = .darkGray
    return button
  }()

  override func viewDidLoad() {
    super.viewDidLoad()
    self.view.addSubviews(compactSolid, compactTransparent, expandedSolid, expandedTransparent, singleSolidCompact)

    Observable.merge(
      compactSolid.rx.tap.map { _ in WidgetType.compactSolid},
      compactTransparent.rx.tap.map { _ in WidgetType.compactTransparent},
      expandedSolid.rx.tap.map { _ in WidgetType.expandedSolid},
      expandedTransparent.rx.tap.map { _ in WidgetType.expandedTransparent}
    ).observeOn(MainScheduler.asyncInstance)
      .subscribe(onNext: {[weak self] type in
        self?.openWidgets(type: type)
      })
      .disposed(by: disposeBag)

    Observable.merge(
      singleSolidCompact.rx.tap.map { _ in WidgetType.compactSolid}
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
    case .compactSolid:
      widgetController.setupParams(isExpanded: false, isTransparent: false)
    case .compactTransparent:
      widgetController.setupParams(isExpanded: false, isTransparent: true)
    case .expandedSolid:
      widgetController.setupParams(isExpanded: true, isTransparent: false)
    case .expandedTransparent:
      widgetController.setupParams(isExpanded: true, isTransparent: true)
    }

    navigationController?.pushViewController(widgetController, animated: true)
  }

  private func openWidgets(type: WidgetType) {
    let widgetController = GroupStandingsViewController()
    switch type {
    case .compactSolid:
      widgetController.setupParams(isExpanded: false, isTransparent: false)
    case .compactTransparent:
      widgetController.setupParams(isExpanded: false, isTransparent: true)
    case .expandedSolid:
      widgetController.setupParams(isExpanded: true, isTransparent: false)
    case .expandedTransparent:
      widgetController.setupParams(isExpanded: true, isTransparent: true)
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
    compactSolid.pin.top(view.safeAreaInsets.top).horizontally(20.ui).height(100.ui)
    compactTransparent.pin.below(of: compactSolid).marginTop(20.ui).horizontally(20.ui).height(100.ui)

    expandedSolid.pin.below(of: compactTransparent).marginTop(20.ui).horizontally(20.ui).height(100.ui)
    expandedTransparent.pin.below(of: expandedSolid).marginTop(20.ui).horizontally(20.ui).height(100.ui)

    singleSolidCompact.pin.below(of: expandedTransparent).marginTop(20.ui).horizontally(20.ui).height(100.ui)
  }

  private enum WidgetType {
    case compactSolid
    case compactTransparent
    case expandedSolid
    case expandedTransparent
  }
  
}
