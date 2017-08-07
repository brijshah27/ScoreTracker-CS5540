package com.example.brij.myapplication.Database;

import android.provider.BaseColumns;

/**
 * Created by daminshah on 7/31/17.
 */

public class Contract  {

    public static class TABLE_GAMES implements BaseColumns {

        public static final String TABLE_NAME="match";
        public static final String COLUMN_NAME_HOMETEAM="hometeam";
        public static final String COLUMN_NAME_AWAYTEAM="awayteam";
        public static final String COLUMN_NAME_HOMETEAMCITY="hometeamcity";
        public static final String COLUMN_NAME_AWAYTEAMCITY="awayteamcity";
        public static final String COLUMN_NAME_HOMESCORE="homescore";
        public static final String COLUMN_NAME_AWAYSCORE="awayscore";
        public static final String COLUMN_NAME_LOCATION="location";
        public static final String COLUMN_NAME_DATE = "gameDate";

    }
}
