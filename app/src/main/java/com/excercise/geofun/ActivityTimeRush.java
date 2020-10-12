package com.excercise.geofun;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Random;

public class ActivityTimeRush extends AppCompatActivity implements View.OnClickListener {

    DatabaseHelper dbh;

    Random rng = new Random();

    Button answer1Button;
    Button answer2Button;
    Button answer3Button;
    Button answer4Button;
    TextView questionChosen;
    TextView countryChosen;
    TextView currentScoreDisplay;
    TextView timeRemaining;


    String answerClicked;
    int currentScore = 0;
    int testSeconds = 10000;
    int realModeSeconds = 60000;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timerush);
        initDB();
        initUI();
    }

    public void initDB(){
        dbh = new DatabaseHelper(this, this);
    }

    private void initUI() {
        setUpButtonsAndViews();
        updateScoreView();
        updateQuestionAnswerPairs();
        returnToMainMenu();
        setUpTimer();
    }

    public void setUpTimer(){
        new CountDownTimer(testSeconds, 1000) {

            public void onTick(long millisUntilFinished) {
                timeRemaining.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                resetAndSaveScore();
                initUI();
            }
        }.start();
    }

    public void returnToMainMenu() {
        FloatingActionButton interactiveBackButton = findViewById(R.id.timeRushBackButton);
        interactiveBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityTimeRush.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setUpButtonsAndViews(){
        answer1Button = findViewById(R.id.answer1);
        answer2Button = findViewById(R.id.answer2);
        answer3Button = findViewById(R.id.answer3);
        answer4Button = findViewById(R.id.answer4);

        answer1Button.setOnClickListener(this);
        answer2Button.setOnClickListener(this);
        answer3Button.setOnClickListener(this);
        answer4Button.setOnClickListener(this);

        questionChosen = findViewById(R.id.questionChosen);
        countryChosen = findViewById(R.id.countryChosen);
        currentScoreDisplay = findViewById(R.id.currentScoreValue);

        timeRemaining = findViewById(R.id.timeValue);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.answer1:
                answerClicked = answer1Button.getText().toString();
                onAnswerClick();
                break;
            case R.id.answer2:
                answerClicked = answer2Button.getText().toString();
                onAnswerClick();
                break;
            case R.id.answer3:
                answerClicked = answer3Button.getText().toString();
                onAnswerClick();
                break;
            case R.id.answer4:
                answerClicked = answer4Button.getText().toString();
                onAnswerClick();
                break;
        }
    }

    public void onAnswerClick() {
        checkAnswers();
        updateScoreView();
        updateQuestionAnswerPairs();
    }

    public void updateQuestionAnswerPairs(){
        removePreviousQuestion();
        setQuestion();
        removePreviousAnswers();
        setAnswers();
    }

    public void updateScoreView(){
        currentScoreDisplay.setText(String.valueOf(currentScore));
    }


    public void setQuestion() {
        int iQ = rng.nextInt(3);
        dbh.getQuestion(iQ, new QuestionQueryResultListener() {
            @Override
            public void onResult(Question question) {
                questionChosen.setText(question.getQuestion());
            }
        });

        int iC = rng.nextInt(5);
        dbh.getCountry(iC, new CountryQueryResultListener() {
            @Override
            public void onResult(Country country) {
                countryChosen.setText(country.getName());
            }
        });

    }

    public void removePreviousQuestion(){
        questionChosen.setText("");
        countryChosen.setText("");
    }

    public void removePreviousAnswers(){
        answer1Button.setText("");
        answer2Button.setText("");
        answer3Button.setText("");
        answer4Button.setText("");
        questionChosen.setText("");
    }

    public void setAnswers(){
        setAnswerOne();
        setAnswerTwo();
        setAnswerThree();
        setAnswerFour();
    }

    public void setAnswerOne(){
        int i = rng.nextInt(15);
        dbh.getAnswer(i, new AnswerQueryResultListener() {
            @Override
            public void onResult(Answer answer) {
                if(questionChosen.getText().equals(answer.getAnswerType())) {
                    answer1Button.setText(answer.getAnswerValue());
                }
                else{
                    answer1Button.setText("");
                }
            }
        });
    }

    public void setAnswerTwo(){
        int i = rng.nextInt(15);
        dbh.getAnswer(i, new AnswerQueryResultListener() {
            @Override
            public void onResult(Answer answer) {
                if(questionChosen.getText().equals(answer.getAnswerType())) {
                    answer2Button.setText(answer.getAnswerValue());
                }
                else{
                    answer2Button.setText("");
                }
            }
        });
    }

    public void setAnswerThree(){
        int i = rng.nextInt(15);
        dbh.getAnswer(i, new AnswerQueryResultListener() {
            @Override
            public void onResult(Answer answer) {
                if(questionChosen.getText().equals(answer.getAnswerType())) {
                    answer3Button.setText(answer.getAnswerValue());
                }
                else{
                    answer3Button.setText("");
                }
            }
        });
    }

    public void setAnswerFour(){
        int i = rng.nextInt(15);
        dbh.getAnswer(i, new AnswerQueryResultListener() {
            @Override
            public void onResult(Answer answer) {
                if(questionChosen.getText().equals(answer.getAnswerType())) {
                    answer4Button.setText(answer.getAnswerValue());
                }
                else{
                    answer4Button.setText("");
                }
            }
        });
    }

    public void checkAnswers(){
        switch (countryChosen.getText().toString()){
            case "Deutschland":
                switch (questionChosen.getText().toString()){
                    case "Hauptstadt":
                        if(answerClicked.equals("Berlin")){
                            currentScore =+1;
                        }
                        else{
                            resetAndSaveScore();
                        }
                        break;
                    case "Amtssprache":
                        if(answerClicked.equals("Deutsch")){
                            currentScore =+1;
                        }
                        else{
                            resetAndSaveScore();
                        }
                        break;
                    case "Einwohnerzahl":
                        if(answerClicked.equals("83 Millionen")){
                            currentScore =+1;
                        }
                        else{
                            resetAndSaveScore();
                        }
                        break;
                }
                break;
            case "England":
                switch (questionChosen.getText().toString()){
                    case "Hauptstadt":
                        if(answerClicked.equals("London")){
                            currentScore =+1;
                        }
                        else{
                            resetAndSaveScore();
                        }
                        break;
                    case "Amtssprache":
                        if(answerClicked.equals("Englisch")){
                            currentScore =+1;
                        }
                        else{
                            resetAndSaveScore();
                        }
                        break;
                    case "Einwohnerzahl":
                        if(answerClicked.equals("56 Millionen")){
                            currentScore =+1;
                        }
                        else{
                            resetAndSaveScore();
                        }
                        break;
                }
                break;
            case "Frankreich":
                switch (questionChosen.getText().toString()){
                    case "Hauptstadt":
                        if(answerClicked.equals("Paris")){
                            currentScore =+1;
                        }
                        else{
                            resetAndSaveScore();
                        }
                        break;
                    case "Amtssprache":
                        if(answerClicked.equals("Franzoesisch")){
                            currentScore =+1;
                        }
                        else{
                            resetAndSaveScore();
                        }
                        break;
                    case "Einwohnerzahl":
                        if(answerClicked.equals("67 Millionen")){
                            currentScore =+1;
                        }
                        else{
                            resetAndSaveScore();
                        }
                        break;
                }
                break;
            case "Schweden":
                switch (questionChosen.getText().toString()){
                    case "Hauptstadt":
                        if(answerClicked.equals("Stockholm")){
                            currentScore =+1;
                        }
                        else{
                            resetAndSaveScore();
                        }
                        break;
                    case "Amtssprache":
                        if(answerClicked.equals("Schwedisch")){
                            currentScore =+1;
                        }
                        else{
                            resetAndSaveScore();
                        }
                        break;
                    case "Einwohnerzahl":
                        if(answerClicked.equals("10 Millionen")){
                            currentScore =+1;
                        }
                        else{
                            resetAndSaveScore();
                        }
                        break;
                }
                break;
            case "Belgien":
                switch (questionChosen.getText().toString()){
                    case "Hauptstadt":
                        if(answerClicked.equals("Brüssel")){
                            currentScore =+1;
                        }
                        else{
                            resetAndSaveScore();
                        }
                        break;
                    case "Amtssprache":
                        if(answerClicked.equals("Belgisch")){
                            currentScore =+1;
                        }
                        else{
                            resetAndSaveScore();
                        }
                        break;
                    case "Einwohnerzahl":
                        if(answerClicked.equals("11,5 Millionen")){
                            currentScore =+1;
                        }
                        else{
                            resetAndSaveScore();
                        }
                        break;
                }
                break;
        }

    }

    public void resetAndSaveScore(){
        String sT = "TimeRush";
        Score s = new Score(currentScore, sT);
        dbh.storeScore(s);
        currentScore = 0;
        currentScoreDisplay.setText(String.valueOf(currentScore));
    }

}
