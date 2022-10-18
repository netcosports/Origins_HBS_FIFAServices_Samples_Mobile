//
//  HBSEventEmitter.m
//  react-native-hbssdk
//
//  Created by Sergei Mikhan on 17.10.22.
//

#import <React/RCTBridgeModule.h>
#import <React/RCTEventEmitter.h>

#import <HBSSDK/HBSSDK-Swift.h>

@interface HBSEventEmitter : RCTEventEmitter <RCTBridgeModule>
@end

@implementation HBSEventEmitter
{
  bool hasListeners;
}

RCT_EXPORT_MODULE(HBSEventEmitter);

- (instancetype)init {
  return [super init];
}

- (NSArray<NSString *> *)supportedEvents {
  return @[@"presentVideoPlayer"];
}

+ (BOOL)requiresMainQueueSetup {
  return YES;
}

-(void)startObserving {
  hasListeners = YES;

  [Integration setPresentPlayerBlock:^(VideoPresentationContext * _Nonnull context) {
     [self sendEventWithName:@"presentVideoPlayer" body:@{
       @"matchId": context.eventId == nil ? @"" : context.eventId,
       @"videoUrl": context.videoURL.absoluteURL == nil ? @"" : context.videoURL.absoluteURL
     }];
  }];}

-(void)stopObserving {
    hasListeners = NO;
}

@end
