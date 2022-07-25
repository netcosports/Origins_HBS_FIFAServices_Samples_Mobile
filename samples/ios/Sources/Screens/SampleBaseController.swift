//
//  SampleBaseController.swift
//  HBSSDK
//
//  Created by Denis Shikunets on 3/24/22.
//  Copyright © 2022 Netcosports. All rights reserved.
//

import UIKit
import HBSSDK
import Alidade
import RxSwift

class SampleBaseController: UIViewController, UIGestureRecognizerDelegate {

  let disposeBag = DisposeBag()
  private let backButtonButton = UIButton()

  private let background = GradientView()

  override func viewDidLoad() {
    super.viewDidLoad()
    backButtonButton.setImage(UIImage(named: "sampleBackButton"), for: .normal) //fixme use arrow
    self.view.backgroundColor = .white
    self.view.addSubviews(background)
    background.setupHbsSampleBackground()
    navigationItem.setHidesBackButton(true, animated: false)
    navigationItem.leftBarButtonItems = [
      UIBarButtonItem(customView: backButtonButton)
    ]
    backButtonButton.rx.tap.subscribe(onNext: {[weak self] _ in
      self?.navigationController?.popViewController(animated: true)
    })
      .disposed(by: disposeBag)
  }

  override func traitCollectionDidChange(_ previousTraitCollection: UITraitCollection?) {
    super.traitCollectionDidChange(previousTraitCollection)
    background.setupHbsSampleBackground()
  }

  override func viewDidLayoutSubviews() {
    super.viewDidLayoutSubviews()
    background.pin.all()
  }

  override func viewDidAppear(_ animated: Bool) {
    super.viewDidAppear(animated)
    self.navigationController?.interactivePopGestureRecognizer?.isEnabled = true
    self.navigationController?.interactivePopGestureRecognizer?.delegate = self
  }

  func gestureRecognizerShouldBegin(_ gestureRecognizer: UIGestureRecognizer) -> Bool {
    return true
  }
  
}
