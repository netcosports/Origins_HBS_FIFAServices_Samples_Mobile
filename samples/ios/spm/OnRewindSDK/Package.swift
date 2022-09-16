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
        url: "https://origins-mobile-products.s3.eu-west-1.amazonaws.com/onrewind_player/directv/1.0.7/OnRewindSDK.xcframework.zip",
        checksum: "fc492f85a38fd2c7bb5649e3c16f9a9111ea3792c7968ab0759a56e830eb935a"
    ),
    .binaryTarget(
        name: "onrewindshared",
        url: "https://origins-mobile-products.s3.eu-west-1.amazonaws.com/onrewind_player/directv/1.0.7/onrewindshared.xcframework.zip",
        checksum: "e2bb7fcac53b2b98a75e578819e01f2fcc097e3f39891da30ea5a7b777daad1b"
    )
  ],
  swiftLanguageVersions: [.v5]
)
