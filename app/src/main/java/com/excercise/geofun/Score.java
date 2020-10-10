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

    public Score(int value){
        this.value = value;
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