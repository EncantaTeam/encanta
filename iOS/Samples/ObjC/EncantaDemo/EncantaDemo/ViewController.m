//
//  ViewController.m
//  EncantaDemo
//
//  Created by Mike on 8/10/15.
//  Copyright (c) 2015 Citrix. All rights reserved.
//

#import "ViewController.h"
#import <AVFoundation/AVFoundation.h>
#import <Encanta/Encanta.h>


@interface ViewController ()

@property (weak, nonatomic) IBOutlet UILabel *chatEnabledLabel;
@property (weak, nonatomic) IBOutlet UILabel *unreadCountLabel;
@property (weak, nonatomic) IBOutlet UITextField *nameField;

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.unreadCountLabel.text = [NSString stringWithFormat:@"Unread count: %lu", [Encanta sharedInstance].unreadCount];
    
    [[NSNotificationCenter defaultCenter] addObserver:self
                                             selector:@selector(handleNewSupportChatNotification:)
                                                 name:kEncantaChatReceivedNotification
                                               object:nil];
    
    self.chatEnabledLabel.text = [NSString stringWithFormat:@"Encanta live chat is %@",
                        [Encanta sharedInstance].chatEnabled ? @"ON" : @"OFF"];
    
    
    [[Encanta sharedInstance] addObserver:self
                               forKeyPath:@"chatEnabled"
                                  options:(NSKeyValueObservingOptionOld|NSKeyValueObservingOptionNew) context:NULL];
    [[Encanta sharedInstance] addObserver:self
                               forKeyPath:@"unreadCount"
                                  options:(NSKeyValueObservingOptionOld|NSKeyValueObservingOptionNew) context:NULL];

}

- (void) dealloc {
    [[Encanta sharedInstance] removeObserver:self forKeyPath:@"chatEnabled"];
    [[Encanta sharedInstance] removeObserver:self forKeyPath:@"unreadCount"];
    [[NSNotificationCenter defaultCenter] removeObserver:self];
}


- (IBAction)showEncanta:(id)sender {
    
    [Encanta sharedInstance].viewBackgroundColor        = [UIColor lightGrayColor];
    [Encanta sharedInstance].messageTextFont            = [UIFont fontWithName:@"Courier" size:16.0];
    [Encanta sharedInstance].timestampTextFont          = [UIFont fontWithName:@"Times New Roman" size:11.0];
    [Encanta sharedInstance].incomingMessageTextColor   = [UIColor whiteColor];
    [Encanta sharedInstance].outgoingMessageTextColor   = [UIColor whiteColor];
    
    [Encanta sharedInstance].incomingMessageBubbleColor = [UIColor orangeColor];
    [Encanta sharedInstance].outgoingMessageBubbleColor = [UIColor darkGrayColor];
    
    [Encanta sharedInstance].incomingTimestampTextColor = [UIColor darkGrayColor];
    [Encanta sharedInstance].outgoingTimestampTextColor = [UIColor lightGrayColor];

    [self.navigationController pushEncantaViewControllerWithTitle:@"Encanta" animated:YES];
}

- (IBAction)onSetName:(id)sender {

    [[Encanta sharedInstance] setUserName:self.nameField.text];
}


- (void) observeValueForKeyPath:(NSString *)keyPath
                       ofObject:(id)object
                         change:(NSDictionary *)change
                        context:(void *)context {
    
    
    if ([keyPath isEqualToString:@"chatEnabled"]) {
        self.chatEnabledLabel.text = [NSString stringWithFormat:@"Encanta live chat is %@",
                            [Encanta sharedInstance].chatEnabled ? @"ON" : @"OFF"];
    }
    else if ([keyPath isEqualToString:@"unreadCount"]) {
        self.unreadCountLabel.text = [NSString stringWithFormat:@"Unread count: %lu", [Encanta sharedInstance].unreadCount];
    }
    else {
        [super observeValueForKeyPath:keyPath ofObject:object change:change context:context];
    }
}



- (void) handleNewSupportChatNotification:(NSNotification*) notification {
    
    AudioServicesPlaySystemSound(kSystemSoundID_Vibrate);
    
    NSString *message = @"Encanta message received.";
    self.navigationItem.prompt = message;
    dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(2.0 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
        self.navigationItem.prompt = nil;
    });
}

@end
