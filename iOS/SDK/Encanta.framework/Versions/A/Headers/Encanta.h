/*
 * Copyright (c) Citrix Systems, Inc.
 * All Rights Reserved Worldwide.
 *
 * THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO CITRIX SYSTEMS, INC.
 * AND CONSTITUTES A VALUABLE TRADE SECRET.  Any unauthorized use,
 * reproduction, modification, or disclosure of this program is
 * strictly prohibited.  Any use of this program by an authorized
 * licensee is strictly subject to the terms and conditions,
 * including confidentiality obligations, set forth in the applicable
 * License and Co-Branding Agreement between Citrix Systems, Inc. and
 * the licensee.
 */

#import <UIKit/UIKit.h>

/**
 *  Notification sent when local user sends an Encanta chat message
 */
extern NSString* const kEncantaChatSentNotification;

/**
 *  Notification sent when new Encanta chat message received from web portal
 */
extern NSString* const kEncantaChatReceivedNotification;


/**
 *  Logging levels
 */
typedef NS_ENUM(NSInteger, ENLogLevel){
    /**
     *  no logging
     */
    ENLogLevelNone = 0,
    /**
     *  Logs errors only
     */
    ENLogLevelError,
    /**
     *  Logs Errors and Warnings
     */
    ENLogLevelWarn,
    /**
     *  Logs everything
     */
    ENLogLevelVerbose
};


@interface Encanta : NSObject

/**
 *  Version of Encanta framework
 */
@property (nonatomic, readonly) NSString* encantaVersion;

/**
 *  Set of chat messages
 */
@property (nonatomic, readonly) NSSet* messages;                     // KVO
/**
 *  Number of unread chat messages
 */
@property (nonatomic, readonly) NSUInteger unreadCount;              // KVO
/**
 *  Is live chat enabled (controlled via web portal)
 */
@property (nonatomic, readonly) BOOL chatEnabled;                    // KVO

///////////////////////
// UI customization
///////////////////////

/**
 *  Background color of messaging view
 */
@property (nonatomic, copy) UIColor* viewBackgroundColor;

/**
 *  Color of chat message bubbles
 */
@property (nonatomic, copy) UIColor* incomingMessageBubbleColor;
@property (nonatomic, copy) UIColor* outgoingMessageBubbleColor;

/**
 *  Color of chat message text
 */
@property (nonatomic, copy) UIColor* incomingMessageTextColor;
@property (nonatomic, copy) UIColor* outgoingMessageTextColor;

/**
 *  Chat text font
 */
@property (nonatomic, copy) UIFont* messageTextFont;

/**
 *  Color of chat message timestamp text
 */
@property (nonatomic, copy) UIColor* incomingTimestampTextColor;
@property (nonatomic, copy) UIColor* outgoingTimestampTextColor;

/**
 *  Timestamp text font
 */
@property (nonatomic, copy) UIFont* timestampTextFont;

/**
 *  Set to YES to tell Encanta UI components to support Dymamic Type
 */
@property (nonatomic, assign) BOOL usesDynamicType;

/**
 *  Text to display in messaging view when view is displayed.
 *  Text is shown for 10 seconds and then automatically disappears.
 */
@property (nonatomic, copy) NSString* welcomeBannerText;

/**
 *  Sets the logging level internal to the Encanta framework
 *
 *  @param level See ENLogLevel enumeration
 */
-(void)setLogLevel:(ENLogLevel)level;

/**
 *  Creates the singleton instance
 *
 *  @param appToken  application token, created in Encanta web portal
 *
 *  @return singleton instance of the API object
 */
+ (Encanta*)sharedInstanceWithAppToken:(NSString*) appToken;


/**
 *  Returns the previously instantiated singleton instance of the API.
 *
 *  @return singleton instance of the API object
 */
+ (Encanta*)sharedInstance;

/**
 *  Register a unique user on this device with Encanta
 *
 *  @param userId       a unique identifier for the local user
 *  @param name         the user's name (optional)
 *  @param successBlock block called if registration is successful
 *  @param failureBlock block called if registration fails
 */
- (void) registerUserWithUserId:(NSString *)userId userName:(NSString*) name
                      onSuccess:(void (^)())successBlock
                      onFailure:(void (^)(NSError*))failureBlock;

/**
 *  Provide name of local user to Encanta. Allow chat conversations to be
 *  labeled with customer's name.
 *
 *  @param name local user's name
 */
- (void) setUserName:(NSString*) name;


/**
 *  Register this device to receive push notifications from the Encanta server
 *
 *  @param deviceToken push notification device token received from the OS
 */
- (void) registerForPushNotificationsWithDeviceToken:(NSData*) deviceToken;

/**
 *  Set a custom property, which will be displayed in the web portal
 *
 *  @param propertyName property name
 *  @param value        property value
 *                      supported types: NSString, NSNumber, NSDate
 */
- (void) setPropertyWithName:(NSString*) propertyName value:(id) value;

/**
 *  Increments a numeric property with the specified name. If a property with
 *  this name does not yet exist, it is created and given a value of 1.
 *
 *  @param propertyName property name
 */
- (void) incrementPropertyWithName:(NSString*) propertyName;

/**
 *  Returns an instance of the Encanta messaging view controller
 *
 *  @return Encanta messaging view controller
 */
- (UIViewController*) messageViewController;

/**
 *  Indicates if the Encanta messaging view controller is currently visible
 *
 *  @return YES if Encanta messaging view controller is currently visible
 */
- (BOOL) messageViewControllerIsVisible;

@end

// Convenience categores for showing the Encanta messaging view
@interface UIViewController (Encanta)

- (void) presentEncantaViewControllerWithTitle:(NSString*) viewTitle
                                      animated:(BOOL)flag
                                    completion:(void (^)())completion;

@end

@interface UINavigationController (Encanta)

- (void) pushEncantaViewControllerWithTitle:(NSString*) viewTitle
                                   animated:(BOOL)flag;

@end
