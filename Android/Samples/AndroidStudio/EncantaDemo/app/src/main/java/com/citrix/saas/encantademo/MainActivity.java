/*
 * MainActivity.java
 *
 * Created by Cory on 8/10/15.
 * Copyright (c) 2015 Citrix. All rights reserved.
 *
 */

package com.citrix.saas.encantademo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.citrix.saas.convoi.R;
import com.citrix.saas.encanta.Encanta;

public class MainActivity extends AppCompatActivity {

    Button customAction1;
    Button customAction2;
    EditText setNameText;
    Button setNameButton;
    Button isEnabled;
    Button getUnreadCount;
    Button getVersion;
    ToggleButton pauseNotifications;
    Button showEncanta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EncantaDemoApplication.setCurrentActivity(this);
        setContentView(R.layout.activity_main);
        customAction1 = (Button) findViewById(R.id.encanta_custom_action_1);
        customAction1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Encanta.setStringProperty("customAction1", "Performed");
                Encanta.setIntProperty("intProperty", 1);
                Toast.makeText(getApplicationContext(), "Setting property 'customAction1' to 'Performed'", Toast.LENGTH_LONG).show();
            }
        });

        customAction2 = (Button) findViewById(R.id.encanta_custom_action_2);
        customAction2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Encanta.incrementProperty("customAction2");
                Toast.makeText(getApplicationContext(), "Incrementing property 'customAction2' by 1", Toast.LENGTH_LONG).show();
            }
        });

        setNameText = (EditText) findViewById(R.id.encanta_set_name);
        setNameButton = (Button) findViewById(R.id.btn_encanta_set_name);
        setNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Encanta.setUserName(setNameText.getText().toString());
                Toast.makeText(getApplicationContext(), "Setting name for this user to be: " + setNameText.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });

        isEnabled = (Button) findViewById(R.id.encanta_is_enabled);
        isEnabled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Is Encanta enabled through the web console? " + (Encanta.isEnabled() ? "Yes" : "No"), Toast.LENGTH_LONG).show();
            }
        });

        pauseNotifications = (ToggleButton) findViewById(R.id.encanta_pause_notifications);
        pauseNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), (pauseNotifications.isChecked() ? "Pausing" : "Unpausing") + " custom notifications", Toast.LENGTH_LONG).show();
                    Encanta.pauseNotifications(pauseNotifications.isChecked());
            }
        });

        getUnreadCount = (Button) findViewById(R.id.encanta_get_unread_count);
        getUnreadCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Unread Message Count: " + Encanta.getUnreadMessageCount(), Toast.LENGTH_LONG).show();
            }
        });

        getVersion = (Button) findViewById(R.id.encanta_get_version);
        getVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Encanta version: " + Encanta.getEncantaVersion(), Toast.LENGTH_LONG).show();
            }
        });

        showEncanta = (Button) findViewById(R.id.encanta_show);
        showEncanta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Encanta.startSupportChatActivity(MainActivity.this);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem encantaSupportChatItem;
        encantaSupportChatItem = menu.findItem(R.id.action_support_chat);
        encantaSupportChatItem.setVisible(Encanta.isEnabled());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_support_chat) {
            Encanta.startSupportChatActivity(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
