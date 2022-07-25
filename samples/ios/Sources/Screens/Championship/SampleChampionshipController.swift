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
    widget.openMatchDetailsBlock = { [weak self] matchId in
      self?.testOpenMatch(matchId: matchId)
    }
  }

  override func viewDidLayoutSubviews() {
    super.viewDidLayoutSubviews()
    widget.pin.marginTop(self.view.safeAreaInsets.top)
      .all()
  }
}

extension UIViewController {
  func testOpenMatch(matchId: String) {
    let controller = UIAlertController(
      title: "Custom click handle",
      message: "Open match: \(matchId)",
      preferredStyle: .alert
    )
    controller.addAction(.init(title: "Ok", style: .default))
    self.present(controller, animated: false)
  }
}
