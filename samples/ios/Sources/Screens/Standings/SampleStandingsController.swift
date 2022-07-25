//
//  SampleStandingsController.swift
//  HBSSDK
//
//  Created by Denis Shikunets on 3/22/22.
//  Copyright © 2022 Netcosports. All rights reserved.
//

import UIKit
import hbsshared

class SampleStandingsController: SampleBaseController {

  private let logo = UIImageView()

  private let compact = UIButton {
    $0.setupHbsButton("Compact")
  }

  private let expanded = UIButton {
    $0.setupHbsButton("Expanded")
  }

  private let singleCompact = UIButton {
    $0.setupHbsButton("Single group compact")
  }

  private let singleExpanded = UIButton {
    $0.setupHbsButton("Single group expanded")
  }

  override func viewDidLoad() {
    super.viewDidLoad()
    logo.image = getLogoImage()
    self.view.addSubviews(logo, compact, expanded, singleCompact, singleExpanded)

    UIButton.appearance().tintColor = .white

    singleExpanded.rx.tap
      .subscribe(onNext: {[weak self] _ in
        let controller = SampleSingleGroupStandingsController()
        controller.setupParams(isExpanded: true)
        self?.openController(controller)
      })
      .disposed(by: disposeBag)

    singleCompact.rx.tap
      .subscribe(onNext: {[weak self] _ in
        let controller = SampleSingleGroupStandingsController()
        controller.setupParams(isExpanded: false)
        self?.openController(controller)
      })
      .disposed(by: disposeBag)

    compact.rx.tap
      .subscribe(onNext: {[weak self] _ in
        let controller = SampleCarouselStandingsController()
        controller.setupParams(isExpanded: false)
        self?.openController(controller)
      })
      .disposed(by: disposeBag)

    expanded.rx.tap
      .subscribe(onNext: {[weak self] _ in
        let controller = SampleCarouselStandingsController()
        controller.setupParams(isExpanded: true)
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
    compact.pin.below(of: logo).marginTop(48.ui).horizontally(28.ui).height(75.ui)
    expanded.pin.below(of: compact).marginTop(10.ui).horizontally(28.ui).height(75.ui)
    singleCompact.pin.below(of: expanded).marginTop(10.ui).horizontally(28.ui).height(75.ui)
    singleExpanded.pin.below(of: singleCompact).marginTop(10.ui).horizontally(28.ui).height(75.ui)
  }
}

