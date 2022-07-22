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
import OnRewindSDK

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
		//FirebaseApp.configure()

    HBSSDK.Integration.initSdk()

		let window = UIWindow(frame: UIScreen.main.bounds)
		window.backgroundColor = .white

		let controller = MyCustomNavigation(rootViewController: SampleHomeController())
		window.rootViewController = controller
		window.makeKeyAndVisible()
		self.window = window

    OnRewind.initialize()
    OnRewind.set(baseUrl: "https://api-gateway.onrewind.tv/main-api")

    HBSSDK.Integration.presentPlayerBlock = { context in
      let params = OnRewind.EventParams.eventId("78fbebc2-fc52-439e-81f4-8557bba62c1b", accountKey: "SkH0O4D5H")
      OnRewind.presentPlayer(
        with: params,
        from: context.presentationController
      )
    }

		return true
	}
}
