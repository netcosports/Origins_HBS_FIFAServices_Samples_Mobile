//
//  HeadToHeadViewManager.m
//  react-native-hbssdk
//
//  Created by Sergei Mikhan on 12.07.22.
//

#import <React/RCTViewManager.h>
#import <HBSSDK/HBSSDK-Swift.h>

@interface HeadToHeadViewManager: RCTViewManager

@end

@implementation HeadToHeadViewManager

RCT_EXPORT_MODULE(HeadToHead);

- (UIView *) view {
  UIView<HeadToHeadWidget> *view = [HeadToHead widget];
  return view;
}

+ (BOOL)requiresMainQueueSetup {
  return true;
}

RCT_CUSTOM_VIEW_PROPERTY(data, String, UIView<HeadToHeadWidget>)
{
  NSString *teamId1 = [RCTConvert NSString:json[@"teamId1"]];
  NSString *teamId2 = [RCTConvert NSString:json[@"teamId2"]];
  [view setupParamsWithTeam1Id:teamId1 team2Id:teamId2];
}

@end
