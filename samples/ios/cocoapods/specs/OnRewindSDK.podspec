Pod::Spec.new do |s|  
    s.name = 'OnRewindSDK'
    s.version = '1.0.12'
    s.summary = 'OnRewind summary'
    s.homepage = 'https://github.com/netcosports'

    s.author = { 'Sergei Mikhan' => 'sergei@netcosports.com' }
    s.license = {
        :type => "Copyright",
        :text => "Copyright 2020 Origins Digital"
    }

    s.platform = :ios
    s.source = { :http => 'https://origins-mobile-products.s3.eu-west-1.amazonaws.com/onrewind_player/directv/1.0.12/OnRewindSDK.xcframework.zip' }

    s.ios.deployment_target = '11.0'
    s.ios.vendored_frameworks = 'OnRewindSDK.xcframework'
    s.static_framework = true

	s.dependency 'onrewindshared'


    def s.post_install(target)
        target.build_configurations.each do |config|
            config.build_settings['ARCHS'] = ["arm64", "x86_64"]
        end        
    end    
end
