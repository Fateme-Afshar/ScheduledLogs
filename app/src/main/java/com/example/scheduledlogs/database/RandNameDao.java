package com.example.scheduledlogs.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.scheduledlogs.model.RandomName;

import java.util.List;

@Dao
public interface RandNameDao {
    @Query(value = "SELECT * FROM NAMESTABLE WHERE id=:id")
    RandomName get(int id);

    @Query(value = "SELECT * FROM NAMESTABLE")
    List<RandomName> getList();

    @Insert
    void insert(RandomName t);

    @Delete
    void delete(RandomName t);

    @Update
    void update(RandomName t);
}
