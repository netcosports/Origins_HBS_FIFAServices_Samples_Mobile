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
      url: "https://origins-mobile-products.s3.eu-west-1.amazonaws.com/hbssdk/whitelabel/1.0.65/HBSSDK.xcframework.zip",
      checksum: "c72df007ef1a8af7e167b2b1c8bd1fbf90301c25808c5f5cafe2e943270502ef"
    ),
    .binaryTarget(
      name: "OnRewindSDK",
      url: "https://origins-mobile-products.s3.eu-west-1.amazonaws.com/OnRewindSDK.xcframework.zip",
      checksum: "7c7f5badcf0e9657eb1bcbccdadd88acfda872b59761cf3510aa9f820bc93c83"
    ),
    .binaryTarget(
      name: "SportBuff",
      url: "https://buffup-public.s3.eu-west-2.amazonaws.com/ios-sdk/sportbuff-ios-sdk-0.2.0.0.zip",
      checksum: "eba418bfb5cf938203be14ee5070baa0cdd15df95de70030e6196357e8ff7a54"
    ),
    .binaryTarget(
      name: "hbsshared",
      url: "https://origins-mobile-products.s3.eu-west-1.amazonaws.com/hbssdk/whitelabel/1.0.65/hbsshared.xcframework.zip",
      checksum: "bcb0d8177e722b374da3984ad234888ca2e7afe929c3765a7263e294e7b274bd"
    )
  ],
  swiftLanguageVersions: [.v5]
)
