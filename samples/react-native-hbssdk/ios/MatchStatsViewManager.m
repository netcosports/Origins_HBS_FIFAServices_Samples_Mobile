//
//  LineupViewManager.m
//  react-native-hbssdk
//
//  Created by Denis Shikunets on 9/15/22.
//

#import <React/RCTViewManager.h>
#import <HBSSDK/HBSSDK-Swift.h>


@interface MatchStatsViewManager: RCTViewManager

@end

@implementation MatchStatsViewManager

RCT_EXPORT_MODULE(MatchStats);

- (UIView *) view {
  UIView<MatchStatsWidget> *view = [MatchCenter matchStatsWidget];
  return view;
}

+ (BOOL)requiresMainQueueSetup {
  return true;
}

RCT_CUSTOM_VIEW_PROPERTY(data, String, UIView<MatchStatsWidget>)
{
  if ([json objectForKey:@"matchId"] != nil) {
    NSString *matchId = [RCTConvert NSString:json[@"matchId"]];
    [view setupMatchIdWithMatchId:matchId];
  }
}

@end
