/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import React from 'react';
import {
  ScrollView,
  UIManager,
  StyleSheet,
  SafeAreaView,
  NativeModules,
} from 'react-native';

import TopPlayerStats from './TopPlayerStats.js';
import TeamMatches from './TeamMatches.js';
import TestWidget from './TestWidget.js';
import VideosWidget from './VideosWidget.js';

const { HBSSDK } = NativeModules;

//var TopPlayerStatsConsts = UIManager.TopPlayerStats.Constants;
//var TeamMatchesConsts = UIManager.TeamMatches.Constants;

console.log(HBSSDK);

const App: () => Node = () => {

  return (
    <SafeAreaView >
     <ScrollView style={{ width: "100%", height: "100%", backgroundColor: 'orange' }} contentContainerStyle={{width: "100%"}}>
        <VideosWidget style={styles.videos}>
        </VideosWidget>

        <TopPlayerStats statType={"goals"} style={styles.topPlayerStats}>
        </TopPlayerStats>

        <TopPlayerStats statType={"assist"} style={styles.topPlayerStats}>
        </TopPlayerStats>

        <TeamMatches teamId={"anyId"} style={styles.teamMatches}>
        </TeamMatches>

        <TeamMatches teamId={"anotherId"} style={styles.teamMatches}>
        </TeamMatches>
      </ScrollView>
    </SafeAreaView>
  );
};

var styles = StyleSheet.create({
  topPlayerStats: {
    width: 300,
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

export default App;
