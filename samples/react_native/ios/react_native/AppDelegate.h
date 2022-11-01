#import <React/RCTBridgeDelegate.h>
#import <UIKit/UIKit.h>

@interface AppDelegate : UIResponder <UIApplicationDelegate, RCTBridgeDelegate>

@property (nonatomic, strong) UIWindow *window;


@end


extern NSString* const PARAM_COMPETITION;
extern NSString* const PARAM_SEASON;
