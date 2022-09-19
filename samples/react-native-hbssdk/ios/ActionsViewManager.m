//
//  LineupViewManager.m
//  react-native-hbssdk
//
//  Created by Denis Shikunets on 9/15/22.
//

#import <React/RCTViewManager.h>
#import <HBSSDK/HBSSDK-Swift.h>

@interface ActionsViewManager: RCTViewManager

@end

@implementation ActionsViewManager

RCT_EXPORT_MODULE(Actions);

- (UIView *) view {
  UIView<ActionsWidget> *view = [MatchCenter actionsWidget];
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
