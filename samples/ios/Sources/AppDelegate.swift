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
    OnRewind.set(baseUrl: "")

    HBSSDK.Integration.presentPlayerBlock = { context in
      OnRewind.presentPlayer(
        with: OnRewind.EventParams.videoStream(context.videoURL, isLive: false),
        from: context.presentationController
      )
    }

		return true
	}
}
