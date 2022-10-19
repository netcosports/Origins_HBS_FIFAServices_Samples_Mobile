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
  teamId: string,
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

////////////////////////
// lineup
type LineupProps = {
  matchId: string,
  style: ViewStyle;
};

const LineupComponentName = 'Lineup';

export const Lineup =
  UIManager.getViewManagerConfig(LineupComponentName) != null
    ? requireNativeComponent<LineupProps>(LineupComponentName)
    : () => {
        throw new Error(LINKING_ERROR);
      };

////////////////////////

////////////////////////
// actions
type ActionProps = {
  matchId: string,
  style: ViewStyle;
};

const ActionsComponentName = 'Actions';

export const Actions =
  UIManager.getViewManagerConfig(ActionsComponentName) != null
    ? requireNativeComponent<ActionProps>(ActionsComponentName)
    : () => {
        throw new Error(LINKING_ERROR);
      };

////////////////////////

////////////////////////
// match stats
type MatchStatsProps = {
  matchId: string,
  style: ViewStyle;
};

const MatchStatsComponentName = 'MatchStats';

export const MatchStats =
  UIManager.getViewManagerConfig(MatchStatsComponentName) != null
    ? requireNativeComponent<MatchStatsProps>(MatchStatsComponentName)
    : () => {
        throw new Error(LINKING_ERROR);
      };

////////////////////////

////////////////////////
// lineup
type MatchHeaderProps = {
  matchId: string,
  style: ViewStyle;
};

const MatchHeaderComponentName = 'MatchHeader';

export const MatchHeader =
  UIManager.getViewManagerConfig(MatchHeaderComponentName) != null
    ? requireNativeComponent<MatchHeaderProps>(MatchHeaderComponentName)
    : () => {
        throw new Error(LINKING_ERROR);
      };

const ExpandedMatchHeaderComponentName = 'ExpandedMatchHeader';

export const ExpandedMatchHeader =
  UIManager.getViewManagerConfig(ExpandedMatchHeaderComponentName) != null
    ? requireNativeComponent<MatchHeaderProps>(ExpandedMatchHeaderComponentName)
    : () => {
        throw new Error(LINKING_ERROR);
      };

////////////////////////

// team matches stats
type TeamMatchesStatsProps = {
  teamId: string;
  style: ViewStyle;
};

const TeamMatchesStatsComponentName = 'TeamMatchesStats';

export const TeamMatchesStats =
  UIManager.getViewManagerConfig(TeamMatchesStatsComponentName) != null
    ? requireNativeComponent<TeamMatchesStatsProps>(TeamMatchesStatsComponentName)
    : () => {
        throw new Error(LINKING_ERROR);
      };

////////////////////////
// team board
type TeamBoardProps = {
  teamId: string;
  style: ViewStyle;
};

const TeamBoardComponentName = 'TeamBoard';

export const TeamBoard =
  UIManager.getViewManagerConfig(TeamBoardComponentName) != null
    ? requireNativeComponent<TeamBoardProps>(TeamBoardComponentName)
    : () => {
        throw new Error(LINKING_ERROR);
      };

////////////////////////
// venue
type VenueProps = {
  style: ViewStyle;
};

const VenueComponentName = 'Venue';

export const Venue =
  UIManager.getViewManagerConfig(VenueComponentName) != null
    ? requireNativeComponent<VenueProps>(VenueComponentName)
    : () => {
        throw new Error(LINKING_ERROR);
      };

////////////////////////
// watch
type WatchProps = {
  teamId: string;
  style: ViewStyle;
};

const WatchComponentName = 'Watch';

export const Watch =
  UIManager.getViewManagerConfig(WatchComponentName) != null
    ? requireNativeComponent<WatchProps>(WatchComponentName)
    : () => {
        throw new Error(LINKING_ERROR);
      };

////////////////////////
// squad
type SquadProps = {
  teamId: string;
  style: ViewStyle;
};

const SquadComponentName = 'Squad';

export const Squad =
  UIManager.getViewManagerConfig(SquadComponentName) != null
    ? requireNativeComponent<SquadProps>(SquadComponentName)
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
  category: string;
  subcategory: string;
  title: string;
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
////////////////////////
// Team List
type TeamListProps = {
  style: ViewStyle;
};

const TeamListComponentName = 'TeamList';

export const TeamList =
  UIManager.getViewManagerConfig(TeamListComponentName) != null
    ? requireNativeComponent<TeamListProps>(TeamListComponentName)
    : () => {
        throw new Error(LINKING_ERROR);
      };


////////////////////////
// head to head
type HeadToHeadProps = {
  data: { teamId: string, teamId1: string, teamId2: string };
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
  data: { groupId: string, teamId: string, roundId: string, matchId: string };
  style: ViewStyle;
};

const ExpandedMatchesComponentName = 'ExpandedMatches';
const LargeMatchesComponentName = 'LargeMatches';
const MediumMatchesComponentName = 'MediumMatches';
const SmallMatchesComponentName = 'SmallMatches';

export const ExpandedMatches =
  UIManager.getViewManagerConfig(ExpandedMatchesComponentName) != null
    ? requireNativeComponent<MatchesProps>(ExpandedMatchesComponentName)
    : () => {
        throw new Error(LINKING_ERROR);
      };


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
