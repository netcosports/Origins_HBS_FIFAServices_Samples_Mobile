Pod::Spec.new do |s|  
    s.name = 'onrewindshared'
    s.version = '1.0.45'
    s.summary = 'Summary of onrewindshared'
    s.homepage = 'https://github.com/netcosports'

    s.author = { 'Sergei Mikhan' => 'sergei@netcosports.com' }
    s.license = {
        :type => "Copyright",
        :text => "Copyright 2020 Origins Digital"
    }

    s.platform = :ios
    s.source = { :http => 'https://origins-mobile-products.s3.eu-west-1.amazonaws.com/hbs_onrewind_player/viacom18/1.0.45/onrewindshared.xcframework.zip' }

    s.ios.deployment_target = '12.0'
    s.ios.vendored_frameworks = 'onrewindshared.xcframework'



end
