Pod::Spec.new do |s|
  s.name = 'OnRewindSDK'
  s.version = '0.0.1'
  s.summary = 'OnRewindSDK framework'

  s.homepage = 'https://github.com/netcosports/OnRewindSDK'
  s.license = {
    :type => "Copyright",
    :text => "Copyright 2022 Netcosports"
  }
  s.author = {
    'Sergei Mikhan' => 'sergei@netcosports.com'
  }

  s.source = { :http => 'https://origins-mobile-products.s3.eu-west-1.amazonaws.com/onrewind/whitelabel/0.0.1/OnRewindSDK.xcframework.zip' }

  s.ios.deployment_target = '13.0'
  s.ios.vendored_frameworks = 'OnRewindSDK.xcframework'
  s.swift_version = ['5.0', '5.1', '5.2']

end
