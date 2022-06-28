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
      path: "xcframeworks/HBSSDK.xcframework"
    ),
    .binaryTarget(
      name: "OnRewindSDK",
      path: "xcframeworks/OnRewindSDK.xcframework"
    ),
    .binaryTarget(
      name: "SportBuff",
      path: "xcframeworks/SportBuff.xcframework"
    ),
    .binaryTarget(
      name: "hbsshared",
      path: "xcframeworks/hbsshared.xcframework"
    )
  ],
  swiftLanguageVersions: [.v5]
)
