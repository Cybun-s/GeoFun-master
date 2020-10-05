package com.excercise.geofun;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ActivityInteractive extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }

    private void initUI() {
        setContentView(R.layout.activity_interactive);
        returnToPreviousPage();
    }

    private void returnToPreviousPage(){
        FloatingActionButton infiniteBackButton = findViewById(R.id.interactiveBackButton);
        infiniteBackButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.interactiveBackButton:
                Intent intentBack = new Intent(com.excercise.geofun.ActivityInteractive.this, MainActivity.class);
                startActivity(intentBack);
                break;
        }
    }

}
