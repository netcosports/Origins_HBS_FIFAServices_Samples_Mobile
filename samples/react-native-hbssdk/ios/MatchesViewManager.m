//
//  LargeMatchesViewManager.m
//  react-native-hbssdk
//
//  Created by Sergei Mikhan on 13.07.22.
//

#import <React/RCTViewManager.h>
#import <HBSSDK/HBSSDK-Swift.h>

@interface BaseMatchesViewManager: RCTViewManager

@end

@implementation BaseMatchesViewManager

+ (BOOL)requiresMainQueueSetup {
  return true;
}



@end

@interface LargeMatchesViewManager: BaseMatchesViewManager

@end

@implementation LargeMatchesViewManager

RCT_EXPORT_MODULE(LargeMatches);

- (UIView *) view {
  UIView<BigMatchesWidget, MatchesWidget> *view = [Matches bigWidget];
  return view;
}

RCT_CUSTOM_VIEW_PROPERTY(data, String, UIView<MatchesWidget>)
{
  if ([json objectForKey:@"groupId"] != nil) {
    NSString* groupId = [RCTConvert NSString:json[@"groupId"]];
    [view setGroupIdWithGroupId:groupId];
  } else if ([json objectForKey:@"teamId"] != nil) {
    NSString* teamId = [RCTConvert NSString:json[@"teamId"]];
    [view setTeamIdWithTeamId:teamId];
  } else {
    NSString* roundId = [RCTConvert NSString:json[@"roundId"]];
    [view setRoundIdWithRoundId:roundId];
  }
}

@end

@interface MediumMatchesViewManager: BaseMatchesViewManager

@end

@implementation MediumMatchesViewManager

RCT_EXPORT_MODULE(MediumMatches);

- (UIView *) view {
  UIView<MatchesWidget> *view = [Matches mediumWidget];
  return view;
}


RCT_CUSTOM_VIEW_PROPERTY(data, String, UIView<MatchesWidget>)
{
  if ([json objectForKey:@"groupId"] != nil) {
    NSString* groupId = [RCTConvert NSString:json[@"groupId"]];
    [view setGroupIdWithGroupId:groupId];
  } else if ([json objectForKey:@"teamId"] != nil) {
    NSString* teamId = [RCTConvert NSString:json[@"teamId"]];
    [view setTeamIdWithTeamId:teamId];
  } else {
    NSString* roundId = [RCTConvert NSString:json[@"roundId"]];
    [view setRoundIdWithRoundId:roundId];
  }
}
@end

@interface SmallMatchesViewManager: BaseMatchesViewManager

@end

@implementation SmallMatchesViewManager

RCT_EXPORT_MODULE(SmallMatches);

- (UIView *) view {
  UIView<MatchesWidget> *view = [Matches smallWidget];
  return view;
}

RCT_CUSTOM_VIEW_PROPERTY(data, String, UIView<MatchesWidget>)
{
  if ([json objectForKey:@"groupId"] != nil) {
    NSString* groupId = [RCTConvert NSString:json[@"groupId"]];
    [view setGroupIdWithGroupId:groupId];
  } else if ([json objectForKey:@"teamId"] != nil) {
    NSString* teamId = [RCTConvert NSString:json[@"teamId"]];
    [view setTeamIdWithTeamId:teamId];
  } else {
    NSString* roundId = [RCTConvert NSString:json[@"roundId"]];
    [view setRoundIdWithRoundId:roundId];
  }
}

@end
