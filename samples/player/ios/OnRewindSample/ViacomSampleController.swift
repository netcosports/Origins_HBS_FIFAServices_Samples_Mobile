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
  private let enLangButton = UIButton()
  private let hiLangButton = UIButton()
  private let disposeBag = DisposeBag()
  private var isHypeVisible = false
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

    self.view.addSubview(hypeButton)
    hypeButton.setTitle("HYPE", for: .normal)
    hypeButton.setTitleColor(UIColor.black, for: .normal)
    hypeButton.backgroundColor = .orange

    hypeButton.rx.tap.subscribe(onNext: { [weak self] in
      guard let self = self else { return }
      self.isHypeVisible = !self.isHypeVisible
      self.playerController?.setPlayerControls(visibility: self.isHypeVisible)
    }).disposed(by: disposeBag)
  }

  override func viewDidLayoutSubviews() {
    super.viewDidLayoutSubviews()
    guard let player = playerController else {
      return
    }

    if UIDevice.current.orientation.isLandscape {
      player.view.pin.all()
    } else {
      player.view.pin.top(120.0).horizontally().aspectRatio(16.0/9.0)
    }

    hypeButton.pin.top(to: player.view.edge.top).hCenter().marginTop(20.0).width(80.0).height(30.0)
  }

  override var supportedInterfaceOrientations: UIInterfaceOrientationMask {

    return .allButUpsideDown
  }

  override var shouldAutorotate: Bool {
    return true
  }
}
