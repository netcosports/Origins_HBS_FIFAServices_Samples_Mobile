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

+ (BOOL)requiresMainQueueSetup {
  return true;
}

RCT_CUSTOM_VIEW_PROPERTY(data, String, UIView<VideoWidget>)
{
  NSString* category = [RCTConvert NSString:json[@"category"]];
  [view setupCategoryWithCategory:category subCategory:nil];
}

@end

