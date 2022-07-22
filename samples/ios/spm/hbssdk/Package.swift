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
                     url: "https://origins-mobile-products.s3.eu-west-1.amazonaws.com/hbssdk/whitelabel/1.0.77/HBSSDK.xcframework.zip",
                     checksum: "69986daa6fd50a361102ddbf34c02952a8eee8160880578b362cc9a307857647"
                    ),
.binaryTarget(
                            name: "hbsshared",                                        
                            url: "https://origins-mobile-products.s3.eu-west-1.amazonaws.com/hbssdk/whitelabel/1.0.77/hbsshared.xcframework.zip",
                            checksum: "8910cf6b6c1ae32ed2ae7f95b400c0d2f2ae252487ae955d29b3134fd7e355ce"
                          )
    ],
    swiftLanguageVersions: [.v5]
)
