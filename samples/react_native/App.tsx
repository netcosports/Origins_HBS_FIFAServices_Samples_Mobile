import React, { Component } from 'react';

import { TopPlayerStats } from '@origins-digital/react-native-hbssdk';
import { TeamMatches } from '@origins-digital/react-native-hbssdk';
import { Videos } from '@origins-digital/react-native-hbssdk';
import { Standings } from '@origins-digital/react-native-hbssdk';
import { Championship } from '@origins-digital/react-native-hbssdk';
import { TeamList } from '@origins-digital/react-native-hbssdk';
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
import { Squad } from '@origins-digital/react-native-hbssdk';

import HBSSDK from '@origins-digital/react-native-hbssdk'

import { NativeModules, NativeEventEmitter, StyleSheet, SafeAreaView, ScrollView, EmitterSubscription } from 'react-native';

const { OnRewind, HBSEventEmitter, HbsConfiguration } = NativeModules;

export default class App extends Component {

  private eventListener!: EmitterSubscription;
  private eventEmitter!: NativeEventEmitter;

  componentDidMount() {
    this.eventEmitter = new NativeEventEmitter(HBSEventEmitter)
    this.eventListener = this.eventEmitter.addListener('presentVideoPlayer', (data) => {
      OnRewind.presentPlayer(data.matchId, data.streamUrl)
    })
    HbsConfiguration.updateConfiguration("fwc", "2022")
  }

  componentDidUpdate() {
    console.log("did unmount")
    this.eventListener.remove();
  }

  render() {
    return (
      <SafeAreaView >
      <ScrollView
          style={{ width: "100%", height: "100%", backgroundColor: '' }}
          contentContainerStyle={{ width: "100%" }}>

          <Videos data = {{ category: "Matches - Match Clips", title: "My awesome title"}} style={styles.videos} />
          <Videos data = {{ category: "Matches - Match Clips", subcategory: "Arrival", title: "Check subcategory"}} style={styles.videos} />
          <Videos data = {{ matchId: "134086", title: "Match Videos"}} style={styles.videos} />
          <Watch data = {{ matchId: "134085" }} style={styles.watch} />
  {/*
  */}
        </ScrollView>
      </SafeAreaView>
    );
  }
}

/*

<TeamList style={styles.teamlist} />
<MediumMatches onMatchSelected={_onOpenMatchDetails} data={{ teamId: "43960"}} style={styles.mediumMatches} />
<Standings data={{ groupId: "255933", isExpanded: true }} style={styles.standings} />
<Standings data={{ isExpanded: false }} style={styles.standings} />
<SmallMatches data={{ groupId: "255933"}} style={styles.smallMatches} />
<LargeMatches data={{ roundId: "255951"}} style={styles.largeMatches} />
<ExpandedMatches data={{ roundId: "255951"}} style={styles.expanedMatches} />
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


<Watch data = {{ teamId: "43960" }} style={styles.watch} />
<Watch data = {{ groupId: "255933" }} style={styles.watch} />
<Watch data = {{ roundId: "255951" }} style={styles.watch} />
<Watch data = {{ matchId: "84872" }} style={styles.watch} />
<Actions data={{ matchId: "84872"}} style={styles.actions} />
<Squad data={{ teamId: "43960" }} style={styles.actions} />

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
    height: 600,
  },
  matchCenter: {
    width: "100%",
    height: 600,
  },
  favorites: {
    width: "100%",
    height: 600,
  },
  teamlist: {
    width: "100%",
    height: 600,
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
  },
  squad: {
    width: "100%",
    height: HBSSDK.squadComponentHeight,
  }
});
