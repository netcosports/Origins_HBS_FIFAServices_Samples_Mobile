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
                     url: "https://origins-mobile-products.s3.eu-west-1.amazonaws.com/hbssdk/whitelabel/1.0.86/HBSSDK.xcframework.zip",
                     checksum: "7aba2f29b6adf934a4dd51acb61b7e2be0f8f2c64daae850bd58de12f05beb31"
                    ),
        .binaryTarget(
                            name: "hbsshared",
                            url: "https://origins-mobile-products.s3.eu-west-1.amazonaws.com/hbssdk/whitelabel/1.0.86/hbsshared.xcframework.zip",
                            checksum: "f0b2138d29c0a1180b162ff50c5e661cb5f6e3a7aa84d4d827c426e574a3f779"
                          )
    ],
    swiftLanguageVersions: [.v5]
)
