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
  style: ViewStyle;
};

const VideosComponentName = 'Videos';

export const Videos =
  UIManager.getViewManagerConfig(VideosComponentName) != null
    ? requireNativeComponent<VideosProps>(VideosComponentName)
    : () => {
        throw new Error(LINKING_ERROR);
      };

///////////////////////
// module
export default HBSSDK;
