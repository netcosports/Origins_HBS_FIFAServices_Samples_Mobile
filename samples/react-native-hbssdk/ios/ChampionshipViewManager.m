//
//  ChampionshipViewManager.m
//  react-native-hbssdk
//
//  Created by Sergei Mikhan on 11.07.22.
//

#import <React/RCTViewManager.h>
#import <HBSSDK/HBSSDK-Swift.h>

@interface ChampionshipViewManager: RCTViewManager

@property (nonatomic, copy) RCTBubblingEventBlock onOpenMatchDetails;
@property (weak, nonatomic) UIView<ChampionshipWidget> *currentView;
@end

@implementation ChampionshipViewManager

RCT_EXPORT_MODULE(Championship);

- (UIView *) view {
  UIView<ChampionshipWidget> *view = [Championship widget];
  self.currentView = view;
  return view;
}

+ (BOOL)requiresMainQueueSetup {
  return true;
}

RCT_EXPORT_VIEW_PROPERTY(onOpenMatchDetails, RCTBubblingEventBlock)

- (void)setOnOpenMatchDetails:(RCTBubblingEventBlock)onOpenMatchDetails {
  _onOpenMatchDetails = onOpenMatchDetails;

  [self view];
  __weak typeof(self) weakSelf = self;
  self.currentView.openMatchDetailsBlock = ^(NSString * _Nonnull match) {
    weakSelf.onOpenMatchDetails(@{ @"matchId": match });
  };
}

@end
