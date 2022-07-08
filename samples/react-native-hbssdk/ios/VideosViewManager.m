//
//  VideosViewManager.m
//  HBSWidgetsDemo
//
//  Created by Sergei Mikhan on 28.04.22.
//

#import <React/RCTViewManager.h>
#import <HBSSDK/HBSSDK-Swift.h>

@interface VideosViewManager: RCTViewManager

@end

@implementation VideosViewManager

RCT_EXPORT_MODULE(Videos);

- (UIView *) view {
  UIView<VideoWidget> *view = [Videos widget];
  return view;
}

//RCT_CUSTOM_VIEW_PROPERTY(statType, NSString, UIView<VideoWidget>)
//{
//  NSString *typeString = [RCTConvert NSString:json];
//  TopPlayerStatsWidgetType type = [typeString isEqualToString:@"goal"] ?
//    TopPlayerStatsWidgetTypeGoals :
//    TopPlayerStatsWidgetTypeAssists;
//
//  [view setupWidgetParamsWithStatsType:type isTransparent:false];
//}

+ (BOOL)requiresMainQueueSetup {
  return true;
}

- (NSDictionary *)constantsToExport {
  CGFloat height = [Videos sizeFor:CGSizeMake(100.0, 0.0)].height;
  return @{ @"ComponentHeight": @(height) };
}

@end

