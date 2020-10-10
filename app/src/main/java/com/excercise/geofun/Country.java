package com.excercise.geofun;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Countries")
public class Country {

    @PrimaryKey
    public int id;

    @ColumnInfo(name = "country_Name")
    public String name;

    public Country (int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getID(){
        return id;
    }

    public String getName(){
        return name;
    }

    public void setID(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

}
