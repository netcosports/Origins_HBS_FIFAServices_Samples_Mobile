// swift-tools-version:5.3
import PackageDescription


let package = Package(
  name: "OnRewindSDK",
  platforms: [
    .iOS(.v13)
  ],
  products: [
    .library(name: "OnRewindSDK", targets: ["OnRewindSDK", "SportBuff"])
  ],
  dependencies: [

  ],
  targets: [
    .binaryTarget(
      name: "OnRewindSDK",
      url: "https://origins-mobile-products.s3.eu-west-1.amazonaws.com/onrewind/whitelabel/0.0.1/OnRewindSDK.xcframework.zip",
      checksum: "261bb2983ea0f6dcd94e0880c49f86579023441c7c3a327cafbf7d6779323d2d"
    ),
    .binaryTarget(
      name: "SportBuff",
      url: "https://buffup-public.s3.eu-west-2.amazonaws.com/ios-sdk/sportbuff-ios-sdk-0.2.0.0.zip",
      checksum: "eba418bfb5cf938203be14ee5070baa0cdd15df95de70030e6196357e8ff7a54"
    )
  ],
  swiftLanguageVersions: [.v5]
)
