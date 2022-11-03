Pod::Spec.new do |s|  
    s.name = 'OnRewindSDK'
    s.version = '1.0.51'
    s.summary = 'OnRewind summary'
    s.homepage = 'https://github.com/netcosports'

    s.author = { 'Sergei Mikhan' => 'sergei@netcosports.com' }
    s.license = {
        :type => "Copyright",
        :text => "Copyright 2020 Origins Digital"
    }
    s.source = { :http => 'https://origins-mobile-products.s3.eu-west-1.amazonaws.com/hbs_onrewind_player/whitelabel/1.0.51/OnRewindSDK.xcframework.zip' }

    s.ios.deployment_target = '12.0'
    s.ios.vendored_frameworks = 'OnRewindSDK.xcframework'

	s.dependency 'onrewindshared'


	s.dependency 'google-cast-sdk', '4.7.0'
	s.dependency 'SportBuff', '0.2.0.0'
	s.dependency 'onrewindshared'


end
