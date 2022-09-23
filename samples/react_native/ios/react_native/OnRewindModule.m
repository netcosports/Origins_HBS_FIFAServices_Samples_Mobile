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
  [OnRewind setWithBaseUrl:@"https://api-gateway.onrewind.tv/main-api"];
  return [super init];
}

RCT_EXPORT_MODULE(OnRewind);

RCT_EXPORT_METHOD(presentPlayer:(NSString *)matchId streamUrl:(NSString *)streamUrl)
{
  dispatch_async(dispatch_get_main_queue(), ^{
    UIViewController* controller =
      [[(AppDelegate*)[ [UIApplication sharedApplication] delegate] window] rootViewController];
    NSString * accountKey = @"SkH0O4D5H";
    // FIXME: test value
    NSString* matchId = @"78fbebc2-fc52-439e-81f4-8557bba62c1b";
    [OnRewind presentPlayerWithEventId:matchId//@"78fbebc2-fc52-439e-81f4-8557bba62c1b"
                            accountKey:accountKey
          fromPresentingViewController:controller];
  });
}

+ (BOOL)requiresMainQueueSetup {
  return YES;
}

@end
