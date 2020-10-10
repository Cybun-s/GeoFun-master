package com.excercise.geofun;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface CountriesDao {

    @Insert(onConflict = REPLACE)
    public void insert(Country c);

    @Query("SELECT * FROM Countries WHERE id LIKE :id")
    Country findByID(int id);

}

