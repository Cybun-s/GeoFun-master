package com.excercise.geofun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.nio.file.AtomicMoveNotSupportedException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    DatabaseHelper dbh;

    ArrayList countriesList = new ArrayList<String>();
    ArrayList questionsList = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
        initDB();
        fillDatabase();

    }

    public void initUI(){
        setContentView(R.layout.activity_main);
        Button infiniteMode = findViewById(R.id.mode_1_button);
        Button timeRushMode = findViewById(R.id.mode_2_button);
        Button interactiveMode = findViewById(R.id.mode_3_button);
        FloatingActionButton editSettings = findViewById(R.id.settings_button);

        infiniteMode.setOnClickListener(this);
        timeRushMode.setOnClickListener(this);
        interactiveMode.setOnClickListener(this);
        editSettings.setOnClickListener(this);
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
        createAnswers();
        createCountries();
        createQuestions();
    }

    public void initDB(){
        dbh = new DatabaseHelper(this, this);
    }

    public void createQuestions(){

        questionsList.add("Hauptstadt");
        questionsList.add("Amtssprache");
        questionsList.add("Einwohnerzahl");

        for(int i = 0; i < questionsList.size(); i++){
            Question q = new Question(i, questionsList.get(i).toString());
            dbh.storeQuestion(q);
            }

    }

    public void createAnswers(){
        Answer gerCap = new Answer(0,"Hauptstadt", "Berlin");
        Answer belCap = new Answer(1,"Hauptstadt", "Brüssel");
        Answer enCap = new Answer(2,"Hauptstadt", "London");
        Answer sweCap = new Answer(3,"Hauptstadt", "Stockholm");
        Answer frCap = new Answer(4,"Hauptstadt", "Paris");
        Answer gerSpe = new Answer(5,"Amtssprache", "Deutsch");
        Answer belSpe = new Answer(6,"Amtssprache", "Belgisch");
        Answer enSpe = new Answer(7,"Amtssprache", "Englisch");
        Answer sweSpe = new Answer( 8,"Amtssprache", "Schwedisch");
        Answer frSpe = new Answer(9,"Amtssprache", "Franzoesisch");
        Answer gerRes = new Answer(10, "Einwohnerzahl", "83 Millionen");
        Answer belRes = new Answer(11, "Einwohnerzahl", "11,5 Millionen");
        Answer enRes = new Answer(12, "Einwohnerzahl", "56 Millionen");
        Answer sweRes = new Answer(13, "Einwohnerzahl", "10 Millionen");
        Answer frRes = new Answer(14, "Einwohnerzahl", "67 Millionen");

        dbh.storeAnswer(gerCap);
        dbh.storeAnswer(belCap);
        dbh.storeAnswer(enCap);
        dbh.storeAnswer(sweCap);
        dbh.storeAnswer(frCap);
        dbh.storeAnswer(gerSpe);
        dbh.storeAnswer(belSpe);
        dbh.storeAnswer(enSpe);
        dbh.storeAnswer(sweSpe);
        dbh.storeAnswer(frSpe);
        dbh.storeAnswer(gerRes);
        dbh.storeAnswer(belRes);
        dbh.storeAnswer(enRes);
        dbh.storeAnswer(sweRes);
        dbh.storeAnswer(frRes);


    }

    public void createCountries(){

        countriesList.add("Deutschland");
        countriesList.add("Belgien");
        countriesList.add("England");
        countriesList.add("Schweden");
        countriesList.add("Frankreich");

        for(int i=0; i<countriesList.size();i++){
            Country c = new Country(i, countriesList.get(i).toString());
            dbh.storeCountry(c);
            }

    }


}


