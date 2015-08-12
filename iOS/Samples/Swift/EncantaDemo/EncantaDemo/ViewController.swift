//
//  ViewController.swift
//  EncantaDemo
//
//  Created by Mike on 8/10/15.
//  Copyright (c) 2015 Citrix. All rights reserved.
//

import UIKit
import AVFoundation

class ViewController: UIViewController {

    @IBOutlet weak var chatEnableLabel: UILabel!
    @IBOutlet weak var unreadCountLabel: UILabel!
    @IBOutlet weak var nameField: UITextField!

    override func viewDidLoad() {
        super.viewDidLoad()

        self.unreadCountLabel.text = "Unread count \(Encanta.sharedInstance().unreadCount)"
        
        NSNotificationCenter.defaultCenter().addObserver(self, selector:"handleNewSupportChatNotification:",
                name:kEncantaChatReceivedNotification, object: nil)
        
        let chatEnabledText = Encanta.sharedInstance().chatEnabled ? "ON" : "OFF"
        self.chatEnableLabel.text = "Encanta live chat is \(chatEnabledText)"

        Encanta.sharedInstance().addObserver(self, forKeyPath: "unreadCount", options:.New, context:nil)
        Encanta.sharedInstance().addObserver(self, forKeyPath: "chatEnabled", options:.New, context:nil)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


    deinit {
        Encanta.sharedInstance().removeObserver(self, forKeyPath: "unreadCount", context: nil)
        Encanta.sharedInstance().removeObserver(self, forKeyPath: "chatEnabled", context: nil)
        NSNotificationCenter.defaultCenter().removeObserver(self)
    }

    
    @IBAction func onSetName() {
        Encanta.sharedInstance().setUserName(self.nameField.text)
    }
    
    @IBAction func showEncanta() {
        Encanta.sharedInstance().viewBackgroundColor          = UIColor.lightGrayColor()
        Encanta.sharedInstance().messageTextFont              = UIFont(name: "Courier", size: 16.0)
        Encanta.sharedInstance().timestampTextFont            = UIFont(name: "Times New Roman", size: 11.0)
        Encanta.sharedInstance().incomingMessageTextColor     = UIColor.whiteColor()
        Encanta.sharedInstance().outgoingMessageTextColor     = UIColor.whiteColor()
        Encanta.sharedInstance().incomingMessageBubbleColor   = UIColor.orangeColor()
        Encanta.sharedInstance().outgoingMessageBubbleColor   = UIColor.darkGrayColor()
        Encanta.sharedInstance().incomingTimestampTextColor   = UIColor.darkGrayColor()
        Encanta.sharedInstance().outgoingTimestampTextColor   = UIColor.lightGrayColor()
        
        self.navigationController!.pushEncantaViewControllerWithTitle("Encanta", animated:true)
    }
    
    override func observeValueForKeyPath(keyPath: String, ofObject object: AnyObject, change: [NSObject : AnyObject], context: UnsafeMutablePointer<Void>) {

        if keyPath == "chatEnabled" {
            let chatEnabledText = Encanta.sharedInstance().chatEnabled ? "ON" : "OFF"
            self.chatEnableLabel.text = "Encanta live chat is \(chatEnabledText)"
        }
        else if keyPath == "unreadCount" {
            self.unreadCountLabel.text = "Unread count \(Encanta.sharedInstance().unreadCount)"
        }
        else {
            super.observeValueForKeyPath(keyPath, ofObject: object, change: change, context: context)
        }
    }
    
    @objc func handleNewSupportChatNotification(notification: NSNotification){

        AudioServicesPlayAlertSound(SystemSoundID(kSystemSoundID_Vibrate))
        
        let message = "Encanta message received."
        self.navigationItem.prompt = message
        dispatch_after(dispatch_time(DISPATCH_TIME_NOW, Int64(2 * Double(NSEC_PER_SEC))), dispatch_get_main_queue()) {
            self.navigationItem.prompt = nil
        }
    }
}




