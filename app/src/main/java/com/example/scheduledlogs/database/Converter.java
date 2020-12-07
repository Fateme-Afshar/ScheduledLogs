package com.example.scheduledlogs.database;

import androidx.room.TypeConverter;

import java.util.Date;

public class Converter {
    @TypeConverter
    public static Date conLongToDate(Long timestamp) {
        return timestamp==null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long vonDateToLong(Date date) {
        return date==null ? null : date.getTime();
    }
}
