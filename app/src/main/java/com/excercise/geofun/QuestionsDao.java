package com.excercise.geofun;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface QuestionsDao {

    @Insert(onConflict = REPLACE)
    public void insert(Question q);

    @Query("SELECT * FROM Questions WHERE id LIKE :id")
    Question findByID(int id);

}
