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

RCT_CUSTOM_VIEW_PROPERTY(data, String, UIView<TeamBoardWidget>)
{
  NSString *teamId = [json objectForKey:@"teamId"];
  BOOL allowChangeTeam = [RCTConvert BOOL:json[@"allowChangeTeam"]];

  [view setupTeamIdWithTeamId:teamId allowChangeTeam:allowChangeTeam];
}

+ (BOOL)requiresMainQueueSetup {
  return true;
}

@end
