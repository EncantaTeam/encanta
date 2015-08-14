Encanta iOS SDK Samples
=======================
Example applications that use the Encanta iOS SDK

This repository is meant to provide some examples for you to better understand
the features of the Encanta iOS SDK. Feel free to copy and
modify the demo app source code for your own projects. Please consider sharing
your modifications with us, especially if they might benefit other developers
using the Encanta iOS SDK.

In addition to the sample applications, this project also contains the current version of the [Encanta SDK for iOS](https://github.com/EncantaTeam/encanta/tree/master/iOS/SDK) as well as the [Encanta Developer's Guide](https://github.com/EncantaTeam/encanta/blob/master/iOS/SDK/Encanta%20Developer's%20Guide%20-%20iOS.pdf).

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
