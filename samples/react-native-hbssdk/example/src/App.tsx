import * as React from 'react';

import { StyleSheet, SafeAreaView, ScrollView } from 'react-native';
import { TopPlayerStats } from 'react-native-hbssdk';
import { TeamMatches } from 'react-native-hbssdk';
import { Videos } from 'react-native-hbssdk';
import { Standings } from 'react-native-hbssdk';
import { Championship } from 'react-native-hbssdk';

import HBSSDK from 'react-native-hbssdk'

export default function App() {
  var _onOpenMatchDetails = (matchId: string) => {
    console.log("Match id is" + matchId)
  };
  return (
    <SafeAreaView >
     <ScrollView 
        style={{ width: "100%", height: "100%", backgroundColor: 'orange' }}
        contentContainerStyle={{ width: "100%" }}>

        <TopPlayerStats statType={"goals"} style={styles.topPlayerStats} />
        <TopPlayerStats statType={"assist"} style={styles.topPlayerStats} />

        <TeamMatches teamId={"anyId"} style={styles.teamMatches} />

        <Videos style={styles.videos} />
        <Standings data={{ groupId: "no", isExpanded: true }} style={styles.standings} />
        <Championship  style={styles.championship} />

      </ScrollView>
    </SafeAreaView>
  );
}

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
    height: HBSSDK.championshipComponentHeight,
  },
});
