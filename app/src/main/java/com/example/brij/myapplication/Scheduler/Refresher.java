package com.example.brij.myapplication.Scheduler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.brij.myapplication.Database.DBHelper;
import com.example.brij.myapplication.Database.DBUtils;
import com.example.brij.myapplication.model.NBAData;
import com.example.brij.myapplication.utilities.NetworkUtils;
import com.example.brij.myapplication.utilities.parseJSON;
import com.firebase.jobdispatcher.JobParameters;

import java.util.ArrayList;

/**
 * Created by daminshah on 7/31/17.
 */

public class Refresher extends com.firebase.jobdispatcher.JobService {

    private static final String TAG="NewsService";
    @Override
    public boolean onStartJob(JobParameters job) {
        new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {

                newsrefresh(Refresher.this);

                return null;


            }
        }.execute();

        Toast.makeText(Refresher.this, "Scores updated from " + "server!",
                Toast.LENGTH_SHORT).show();


        return false;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        return false;
    }

    public static void newsrefresh(Context context) {
        try {
           // URL newsURL = NetworkUtils.buildURL();
            ArrayList<NBAData> nba= null;
            ArrayList<NBAData> mlb= null;

            String jsonNBA = NetworkUtils.getResponseFromHttpUrl();
            nba = parseJSON.parseJsonData(context , jsonNBA);


            String jsonMLB = NetworkUtils.getResponseFromHttpUrlMlb();
            mlb = parseJSON.parseJsonData(context , jsonMLB);


            ArrayList<NBAData> results=new ArrayList<>();

            results.addAll(nba);
            results.addAll(mlb);



            SQLiteDatabase db = new DBHelper(context).getWritableDatabase();
            DBUtils.insertnews(db, results);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
