//
//  LineupViewManager.m
//  react-native-hbssdk
//
//  Created by Denis Shikunets on 9/15/22.
//

#import <React/RCTViewManager.h>
#import <HBSSDK/HBSSDK-Swift.h>

@interface SquadViewManager: RCTViewManager

@end

@implementation SquadViewManager

RCT_EXPORT_MODULE(Squad);

- (UIView *) view {
  UIView<SquadWidget> *view = [Teams squadWidget];
  return view;
}

+ (BOOL)requiresMainQueueSetup {
  return true;
}

RCT_CUSTOM_VIEW_PROPERTY(data, String, UIView<SquadWidget>)
{
  if ([json objectForKey:@"teamId"] != nil) {
    NSString *teamId = [RCTConvert NSString:json[@"teamId"]];
    [view setupTeamIdWithTeamId:teamId];
  }
}

@end
