//
//  HbsConfigurationModule.m
//  react_native
//
//  Created by Denis Shikunets on 11/1/22.
//

#import <React/RCTBridgeModule.h>
#import <React/RCTBridgeMethod.h>

#import <OnRewindSDK/OnRewindSDK-Swift.h>

#import "AppDelegate.h"
#import <HBSSDK/HBSSDK-Swift.h>

@interface HbsConfigurationModule : NSObject <RCTBridgeModule>

@end

@implementation HbsConfigurationModule

- (instancetype)init {
  return [super init];
}

RCT_EXPORT_MODULE(HbsConfiguration);

RCT_EXPORT_METHOD(updateConfiguration:(NSString *)competition season:(NSString *)season)
{
  
  NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
  [defaults setObject:competition forKey:PARAM_COMPETITION];
  [defaults setObject:season forKey:PARAM_SEASON];
  [defaults synchronize];

  [Integration setupCompetionConfigurationWithComptitionId:competition season:season];
}

+ (BOOL)requiresMainQueueSetup {
  return YES;
}

@end
