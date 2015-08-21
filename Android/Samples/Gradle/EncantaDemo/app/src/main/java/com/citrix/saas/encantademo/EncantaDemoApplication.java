/*
 * EncantaDemoApplication.java
 *
 * Created by Cory on 8/10/15.
 * Copyright (c) 2015 Citrix. All rights reserved.
 *
 */
package com.citrix.saas.encantademo;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.citrix.saas.encanta.Encanta;
import com.citrix.saas.encanta.listeners.OnChatEnabledToggled;
import com.citrix.saas.encanta.listeners.OnNewMessageReceived;

public class EncantaDemoApplication extends Application {

    private static Context appContext;
    private static Activity currentActivity;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        Encanta.setLogLevel(Log.DEBUG);
        Encanta.init(this, "<ENCANTA_APP_TOKEN>", onMessageReceivedListener, chatToggledListener);
//        Encanta.setUserId("id-for-a-unique-person");
        Encanta.setPromptForUserName(true);
    }

    OnChatEnabledToggled chatToggledListener = new OnChatEnabledToggled() {
        @Override
        public void onChatToggled(final boolean enabled) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            getCurrentActivity().invalidateOptionsMenu();
                            // Handle event when real-time chat is enabled or disabled through the web console
                            Toast.makeText(getAppContext(), "Encanta was set to be " + (enabled ? "on" : "off") + "!  Toggle the option for the user to initiate a support chat, if desired", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            };
            new Thread(runnable).start();
        }

        @Override
        public void onEncantaInitialized(final boolean chatEnabled) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            getCurrentActivity().invalidateOptionsMenu();
                            // Handle event when Encanta.init() finishes and the current state of the chatEnabled flag is sent to your app
                            Toast.makeText(getAppContext(), "On initialization, Encanta was set to be " + (chatEnabled ? "on" : "off"), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            };
            new Thread(runnable).start();
        }
    };

    private Handler handler = new Handler();

    OnNewMessageReceived onMessageReceivedListener = new OnNewMessageReceived() {
        @Override
        public void receivedMessage() {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getAppContext(), "One or more new Encanta messages are pending viewing!", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            };
            new Thread(runnable).start();
        }

        @Override
        public void receivedMessage(final String message) {

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getAppContext(), "A message was just received through Encanta: '" + message + "'!  Do something to notify the user if desired", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            };
            new Thread(runnable).start();
        }
    };

    public static Context getAppContext() {
        return appContext;
    }

    public static Activity getCurrentActivity() {
        return currentActivity;
    }

    public static void setCurrentActivity(Activity curActivity) {
        currentActivity = curActivity;
    }

}
