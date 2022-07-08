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
      url: "https://origins-mobile-products.s3.eu-west-1.amazonaws.com/OnRewindSDK.xcframework.zip",
      checksum: "7c7f5badcf0e9657eb1bcbccdadd88acfda872b59761cf3510aa9f820bc93c83"
    ),
    .binaryTarget(
      name: "SportBuff",
      url: "https://buffup-public.s3.eu-west-2.amazonaws.com/ios-sdk/sportbuff-ios-sdk-0.2.0.0.zip",
      checksum: "eba418bfb5cf938203be14ee5070baa0cdd15df95de70030e6196357e8ff7a54"
    )
  ],
  swiftLanguageVersions: [.v5]
)
