package com.excercise.geofun;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Question {

    @PrimaryKey
    public int id;

    @ColumnInfo(name = "question")
    public String question;

    public Question(String question){
        this.question = question;
    }

    public int getID(){
        return id;
    }

    public String getQuestion(){
        return question;
    }

    public void setID(int id){
        this.id = id;
    }

    public void setQuestion(String question){
        this.question = question;
    }

}
