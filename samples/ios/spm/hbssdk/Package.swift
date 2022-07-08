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
                     url: "https://origins-mobile-products.s3.eu-west-1.amazonaws.com/hbssdk/whitelabel/1.0.71/HBSSDK.xcframework.zip",
                     checksum: "e12e866ad870db5ce9ea94a889e86d3739f42ff41825bbb9044fa292b9bb4647"
                    ),
.binaryTarget(
                            name: "hbsshared",                                        
                            url: "https://origins-mobile-products.s3.eu-west-1.amazonaws.com/hbssdk/whitelabel/1.0.71/hbsshared.xcframework.zip",
                            checksum: "4a0969375e6230132c239f9047d76063e603a130a23eee37829cfc464ebe30d6"
                          )
    ],
    swiftLanguageVersions: [.v5]
)
