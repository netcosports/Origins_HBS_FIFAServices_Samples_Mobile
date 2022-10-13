//
//  AppDelegate.swift
//  iosApp
//
//  Created by Eugene Filipkov on 10.03.21.
//  Copyright ¬© 2021 Origins-Digital. All rights reserved.
//

import UIKit
import HBSSDK
import hbsshared
import Alidade

import OnRewindSDK
import PinLayout

class MyCustomNavigation: UINavigationController {

  override var shouldAutorotate: Bool {
    return false
  }

  override var supportedInterfaceOrientations: UIInterfaceOrientationMask {
    return .portrait
  }
}

@UIApplicationMain
public class AppDelegate: UIResponder, UIApplicationDelegate {
	public var window: UIWindow?

	public func application(_ application: UIApplication,
													didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {

    UI.setBaseWidths([.pad: 768, .phone: 414])


		let window = UIWindow(frame: UIScreen.main.bounds)
		window.backgroundColor = .white

    HBSSDK.Integration.initSdk(
      baseUrl: "https://dev-hbs-stats-provider.origins-digital.com/",
      accountKey: "uZknQc_1h",
      competitionId: "fu17wwc",
      season: "2022"
    )
    OnRewind.set(
      baseUrl: "https://dev-hbs-stats-provider.origins-digital.com/",
      akamaiPrivateKey: "0df73252ceaf17d78589371d5b8d1bbb",
      accountKey: "uZknQc_1h",
      competitionId: "fu17wwc",
      seasonId: "2022"
    )

    Integration.presentPlayerBlock = { context in
      // TEST value
      //let params: OnRewind.EventParams = .matchId("134080")
      if let matchId = context.eventId {
        OnRewind.presentPlayer(
          withMatchId: matchId,
          fromPresentingViewController: context.presentationController
        )
      } else if let url = context.videoURL {
        OnRewind.presentPlayer(
          withVideoURL: url, isLive: false,
          fromPresentingViewController: context.presentationController
        )
      }
    }



		let controller = MyCustomNavigation(rootViewController: SampleMainController())
		window.rootViewController = controller
		window.makeKeyAndVisible()
		self.window = window

		return true
	}

  func setupMatchClickListener(clickHandler: HbsMatchCenterClickHandler) {
    if clickHandler == .global {
      Integration.presentMatchBlock = { context in
        let matchController = SampleMatchCenterController()
        matchController.setupMatchId(matchId: context.matchId, isLocal: false)
        context.presentationController.present(matchController, animated: true)
      }
    } else {
      Integration.presentMatchBlock = nil
    }
  }
}
