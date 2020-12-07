package com.example.scheduledlogs.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.scheduledlogs.model.RandomName;

@TypeConverters({Converter.class})
@Database(entities = {RandomName.class},version = 1)
public abstract class RandomNameDatabase extends RoomDatabase {

    public abstract RandNameDao getDao();
}
