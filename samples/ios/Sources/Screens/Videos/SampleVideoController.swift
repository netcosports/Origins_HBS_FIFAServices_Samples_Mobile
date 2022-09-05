//
//  SampleVideoController.swift
//  HBSSDK
//
//  Created by Denis Shikunets on 3/23/22.
//  Copyright © 2022 Netcosports. All rights reserved.
//

import UIKit
import HBSSDK

class SampleVideoController: SampleBaseController {

  private let videoWidget1 = HBSSDK.Videos.widget()
  private let videoWidget2 = HBSSDK.Videos.widget()
//  private let videoCategories = HBSSDK.Videos.categoriesWidget()
  private let logo = UIImageView()

  override func viewDidLoad() {
    super.viewDidLoad()
    logo.image = getLogoImage()
    self.view.addSubviews(logo, videoWidget1, videoWidget2)

    videoWidget1.setVideoCategory(category: .matchClips)
    videoWidget2.setVideoCategory(category: .matchFeeds)
  }

  override func viewDidLayoutSubviews() {
    super.viewDidLayoutSubviews()
    logo.pin.top(self.view.getStatusBarHeight()).size(78.ui).hCenter()
    videoWidget1.pin.below(of: logo).start().size(HBSSDK.Videos.videoWidgetSize(for: self.view.bounds.size))
    videoWidget2.pin.below(of: videoWidget1).start().size(HBSSDK.Videos.videoWidgetSize(for: self.view.bounds.size))
  }
}
