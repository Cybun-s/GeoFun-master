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
        returnToPreviousPage();

    }

    private void returnToPreviousPage(){
        Button settingsBackButton = findViewById(R.id.settings_back);
        settingsBackButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intentBack = new Intent(ActivitySettings.this, MainActivity.class);
        startActivity(intentBack);
    }

    }

