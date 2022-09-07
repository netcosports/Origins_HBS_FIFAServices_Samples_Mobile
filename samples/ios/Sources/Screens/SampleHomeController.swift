//
//  SampleHomeController.swift
//  HBSSDK
//
//  Created by Denis Shikunets on 3/22/22.
//  Copyright © 2022 Netcosports. All rights reserved.
//

import UIKit
import HBSSDK
import Alidade
import RxSwift
import RxCocoa

class SampleHomeController: SampleBaseController {

  static let hbsDarkWidgetBackgroundEndColor: UIColor = #colorLiteral(red: 0.10980392, green: 0.003921569, blue: 0.06666667, alpha: 1.0)
  static let hbsDarkWidgetBackgroundStartColor: UIColor = #colorLiteral(red: 0.5411765, green: 0.08235294, blue: 0.21960784, alpha: 1.0)
  static let hbsDarkWidgetBackgroundStartColorLight: UIColor = #colorLiteral(red: 0.9568627451, green: 0.9568627451, blue: 0.9568627451, alpha: 1)
  static let horizontalMargin = 28.ui

  private let logo = UIImageView()


  private let scrollView = UIScrollView {
    $0.showsVerticalScrollIndicator = false
    $0.contentInsetAdjustmentBehavior = .never
  }

  private let standingsButton = UIButton {
    $0.setupHbsButton("Group Standings")
  }

  private let matchboxButton = UIButton {
    $0.setupHbsButton("Matchbox")
  }

  private let statsButton = UIButton {
    $0.setupHbsButton("Stats")
  }

  private let championshipButton = UIButton {
    $0.setupHbsButton("Championship")
  }

  private let videosButton = UIButton {
    $0.setupHbsButton("Videos")
  }

  private let favoritesButton = UIButton {
    $0.setupHbsButton("Favorites")
  }

  private let teamsButton = UIButton {
    $0.setupHbsButton("Teams")
  }

  private let teamBoardButton = UIButton {
    $0.setupHbsButton("Team board")
  }

  private let headToHeadButton = UIButton {
    $0.setupHbsButton("Head To Head")
  }

  private let actionsButton = UIButton {
    $0.setupHbsButton("Actions")
  }

  private let venuesButton = UIButton {
    $0.setupHbsButton("Venues")
  }

  private let watchButton = UIButton {
    $0.setupHbsButton("Watch")
  }


  public override func viewDidLoad() {
    super.viewDidLoad()
    logo.image = getLogoImage()
    self.view.addSubviews(scrollView)
    self.scrollView.addSubviews(logo, standingsButton, matchboxButton, statsButton, championshipButton, videosButton, favoritesButton, teamsButton, teamBoardButton, headToHeadButton, actionsButton, venuesButton, watchButton)

    standingsButton.rx.tap.observeOn(MainScheduler.asyncInstance)
      .subscribe(onNext: { [weak self] _ in
        guard let self = self else { return }
        self.openController(SampleStandingsController())
      })
      .disposed(by: disposeBag)

    matchboxButton.rx.tap.observeOn(MainScheduler.asyncInstance)
      .subscribe(onNext: { [weak self] _ in
        guard let self = self else { return }
        self.openController(SampleMatchboxTypeController())
      })
      .disposed(by: disposeBag)

    statsButton.rx.tap.observeOn(MainScheduler.asyncInstance)
      .subscribe(onNext: { [weak self] _ in
        guard let self = self else { return }
        self.openController(SampleStatsController())
      })
      .disposed(by: disposeBag)

    championshipButton.rx.tap//.observe(on: MainScheduler.asyncInstance)
      .subscribe(onNext: { [weak self] _ in
        guard let self = self else { return }
        self.openController(SampleChampionshipController())
      })
      .disposed(by: disposeBag)

    videosButton.rx.tap.observeOn(MainScheduler.asyncInstance)
      .subscribe(onNext: { [weak self] _ in
        guard let self = self else { return }
        let controller = SampleVideoController()
        self.openController(controller)
      })
      .disposed(by: disposeBag)

    favoritesButton.rx.tap.observeOn( MainScheduler.asyncInstance)
      .subscribe(onNext: { [weak self] _ in
        guard let self = self else { return }
        let controller = SampleFavoriteController()
        self.openController(controller)
      })
      .disposed(by: disposeBag)

    teamsButton.rx.tap.observeOn(MainScheduler.asyncInstance)
      .subscribe(onNext: { [weak self] _ in
        guard let self = self else { return }
        let controller = SampleTeamsController()
        self.openController(controller)
      })
      .disposed(by: disposeBag)

    teamBoardButton.rx.tap.observeOn(MainScheduler.asyncInstance)
      .subscribe(onNext: { [weak self] _ in
        guard let self = self else { return }
        let controller = SampleTeamBoardController()
        self.openController(controller)
      })
      .disposed(by: disposeBag)

    headToHeadButton.rx.tap.observeOn(MainScheduler.asyncInstance)
      .subscribe(onNext: { [weak self] _ in
        guard let self = self else { return }
        let controller = SampleHeadToHeadController()
        self.openController(controller)
      })
      .disposed(by: disposeBag)


    actionsButton.rx.tap.observeOn(MainScheduler.asyncInstance)
      .subscribe(onNext: { [weak self] _ in
        guard let self = self else { return }
        let controller = SampleActionController()
        self.openController(controller)
      })
      .disposed(by: disposeBag)

    venuesButton.rx.tap.observeOn(MainScheduler.asyncInstance)
      .subscribe(onNext: { [weak self] _ in
        guard let self = self else { return }
        let controller = SampleVenuesController()
        self.openController(controller)
      })
      .disposed(by: disposeBag)

    watchButton.rx.tap.observeOn(MainScheduler.asyncInstance)
      .subscribe(onNext: { [weak self] _ in
        guard let self = self else { return }
        let controller = SampleWatchController()
        self.openController(controller)
      })
      .disposed(by: disposeBag)

  }

  private func openController(_ controller: UIViewController) {
    self.navigationController?.pushViewController(controller, animated: true)
  }

  public override func viewDidLayoutSubviews() {
    super.viewDidLayoutSubviews()
    scrollView.pin.all()
    logo.pin.top(self.view.getStatusBarHeight()).size(78.ui).hCenter()
    standingsButton.pin.below(of: logo).marginTop(48.ui).horizontally(28.ui).height(75.ui)
    matchboxButton.pin.below(of: standingsButton).marginTop(10.ui).horizontally(28.ui).height(75.ui)
    statsButton.pin.below(of: matchboxButton).marginTop(10.ui).horizontally(28.ui).height(75.ui)
    championshipButton.pin.below(of: statsButton).marginTop(10.ui).horizontally(28.ui).height(75.ui)
    videosButton.pin.below(of: championshipButton).marginTop(10.ui).horizontally(28.ui).height(75.ui)
    favoritesButton.pin.below(of: videosButton).marginTop(10.ui).horizontally(28.ui).height(75.ui)
    teamsButton.pin.below(of: favoritesButton).marginTop(10.ui).horizontally(28.ui).height(75.ui)
    teamBoardButton.pin.below(of: teamsButton).marginTop(10.ui).horizontally(28.ui).height(75.ui)
    headToHeadButton.pin.below(of: teamBoardButton).marginTop(10.ui).horizontally(28.ui).height(75.ui)
    actionsButton.pin.below(of: headToHeadButton).marginTop(10.ui).horizontally(28.ui).height(75.ui)
    venuesButton.pin.below(of: actionsButton).marginTop(10.ui).horizontally(28.ui).height(75.ui)
    watchButton.pin.below(of: venuesButton).marginTop(10.ui).horizontally(28.ui).height(75.ui)
    scrollView.contentSize = .init(width: self.view.width, height: watchButton.frame.maxY + 24.ui)
  }

  static func getColor(lightMode: UIColor, darkMode: UIColor) -> UIColor {
      guard #available(iOS 13.0, *) else { return lightMode }

      return UIColor { (traitCollection) -> UIColor in
          return traitCollection.userInterfaceStyle == .light ? lightMode : darkMode
      }
  }

}

public extension UIButton {
  func setupHbsButton(_ title: String) {
    self.setTitle(title, for: .normal)
    self.setTitleColor(
      SampleHomeController.getColor(
        lightMode: .init(red: 0.52, green: 0.1, blue: 0.3, alpha: 1),
        darkMode: .white
      ),
      for: .normal)
//    self.setAttributedTitle(title.styled(as: TextStylesExportedPublic.provider.sampleButtonTitle), for: .normal)
    self.backgroundColor = SampleHomeController.getColor(lightMode: .white, darkMode: .white.withAlphaComponent(0.1))
    self.setupHbsSampleCorners()
  }

}


extension GradientView {
  func setupHbsSampleBackground() {
    //    setupHbsBackground(isTransparent: false)
    self.direction = .init(start: .init(x: 1.0, y: 0.0), end: .init(x: 0.0, y: 1.0))
    let startColor = SampleHomeController.getColor(lightMode: SampleHomeController.hbsDarkWidgetBackgroundStartColorLight, darkMode: SampleHomeController.hbsDarkWidgetBackgroundStartColor)
    let endColor = SampleHomeController.getColor(lightMode: SampleHomeController.hbsDarkWidgetBackgroundStartColorLight, darkMode: SampleHomeController.hbsDarkWidgetBackgroundEndColor)
    self.colors = [ startColor , endColor ]
  }
}


extension UIViewController {
  func getLogoImage() -> UIImage? {
    return UIImage(named: "logoQatar")
  }
}


extension UIView {
  func setupHbsSampleCorners() {
    self.layer.cornerRadius = 5.ui
  }
}
