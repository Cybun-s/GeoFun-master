package com.excercise.geofun;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Scores {

    @PrimaryKey
    public int sID;

    @ColumnInfo(name="scoreValue")
        public int value;
}
