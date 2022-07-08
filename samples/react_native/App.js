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
  StyleSheet,
  SafeAreaView,
} from 'react-native';

import TopPlayerStats from 'react-native-hbssdk'
//import TeamMatches from 'react-native-hbssdk'
//import VideosWidget from 'react-native-hbssdk'

//import HBSSDK from 'react-native-hbssdk'

console.log(TopPlayerStats);

const App: () => Node = () => {

  return (
    <SafeAreaView >
     <ScrollView style={{ width: "100%", height: "100%", backgroundColor: 'orange' }} contentContainerStyle={{width: "100%"}}>

        <TopPlayerStats statType={"goals"} >
        </TopPlayerStats>

        <TopPlayerStats statType={"assist"}>
        </TopPlayerStats>

      </ScrollView>
    </SafeAreaView>
  );
};

//var styles = StyleSheet.create({
//  topPlayerStats: {
//    width: 300,
//    height: HBSSDK.topPlayerStatsComponentHeight,
//    backgroundColor: 'green'
//  },
//  teamMatches: {
//    width: "100%",
//    height: HBSSDK.teamMatchesComponentHeight,
//  },
//  videos: {
//    width: "100%",
//    height: HBSSDK.videosComponentHeight,
//  },
//});
//
export default App;
