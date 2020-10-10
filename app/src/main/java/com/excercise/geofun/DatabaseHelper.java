package com.excercise.geofun;

import android.app.Activity;
import android.content.Context;

import androidx.room.Room;

import java.util.concurrent.Executors;

public class DatabaseHelper {

    private AppDatabase adb;

    private final Activity activity;

    public DatabaseHelper(Activity activity, Context context){
        this.activity = activity;
        adb = Room.databaseBuilder(context,
                AppDatabase.class, "geoFunDatabase").build();
    }

    public void storeAnswer(Answer a){
        AddAnswerTask task = new AddAnswerTask(a);
        Executors.newSingleThreadExecutor().submit(task);
    }

    public void storeCountry(Country c){
        AddCountriesTask task = new AddCountriesTask(c);
        Executors.newSingleThreadExecutor().submit(task);
    }

    public void storeQuestion(Question q){
        AddQuestionTask task = new AddQuestionTask(q);
        Executors.newSingleThreadExecutor().submit(task);
    }

    public void storeScore(Score s){
        AddScoreTask task = new AddScoreTask(s);
        Executors.newSingleThreadExecutor().submit(task);
    }

    public void getAnswer(int i, AnswerQueryResultListener listener) {
        FindAnswerTask task = new FindAnswerTask(i, listener);
        Executors.newSingleThreadExecutor().submit(task);
    }

    public void getQuestion(int i, QuestionQueryResultListener listener) {
        FindQuestionTask task = new FindQuestionTask(i, listener);
        Executors.newSingleThreadExecutor().submit(task);
    }

    public void getCountry(int i, CountryQueryResultListener listener) {
        FindCountryTask task = new FindCountryTask(i, listener);
        Executors.newSingleThreadExecutor().submit(task);
    }

    private class AddAnswerTask implements Runnable {
        private Answer answer;
        public AddAnswerTask(Answer answer) {
            this.answer = answer;
        }

        @Override
        public void run() {
            adb.answersDao().insert(answer);
        }
    }

    private class AddCountriesTask implements Runnable {
        private Country country;
        public AddCountriesTask(Country country) {
            this.country = country;
        }

        @Override
        public void run() {
            adb.countriesDao().insert(country);
        }
    }

    private class AddQuestionTask implements Runnable {
        private Question question;
        public AddQuestionTask(Question question) {
            this.question = question;
        }

        @Override
        public void run() {
            adb.questionsDao().insert(question);
        }
    }

    private class AddScoreTask implements Runnable {
        private Score score;
        public AddScoreTask(Score score) {
            this.score = score;
        }

        @Override
        public void run() {
            adb.scoresDao().insert(score);
        }
    }

    private class FindAnswerTask implements Runnable {
        int i;
        private AnswerQueryResultListener listener;

        public FindAnswerTask(int i,AnswerQueryResultListener listener) {
            this.i = i;
            this.listener = listener;
        }

        @Override
        public void run() {
            final Answer answer = adb.answersDao().findByID(i);
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    listener.onResult(answer);
                }
            });
        }
    }

    private class FindQuestionTask implements Runnable {
        int i;
        private QuestionQueryResultListener listener;

        public FindQuestionTask(int i, QuestionQueryResultListener listener) {
            this.i = i;
            this.listener = listener;
        }

        @Override
        public void run() {
            final Question question = adb.questionsDao().findByID(i);
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    listener.onResult(question);
                }
            });
        }
    }

    private class FindCountryTask implements Runnable {
        int i;
        private CountryQueryResultListener listener;

        public FindCountryTask(int i, CountryQueryResultListener listener) {
            this.i = i;
            this.listener = listener;
        }

        @Override
        public void run() {
            final Country country = adb.countriesDao().findByID(i);
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    listener.onResult(country);
                }
            });
        }
    }

    private class FindScoreTask implements Runnable {
        private int score;
        private ScoreQueryResultListener listener;

        public FindScoreTask(int score, ScoreQueryResultListener listener) {
            this.score = score;
            this.listener = listener;
        }

        @Override
        public void run() {
            /*final Score score = adb.scoresDao().findByID(rng.nextInt(1));
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    listener.onResult(score);
                }
            });*/
        }
    }







}
