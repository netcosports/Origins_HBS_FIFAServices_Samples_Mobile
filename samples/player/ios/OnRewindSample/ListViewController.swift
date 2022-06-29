//
//  ListViewController.swift
//  Demo
//
//  Created by Sergei Mikhan on 29.01.21.
//

import UIKit
import OnRewindSDK
import PinLayout

import RxSwift

import Astrolabe

class PlaceholderCollectionCell: CollectionViewCell, Reusable {

  let label: UILabel = {
    let label = UILabel()
    label.textColor = .black
    label.font = .systemFont(ofSize: 14.0)
    label.numberOfLines = 2
    label.textAlignment = .center
    label.text = "PLACEHOLDER"
    return label
  }()

  override func setup() {
    super.setup()
    contentView.backgroundColor = .lightGray
    contentView.addSubview(label)
  }

  override func layoutSubviews() {
    super.layoutSubviews()
    label.pin.all()
  }

  typealias Data = Void

  class func size(for data: Data, containerSize: CGSize) -> CGSize {
    return CGSize(width: containerSize.width - 16.0, height: 120.0)
  }
}


class PlayerCollectionCell: CollectionViewCell, Reusable {

  private weak var playerController: (UIViewController & OnRewindEmbeddedController)?
  override func setup() {
    super.setup()
    contentView.backgroundColor = .darkGray
  }

  override func layoutSubviews() {
    super.layoutSubviews()
    playerController?.view.pin.all()
  }

  struct ViewModel {
    weak var player: (UIViewController & OnRewindEmbeddedController)?
  }

  typealias Data = ViewModel

  func setup(with data: Data) {
    guard playerController !== data.player else { return }
    playerController = data.player
    guard let player = playerController, let parent = hostViewController else { return }
    contentView.addSubview(player.view)
    parent.addChild(player)
    player.didMove(toParent: parent)
    setNeedsLayout()
  }

  override func willDisplay() {
    super.willDisplay()
    playerController?.play()
  }

  override func endDisplay() {
    super.endDisplay()
    playerController?.pause()
  }

  class func size(for data: Data, containerSize: CGSize) -> CGSize {
    let width = containerSize.width - 16.0
    let height = width * 9.0 / 16.0
    return CGSize(width: width, height: height)
  }
}

class ListViewController: UIViewController {

  var playerController: (UIViewController & OnRewindEmbeddedController)?
  let params: OnRewind.EventParams
  let containerView = CollectionView<LoaderDecoratorSource<CollectionViewSource>>()

  init(params: OnRewind.EventParams) {
    self.params = params
    super.init(nibName: nil, bundle: nil)
  }

  required init?(coder: NSCoder) {
    fatalError("init(coder:) has not been implemented")
  }

  override func viewDidLoad() {
    super.viewDidLoad()

    let layout = UICollectionViewFlowLayout()
    layout.minimumLineSpacing = 10.0
    containerView.collectionViewLayout = layout
    view.addSubview(containerView)
    containerView.source.hostViewController = self
    view.backgroundColor = UIColor.white
    playerController = OnRewind.playerController(with: params)
    guard let player = playerController else {
      return
    }
    typealias Placeholder = CollectionCell<PlaceholderCollectionCell>
    typealias Player = CollectionCell<PlayerCollectionCell>
    let cells: [Cellable] = [
      Placeholder(data: ()),
      Player(data: .init(player: player)),
      Placeholder(data: ()),
      Placeholder(data: ()),
      Placeholder(data: ()),
      Placeholder(data: ()),
      Placeholder(data: ()),
      Placeholder(data: ()),
      Placeholder(data: ()),
      Placeholder(data: ()),
      Placeholder(data: ()),
      Placeholder(data: ()),
      Placeholder(data: ())
    ]

    containerView.source.sections = [
      Section(cells: cells)
    ]
    containerView.reloadData()
  }

  override func viewDidLayoutSubviews() {
    super.viewDidLayoutSubviews()
    containerView.pin.all()
  }

  override var supportedInterfaceOrientations: UIInterfaceOrientationMask {
    return isPad ? .landscape : .portrait
  }
}

