package com.excercise.geofun;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Answers {

    @PrimaryKey
    public int aID;

    @ColumnInfo(name = "answerType")
        public String answerType;

    @ColumnInfo(name = "answerValue")
        public String answerValue;
}
