import * as React from 'react';

import { StyleSheet, SafeAreaView, ScrollView } from 'react-native';

import { TopPlayerStats } from '@origins-digital/react-native-hbssdk';
import { TeamMatches } from '@origins-digital/react-native-hbssdk';
import { Videos } from '@origins-digital/react-native-hbssdk';
import { Standings } from '@origins-digital/react-native-hbssdk';
import { Championship } from '@origins-digital/react-native-hbssdk';
import { Favorites } from '@origins-digital/react-native-hbssdk';
import { HeadToHead } from '@origins-digital/react-native-hbssdk';
import { TeamMatchesStats } from '@origins-digital/react-native-hbssdk';
import { LargeMatches } from '@origins-digital/react-native-hbssdk';
import { MediumMatches } from '@origins-digital/react-native-hbssdk';
import { SmallMatches } from '@origins-digital/react-native-hbssdk';
import { TeamBoard } from '@origins-digital/react-native-hbssdk';
import { Venue } from '@origins-digital/react-native-hbssdk';
import { Watch } from '@origins-digital/react-native-hbssdk';

import HBSSDK from '@origins-digital/react-native-hbssdk'

export default function App() {
  var _onOpenMatchDetails = (matchId: string) => {
    console.log("Match id is" + matchId)
  };
  return (
    <SafeAreaView >
     <ScrollView
        style={{ width: "100%", height: "100%", backgroundColor: '' }}
        contentContainerStyle={{ width: "100%" }}>

        <Videos data={{ category: "Matches - Match Clips"}} style={styles.videos} />

        <Standings data={{ isExpanded: false }} style={styles.standings} />

{/*
*/}
      </ScrollView>
    </SafeAreaView>
  );
}

/*

        <Standings data={{ groupId: "255933", isExpanded: true }} style={styles.standings} />
        <Standings data={{ isExpanded: false }} style={styles.standings} />
<SmallMatches data={{ groupId: "255933"}} style={styles.smallMatches} />
<MediumMatches data={{ teamId: "43960"}} style={styles.mediumMatches} />
<LargeMatches data={{ roundId: "255951"}} style={styles.largeMatchesWithEvents} />

<TeamMatchesStats teamId="43960" style={styles.teamMatchesStats} />
<Watch teamId="43960" style={styles.watch} />
<Venue style={styles.venue} />
<HeadToHead data={{ teamId: "43960" }} style={styles.headToHead} />
<Favorites style={styles.favorites} />
<Championship  style={styles.championship} />
<TeamBoard teamId="43960" style={styles.teamBoard} />

<TopPlayerStats statType={"goals"} style={styles.topPlayerStats} />
<TopPlayerStats statType={"assist"} style={styles.topPlayerStats} />
<TeamMatches teamId={"43960"} style={styles.teamMatches} />
<Videos style={styles.videos} />
*/

//
//         <MatchCenter matchId={"anyId"} style={styles.matchCenter} />
//         <Championship _onOpenMatchDetails={_onOpenMatchDetails} style={styles.championship} />

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
  matchCenter: {
    width: "100%",
    height: HBSSDK.matchCenterComponentHeight,
  },
  favorites: {
    width: "100%",
    height: 520//HBSSDK.favoritesComponentHeight,
  },
  headToHead: {
    width: "100%",
    height: HBSSDK.headToHeadComponentHeight,
  },
  smallMatches: {
    width: "100%",
    height: HBSSDK.smallMatchesComponentHeight,
  },
  mediumMatches: {
    width: "100%",
    height: HBSSDK.mediumMatchesComponentHeight,
  },
  largeMatchesWithEvents: {
    width: "100%",
    height: HBSSDK.matchesWithEventsComponentHeight,
  },
  teamBoard: {
    width: "100%",
    height: HBSSDK.teamBoardComponentHeight,
  },
  venue: {
    width: "100%",
    height: HBSSDK.venueComponentHeight,
  },
  watch: {
    width: "100%",
    height: HBSSDK.watchComponentHeight,
  },
  teamMatchesStats: {
    width: "100%",
    height: HBSSDK.teamMatchesStatsComponentHeight,
  }
});
