Pod::Spec.new do |s|  
    s.name = 'HBSSDK'
    s.version = '1.0.77'
    s.summary = 'HBSSDK summary'
    s.homepage = 'https://github.com/netcosports'

    s.author = { 'Sergei Mikhan' => 'sergei@netcosports.com' }
    s.license = {
        :type => "Copyright",
        :text => "Copyright 2020 Origins Digital"
    }

    s.platform = :ios
    s.source = { :http => 'https://origins-mobile-products.s3.eu-west-1.amazonaws.com/hbssdk/whitelabel/1.0.77/HBSSDK.xcframework.zip' }

    s.ios.deployment_target = '12.0'
    s.ios.vendored_frameworks = 'HBSSDK.xcframework'
    s.static_framework = true

	s.dependency 'hbsshared'


    def s.post_install(target)
        target.build_configurations.each do |config|
            config.build_settings['ARCHS'] = ["arm64", "x86_64"]
        end        
    end    
end
