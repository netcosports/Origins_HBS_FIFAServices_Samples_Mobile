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
  if ([json objectForKey:@"teamId"] != nil) {
    NSString *teamId = [RCTConvert NSString:json[@"teamId"]];
    [view setupOneTeamIdWithTeamId:teamId];
  } else if ([json objectForKey:@"teamId1"] != nil && [json objectForKey:@"teamId2"] != nil) {
    NSString *teamId1 = [RCTConvert NSString:json[@"teamId1"]];
    NSString *teamId2 = [RCTConvert NSString:json[@"teamId2"]];
    [view setupTwoTeamIdsWithTeam1Id:teamId1 team2Id:teamId2];
  } else {
    [view setupNoTeams];
  }
}

@end
