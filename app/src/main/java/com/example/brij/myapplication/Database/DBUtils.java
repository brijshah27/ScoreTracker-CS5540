package com.example.brij.myapplication.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.brij.myapplication.model.NBAData;

import java.util.ArrayList;

import static com.example.brij.myapplication.Database.Contract.TABLE_GAMES.COLUMN_NAME_AWAYSCORE;
import static com.example.brij.myapplication.Database.Contract.TABLE_GAMES.COLUMN_NAME_AWAYTEAM;
import static com.example.brij.myapplication.Database.Contract.TABLE_GAMES.COLUMN_NAME_AWAYTEAMCITY;
import static com.example.brij.myapplication.Database.Contract.TABLE_GAMES.COLUMN_NAME_DATE;
import static com.example.brij.myapplication.Database.Contract.TABLE_GAMES.COLUMN_NAME_HOMESCORE;
import static com.example.brij.myapplication.Database.Contract.TABLE_GAMES.COLUMN_NAME_HOMETEAM;
import static com.example.brij.myapplication.Database.Contract.TABLE_GAMES.COLUMN_NAME_HOMETEAMCITY;
import static com.example.brij.myapplication.Database.Contract.TABLE_GAMES.COLUMN_NAME_LOCATION;
import static com.example.brij.myapplication.Database.Contract.TABLE_GAMES.TABLE_NAME;

/**
 * Created by daminshah on 7/31/17.
 */

public class DBUtils {

    public static Cursor getAllitems(SQLiteDatabase db){
        Cursor cursor= db.query(
                Contract.TABLE_GAMES.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                Contract.TABLE_GAMES.COLUMN_NAME_DATE+ " DESC");
        return cursor;
    }


    public static void  insertnews(SQLiteDatabase db, ArrayList<NBAData> Items) {

        //Deletes everything from database and populates it with new records .
        deleteNewsitem(db);
        db.beginTransaction();
        try {
            for (NBAData Item : Items) {
                ContentValues cv = new ContentValues();
                cv.put(COLUMN_NAME_HOMETEAM, Item.getHomeTeam());
                cv.put(COLUMN_NAME_AWAYTEAM, Item.getAwayTeam());
                cv.put(COLUMN_NAME_HOMETEAMCITY, Item.getHomeTeamCity());
                cv.put(COLUMN_NAME_AWAYTEAMCITY, Item.getAwayTeamCity());
                cv.put(COLUMN_NAME_HOMESCORE, Item.getHomeScore());
                cv.put(COLUMN_NAME_AWAYSCORE, Item.getAwayScore());
                cv.put(COLUMN_NAME_AWAYSCORE, Item.getAwayScore());
                cv.put(COLUMN_NAME_LOCATION, Item.getLocation() );
                cv.put(COLUMN_NAME_DATE, Item.getGameDate());
                db.insert(TABLE_NAME, null, cv);
            }
            db.setTransactionSuccessful();

        } finally {
            db.endTransaction();
            db.close();
        }
    }
    public static void deleteNewsitem(SQLiteDatabase db) {
        db.delete(Contract.TABLE_GAMES.TABLE_NAME, null, null);
    }
}
