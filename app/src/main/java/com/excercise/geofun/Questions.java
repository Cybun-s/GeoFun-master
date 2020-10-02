package com.excercise.geofun;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Questions {

    @PrimaryKey
    public int qID;

    @ColumnInfo(name = "question")
        public String question;

}
