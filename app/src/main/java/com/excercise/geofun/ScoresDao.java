package com.excercise.geofun;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

@Dao
public interface ScoresDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Answer answer);
}
