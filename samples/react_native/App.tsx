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
import { ExpandedMatches } from '@origins-digital/react-native-hbssdk';
import { MediumMatches } from '@origins-digital/react-native-hbssdk';
import { SmallMatches } from '@origins-digital/react-native-hbssdk';
import { TeamBoard } from '@origins-digital/react-native-hbssdk';
import { Venue } from '@origins-digital/react-native-hbssdk';
import { Watch } from '@origins-digital/react-native-hbssdk';
import { Lineup } from '@origins-digital/react-native-hbssdk';
import { MatchStats } from '@origins-digital/react-native-hbssdk';
import { MatchHeader } from '@origins-digital/react-native-hbssdk';
import { ExpandedMatchHeader } from '@origins-digital/react-native-hbssdk';
import { Actions } from '@origins-digital/react-native-hbssdk';

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


        <Standings data={{ isExpanded: false }} style={styles.standings} />

        <Actions data={{ matchId: "84872"}} style={styles.actions} />

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
<LargeMatches data={{ roundId: "255951"}} style={styles.largeMatches} />
<ExpandedMatches data={{ roundId: "255951"}} style={styles.expanedMatches} />
<Videos data={{ category: "Matches - Match Clips"}} style={styles.videos} />
<TeamMatchesStats teamId="43960" style={styles.teamMatchesStats} />
<TopPlayerStats data={{ teamId: "43948", statsType: "goals" }} style={styles.topPlayerStats} />
<TopPlayerStats data={{ teamId: "43948", statsType: "shots" }} style={styles.topPlayerStats} />
<Venue style={styles.venue} />
<HeadToHead data={{ teamId: "43960" }} style={styles.headToHead} />
<HeadToHead data={{ teamId1: "43960", teamId2: "43948" }} style={styles.headToHead} />
<HeadToHead data = {{}} style={styles.headToHead} />
<TeamBoard data={{ teamId: "43948", allowChangeTeam: true }} style={styles.teamBoard} />
<TeamBoard data={{ teamId: "43948" }} style={styles.teamBoard} />
<Lineup data={{ matchId: "84872"}} style={styles.lineup} />
<MatchStats data={{ matchId: "84872"}} style={styles.matchStats} />
<MatchHeader data={{ matchId: "84872"}} style={styles.matchHeader} />
<ExpandedMatchHeader  data={{ matchId: "84872"}} style={styles.expandedMatchHeader} />

<Watch teamId="43960" style={styles.watch} />
<Favorites style={styles.favorites} />
<Championship  style={styles.championship} />

<TopPlayerStats statType={"goals"} style={styles.topPlayerStats} />
<TopPlayerStats statType={"assist"} style={styles.topPlayerStats} />
<TeamMatches teamId={"43960"} style={styles.teamMatches} />
*/

//
//         <MatchCenter matchId={"anyId"} style={styles.matchCenter} />
//         <Championship _onOpenMatchDetails={_onOpenMatchDetails} style={styles.championship} />

var styles = StyleSheet.create({
  topPlayerStats: {
    width: "100%",
    height: HBSSDK.topPlayerStatsComponentHeight
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
    height: HBSSDK.championshipComponentHeight,
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
  largeMatches: {
    width: "100%",
    height: HBSSDK.largeMatchesComponentHeight,
  },
  expanedMatches: {
    width: "100%",
    height: HBSSDK.expandedMatchesComponentHeight,
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
  },
  lineup: {
    width: "100%",
    height: 500,
  },
  matchStats: {
    width: "100%",
    height: 500,
  },
  matchHeader: {
    width: "100%",
    height: HBSSDK.matchHeaderComponentHeight,
    backgroundColor: '#000'
  },
  expandedMatchHeader: {
    width: "100%",
    height: HBSSDK.expandedMatchHeaderComponentHeight,
    backgroundColor: '#f47'
  },
  actions: {
    width: "100%",
    height: HBSSDK.actionsComponentHeight,
  }
});
