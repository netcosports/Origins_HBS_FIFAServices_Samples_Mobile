//
//  FavoritesViewManager.m
//  react-native-hbssdk
//
//  Created by Sergei Mikhan on 12.07.22.
//


#import <React/RCTViewManager.h>
#import <HBSSDK/HBSSDK-Swift.h>

@interface FavoritesViewManager: RCTViewManager

@end

@implementation FavoritesViewManager

RCT_EXPORT_MODULE(Favorites);

- (UIView *) view {
  UIView<FavoriteWidget> *view = [Favorites widget];
  return view;
}

+ (BOOL)requiresMainQueueSetup {
  return true;
}

@end


