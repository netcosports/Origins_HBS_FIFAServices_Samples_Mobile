import {
  requireNativeComponent,
  UIManager,
  Platform,
  ViewStyle,
  NativeModules
} from 'react-native';

const { HBSSDK } = NativeModules;

const LINKING_ERROR =
  `The package 'react-native-hbssdk' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo managed workflow\n';

////////////////////////
// top player
type TopPlayerStatsProps = {
  statType: string;
  style: ViewStyle;
};

const TopPlayerStatsComponentName = 'TopPlayerStats';

export const TopPlayerStats =
  UIManager.getViewManagerConfig(TopPlayerStatsComponentName) != null
    ? requireNativeComponent<TopPlayerStatsProps>(TopPlayerStatsComponentName)
    : () => {
        throw new Error(LINKING_ERROR);
      };


////////////////////////
// top player
type TeamMatchesProps = {
  teamId: string;
  style: ViewStyle;
};

const TeamMatchesComponentName = 'TeamMatches';

export const TeamMatches =
  UIManager.getViewManagerConfig(TeamMatchesComponentName) != null
    ? requireNativeComponent<TeamMatchesProps>(TeamMatchesComponentName)
    : () => {
        throw new Error(LINKING_ERROR);
      };

////////////////////////
// videos
type VideosProps = {
  data: { groupId: string, isExpanded: boolean };
  style: ViewStyle;
};

const VideosComponentName = 'Videos';

export const Videos =
  UIManager.getViewManagerConfig(VideosComponentName) != null
    ? requireNativeComponent<VideosProps>(VideosComponentName)
    : () => {
        throw new Error(LINKING_ERROR);
      };

////////////////////////
// standings
type StandingsProps = {
  data: { groupId: string, isExpanded: boolean };
  style: ViewStyle;
};

const StandingsComponentName = 'Standings';

export const Standings =
  UIManager.getViewManagerConfig(StandingsComponentName) != null
    ? requireNativeComponent<StandingsProps>(StandingsComponentName)
    : () => {
        throw new Error(LINKING_ERROR);
      };

////////////////////////
// championship
type ChampionshipProps = {
//  onRegionChange: Function,
  style: ViewStyle;
};

const ChampionshipComponentName = 'Championship';

export const Championship =
  UIManager.getViewManagerConfig(ChampionshipComponentName) != null
    ? requireNativeComponent<ChampionshipProps>(ChampionshipComponentName)
    : () => {
        throw new Error(LINKING_ERROR);
      };

////////////////////////
// favorites
type FavoritesProps = {
  style: ViewStyle;
};

const FavoritesComponentName = 'Favorites';

export const Favorites =
  UIManager.getViewManagerConfig(FavoritesComponentName) != null
    ? requireNativeComponent<FavoritesProps>(FavoritesComponentName)
    : () => {
        throw new Error(LINKING_ERROR);
      };


////////////////////////
// head to head
type HeadToHeadProps = {
  data: { teamId1: string, teamId2: string };
  style: ViewStyle;
};

const HeadToHeadComponentName = 'HeadToHead';

export const HeadToHead =
  UIManager.getViewManagerConfig(HeadToHeadComponentName) != null
    ? requireNativeComponent<HeadToHeadProps>(HeadToHeadComponentName)
    : () => {
        throw new Error(LINKING_ERROR);
      };

////////////////////////
// matchcenter
type MatchCenterProps = {
  matchId: string;
  style: ViewStyle;
};

const MatchCenterComponentName = 'MatchCenter';

export const MatchCenter =
  UIManager.getViewManagerConfig(MatchCenterComponentName) != null
    ? requireNativeComponent<MatchCenterProps>(MatchCenterComponentName)
    : () => {
        throw new Error(LINKING_ERROR);
      };

////////////////////////
// large matches
type MatchesProps = {
  data: { groupId: string, teamId: string, roundId: string };
  style: ViewStyle;
};

const LargeMatchesComponentName = 'LargeMatches';
const MediumMatchesComponentName = 'MediumMatches';
const SmallMatchesComponentName = 'SmallMatches';

export const LargeMatches =
  UIManager.getViewManagerConfig(LargeMatchesComponentName) != null
    ? requireNativeComponent<MatchesProps>(LargeMatchesComponentName)
    : () => {
        throw new Error(LINKING_ERROR);
      };

export const MediumMatches =
  UIManager.getViewManagerConfig(MediumMatchesComponentName) != null
    ? requireNativeComponent<MatchesProps>(MediumMatchesComponentName)
    : () => {
        throw new Error(LINKING_ERROR);
      };

export const SmallMatches =
  UIManager.getViewManagerConfig(SmallMatchesComponentName) != null
    ? requireNativeComponent<MatchesProps>(SmallMatchesComponentName)
    : () => {
        throw new Error(LINKING_ERROR);
      };
///////////////////////
// module
export default HBSSDK;

