package com.example.scheduledlogs.database;

public class RandNameSchema {
    public static final int VERSION=1;
    public static final String NAME="randName.db";

    public static final class NamesTable{
        public static final String NAME="namesTable";

        public static final class Cols{
            public static final String ID="id";
            public static final String NAME="name";
            public static final String DATE="date";
            public static final String TIME_STAMP="timestamp";
            public static final String WIFI_STATE="wifiState";
        }
    }
}
