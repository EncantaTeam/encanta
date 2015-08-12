Encanta iOS SDK Samples
=======================
Example applications that use the Encanta iOS SDK

This repository is meant to provide some examples for you to better understand
the features of the Encanta iOS SDK. Feel free to copy and
modify the demo app source code for your own projects. Please consider sharing
your modifications with us, especially if they might benefit other developers
using the Encanta iOS SDK.

Create an Account
---------------------------
[Sign in, or create an account](http://app.getencanta.com/signin) - it's free!

Create a new app and grab the application token.

In the AppDelegate, replace the placeholder for the App token with the value for your app:

###### Objective-C:
```objc
[Encanta sharedInstanceWithAppToken:ENCANTA_APP_TOKEN];
```
###### Swift:
```swift
Encanta.sharedInstanceWithAppToken(ENCANTA_APP_TOKEN)
```

Get the SDK
---------------------------
[Download][encanta-ios-sdk] the iOS SDK, and add Encanta.framework and Encanta.bundle to the demo project.

Add Dependencies
---------------------------
Following the instructions [here](https://github.com/AFNetworking/AFNetworking) to add AFNetworking to the demo project.

Getting Sample Code Updates
---------------------------

This README and the sample applications are maintained separately from
releases of the [Encanta iOS SDK][encanta-ios-sdk]. To get the latest
updates to these example applications and accompanying documentation, be sure
to clone the sample repository itself:
https://github.com/EncantaTeam/encanta-ios-samples

[encanta-ios-samples]: https://github.com/EncantaTeam/encanta-ios-samples/
[encanta-ios-sdk]: http://app.getencanta.com
