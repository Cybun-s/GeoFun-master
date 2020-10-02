package com.excercise.geofun;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Countries {

    @PrimaryKey
    public int cID;

    @ColumnInfo(name = "country_Name")
        public String name;

}
