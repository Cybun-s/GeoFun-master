package com.excercise.geofun;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface AnswersDao {

    @Insert(onConflict = REPLACE)
    public void insert(Answer a);


    @Query("SELECT * FROM Answers WHERE id LIKE :id")
    Answer findByID(int id);

}
