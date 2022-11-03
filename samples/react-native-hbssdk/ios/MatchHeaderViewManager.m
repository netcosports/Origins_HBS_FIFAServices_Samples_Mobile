//
//  LineupViewManager.m
//  react-native-hbssdk
//
//  Created by Denis Shikunets on 9/15/22.
//

#import <React/RCTViewManager.h>
#import <HBSSDK/HBSSDK-Swift.h>

@interface MatchHeaderViewManager: RCTViewManager

@end

@implementation MatchHeaderViewManager

RCT_EXPORT_MODULE(MatchHeader);

- (UIView *) view {
  UIView<MatchCenterHeaderWidget> *view = [MatchCenter headerWidget];
  return view;
}

+ (BOOL)requiresMainQueueSetup {
  return true;
}

RCT_CUSTOM_VIEW_PROPERTY(data, String, UIView<MatchCenterHeaderWidget>)
{
  if ([json objectForKey:@"matchId"] != nil) {
    NSString *matchId = [RCTConvert NSString:json[@"matchId"]];
    [view setMatchIdWithMatchId:matchId];
  }
}

@end


@interface ExpandedMatchHeaderViewManager: RCTViewManager

@end

@implementation ExpandedMatchHeaderViewManager

RCT_EXPORT_MODULE(ExpandedMatchHeader);

- (UIView *) view {
  UIView<MatchCenterHeaderWidget> *view = [MatchCenter expandedHeaderWidget];
  return view;
}

+ (BOOL)requiresMainQueueSetup {
  return true;
}

RCT_CUSTOM_VIEW_PROPERTY(data, String, UIView<MatchCenterHeaderWidget>)
{
  if ([json objectForKey:@"matchId"] != nil) {
    NSString *matchId = [RCTConvert NSString:json[@"matchId"]];
    [view setMatchIdWithMatchId:matchId];
  }
}

@end
