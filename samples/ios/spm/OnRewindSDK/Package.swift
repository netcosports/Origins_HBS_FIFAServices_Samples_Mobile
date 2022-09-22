// swift-tools-version:5.3
import PackageDescription


let package = Package(
  name: "OnRewindSDK",
  platforms: [
    .iOS(.v11)
  ],
  products: [
    .library(name: "OnRewindSDK", targets: ["OnRewindSDK", "onrewindshared"])
  ],
  dependencies: [

  ],
  targets: [
    .binaryTarget(
        name: "OnRewindSDK",
        url: "https://origins-mobile-products.s3.eu-west-1.amazonaws.com/onrewind_player/directv/1.0.12/OnRewindSDK.xcframework.zip",
        checksum: "2c543a3d9d435ab50c9ff0c1ae145b290ab3b9557e8db484e85a25f9745b0516"
    ),
    .binaryTarget(
        name: "onrewindshared",
        url: "https://origins-mobile-products.s3.eu-west-1.amazonaws.com/onrewind_player/directv/1.0.12/onrewindshared.xcframework.zip",
        checksum: "80806be336f03944ad01323cb2155b4bd745134ae63e375a960d733d5f64ced7"
    )
  ],
  swiftLanguageVersions: [.v5]
)
