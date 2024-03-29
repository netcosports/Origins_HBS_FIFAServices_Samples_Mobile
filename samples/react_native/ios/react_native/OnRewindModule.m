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

  return [super init];
}

RCT_EXPORT_MODULE(OnRewind);

+ (UIViewController*) topMostController
{
    UIViewController *topController = [UIApplication sharedApplication].keyWindow.rootViewController;

    while (topController.presentedViewController) {
        topController = topController.presentedViewController;
    }

    return topController;
}

RCT_EXPORT_METHOD(presentPlayer:(NSString *)matchId streamUrl:(NSString *)streamUrl)
{
  dispatch_async(dispatch_get_main_queue(), ^{
    UIViewController* controller =
      [OnRewindModule topMostController];
    // FIXME: test value
    //NSString* matchId = @"134080";
    [OnRewind presentPlayerWithMatchId:matchId fromPresentingViewController:controller preferredStreamLanguage:@"en"];
  });
}

+ (BOOL)requiresMainQueueSetup {
  return YES;
}

@end
