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

RCT_CUSTOM_VIEW_PROPERTY(data, String, UIView<WatchWidget>)
{
  if ([json objectForKey:@"groupId"] != nil) {
    NSString* groupId = [RCTConvert NSString:json[@"groupId"]];
    [view setupGroupIdWithGroupId:groupId];
  } else if ([json objectForKey:@"teamId"] != nil) {
    NSString* teamId = [RCTConvert NSString:json[@"teamId"]];
    [view setupTeamIdWithTeamId:teamId];
  } else if ([json objectForKey:@"roundId"] != nil) {
    NSString* roundId = [RCTConvert NSString:json[@"roundId"]];
    [view setupRoundIdWithRoundId:roundId];
  } else if ([json objectForKey:@"matchId"] != nil) {
    NSString* matchId = [RCTConvert NSString:json[@"matchId"]];
    [view setupMatchIdWithMatchId:matchId];
  }
}

+ (BOOL)requiresMainQueueSetup {
  return true;
}

@end
