# @origins-digital/react-native-hbssdk

HBSSDK

## Installation

```sh
npm install @origins-digital/react-native-hbssdk
```

## iOS integration

You neeed to add the following spec into your `Podfile`:

```ruby
def download_spec! (options={})
  url = options[:url]
  Dir.chdir('./specs'){
    `curl -X GET '#{url}' -O -k -f -L`
  }
end


# use links to podspecs provided to you
download_spec!(url: 'https://origins-mobile-products.s3.eu-west-1.amazonaws.com/hbssdk/whitelabel/1.0.71/HBSSDK.podspec')
download_spec!(url: 'https://origins-mobile-products.s3.eu-west-1.amazonaws.com/hbssdk/whitelabel/1.0.71/hbsshared.podspec')
download_spec!(url: 'https://origins-mobile-products.s3.eu-west-1.amazonaws.com/OnRewindSDK.podspec')

target 'your_project_target' do
  # SDK specs
  pod 'hbsshared', :podspec => './specs/hbsshared.podspec'
  pod 'HBSSDK', :podspec => './specs/HBSSDK.podspec'
  pod 'OnRewindSDK', :podspec => './specs/OnRewindSDK.podspec'
  pod 'SportBuff', '0.2.0.0'

end
```

Also, you may need to integrate OnRewind SDK. For this you need to add the following into your AppDelegate.m:

```objc
#import <HBSSDK/HBSSDK-Swift.h>
#import <OnRewindSDK/OnRewindSDK-Swift.h>

// ...

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
  [Integration setPresentPlayerBlock:^(PresentationContext * _Nonnull context) {
    [OnRewind presentPlayerWithVideoURL:context.videoURL
                                isLive:NO
          fromPresentingViewController:context.presentationController];
  }];

  [OnRewind setWithBaseUrl:@""];

// ...
}
```
You can check details of cocoapods integration of lib in https://github.com/netcosports/Origins_HBS_FIFAServices_Samples_Mobile/tree/main/samples/ios/cocoapods

## Android integration

Add maven repository to the app build.gradle:

```groovy
allprojects {
    repositories {
        ...
        maven {
            url "https://artifactory-blr.netcodev.com/artifactory/libs-release"
            credentials {
                username "username"
                password "password"
            }
        }
    }
}
```

Add dependancies: 

```groovy
implementation "com.origins-digital.hbswidgets:widgets-whitelabel:1.0.69"
implementation "com.onrewind:onrewind-sdk:1.7.9"
```

Add OnRewind and HBSSDK integrations into your Appllication implementation:

```java
HbsSdk.init(this);
    OnRewind.initialize(
            new OnRewind.InitParams.Builder()
                    .setApplicationContext(this)
                    .setBaseUrl("https://api-gateway.onrewind.tv/main-api/")
                    .build()
    );
```

You can check details of android integration in https://github.com/netcosports/Origins_HBS_FIFAServices_Samples_Mobile/tree/main/samples/android

## Usage

```typescript
import { TopPlayerStats } from 'react-native-hbssdk';
import { TeamMatches } from 'react-native-hbssdk';
import { Videos } from 'react-native-hbssdk';
import { Standings } from 'react-native-hbssdk';
import { Championship } from 'react-native-hbssdk';
import { Favorites } from 'react-native-hbssdk';
import { HeadToHead } from 'react-native-hbssdk';

import HBSSDK from 'react-native-hbssdk'

// ...

<TopPlayerStats statType={"goals"} style={styles.topPlayerStats} />
<TopPlayerStats statType={"assist"} style={styles.topPlayerStats} />
<TeamMatches teamId={"anyId"} style={styles.teamMatches} />
<Videos style={styles.videos} />
<Standings data={{ groupId: "someId", isExpanded: true }} style={styles.standings} />
<Championship  style={styles.championship} />
<Favorites style={styles.favorites} />
<HeadToHead data={{ teamId1: "someId", teamId2: "anotherId" }} style={styles.headToHead} />
```

For all widgets SDK provides default height. You can access it like this:

```typescript
var styles = StyleSheet.create({
  topPlayerStats: {
    width: "100%",
    height: HBSSDK.topPlayerStatsComponentHeight,
    backgroundColor: 'green'
  },
  teamMatches: {
    width: "100%",
    height: HBSSDK.teamMatchesComponentHeight,
  },
  videos: {
    width: "100%",
    height: HBSSDK.videosComponentHeight,
  },
  standings: {
    width: "100%",
    height: HBSSDK.standingsComponentHeight,
  },
  championship: {
    width: "100%",
    height: 520//HBSSDK.championshipComponentHeight,
  },
  favorites: {
    width: "100%",
    height: 520//HBSSDK.favoritesComponentHeight,
  },
  headToHead: {
    width: "100%",
    height: HBSSDK.headToHeadComponentHeight,
  }
});
```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT


