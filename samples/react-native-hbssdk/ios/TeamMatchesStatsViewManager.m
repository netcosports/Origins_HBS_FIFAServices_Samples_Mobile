//
//  TeamMatchesStatsViewManager.m
//  react-native-hbssdk
//
//  Created by Sergei Mikhan on 13.07.22.
//

#import <React/RCTViewManager.h>
#import <HBSSDK/HBSSDK-Swift.h>

@interface TeamMatchesStatsViewManager: RCTViewManager

@end

@implementation TeamMatchesStatsViewManager

RCT_EXPORT_MODULE(TeamMatchesStats);

- (UIView *) view {
  UIView<TeamMatchesStatsWidget> *view = [Stats teamMatchesWidget];
  return view;
}

RCT_CUSTOM_VIEW_PROPERTY(teamId, NSString, UIView<TeamMatchesStatsWidget>)
{
  NSString *teamId = [RCTConvert NSString:json];
  [view setupWidgetParamsWithTeamId:teamId];
}

+ (BOOL)requiresMainQueueSetup {
  return true;
}

@end

