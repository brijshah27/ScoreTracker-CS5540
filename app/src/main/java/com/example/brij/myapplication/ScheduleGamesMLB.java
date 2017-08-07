package com.example.brij.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.brij.myapplication.model.ScheduleModel;
import com.example.brij.myapplication.utilities.NetworkUtils;
import com.example.brij.myapplication.utilities.ScheduleAdapter;
import com.example.brij.myapplication.utilities.parseJSON;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ScheduleGamesMLB extends AppCompatActivity {

    private RecyclerView rv;

    private final String TAG = "schedule games MLB";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_games_mlb);

        rv = (RecyclerView) findViewById(R.id.news_response_result2);

        rv.setLayoutManager(new LinearLayoutManager(this));

        Log.d(TAG, "INSIDE ONCREATE");
        fetchSchedule task = new fetchSchedule();
        task.execute();


    }
    public class fetchSchedule extends AsyncTask<URL, Void, ArrayList<ScheduleModel>> {

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected ArrayList<ScheduleModel> doInBackground(URL... params) {
            Log.d(TAG, "INSIDE DOINBACKGROUND");
            ArrayList<ScheduleModel> resultSchedule = null;
            String scheduleResponse = "";
            URL url = null;
            try {
                url = new URL("https://api.mysportsfeeds.com/v1.1/pull/mlb/2017-regular/full_game_schedule.json");
                Log.d(TAG, "URL is>>>>>>>>>>"+url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            try{
                scheduleResponse = NetworkUtils.getScheduleNBA(url);
//Log.d(TAG,"Getting schedule>>>>>>");
            }catch (Exception e){
//Log.d(TAG, "$$$$INSIDE EXCEPTION$$$$$$");
                e.printStackTrace();
            }
            try {
                resultSchedule = parseJSON.parseNBAScheduleJSON(scheduleResponse);

            Log.d(TAG, "$$$$Getting prase dataN$$$$$$");
            }catch (Exception e){
//Log.d(TAG, "$$$$INSIDE EXCEPTION 22222$$$$$$");
                e.printStackTrace();
            }
//Log.d(TAG, "&&&& RESULTSCHEDULE: "+ resultSchedule);
            return resultSchedule;
        }

        @Override
        protected void onPostExecute(final ArrayList<ScheduleModel> scheduleModels) {

            super.onPostExecute(scheduleModels);


            if(scheduleModels!=null){
                ScheduleAdapter adapter = new ScheduleAdapter(scheduleModels, new ScheduleAdapter.ItemClickListener() {

                    @Override
                    public void onItemClick(int clickedItemIndex) {
                        String date = scheduleModels.get(clickedItemIndex).getGameDate();
                        Toast.makeText(ScheduleGamesMLB.this,"Showing schedule for:"+date,Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ScheduleGamesMLB.this, ScheduleDetails.class);
                        intent.putExtra("homeTeam", scheduleModels.get(clickedItemIndex).getHomeTeam());
                        intent.putExtra("awayTeam", scheduleModels.get(clickedItemIndex).getAwayTeam());
                        intent.putExtra("gameDate", scheduleModels.get(clickedItemIndex).getGameDate());
                        intent.putExtra("location", scheduleModels.get(clickedItemIndex).getGameLocation());
                        startActivity(intent);
                    }
                });
                rv.setAdapter(adapter);
            }
        }


    }
    @Override
    public void onBackPressed() {


        super.onBackPressed();

        Intent intent = new Intent(this, ScheduleGames.class);
        startActivity(intent);
    }

}
