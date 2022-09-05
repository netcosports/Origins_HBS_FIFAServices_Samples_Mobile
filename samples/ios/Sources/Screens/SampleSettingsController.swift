//
//  SampleSettingsController.swift
//  iosApp
//
//  Created by administrator on 9/5/22.
//  Copyright © 2022 Netcosports. All rights reserved.
//

import UIKit
import RxSwift
import RxCocoa
import HBSSDK

class SampleSettingsController: UIViewController {

  private let sampleSettings = HbsSampleSettings()

  private let titleLabel = UILabel {
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

  private let close = UIButton {
    $0.setTitle("Close", for: .normal)
    $0.setTitleColor(.black, for: .normal)
    $0.layer.borderWidth = 2.ui
    $0.layer.borderColor = UIColor.black.cgColor
  }

  override func viewDidLoad() {
    super.viewDidLoad()
    self.view.backgroundColor = .white

    self.view.addSubviews(titleLabel, directionAuto, directionLtr, directionRtl)

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

  override func viewDidLayoutSubviews() {
    super.viewDidLayoutSubviews()
    titleLabel.pin.top(20.ui).start(20.ui).sizeToFit()
    directionAuto.pin.below(of: titleLabel).start(to: titleLabel.edge.start).marginTop(10.ui).width(90.ui).height(40.ui)

    directionLtr.pin.after(of: directionAuto).marginStart(20.ui).top(to: directionAuto.edge.top).width(90.ui).height(40.ui)

    directionRtl.pin.after(of: directionLtr).marginStart(20.ui).top(to: directionAuto.edge.top).width(90.ui).height(40.ui)

    close.pin.bottom(30.ui).width(200.ui).height(50.ui).hCenter()

  }

}
