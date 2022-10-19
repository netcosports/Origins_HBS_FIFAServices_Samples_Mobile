//
//  OnRewindModule.m
//  react_native
//
//  Created by Sergei Mikhan on 23.09.22.
//

#import <React/RCTBridgeModule.h>
#import <React/RCTBridgeMethod.h>

#import <OnRewindSDK/OnRewindSDK-Swift.h>

#import "AppDelegate.h"

@interface OnRewindModule : NSObject <RCTBridgeModule>

@end

@implementation OnRewindModule

- (instancetype)init {
  
  [OnRewind
   setWithBaseUrl:@"https://dev-hbs-stats-provider.origins-digital.com/api/hbs/"
   akamaiPrivateKey:@"0df73252ceaf17d78589371d5b8d1bbb"
   accountKey:@"uZknQc_1h"
   competitionId:@"fu17wwc"
   seasonId:@"2022"
  ];

  return [super init];
}

RCT_EXPORT_MODULE(OnRewind);

RCT_EXPORT_METHOD(presentPlayer:(NSString *)matchId streamUrl:(NSString *)streamUrl)
{
  dispatch_async(dispatch_get_main_queue(), ^{
    UIViewController* controller =
      [[(AppDelegate*)[ [UIApplication sharedApplication] delegate] window] rootViewController];
    // FIXME: test value
    //NSString* matchId = @"134080";
    [OnRewind presentPlayerWithMatchId:matchId fromPresentingViewController:controller preferredStreamLanguage:@"en"];
  });
}

+ (BOOL)requiresMainQueueSetup {
  return YES;
}

@end
