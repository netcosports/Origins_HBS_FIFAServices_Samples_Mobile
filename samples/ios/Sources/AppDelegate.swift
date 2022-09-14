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

    HBSSDK.Integration.initSdk()

    OnRewind.initialize()
    OnRewind.set(baseUrl: "https://api-gateway.onrewind.tv/main-api")

    Integration.presentPlayerBlock = { context in
      let params = OnRewind.EventParams.configurationURL(
        URL(string: "https://storage.googleapis.com/static-production.netcosports.com/onrewind/hbs_demo_player_config.json")!,
        accountKey: "B1oYoKWDK"
      )
      OnRewind.presentPlayer(
        with: params,
        from: context.presentationController
      )
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
