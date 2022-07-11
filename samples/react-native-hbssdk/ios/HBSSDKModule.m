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
  CGFloat teamMatchesComponentHeight = [Stats teamMatchesSizeFor: CGSizeMake(100.0, 0.0)].height;
  CGFloat topPlayerStatsComponentHeight = [Stats topPlayersSizeFor: CGSizeMake(100.0, 0.0)].height;
  CGFloat videosComponentHeight = [Videos sizeFor: CGSizeMake(100.0, 0.0)].height;

  return @{
    @"teamMatchesComponentHeight": @(teamMatchesComponentHeight),
    @"topPlayerStatsComponentHeight": @(topPlayerStatsComponentHeight),
    @"videosComponentHeight": @(videosComponentHeight)
  };
}

@end
