//
//  WatchViewManager.m
//  react-native-hbssdk
//
//  Created by Sergei Mikhan on 13.07.22.
//

#import <React/RCTViewManager.h>
#import <HBSSDK/HBSSDK-Swift.h>

@interface WatchViewManager: RCTViewManager

@end

@implementation WatchViewManager

RCT_EXPORT_MODULE(Watch);

- (UIView *) view {
  UIView<WatchWidget> *view = [Watch widget];
  return view;
}

RCT_CUSTOM_VIEW_PROPERTY(teamId, NSString, UIView<WatchWidget>)
{
  NSString *teamId = [RCTConvert NSString:json];
  [view setupTeamIdWithTeamId:teamId];
}

+ (BOOL)requiresMainQueueSetup {
  return true;
}

@end
