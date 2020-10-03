package com.excercise.geofun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button infiniteMode = findViewById(R.id.mode_1_button);
        Button timeRushMode = findViewById(R.id.mode_2_button);
        Button interactiveMode = findViewById(R.id.mode_3_button);
        FloatingActionButton editSettings = findViewById(R.id.settings_button);

        infiniteMode.setOnClickListener(this);
        timeRushMode.setOnClickListener(this);
        interactiveMode.setOnClickListener(this);
        editSettings.setOnClickListener(this);

        fillDatabase();

    }


    @Override
                public void onClick(View v) {
                    switch (v.getId()){
                        case R.id.mode_1_button:
                            startInfiniteMode();
                            break;
                        case R.id.mode_2_button:
                            startTimeRushMode();
                            break;
                        case R.id.mode_3_button:
                            startInteractiveMode();
                            break;
                        case R.id.settings_button:
                            startSettings();
                            break;
                    }
                }




    private void startInfiniteMode() {
        Intent intentInfinite = new Intent(MainActivity.this, ActivityInfinite.class);
        startActivity(intentInfinite);
    }

    private void startTimeRushMode() {
        Intent intentTimeRush = new Intent(MainActivity.this, ActivityTimeRush.class);
        startActivity(intentTimeRush);
    }

    private void startInteractiveMode() {
        Intent intentInteractive = new Intent(MainActivity.this, ActivityInteractive.class);
        startActivity(intentInteractive);
    }

    private void startSettings() {
        Intent intentSettings = new Intent(MainActivity.this, ActivitySettings.class);
        startActivity(intentSettings);
    }

    public void fillDatabase(){
    }


}


