package com.excercise.geofun;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ActivityInfinite extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }

    private void initUI() {
        setContentView(R.layout.activity_infinite);
        returnToPreviousPage();

    }

    private void returnToPreviousPage(){
        FloatingActionButton infiniteBackButton = findViewById(R.id.infiniteBackButton);
        infiniteBackButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intentBack = new Intent(ActivityInfinite.this, MainActivity.class);
        startActivity(intentBack);
    }
}
