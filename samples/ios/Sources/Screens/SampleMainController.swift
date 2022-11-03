//
//  SampleMainController.swift
//  iosApp
//
//  Created by Denis Shikunets on 9/5/22.
//  Copyright © 2022 Netcosports. All rights reserved.
//

import UIKit
import Alidade
import RxCocoa
import RxSwift
import HBSSDK
import PinLayout

class SampleMainController: UIViewController {

  static let hbsDarkWidgetBackgroundEndColor: UIColor = #colorLiteral(red: 0.10980392, green: 0.003921569, blue: 0.06666667, alpha: 1.0)
  static let hbsDarkWidgetBackgroundStartColor: UIColor = #colorLiteral(red: 0.5411765, green: 0.08235294, blue: 0.21960784, alpha: 1.0)
  static let hbsDarkWidgetBackgroundStartColorLight: UIColor = #colorLiteral(red: 0.9568627451, green: 0.9568627451, blue: 0.9568627451, alpha: 1)
  static let horizontalMargin = 28.ui

  private let background = GradientView()
  private let logo = UIImageView()

  private let scrollView = UIScrollView {
    $0.showsVerticalScrollIndicator = false
    $0.contentInsetAdjustmentBehavior = .never
  }

  private let disposeBag = DisposeBag()

  private let demoAppButton = UIButton {
    $0.setupHbsButton("Demo app")
  }

  private let settingsButton = UIButton {
    $0.setupHbsButton("Settings")
  }

  override func viewDidLoad() {
    super.viewDidLoad()
    setupSdkParams()
    self.view.addSubviews(background, scrollView)
    scrollView.addSubviews(logo, demoAppButton, settingsButton)
    background.setupHbsSampleBackground()

    logo.image = getLogoImage()

    demoAppButton.rx.tap.observeOn(MainScheduler.asyncInstance)
      .subscribe(onNext: { [weak self] _ in
        guard let self = self,
          let navigationController = self.navigationController else { return }
        self.setupSdkParams()
        let controller = SampleHomeController()
        navigationController.pushViewController(controller, animated: true)
      })
      .disposed(by: disposeBag)

    settingsButton.rx.tap.observeOn(MainScheduler.asyncInstance)
      .subscribe(onNext: { [weak self] _ in
        guard let navigationController = self?.navigationController else { return }
        let controller = SampleSettingsController()
        navigationController.present(controller, animated: true)
      })
      .disposed(by: disposeBag)
  }

  private func setupSdkParams() {
    let appSettings = HbsSampleSettings()
    let hbsDirection = appSettings.getHbsLayoutDirection()
    switch hbsDirection {
    case .ltr:
      UIView.appearance().semanticContentAttribute = .forceLeftToRight
      Pin.layoutDirection = .ltr
    case .rtl:
      UIView.appearance().semanticContentAttribute = .forceRightToLeft
      Pin.layoutDirection = .rtl
    case .auto:
      UIView.appearance().semanticContentAttribute = .unspecified
      Pin.layoutDirection = .auto
      break
    @unknown default:
      break
    }
    HBSSDK.Integration.setupLayoutDirection(layoutDirection: hbsDirection)

    //HBSSDK.Integration.setDisplayActionsInMatchCenter(display: appSettings.isDisplayActions())

    let delegate = UIApplication.shared.delegate as! AppDelegate
    delegate.setupMatchClickListener(clickHandler: appSettings.getHbsMatchCenterClickHandler())
  }

  override func viewDidLayoutSubviews() {
    super.viewDidLayoutSubviews()
    background.pin.all()
    scrollView.pin.all()
    logo.pin.top(self.view.getStatusBarHeight()).size(78.ui).hCenter()

    demoAppButton.pin.below(of: logo).marginTop(48.ui).horizontally(28.ui).height(75.ui)
    settingsButton.pin.below(of: demoAppButton).marginTop(10.ui).horizontally(28.ui).height(75.ui)
  }

  public override func traitCollectionDidChange(_ previousTraitCollection: UITraitCollection?) {
    super.traitCollectionDidChange(previousTraitCollection)
    background.setupHbsSampleBackground()
  }
}
