package com.excercise.geofun;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class ActivitySettings extends AppCompatActivity implements View.OnClickListener {

    public static final String SHARED_PREFERENCES = "sharedPreferences";
    public static final String LOCATION_SWITCH = "switchLocation";
    public static final String NOTIFICATION_SWITCH = "switchNotifications";

    private static final int LOCATION_ALLOWED_CODE = 1;
    private Button settingsReturn;
    private Switch locationSwitch;
    private Switch notificationSwitch;
    private boolean switchOnOff;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        settingsReturn = findViewById(R.id.settings_back);
        locationSwitch = findViewById(R.id.locationSwitch);
        notificationSwitch = findViewById(R.id.notificationSwitch);

        settingsReturn.setOnClickListener(this);
        locationSwitch.setOnClickListener(this);
        notificationSwitch.setOnClickListener(this);
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
            saveState();
        }

        loadState();

    }

    public void changeNotificationSetting(){
    }

    private void saveState() {
        SharedPreferences sharedPreferences = getSharedPreferences(LOCATION_SWITCH, MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(LOCATION_SWITCH, locationSwitch.isChecked());
        edit.putBoolean(NOTIFICATION_SWITCH, notificationSwitch.isChecked());
        edit.apply();
        Toast.makeText(this, "Standort und Benachrichtigungen aktiviert", Toast.LENGTH_SHORT).show();
    }

    private void loadState() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        switchOnOff = sharedPreferences.getBoolean(LOCATION_SWITCH, false);
    }

    private void updateSwitches(){
        locationSwitch.setChecked(switchOnOff);
        notificationSwitch.setChecked(switchOnOff);

    }



}

