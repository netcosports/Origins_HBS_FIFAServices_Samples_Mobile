//
//  AppDelegate.swift
//  iosApp
//
//  Created by Denis Shikunets on 10.03.21.
//  Copyright © 2021 Origins-Digital. All rights reserved.
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
    OnRewind.set(baseUrl: "")

    Integration.presentPlayerBlock = { context in
      guard let videoURL = context.videoURL else {
        return
      }
      OnRewind.presentPlayer(
        with: OnRewind.EventParams.videoStream(videoURL, isLive: false),
        from: context.presentationController,
        richPlayback: false
      )
    }

//    Integration.presentMatchBlock = { context in
//      print("tttt presentMatchBlock: \(context.matchId)")
//    }

    let controller = MyCustomNavigation(rootViewController: SampleMainController())
    window.rootViewController = controller
    window.makeKeyAndVisible()
    self.window = window

    return true
  }
}
