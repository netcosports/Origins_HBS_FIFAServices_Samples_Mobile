//
//  VenueViewManager.m
//  react-native-hbssdk
//
//  Created by Sergei Mikhan on 13.07.22.
//

#import <React/RCTViewManager.h>
#import <HBSSDK/HBSSDK-Swift.h>

@interface VenueViewManager: RCTViewManager

@end

@implementation VenueViewManager

RCT_EXPORT_MODULE(Venue);

- (UIView *) view {
  UIView<VenueWidget> *view = [Venue widget];
  return view;
}

+ (BOOL)requiresMainQueueSetup {
  return true;
}

@end

