//
//  SampleVenueController.swift
//  HBSSDK
//
//  Created by Denis Shikunets on 4/27/22.
//  Copyright © 2022 Netcosports. All rights reserved.
//

import UIKit
import HBSSDK

class SampleVenuesController: SampleBaseController {

  private let root = UIView()
  private let venueWidget = HBSSDK.Venue.widget()

  override func viewDidLoad() {
    super.viewDidLoad()
    self.view.addSubviews(root)
    root.addSubviews(venueWidget)

    venueWidget.openMatchDetailsBlock = { (controller, matchId) in
      controller.testOpenMatch(matchId: matchId)
    }
  }

  override func viewDidLayoutSubviews() {
    super.viewDidLayoutSubviews()
    root.pin.all().marginTop(self.view.safeAreaInsets.top)
    venueWidget.pin.top().start().size(HBSSDK.Venue.widgetSize(containerSize: root.bounds.size))
  }

}
