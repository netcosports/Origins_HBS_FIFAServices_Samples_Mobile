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

  private let groupMatches: UIButton = {
    let button = UIButton()
    button.setTitle("Group Matches", for: .normal)
    button.setTitleColor(.white, for: .normal)
    button.backgroundColor = .darkGray
    return button
  }()


  private let roundMatches: UIButton = {
    let button = UIButton()
    button.setTitle("Round Matches", for: .normal)
    button.setTitleColor(.white, for: .normal)
    button.backgroundColor = .darkGray
    return button
  }()


  private let teamMatches: UIButton = {
    let button = UIButton()
    button.setTitle("Team Matches", for: .normal)
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
    self.view.addSubviews(groupMatches, roundMatches, teamMatches)


    Observable.merge(
      groupMatches.rx.tap.map { _ in
        MatchWidgetDataSource.group(groupId: "202104")
      },
      roundMatches.rx.tap.map {_ in
        MatchWidgetDataSource.round(roundId: "275093")
      },
      teamMatches.rx.tap.map {_ in
        MatchWidgetDataSource.team(teamId: "43855")
      }
    )
      .observeOn(MainScheduler.asyncInstance)
      .subscribe(onNext: { [weak self] dataSource in
        guard let self = self else { return }
        let controller = GroupMatchesController()
        controller.setupParams(dataSource: dataSource)
        self.navigationController?.pushViewController(controller, animated: true)
      })
      .disposed(by: disposeBag)
  }

  override func viewDidLayoutSubviews() {
    super.viewDidLayoutSubviews()
    groupMatches.pin.top(self.view.safeAreaInsets.top).horizontally().height(100.ui)
      .margin(20.ui)

    roundMatches.pin.below(of: groupMatches).horizontally().height(100.ui)
      .margin(20.ui)


    teamMatches.pin.below(of: roundMatches).horizontally().height(100.ui)
      .margin(20.ui)

  }

}
