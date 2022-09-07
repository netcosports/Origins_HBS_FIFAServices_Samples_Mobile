//
//  SampleChampionshipController.swift
//  HBSSDK
//
//  Created by Denis Shikunets on 3/22/22.
//  Copyright © 2022 Netcosports. All rights reserved.
//

import UIKit
import HBSSDK

class SampleChampionshipController: SampleBaseController {

  private let widget = HBSSDK.Championship.widget()

  override func viewDidLoad() {
    super.viewDidLoad()
    self.view.addSubviews(widget)
    print("tttt use local : \(useLocalMatchClickListener)")
    if useLocalMatchClickListener {
      widget.openMatchDetailsBlock = { [weak self] matchId in
        print("tttt open local \(matchId)")
        self?.openLocalMatchCenter(matchId: matchId)
      }
    }

  }

  override func viewDidLayoutSubviews() {
    super.viewDidLayoutSubviews()
    widget.pin.marginTop(self.view.safeAreaInsets.top)
      .all()
  }
}

