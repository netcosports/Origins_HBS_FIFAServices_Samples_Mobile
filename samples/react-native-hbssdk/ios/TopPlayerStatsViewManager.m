//
//  TopStatsViewManager.m
//  HBSWidgetsDemo
//
//  Created by Sergei Mikhan on 8.04.22.
//

#import <React/RCTViewManager.h>
#import <HBSSDK/HBSSDK-Swift.h>

@interface TopPlayerStatsViewManager: RCTViewManager

@end

@implementation TopPlayerStatsViewManager

RCT_EXPORT_MODULE(TopPlayerStats);

- (UIView *) view {
  UIView<TopPlayerStatsWidget> *view = [Stats topPlayersWidget];
  return view;
}

RCT_CUSTOM_VIEW_PROPERTY(data, String, UIView<TopPlayerStatsWidget>)
{

  NSString *typeString = [RCTConvert NSString:json[@"statsType"]];
  NSString *teamId = [RCTConvert NSString:json[@"teamId"]];
  TopPlayerStatsWidgetType type = -1;
  if (typeString != nil) {
    if ([typeString isEqualToString:@"goals"]) {
      type = TopPlayerStatsWidgetTypeGoals;
    } else if ([typeString isEqualToString:@"shots"]) {
      type = TopPlayerStatsWidgetTypeShots;
    }
  }

  if (type >= 0 && teamId != nil) {
    [view setupWidgetParamsWithTeamId:teamId statsType:type];
  }

}

+ (BOOL)requiresMainQueueSetup {
  return true;
}


@end
