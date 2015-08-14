Encanta Android SDK Samples
=======================
Example applications that use the Encanta Android SDK

This repository is meant to provide some examples for you to better understand the features of the Encanta Android SDK. Feel free to copy and modify the demo app source code for your own projects. Please consider sharing your modifications with us, especially if they might benefit other developers using the Encanta Android SDK.

In addition to the sample applications, this project also contains the current version of the [Encanta SDK for Android][encanta-android-sdk-root] as well as the [Encanta Developer's Guide][encanta-android-dev-guide] and the [Encanta Customizable Styles Guide][encanta-android-styles-guide].

Create an Account
---------------------------
[Sign in, or create an account](http://app.getencanta.com/signin) - it's free!

Create a new app and grab the application token.

In the EncantaDemoApplication.java, replace the placeholder for the App token with the String value for your app as specified in the web portal:

```
Encanta.init(this, "ENCANTA_APP_TOKEN”, onMessageReceivedListener, chatToggledListener);
```

Get the SDK
---------------------------
[Download][encanta-android-sdk] the Android SDK, and add the .aar to the /libs/ directory of the demo project.  

Getting Sample Code Updates
---------------------------

This README and the sample applications are maintained separately from
releases of the [Encanta Android SDK][encanta-android-sdk]. To get the latest
updates to these example applications and accompanying documentation, be sure
to clone the sample repository itself from [here][encanta-android-sdk]

[encanta-android-samples]: https://github.com/EncantaTeam/encanta/tree/master/Android/Samples
[encanta-android-sdk-root]: http://github.com/EncantaTeam/encanta/tree/master/Android/SDK
[encanta-android-sdk]: http://github.com/EncantaTeam/encanta/tree/master/Android/SDK/encanta-appcompatRetrofit1.9.aar
[encanta-android-dev-guide]: http://github.com/EncantaTeam/encanta/tree/master/Android/SDK/Encanta%20Developer's%20Guide%20-%20Android.pdf
[encanta-android-styles-guide]: http://github.com/EncantaTeam/encanta/tree/master/Android/SDK/Customizable%20Styles.pdf
