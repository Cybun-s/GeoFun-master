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
        clickedAnswer();

    }

    private void returnToPreviousPage(){
        FloatingActionButton infiniteBackButton = findViewById(R.id.infiniteBackButton);
        infiniteBackButton.setOnClickListener(this);
    }

    private void clickedAnswer(){
        Button answer1 = findViewById(R.id.answer1);
        Button answer2 = findViewById(R.id.answer2);
        Button answer3 = findViewById(R.id.answer3);
        Button answer4 = findViewById(R.id.answer4);

        answer1.setOnClickListener(this);
        answer2.setOnClickListener(this);
        answer3.setOnClickListener(this);
        answer4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.infiniteBackButton:
                Intent intentBack = new Intent(ActivityInfinite.this, MainActivity.class);
                startActivity(intentBack);
                break;
            case R.id.answer1:
            case R.id.answer2:
            case R.id.answer3:
            case R.id.answer4:
                Intent answers = new Intent (ActivityInfinite.this, ActivityInfinite.class);
                startActivity(answers);
                break;
        }
    }

}
