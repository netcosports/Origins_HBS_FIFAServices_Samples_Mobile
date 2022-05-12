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

RCT_EXPORT_MODULE(HBSSDK);

-(NSDictionary *)constantsToExport {
  CGFloat teamMatchesComponentHeight = [[StatsObjc new] teamMatchesSizeFor: CGSizeMake(100.0, 0.0)].height;
  CGFloat topPlayerStatsComponentHeight = [[StatsObjc new] topPlayersSizeFor: CGSizeMake(100.0, 0.0)].height;
  CGFloat videosComponentHeight = [[VideosObjc new] sizeFor: CGSizeMake(100.0, 0.0)].height;

  return @{
    @"teamMatchesComponentHeight": @(teamMatchesComponentHeight),
    @"topPlayerStatsComponentHeight": @(topPlayerStatsComponentHeight),
    @"videosComponentHeight": @(videosComponentHeight)
  };
}

@end
