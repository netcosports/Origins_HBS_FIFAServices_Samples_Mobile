//
//  TeamBoardViewManager.m
//  react-native-hbssdk
//
//  Created by Sergei Mikhan on 13.07.22.
//

#import <React/RCTViewManager.h>
#import <HBSSDK/HBSSDK-Swift.h>

@interface TeamBoardViewManager: RCTViewManager

@end

@implementation TeamBoardViewManager

RCT_EXPORT_MODULE(TeamBoard);

- (UIView *) view {
  UIView<TeamBoardWidget> *view = [TeamBoard widget];
  return view;
}

RCT_CUSTOM_VIEW_PROPERTY(teamId, NSString, UIView<TeamBoardWidget>)
{
  NSString *teamId = [RCTConvert NSString:json];
  [view setupTeamIdWithTeamId:teamId];
}

+ (BOOL)requiresMainQueueSetup {
  return true;
}

@end
