//
//  TeamMatchesViewManager.m
//  HBSWidgetsDemo
//
//  Created by Sergei Mikhan on 15.04.22.
//

#import <React/RCTViewManager.h>
#import <HBSSDK/HBSSDK-Swift.h>

@interface TeamMatchesViewManager: RCTViewManager

@property (nonatomic) StatsObjc* stats;

@end

@implementation TeamMatchesViewManager

RCT_EXPORT_MODULE(TeamMatches);

- (instancetype)init {
  if (self = [super init]) {
    _stats = [StatsObjc new];
  }
  return self;
}

- (UIView *) view {
  UIView<TeamMatchesStatsWidget> *view = [self.stats teamMatchesWidget];
  return view;
}

+ (BOOL)requiresMainQueueSetup {
  return true;
}

RCT_CUSTOM_VIEW_PROPERTY(teamId, String, UIView<TeamMatchesStatsWidget>)
{
  NSString *teamId = [RCTConvert NSString:json];
  [view setupWidgetParamsWithTeamId: teamId];
}

@end

