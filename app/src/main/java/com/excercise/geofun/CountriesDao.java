package com.excercise.geofun;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

@Dao
public interface CountriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Country country);

}

