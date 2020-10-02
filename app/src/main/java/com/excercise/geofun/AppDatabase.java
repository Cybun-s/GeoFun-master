package com.excercise.geofun;

import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {Countries.class, Questions.class, Answers.class, Scores.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CountriesDao countriesDao();
    public abstract QuestionsDao questionsDao();
    public abstract AnswersDao answersDao();
    public abstract ScoresDao scoresDao();

}