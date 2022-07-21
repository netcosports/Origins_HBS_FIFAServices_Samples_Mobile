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

RCT_CUSTOM_VIEW_PROPERTY(statType, NSString, UIView<TopPlayerStatsWidget>)
{
  NSString *typeString = [RCTConvert NSString:json];
  TopPlayerStatsWidgetType type = [typeString isEqualToString:@"goal"] ?
    TopPlayerStatsWidgetTypeGoals :
    TopPlayerStatsWidgetTypeAssists;

  [view setupWidgetParamsWithStatsType:type];
}

+ (BOOL)requiresMainQueueSetup {
  return true;
}


@end

