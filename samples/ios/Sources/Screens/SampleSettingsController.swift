//
//  SampleSettingsController.swift
//  iosApp
//
//  Created by Denis Shikunets on 9/5/22.
//  Copyright © 2022 Netcosports. All rights reserved.
//

import UIKit
import RxSwift
import RxCocoa
import HBSSDK

class SampleSettingsController: UIViewController {

  private let sampleSettings = HbsSampleSettings()

  private let directionLabel = UILabel {
    $0.text = "Layout direction"
  }

  private let directionAuto = UIButton {
    $0.setTitle("Auto", for: .normal)
  }

  private let directionLtr = UIButton {
    $0.setTitle("Ltr", for: .normal)
  }

  private let directionRtl = UIButton {
    $0.setTitle("Rtl", for: .normal)
  }

  private func directionButtons() -> [UIButton] {
    [directionAuto, directionLtr, directionRtl]
  }

  private let actionsLabel = UILabel {
    $0.text = "Display actions in matchcenter"
  }

  private let actionsDisplay = UIButton {
    $0.setTitle("Display", for: .normal)
  }

  private let actionsHide = UIButton {
    $0.setTitle("Hide", for: .normal)
  }

  private func displayActions() -> [UIButton] {
    [actionsDisplay, actionsHide]
  }

  private let matchcenterLabel = UILabel {
    $0.text = "Match center click handler"
  }

  private let matchcenterInternal = UIButton {
    $0.setTitle("Internal", for: .normal)
  }

  private let matchcenterLocal = UIButton {
    $0.setTitle("Local", for: .normal)
  }

  private let matchcenterGlobal = UIButton {
    $0.setTitle("Global", for: .normal)
  }

  private func matchcenterButtons() -> [UIButton] {
    [matchcenterInternal, matchcenterLocal, matchcenterGlobal]
  }

  private let close = UIButton {
    $0.setTitle("Close", for: .normal)
    $0.setTitleColor(.black, for: .normal)
    $0.layer.borderWidth = 2.ui
    $0.layer.borderColor = UIColor.black.cgColor
  }

  override func viewDidLoad() {
    super.viewDidLoad()
    self.view.backgroundColor = .white

    self.view.addSubviews(directionLabel, directionAuto, directionLtr, directionRtl)

    self.view.addSubviews(actionsLabel, actionsDisplay, actionsHide)

    self.view.addSubviews(matchcenterLabel, matchcenterInternal, matchcenterLocal, matchcenterGlobal)

    self.view.addSubviews(close)


    directionAuto.isSelected = true

    let hbsDirection = sampleSettings.getHbsLayoutDirection()

    directionButtons().forEach { view in
      view.isSelected = self.getDirectionByButton(button: view) == hbsDirection
      view.setTitleColor(.black, for: .selected)
      view.setTitleColor(.lightGray, for: .normal)
      view.layer.borderWidth = 2.ui
      view.layer.cornerRadius = 5.ui
      view.layer.borderColor = view.isSelected ? UIColor.black.cgColor : UIColor.lightGray.cgColor
    }


    Observable.merge(
      directionButtons().map { view in
        view.rx.tap.map { _ in
          view
        }
      }
    ).subscribe(onNext: { [weak self] view in
      guard let self = self else { return }
      self.directionButtons().forEach {
        $0.isSelected = $0 == view
        $0.layer.borderColor = $0.isSelected ? UIColor.black.cgColor : UIColor.lightGray.cgColor
        
      }
      self.sampleSettings.setHbsLayoutDirection(direction: self.getDirectionByButton(button: view))
    })
    .disposed(by: disposeBag)

    let displayButton =  sampleSettings.isDisplayActions() ? actionsDisplay : actionsHide

    displayActions().forEach { view in
      view.isSelected = view == displayButton
      view.setTitleColor(.black, for: .selected)
      view.setTitleColor(.lightGray, for: .normal)
      view.layer.borderWidth = 2.ui
      view.layer.cornerRadius = 5.ui
      view.layer.borderColor = view.isSelected ? UIColor.black.cgColor : UIColor.lightGray.cgColor
    }

    Observable.merge(
      displayActions().map { view in
        view.rx.tap.map { _ in
          view
        }
      }
    ).subscribe(onNext: { [weak self] view in
      guard let self = self else { return }
      self.displayActions().forEach {
        $0.isSelected = $0 == view
        $0.layer.borderColor = $0.isSelected ? UIColor.black.cgColor : UIColor.lightGray.cgColor

      }
      self.sampleSettings.setDispayActions(display: view == self.actionsDisplay)
    })
    .disposed(by: disposeBag)

    let matchcenterClickHandler = sampleSettings.getHbsMatchCenterClickHandler()

    matchcenterButtons().forEach { view in
    view.isSelected = self.getMatchCenterClicks(button: view) == matchcenterClickHandler
      view.setTitleColor(.black, for: .selected)
      view.setTitleColor(.lightGray, for: .normal)
      view.layer.borderWidth = 2.ui
      view.layer.cornerRadius = 5.ui
      view.layer.borderColor = view.isSelected ? UIColor.black.cgColor : UIColor.lightGray.cgColor
    }

    Observable.merge(
      matchcenterButtons().map { view in
        view.rx.tap.map { _ in
          view
        }
      }
    ).subscribe(onNext: { [weak self] view in
      guard let self = self else { return }
      self.matchcenterButtons().forEach {
        $0.isSelected = $0 == view
        $0.layer.borderColor = $0.isSelected ? UIColor.black.cgColor : UIColor.lightGray.cgColor

      }
      self.sampleSettings.setHbsMatchCenterClickHandler(handler: self.getMatchCenterClicks(button: view))

    })
    .disposed(by: disposeBag)

    close.rx.tap.subscribe(
      onNext: { [weak self] _ in
        self?.dismiss(animated: true)
      }
    ).disposed(by: disposeBag)
  }

  private let disposeBag = DisposeBag()

  private func getDirectionByButton(button: UIButton) -> HbsLayoutDirection {
    switch button {
    case directionRtl:
      return .rtl
    case directionLtr:
      return .ltr
    default:
      return .auto
    }
  }

  private func getMatchCenterClicks(button: UIButton) -> HbsMatchCenterClickHandler {
    switch button {
    case matchcenterGlobal:
      return .global
    case matchcenterLocal:
      return .local
    default:
      return .none
    }
  }

  override func viewDidLayoutSubviews() {
    super.viewDidLayoutSubviews()
    directionLabel.pin.top(20.ui).start(20.ui).sizeToFit()
    directionAuto.pin.below(of: directionLabel).start(to: directionLabel.edge.start).marginTop(10.ui).width(90.ui).height(40.ui)

    directionLtr.pin.after(of: directionAuto).marginStart(20.ui).top(to: directionAuto.edge.top).width(90.ui).height(40.ui)

    directionRtl.pin.after(of: directionLtr).marginStart(20.ui).top(to: directionAuto.edge.top).width(90.ui).height(40.ui)

    actionsLabel.pin.below(of: directionAuto).marginTop(20.ui).start(20.ui).sizeToFit()

    actionsDisplay.pin.below(of: actionsLabel).start(to: directionLabel.edge.start).marginTop(10.ui).width(90.ui).height(40.ui)

    actionsHide.pin.after(of: actionsDisplay).marginStart(20.ui).top(to: actionsDisplay.edge.top).width(90.ui).height(40.ui)

    matchcenterLabel.pin.below(of: actionsDisplay).marginTop(20.ui).start(20.ui).sizeToFit()

    matchcenterInternal.pin.below(of: matchcenterLabel).start(to: matchcenterLabel.edge.start).marginTop(10.ui).width(90.ui).height(40.ui)

    matchcenterLocal.pin.after(of: matchcenterInternal).marginStart(20.ui).top(to: matchcenterInternal.edge.top).width(90.ui).height(40.ui)

    matchcenterGlobal.pin.after(of: matchcenterLocal).marginStart(20.ui).top(to: matchcenterLocal.edge.top).width(90.ui).height(40.ui)

    close.pin.bottom(30.ui).width(200.ui).height(50.ui).hCenter()

  }

}
