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
                     url: "https://origins-mobile-products.s3.eu-west-1.amazonaws.com/hbssdk/whitelabel/1.0.92/HBSSDK.xcframework.zip",
                     checksum: "f012668260dcaf1616c42911adeb5a09680ecdd7776d69e685b62df5bd8142ea"
                    ),
.binaryTarget(
                            name: "hbsshared",
                            url: "https://origins-mobile-products.s3.eu-west-1.amazonaws.com/hbssdk/whitelabel/1.0.92/hbsshared.xcframework.zip",
                            checksum: "4088638e57e354de6ef2421633da153e6d2d526805b5797bab89dfd80cd5b17f"
                          )
    ],
    swiftLanguageVersions: [.v5]
)
