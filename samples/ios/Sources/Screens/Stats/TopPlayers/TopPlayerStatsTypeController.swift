//
//  TopPlayerStatsTypeController.swift
//  iosApp
//
//  Created by Denis Shikunets on 2/14/22.
//  Copyright © 2022 Netcosports. All rights reserved.
//

import UIKit
import RxSwift
import RxCocoa

final class TopPlayerStatsTypeController: UIViewController {

  private let disposeBag = DisposeBag()

  private let solid: UIButton = {
    let button = UIButton()
    button.setTitle("Solid", for: .normal)
    button.setTitleColor(.white, for: .normal)
    button.backgroundColor = .darkGray
    return button
  }()

  private let transparent: UIButton = {
    let button = UIButton()
    button.setTitle("Transparent", for: .normal)
    button.setTitleColor(.white, for: .normal)
    button.backgroundColor = .darkGray
    return button
  }()

  override func viewDidLoad() {
    super.viewDidLoad()
    changeBackground()
    self.view.addSubviews(solid, transparent)

    Observable.merge(
      solid.rx.tap.map {_ in false },
      transparent.rx.tap.map {_ in true }
    ).subscribe(onNext: { [weak self] isTransparent in
      guard let self = self else { return }
      let controller = TopPlayerStatsController()
      controller.setupViewParams(isTransparent: isTransparent)
      self.navigationController?.pushViewController(controller, animated: true)
    })
      .disposed(by: disposeBag)
  }

  override func viewDidLayoutSubviews() {
    super.viewDidLayoutSubviews()

    solid.pin.horizontally(20.ui).height(80.ui).top(self.view.safeAreaInsets.top)

    transparent.pin.horizontally(20.ui).height(80.ui).below(of: solid).marginTop(20.ui)
  }

  override func traitCollectionDidChange(_ previousTraitCollection: UITraitCollection?) {
    changeBackground()
  }

  private func changeBackground() {
    if self.traitCollection.userInterfaceStyle == .dark {
      self.view.backgroundColor = .black
    } else {
      self.view.backgroundColor = .white
    }
  }
}
