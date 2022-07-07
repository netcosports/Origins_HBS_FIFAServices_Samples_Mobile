import * as React from 'react';

import { StyleSheet, SafeAreaView, ScrollView } from 'react-native';
import { TopPlayerStats } from 'react-native-hbssdk';
import { TeamMatches } from 'react-native-hbssdk';
import { Videos } from 'react-native-hbssdk';

import HBSSDK from 'react-native-hbssdk'

export default function App() {
  return (
    <SafeAreaView >
     <ScrollView 
        style={{ width: "100%", height: "100%", backgroundColor: 'orange' }}
        contentContainerStyle={{ width: "100%" }}>

        <TopPlayerStats statType={"goals"} style={styles.topPlayerStats} />
        <TopPlayerStats statType={"assist"} style={styles.topPlayerStats} />

        <TeamMatches teamId={"anyId"} style={styles.teamMatches} />

        <Videos style={styles.videos} />

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
});
