package com.excercise.geofun;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface ScoresDao {

    @Insert(onConflict = REPLACE)
    public void insert(Score s);

    @Query("SELECT * FROM Scores WHERE scoreValue > :scoreValue")
    Score findByHighest(int scoreValue);
}
