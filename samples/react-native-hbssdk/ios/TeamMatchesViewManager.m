//
//  TeamMatchesViewManager.m
//  HBSWidgetsDemo
//
//  Created by Sergei Mikhan on 15.04.22.
//

#import <React/RCTViewManager.h>
#import <HBSSDK/HBSSDK-Swift.h>

@interface TeamMatchesViewManager: RCTViewManager

@end

@implementation TeamMatchesViewManager

RCT_EXPORT_MODULE(TeamMatches);

- (UIView *) view {
  UIView<TeamMatchesStatsWidget> *view = [Stats teamMatchesWidget];
  return view;
}

+ (BOOL)requiresMainQueueSetup {
  return true;
}

RCT_CUSTOM_VIEW_PROPERTY(teamId, String, UIView<TeamMatchesStatsWidget>)
{
  NSString *teamId = [RCTConvert NSString:json];
  [view setupWidgetParamsWithTeamId:teamId];
}

@end

