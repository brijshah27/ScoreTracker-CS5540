package com.example.brij.myapplication.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by daminshah on 7/31/17.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=4;
    private static final String DATABASE_NAME="scores_3.db";
    private static final String TAG="dbhelper";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String queryString="CREATE TABLE " + Contract.TABLE_GAMES.TABLE_NAME+"(" +
                Contract.TABLE_GAMES._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                Contract.TABLE_GAMES.COLUMN_NAME_HOMETEAM+" TEXT NOT NULL, "+
                Contract.TABLE_GAMES.COLUMN_NAME_AWAYTEAM+" TEXT NOT NULL, "+
                Contract.TABLE_GAMES.COLUMN_NAME_HOMETEAMCITY+" TEXT NOT NULL, "+
                Contract.TABLE_GAMES.COLUMN_NAME_AWAYTEAMCITY+" TEXT NOT NULL, "+
                Contract.TABLE_GAMES.COLUMN_NAME_HOMESCORE+" TEXT NOT NULL, "+
                Contract.TABLE_GAMES.COLUMN_NAME_AWAYSCORE+" TEXT NOT NULL, "+
                Contract.TABLE_GAMES.COLUMN_NAME_LOCATION+" TEXT NOT NULL, "+
                Contract.TABLE_GAMES.COLUMN_NAME_DATE+" TEXT NOT NULL "+
                "); ";


        Log.d(TAG, "Create table SQL: " + queryString);
        db.execSQL(queryString);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
