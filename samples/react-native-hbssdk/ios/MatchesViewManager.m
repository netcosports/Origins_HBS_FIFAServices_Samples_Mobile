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
  UIView<MatchesWidget> *view = [Matches largeWidget];
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
  } else if ([json objectForKey:@"roundId"] != nil) {
    NSString* roundId = [RCTConvert NSString:json[@"roundId"]];
    [view setRoundIdWithRoundId:roundId];
  } else if ([json objectForKey:@"matchId"] != nil) {
    NSString* matchId = [RCTConvert NSString:json[@"matchId"]];
    [view setMatchIdWithMatchId:matchId];
  }
}

@end

@interface ExpandedMatchesViewManager : BaseMatchesViewManager

@end

@implementation ExpandedMatchesViewManager

RCT_EXPORT_MODULE(ExpandedMatches);

- (UIView *) view {
  UIView<MatchesWidget, ExpandedMatchesWidget> *view = [Matches expandedWidget];
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
  } else if ([json objectForKey:@"roundId"] != nil) {
    NSString* roundId = [RCTConvert NSString:json[@"roundId"]];
    [view setRoundIdWithRoundId:roundId];
  } else if ([json objectForKey:@"matchId"] != nil) {
    NSString* matchId = [RCTConvert NSString:json[@"matchId"]];
    [view setMatchIdWithMatchId:matchId];
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
  } else if ([json objectForKey:@"roundId"] != nil) {
    NSString* roundId = [RCTConvert NSString:json[@"roundId"]];
    [view setRoundIdWithRoundId:roundId];
  } else if ([json objectForKey:@"matchId"] != nil) {
    NSString* matchId = [RCTConvert NSString:json[@"matchId"]];
    [view setMatchIdWithMatchId:matchId];
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
  } else if ([json objectForKey:@"roundId"] != nil) {
    NSString* roundId = [RCTConvert NSString:json[@"roundId"]];
    [view setRoundIdWithRoundId:roundId];
  } else if ([json objectForKey:@"matchId"] != nil) {
    NSString* matchId = [RCTConvert NSString:json[@"matchId"]];
    [view setMatchIdWithMatchId:matchId];
  }
}

@end
