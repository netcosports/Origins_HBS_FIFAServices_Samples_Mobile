//
//  SampleWatchSelectTypeController.swift
//  iosApp
//
//  Created by Denis Shikunets on 9/8/22.
//  Copyright © 2022 Netcosports. All rights reserved.
//

import UIKit

class SampleWatchSelectTypeController: SampleBaseController {
  private let logo = UIImageView()

  private let groupMatches = UIButton {
    $0.setupHbsButton("Group watches")
  }

  private let roundMatches = UIButton {
    $0.setupHbsButton("Round watches")
  }
  private let teamMatches = UIButton {
    $0.setupHbsButton("Team watches")
  }
  private let singleMatch = UIButton {
    $0.setupHbsButton("Single watch")
  }


  override func viewDidLoad() {
    super.viewDidLoad()
    logo.image = getLogoImage()
    UIButton.appearance().tintColor = .white
    self.view.addSubviews(logo, groupMatches, roundMatches, teamMatches, singleMatch)


    groupMatches.rx.tap.subscribe(
      onNext: {[weak self] _ in
        let controller = SampleWatchController()
        controller.setupParams(watchType: .group)
        self?.openController(controller)
      }
    )
      .disposed(by: disposeBag)



    teamMatches.rx.tap.subscribe(
      onNext: {[weak self] _ in
        let controller = SampleWatchController()
        controller.setupParams(watchType: .team)
        self?.openController(controller)
      }
    )
      .disposed(by: disposeBag)


    roundMatches.rx.tap.subscribe(
      onNext: {[weak self] _ in
        let controller = SampleWatchController()
        controller.setupParams(watchType: .round)
        self?.openController(controller)
      }
    )
      .disposed(by: disposeBag)

    singleMatch.rx.tap.subscribe(
      onNext: {[weak self] _ in
        let controller = SampleWatchController()
        controller.setupParams(watchType: .match)
        self?.openController(controller)
      }
    )
      .disposed(by: disposeBag)


  }

  private func openController(_ controller: UIViewController) {
    self.navigationController?.pushViewController(controller, animated: true)
  }

  override func viewDidLayoutSubviews() {
    super.viewDidLayoutSubviews()
    logo.pin.top(self.view.safeAreaInsets.top).size(78.ui).hCenter()
    groupMatches.pin.below(of: logo).marginTop(10.ui).height(75.ui).horizontally(28.ui)
    roundMatches.pin.below(of: groupMatches).marginTop(10.ui).height(75.ui).horizontally(28.ui)
    teamMatches.pin.below(of: roundMatches).marginTop(10.ui).height(75.ui).horizontally(28.ui)
    singleMatch.pin.below(of: teamMatches).marginTop(10.ui).height(75.ui).horizontally(28.ui)

  }
}
