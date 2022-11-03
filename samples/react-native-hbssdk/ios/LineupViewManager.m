//
//  LineupViewManager.m
//  react-native-hbssdk
//
//  Created by Denis Shikunets on 9/15/22.
//

#import <React/RCTViewManager.h>
#import <HBSSDK/HBSSDK-Swift.h>

@interface LineupViewManager: RCTViewManager

@end

@implementation LineupViewManager

RCT_EXPORT_MODULE(Lineup);

- (UIView *) view {
  UIView<LineupWidget> *view = [MatchCenter lineupWidget];
  return view;
}

+ (BOOL)requiresMainQueueSetup {
  return true;
}

RCT_CUSTOM_VIEW_PROPERTY(data, String, UIView<LineupWidget>)
{
  if ([json objectForKey:@"matchId"] != nil) {
    NSString *matchId = [RCTConvert NSString:json[@"matchId"]];
    [view setupMatchIdWithMatchId:matchId];
  }
}

@end
