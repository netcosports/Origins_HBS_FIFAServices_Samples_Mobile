//
//  DetailsViewController.swift
//  Demo
//
//  Created by Sergei Mikhan on 11/10/20.
//

import UIKit
import OnRewindSDK
import PinLayout

import RxSwift

class DetailsViewController: UIViewController {

  var playerController: (UIViewController & OnRewindEmbeddedController)?
  let params: OnRewind.EventParams
  private let fullscreenButton = UIButton()
  private let disposeBag = DisposeBag()
  init(params: OnRewind.EventParams) {
    self.params = params
    super.init(nibName: nil, bundle: nil)
  }

  required init?(coder: NSCoder) {
    fatalError("init(coder:) has not been implemented")
  }

  override func viewDidLoad() {
    super.viewDidLoad()
    self.view.addSubview(fullscreenButton)
    fullscreenButton.setTitle("TO FULLSCREEN", for: .normal)
    fullscreenButton.setTitleColor(UIColor.black, for: .normal)
    fullscreenButton.layer.borderWidth = 1.0
    fullscreenButton.layer.borderColor = UIColor.black.cgColor
    fullscreenButton.rx.tap.subscribe(onNext: { [weak self] in
      self?.playerController?.toFullscreen(richPlayback: true)
    }).disposed(by: disposeBag)
    view.backgroundColor = UIColor.lightGray
    playerController = OnRewind.playerController(with: params)
    guard let player = playerController else {
      return
    }
    view.addSubview(player.view)
    self.addChild(player)
    player.didMove(toParent: self)
  }

  override func viewDidLayoutSubviews() {
    super.viewDidLayoutSubviews()
    guard let player = playerController else {
      return
    }

    player.view.pin.top(view.pin.safeArea.top + 44)
      .width(isPad ? 780.0 : 360.0).hCenter().aspectRatio(16.0 / 9.0)

    fullscreenButton.pin.below(of: player.view).horizontally(24).height(52.0).marginTop(44)
  }

  override var supportedInterfaceOrientations: UIInterfaceOrientationMask {
    return isPad ? .landscape : .portrait
  }
}
