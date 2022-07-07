//
//  GroupMatchesTypeController.swift
//  iosApp
//
//  Created by Denis Shikunets on 2/9/22.
//  Copyright © 2022 Netcosports. All rights reserved.
//

import UIKit
import RxSwift
import RxCocoa
import HBSSDK


final class GroupMatchesTypeController: UIViewController {

  private let disposeBag = DisposeBag()

  private let solidGroupMatches: UIButton = {
    let button = UIButton()
    button.setTitle("Solid Group Matches", for: .normal)
    button.setTitleColor(.white, for: .normal)
    button.backgroundColor = .darkGray
    return button
  }()

  private let transparentGroupMatches: UIButton = {
    let button = UIButton()
    button.setTitle("Transparent Group Matches", for: .normal)
    button.setTitleColor(.white, for: .normal)
    button.backgroundColor = .darkGray
    return button
  }()

  private let solidRoundMatches: UIButton = {
    let button = UIButton()
    button.setTitle("Solid Round Matches", for: .normal)
    button.setTitleColor(.white, for: .normal)
    button.backgroundColor = .darkGray
    return button
  }()

  private let transparentRoundMatches: UIButton = {
    let button = UIButton()
    button.setTitle("Transparent Round Matches", for: .normal)
    button.setTitleColor(.white, for: .normal)
    button.backgroundColor = .darkGray
    return button
  }()

  private let solidTeamMatches: UIButton = {
    let button = UIButton()
    button.setTitle("Solid Team Matches", for: .normal)
    button.setTitleColor(.white, for: .normal)
    button.backgroundColor = .darkGray
    return button
  }()

  private let transparentTeamMatches: UIButton = {
    let button = UIButton()
    button.setTitle("Transparent Team Matches", for: .normal)
    button.setTitleColor(.white, for: .normal)
    button.backgroundColor = .darkGray
    return button
  }()

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

  override func viewDidLoad() {
    super.viewDidLoad()
    changeBackground()
    self.view.addSubviews(solidGroupMatches, transparentGroupMatches, solidRoundMatches, transparentRoundMatches, solidTeamMatches, transparentTeamMatches)


    Observable.merge(
      solidGroupMatches.rx.tap.map {_ in (MatchWidgetDataSource.group(groupId: "202104"), false)
      },
      transparentGroupMatches.rx.tap.map {_ in
        (MatchWidgetDataSource.group(groupId: "202104"), true)
      },
      solidRoundMatches.rx.tap.map {_ in
        (MatchWidgetDataSource.round(roundId: "275093"), false)
      },
      transparentRoundMatches.rx.tap.map {_ in
        (MatchWidgetDataSource.round(roundId: "275093"), true)
      },
      solidTeamMatches.rx.tap.map {_ in
        (MatchWidgetDataSource.team(teamId: "43855"), false)
      },
      transparentRoundMatches.rx.tap.map {_ in
        (MatchWidgetDataSource.team(teamId: "43855"), true)
      }
    )
      .observeOn(MainScheduler.asyncInstance)
      .subscribe(onNext: { [weak self] params in
        guard let self = self else { return }
        let dataSource = params.0
        let isTransparent = params.1
        let controller = GroupMatchesController()
        controller.setupParams(dataSource: dataSource, isTransparent: isTransparent)
        self.navigationController?.pushViewController(controller, animated: true)
      })
      .disposed(by: disposeBag)
  }

  override func viewDidLayoutSubviews() {
    super.viewDidLayoutSubviews()
    solidGroupMatches.pin.top(self.view.safeAreaInsets.top).horizontally().height(100.ui)
      .margin(20.ui)

    transparentGroupMatches.pin.below(of: solidGroupMatches).horizontally().height(100.ui)
      .margin(20.ui)

    solidRoundMatches.pin.below(of: transparentGroupMatches).horizontally().height(100.ui)
      .margin(20.ui)

    transparentRoundMatches.pin.below(of: solidRoundMatches).horizontally().height(100.ui)
      .margin(20.ui)

    solidTeamMatches.pin.below(of: transparentRoundMatches).horizontally().height(100.ui)
      .margin(20.ui)

    transparentTeamMatches.pin.below(of: solidTeamMatches).horizontally().height(100.ui)
      .margin(20.ui)

  }

}
