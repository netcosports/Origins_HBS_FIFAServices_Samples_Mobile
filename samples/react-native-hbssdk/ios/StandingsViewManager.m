//
//  StandingsViewManager.m
//  react-native-hbssdk
//
//  Created by Sergei Mikhan on 11.07.22.
//

#import <React/RCTViewManager.h>
#import <HBSSDK/HBSSDK-Swift.h>

@interface StandingsViewManager: RCTViewManager

@end

@implementation StandingsViewManager

RCT_EXPORT_MODULE(Standings);

- (UIView *) view {
  UIView<StandingsWidget> *view = [Standings widget];
  return view;
}

+ (BOOL)requiresMainQueueSetup {
  return true;
}

RCT_CUSTOM_VIEW_PROPERTY(data, String, UIView<StandingsWidget>)
{
  BOOL isExpanded = [RCTConvert BOOL:json[@"isExpanded"]];
  NSString *groupId = nil;
  if ([json objectForKey:@"groupId"] != nil) {
    groupId = [RCTConvert NSString:json[@"groupId"]];
    [view setupSingleGroupWidgetParamsWithGroupId:groupId isExpanded:isExpanded];
  } else {
    [view setupAllGroupsWidgetParamsWithIsExpanded:isExpanded];
  }
}

@end
