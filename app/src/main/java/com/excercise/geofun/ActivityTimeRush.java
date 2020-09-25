package com.excercise.geofun;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ActivityTimeRush extends AppCompatActivity implements View.OnClickListener {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();


    }

    private void initUI(){
        setContentView(R.layout.activity_timerush);
        returnToPreviousPage();
        clickedAnswer();
    }

    private void returnToPreviousPage(){
        FloatingActionButton timeRushBackButton = findViewById(R.id.timeRushBackButton);
        timeRushBackButton.setOnClickListener(this);
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
            case R.id.timeRushBackButton:
                Intent intentBack = new Intent(ActivityTimeRush.this, MainActivity.class);
                startActivity(intentBack);
                break;
            case R.id.answer1:
            case R.id.answer2:
            case R.id.answer3:
            case R.id.answer4:
                Intent answers = new Intent (ActivityTimeRush.this, ActivityTimeRush.class);
                startActivity(answers);
                break;
        }
    }

}
