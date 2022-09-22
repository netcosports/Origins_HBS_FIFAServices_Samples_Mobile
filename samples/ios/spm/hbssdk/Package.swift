// swift-tools-version:5.3
import PackageDescription


let package = Package(
    name: "HBSSDK",
    platforms: [
        .iOS(.v11)
    ],
    products: [
        .library(name: "HBSSDK", targets: ["HBSSDK", "hbsshared"])
    ],
    dependencies: [
        
    ],
    targets: [
        .binaryTarget(
                     name: "HBSSDK",
                     url: "https://origins-mobile-products.s3.eu-west-1.amazonaws.com/hbssdk/directv/1.0.108/HBSSDK.xcframework.zip",
                     checksum: "1f8f375c5e86961929681a17c23630329f956316f19b295677ff8540db4bcb21"
                    ),
.binaryTarget(
                            name: "hbsshared",
                            url: "https://origins-mobile-products.s3.eu-west-1.amazonaws.com/hbssdk/directv/1.0.108/hbsshared.xcframework.zip",
                            checksum: "9e7cbd9496627d25783b07ce5b43719f4e5bb2b700697fe721b46648fa2d318b"
                          )
    ],
    swiftLanguageVersions: [.v5]
)
