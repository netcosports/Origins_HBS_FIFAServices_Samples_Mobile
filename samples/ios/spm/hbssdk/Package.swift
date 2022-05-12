// swift-tools-version:5.3
import PackageDescription


let package = Package(
  name: "HBSSDK",
  platforms: [
    .iOS(.v13)
  ],
  products: [
    .library(name: "HBSSDK", targets: ["HBSSDK", "OnRewindSDK", "SportBuff", "hbsshared"])
  ],
  dependencies: [

  ],
  targets: [
    .binaryTarget(
      name: "HBSSDK",
      path: "xcframewords/HBSSDK.xcframework"
    ),
    .binaryTarget(
      name: "OnRewindSDK",
      path: "xcframewords/OnRewindSDK.xcframework"
    ),
    .binaryTarget(
      name: "SportBuff",
      path: "xcframewords/SportBuff.xcframework"
    ),
    .binaryTarget(
      name: "hbsshared",
      path: "xcframewords/hbsshared.xcframework"
    )
  ],
  swiftLanguageVersions: [.v5]
)
