package com.excercise.geofun;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Answers")
public class Answer {

    @PrimaryKey
    public int id;

    @ColumnInfo(name = "answerType")
    public String answerType;

    @ColumnInfo(name = "answerValue")
    public String answerValue;

    public Answer(int id, String answerType, String answerValue){
        this.id = id;
        this.answerType = answerType;
        this.answerValue = answerValue;
    }

    public int getID(){
        return id;
    }

    public String getAnswerType(){
        return answerType;
    }

    public String getAnswerValue(){
        return answerValue;
    }

    public void setID(int id){
        this.id = id;
    }

    public void setAnswerType(String answerType){
        this.answerType = answerType;
    }

    public void setAnswerValue(String answerValue){
        this.answerValue = answerValue;
    }
}

