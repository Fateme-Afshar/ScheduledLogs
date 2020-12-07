package com.example.scheduledlogs.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.scheduledlogs.database.RandNameSchema.NamesTable;
import com.example.scheduledlogs.database.RandNameSchema.NamesTable.Cols;

import java.util.Date;

@Entity(tableName = NamesTable.NAME)
public class RandomName {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Cols.ID)
    private int mIndex;
    @ColumnInfo(name = Cols.NAME)
    private String mName;
    @ColumnInfo(name = Cols.DATE)
    private Date mDate;
    @ColumnInfo(name = Cols.TIME_STAMP)
    private long mTimeStamp;
    @ColumnInfo(name = Cols.WIFI_STATE)
    private String mWifiState;

    public RandomName(String name) {
        mName = name;
        mDate = new Date();
        mTimeStamp = mDate.getTime();
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public long getTimeStamp() {
        return mTimeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        mTimeStamp = timeStamp;
    }

    public int getIndex() {
        return mIndex;
    }

    public void setIndex(int index) {
        mIndex = index;
    }

    public String getWifiState() {
        return mWifiState;
    }

    public void setWifiState(String wifiState) {
        mWifiState = wifiState;
    }
}
