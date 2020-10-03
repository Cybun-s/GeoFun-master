package com.excercise.geofun;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ActivitySettings extends AppCompatActivity implements View.OnClickListener {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Button settingsBackButton = findViewById(R.id.settings_back);
        Button locationSetting = findViewById(R.id.locationSwitch);
        Button notificationSetting = findViewById(R.id.notificationSwitch);

        settingsBackButton.setOnClickListener(this);
        locationSetting.setOnClickListener(this);
        notificationSetting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.settings_back:
                returnToMainPage();
                break;
            case R.id.locationSwitch:
                changeLocationSetting();
                break;
            case R.id.notificationSwitch:
                changeNotificationSetting();
                break;
        }
    }

    public void returnToMainPage(){
        Intent intentBack = new Intent(ActivitySettings.this, MainActivity.class);
        startActivity(intentBack);
    }

    public void changeLocationSetting(){
    }

    public void changeNotificationSetting(){
    }

}

