//
//  MatchCenterViewManager.m
//  react-native-hbssdk
//
//  Created by Sergei Mikhan on 12.07.22.
//

#import <React/RCTViewManager.h>
#import <HBSSDK/HBSSDK-Swift.h>

@interface MatchCenterViewManager: RCTViewManager

@end

@implementation MatchCenterViewManager

RCT_EXPORT_MODULE(MatchCenter);

- (UIView *) view {
  UIView<MatchCenterWidget> *view = [MatchCenter widget];
  return view;
}

+ (BOOL)requiresMainQueueSetup {
  return true;
}

RCT_CUSTOM_VIEW_PROPERTY(matchId, String, UIView<MatchCenterWidget>)
{
  NSString *matchId = [RCTConvert NSString:json];
  [view setupMatchWith:matchId];
}

@end

