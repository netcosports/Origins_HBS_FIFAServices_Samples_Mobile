//
//  HBSSDKModule.m
//  HBSWidgetsDemo
//
//  Created by Sergei Mikhan on 18.04.22.
//

#import <React/RCTBridgeModule.h>
#import <React/RCTBridgeMethod.h>

#import <HBSSDK/HBSSDK-Swift.h>

@interface HBSSDKModule : NSObject <RCTBridgeModule>

@end

@implementation HBSSDKModule

- (instancetype)init {
  return [super init];
}

RCT_EXPORT_MODULE(HBSSDK);

RCT_EXPORT_METHOD(setPresentPlayerBlock: (RCTResponseSenderBlock)callback)
{
  [Integration setPresentPlayerBlock:^(VideoPresentationContext * _Nonnull context) {
    callback(@[context.eventId == nil ? @"" : context.eventId, context.videoURL.absoluteURL == nil ? @"" : context.videoURL.absoluteURL]);
  }];
}

+ (BOOL)requiresMainQueueSetup {
  return YES;
}

-(NSDictionary *)constantsToExport {
  CGSize containerSize = [UIScreen mainScreen].bounds.size;
  CGFloat teamMatchesComponentHeight = [Stats teamMatchesSizeFor: containerSize].height;
  CGFloat topPlayerStatsComponentHeight = [Stats topPlayersSizeFor: containerSize].height;
  CGFloat videosComponentHeight = [Videos videoWidgetSizeFor:containerSize].height;
  CGFloat standingsComponentHeight = [Standings sizeFor:containerSize].height;
  CGFloat championshipComponentHeight = [Championship widgetSizeFor:containerSize].height;
  CGFloat favoritesComponentHeight = [Favorites sizeFor:containerSize].height;
  CGFloat headToHeadComponentHeight = [HeadToHead sizeFor:containerSize].height;
  CGFloat matchCenterComponentHeight = [MatchCenter widgetSizeFor:containerSize].height;

  CGFloat largeMatchesComponentHeight = [Matches largeSizeFor:containerSize].height;
  CGFloat expandedMatchesComponentHeight = [Matches expandedSizeFor:containerSize].height;

  CGFloat mediumMatchesComponentHeight = [Matches mediumSizeFor:containerSize].height;
  CGFloat smallMatchesComponentHeight = [Matches smallSizeFor:containerSize].height;

  CGFloat teamMatchesStatsComponentHeight = [Stats teamMatchesSizeFor:containerSize].height;
  CGFloat teamBoardComponentHeight = [TeamBoard sizeFor:containerSize].height;
  CGFloat venueComponentHeight = [Venue widgetSizeWithContainerSize:containerSize].height;
  CGFloat watchComponentHeight = [Watch sizeFor:containerSize].height;
  CGFloat squadComponentHeight = [Teams squadWidgetSizeFor:containerSize].height;

  return @{
    @"teamMatchesComponentHeight": @(teamMatchesComponentHeight),
    @"topPlayerStatsComponentHeight": @(topPlayerStatsComponentHeight),
    @"videosComponentHeight": @(videosComponentHeight),
    @"standingsComponentHeight": @(standingsComponentHeight),
    @"favoritesComponentHeight": @(favoritesComponentHeight),
    @"championshipComponentHeight": @(championshipComponentHeight),
    @"headToHeadComponentHeight": @(headToHeadComponentHeight),
    @"matchCenterComponentHeight": @(matchCenterComponentHeight),

    @"largeMatchesComponentHeight": @(largeMatchesComponentHeight),
    @"expandedMatchesComponentHeight": @(expandedMatchesComponentHeight),
    @"mediumMatchesComponentHeight": @(mediumMatchesComponentHeight),
    @"smallMatchesComponentHeight": @(smallMatchesComponentHeight),
    @"teamMatchesStatsComponentHeight": @(teamMatchesStatsComponentHeight),
    @"teamBoardComponentHeight": @(teamBoardComponentHeight),
    @"venueComponentHeight": @(venueComponentHeight),
    @"watchComponentHeight": @(watchComponentHeight),
    @"squadComponentHeight": @(squadComponentHeight)
  };
}

@end
