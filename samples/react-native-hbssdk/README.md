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
    %x(curl -X GET '#{url}' -O -k -f -L)
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

# React native widgets example.

## Matches
There are four types of match widgets
- SmallMatches
<img src="images/match_small.png" width="375"/>
- MediumMatches
<img src="images/match_medium.png" width="375"/>
- LargeMatches
<img src="images/match_large.png" width="375"/>
- ExpandedMatches
<img src="images/match_expanded.png" width="375"/>

```
<SmallMatches data={{ groupId: "255933"}} style={styles.smallMatches} />
<MediumMatches data={{ teamId: "43960"}} style={styles.mediumMatches} />
<LargeMatches data={{ roundId: "255951"}} style={styles.largeMatches} />
<ExpandedMatches data={{ roundId: "255951"}} style={styles.expanedMatches} />
```

It supports 4 types of data:
**Match id**, display matches for given round id:

```
<SmallMatches data={{matchId: "84872}} style={styles.smallMatches} />
```
**Round id**, display matches for given round:
```
<SmallMatches data={{roundId: "255951}} style={styles.smallMatches} />
```

**Group id**, display matches for given group:
```
<SmallMatches data={{roundId: "255933}} style={styles.smallMatches} />
```

**Team id**, display matches for given team:
```
<SmallMatches data={{roundId: "43960}} style={styles.smallMatches} />
```

## TopPlayer Stats widget

This widget display top players from given team for selected stats.
Supported stats type: **goals**, **shots**

```
<TopPlayerStats data={{ teamId: "43948", statsType: "goals" }} style={styles.topPlayerStats} />
```


## Team Matches Stats widget

This widget display stats of matches played by given team (by team id).
```
<TeamMatchesStats teamId="43960" style={styles.teamMatchesStats} />
```

## Standings widget.
This widget is used to display standings. It can display standings for all groups as carousel,
and for single group by group id. There are to display modes: **compact** and **expanded**

You can provide groupdId to display single group, if no group id provided all groups are displayed.
Display mode configured by **isExpanded** field. If it true widget is expanded, otherwise it is compact.


<img src="images/standings_all.png" width="375"/>

<img src="images/standings_single.png" width="375"/>

```
<Standings data={{ groupId: "255933", isExpanded: true }} style={styles.standings} />
<Standings data={{ isExpanded: false }} style={styles.standings} />
```

## Championship widget.
This widget displays all rounds data as carousel. It not requires any parameters


<img src="images/championship.png" width="375"/>

```<Championship  style={styles.championship} />
```

## Match center widgets.
When user clicks on matchbox it displays popup with match details by default.
You can handle clicks by yourself, and use separated widgets.
There are three widgets.
- Match Header
- Lineup
- Match Stats

#### Match header
Header of match center

<img src="images/match_header.png" width="375"/>

```
<MatchHeader data={{ matchId: "84872"}} style={styles.matchHeader} />
```
If you want also display actions inside it use Expanded match header
```

<ExpandedMatchHeader  data={{ matchId: "84872"}} style={styles.expandedMatchHeader} />
```

#### Lineup
Display lineup for given match

<img src="images/match_lineup.png" width="375"/>

```
<Lineup data={{ matchId: "84872"}} style={styles.lineup} />
```

#### Match Stats
Displays match stats for given match

<img src="images/match_stats.png" width="375"/>

```
<MatchStats data={{ matchId: "84872"}} style={styles.matchStats} />
```

## Actions
This widget displays match highlights as carousel
```
<Actions data={{ matchId: "84872"}} style={styles.actions} />
```

## Favorites
This widget display list of teams and allow user to change favorite team.
It does not require any parameters
```
<Favorites style={styles.favorites} />
```

## Squad
This widget is used to display team squad for given team.
```
<Squad data={{ teamId: "43960" }} style={styles.actions} />
```
## Videos
This widget displays list of videos as carousel for given category and (optional) subcategory.
```
<Videos data={{ category: "Matches - Match Clips", subcategory: "TODO" }} style={styles.actions} />
```

## Head to Head
This widget display head to head comparision for two teams.
You can preselect two teams, in this case user is not able to change it.
```
<HeadToHead data={{ teamId1: "43960", teamId2: "43948" }} style={styles.headToHead} />
```

You can preselect one team, in this case user will be able to change second team by himself
```
<HeadToHead data={{ teamId: "43960" }} style={styles.headToHead} />
```

You can preselect no teams, in this case user will be able to select any team for both teams.
```
<HeadToHead data = {{}} style={styles.headToHead} />
```

## Team Board
This widget display info about team, it contains team standings, matches, stats.
You can allow or forbid user to change selected team.
```
// user is allowed to change team
<TeamBoard data={{ teamId: "43948", allowChangeTeam: true }} style={styles.teamBoard} />
// user is not allowed to change team.
<TeamBoard data={{ teamId: "43948" }} style={styles.teamBoard} />
```

## Venue
This widget display list of venues as carousel. It does not require any parameters.
```
<Venue style={styles.venue} />
```

## Watch
This widget display carousel of special match view.
Top part contains match info and open match center when user clicks on it
Bottom part contains video info and launch video player.

<img src="images/watch.png" width="375"/>


Like matches widget it can be configured using **matchId**, **groupId**, **teamId** or **roundId**
```
<Watch data = {{ teamId: "43960" }} style={styles.watch} />
<Watch data = {{ groupId: "255933" }} style={styles.watch} />
<Watch data = {{ roundId: "255951" }} style={styles.watch} />
<Watch data = {{ matchId: "84872" }} style={styles.watch} />
```



## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT
