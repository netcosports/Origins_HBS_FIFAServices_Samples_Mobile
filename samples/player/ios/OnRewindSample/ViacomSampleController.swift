//
//  ViacomSampleController.swift
//  OnRewindSample
//
//  Created by Sergei Mikhan on 9.11.22.
//  Copyright © 2022 Sergei Mikhan. All rights reserved.
//

import UIKit
import OnRewindSDK
import PinLayout

import RxSwift

class ViacomSampleController: UIViewController {

  var playerController: (UIViewController & OnRewindEmbeddedController)?
  let params: OnRewind.EventParams

  private let hypeButton = UIButton()
  private let fullscreeButton = UIButton()
  private let enLangButton = UIButton()
  private let hiLangButton = UIButton()
  private let disposeBag = DisposeBag()
  private var isHypeVisible = true
  private var hasModallyPresented = false
  init(params: OnRewind.EventParams) {
    self.params = params
    super.init(nibName: nil, bundle: nil)
  }

  required init?(coder: NSCoder) {
    fatalError("init(coder:) has not been implemented")
  }

  override func viewDidLoad() {
    super.viewDidLoad()

    view.backgroundColor = UIColor.lightGray
    playerController = OnRewind.playerController(
      with: params,
      presentedModallyScreenClosure: { [weak self] event in
        switch event {
          case .presented:
            self?.hasModallyPresented = true
          case .dismissed:
            self?.hasModallyPresented = false
        }
      },
      playerWrapperClosure: {
        return AVPlayerDemo()
      },
      playerUIEventsClosure: { [weak self] event in
        switch event {
          case .close:
            self?.navigationController?.popViewController(animated: true)
        }
      }
    )
    playerController?.setPlayerControls(visibility: isHypeVisible)

    guard let player = playerController else {
      return
    }
    view.addSubview(player.view)
    self.addChild(player)
    player.didMove(toParent: self)

    self.view.addSubview(fullscreeButton)
    fullscreeButton.setTitle("Fullscreen", for: .normal)
    fullscreeButton.setTitleColor(UIColor.black, for: .normal)
    fullscreeButton.backgroundColor = .orange

    fullscreeButton.rx.tap.subscribe(onNext: { [weak self] in
      if #available(iOS 16.0, *) {
        if let windowScene = UIApplication.shared.connectedScenes.first as? UIWindowScene {
          windowScene.requestGeometryUpdate(.iOS(interfaceOrientations: .landscape)) { _ in }
        }
      } else {
        let value = UIInterfaceOrientation.landscapeLeft.rawValue
        UIDevice.current.setValue(value, forKey: "orientation")
      }
    }).disposed(by: disposeBag)
  }

  override func viewDidLayoutSubviews() {
    super.viewDidLayoutSubviews()
    guard let player = playerController else {
      return
    }

    if view.frame.width > view.frame.height {
      player.view.pin.all()
    } else {
      player.view.pin.top(120.0).horizontally().aspectRatio(16.0/9.0)
    }

    fullscreeButton.pin.top(to: player.view.edge.top).hCenter().marginTop(20.0).height(90).width(140)
  }

  override func viewWillAppear(_ animated: Bool) {
    super.viewWillAppear(animated)
  }

  override func viewDidDisappear(_ animated: Bool) {
    super.viewDidDisappear(animated)
  }

  override var supportedInterfaceOrientations: UIInterfaceOrientationMask {
    // NOTE: please comment this to reproduce same problem your app has
    if hasModallyPresented {
      return .landscape
    }
    return .allButUpsideDown
  }

  override var shouldAutorotate: Bool {
    return true
  }
}
