//
//  SampleTeamsController.swift
//  HBSSDK
//
//  Created by Denis Shikunets on 4/1/22.
//  Copyright © 2022 Netcosports. All rights reserved.
//

import UIKit
import HBSSDK

class SampleTeamsController: SampleBaseController {
  private let teamsWidgets = HBSSDK.Teams.widget()

  private var isCarouselMode = true
  private let root = UIView()

  override func viewDidLoad() {
    super.viewDidLoad()
    self.view.addSubviews(root)
    root.addSubviews(teamsWidgets)
  }

  func setupDisplayMode(isCarouselModel: Bool) {
    self.isCarouselMode = isCarouselModel
  }

  override func viewDidLayoutSubviews() {
    super.viewDidLayoutSubviews()
    root.pin.all().marginTop(self.view.safeAreaInsets.top)
    teamsWidgets.pin.top().start().size(HBSSDK.Teams.size(for: root.frame.size))
  }
}
