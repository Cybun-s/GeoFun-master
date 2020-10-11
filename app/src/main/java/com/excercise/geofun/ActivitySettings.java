package com.excercise.geofun;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class ActivitySettings extends AppCompatActivity implements View.OnClickListener {

    private static final int LOCATION_ALLOWED_CODE = 1;

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
        if (ContextCompat.checkSelfPermission(ActivitySettings.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(ActivitySettings.this, "Standortzugriff wurde bereits erteilt", Toast.LENGTH_SHORT).show();
        } else {
            getLocationPermission();
        }
    }


    private void getLocationPermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION)) {

            new AlertDialog.Builder(this)
                    .setTitle("Standortfreigabe")
                    .setMessage("Um alle Funktionen von GeoFun nutzen zu können, geben Sie den Standort frei.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(ActivitySettings.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_ALLOWED_CODE);
                        }
                    })
                    .setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_ALLOWED_CODE);
        }

    }


    public void changeNotificationSetting(){
    }

}

