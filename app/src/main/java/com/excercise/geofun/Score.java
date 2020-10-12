package com.excercise.geofun;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Scores")
public class Score {

    @PrimaryKey
    public int id;

    @ColumnInfo(name="scoreValue")
    public int value;

    @ColumnInfo(name="scoreType")
    public String scoreType;

    public Score(int value, String scoreType){
        this.value = value;
        this.scoreType = scoreType;
    }

    public int getID(){
        return id;
    }

    public int getValue(){
        return value;
    }

    public void setID(int id){
        this.id = id;
    }

    public void setValue(int value){
        this.value = value;
    }


}