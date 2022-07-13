//
//  HBSSDKModule.m
//  HBSWidgetsDemo
//
//  Created by Sergei Mikhan on 18.04.22.
//

#import <React/RCTBridgeModule.h>
#import <HBSSDK/HBSSDK-Swift.h>

@interface HBSSDKModule : NSObject <RCTBridgeModule>

@end

@implementation HBSSDKModule

- (instancetype)init {
  [Integration initSdk];
  [Integration allowMultipleFavoriteTeamsWithAllow:YES];

  return [super init];
}

RCT_EXPORT_MODULE(HBSSDK);

+ (BOOL)requiresMainQueueSetup {
  return YES;
}

-(NSDictionary *)constantsToExport {
  CGSize containerSize = [UIScreen mainScreen].bounds.size;
  CGFloat teamMatchesComponentHeight = [Stats teamMatchesSizeFor: containerSize].height;
  CGFloat topPlayerStatsComponentHeight = [Stats topPlayersSizeFor: containerSize].height;
  CGFloat videosComponentHeight = [Videos sizeFor: containerSize].height;
  CGFloat standingsComponentHeight = [Standings sizeFor:containerSize].height;
  CGFloat championshipComponentHeight = [Championship widgetSizeFor:containerSize].height;
  CGFloat favoritesComponentHeight = [Favorites sizeFor:containerSize].height;
  CGFloat headToHeadComponentHeight = [HeadToHead sizeFor:containerSize].height;
  CGFloat matchCenterComponentHeight = [MatchCenter widgetSizeFor:containerSize].height;

  CGFloat matchesWithEventsComponentHeight = [Matches bigSizeFor:containerSize displayEvents:YES].height;
  CGFloat matchesWithoutEventsComponentHeight = [Matches bigSizeFor:containerSize displayEvents:NO].height;

  CGFloat mediumMatchesComponentHeight = [Matches mediumSizeFor:containerSize].height;
  CGFloat smallMatchesComponentHeight = [Matches smallSizeFor:containerSize].height;

  return @{
    @"teamMatchesComponentHeight": @(teamMatchesComponentHeight),
    @"topPlayerStatsComponentHeight": @(topPlayerStatsComponentHeight),
    @"videosComponentHeight": @(videosComponentHeight),
    @"standingsComponentHeight": @(standingsComponentHeight),
    @"favoritesComponentHeight": @(favoritesComponentHeight),
    @"championshipComponentHeight": @(championshipComponentHeight),
    @"headToHeadComponentHeight": @(headToHeadComponentHeight),
    @"matchCenterComponentHeight": @(matchCenterComponentHeight),

    @"matchesWithEventsComponentHeight": @(matchesWithEventsComponentHeight),
    @"matchesWithoutEventsComponentHeight": @(matchesWithoutEventsComponentHeight),
    @"mediumMatchesComponentHeight": @(mediumMatchesComponentHeight),
    @"smallMatchesComponentHeight": @(smallMatchesComponentHeight)
  };
}

@end
