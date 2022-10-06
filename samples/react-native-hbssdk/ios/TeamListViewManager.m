//
//  FavoritesViewManager.m
//  react-native-hbssdk
//
//  Created by Sergei Mikhan on 12.07.22.
//


#import <React/RCTViewManager.h>
#import <HBSSDK/HBSSDK-Swift.h>

@interface TeamListViewManager: RCTViewManager

@end

@implementation TeamListViewManager

RCT_EXPORT_MODULE(Favorites);

- (UIView *) view {
  UIView<TeamListWidget> *view = [Teams widget];
  return view;
}

+ (BOOL)requiresMainQueueSetup {
  return true;
}

@end
