package com.example.brij.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brij.myapplication.model.LiveScoreModel;
import com.example.brij.myapplication.utilities.LiveScoreUtil;

import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class LiveScoreDemo extends AppCompatActivity {

    private String TAG = "LiveScore";
    TextView hometeam1;
    TextView awayteam1;
    TextView hometeam2;
    TextView awayteam2;
    TextView score1;
    TextView score2;
    TextView score3;
    TextView score4;
    Thread t;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_score_demo);

        hometeam1 = (TextView)findViewById(R.id.homeTeam);
        awayteam1 = (TextView)findViewById(R.id.awayTeam);
        hometeam2 = (TextView)findViewById(R.id.homeTeam1);
        awayteam2 = (TextView)findViewById(R.id.awayTeam1);


        score1 = (TextView)findViewById(R.id.homeTeamScore);
        score2 = (TextView)findViewById(R.id.awayTeamScore);
        score3 = (TextView)findViewById(R.id.homeTeamScore1);
        score4 = (TextView)findViewById(R.id.awayTeamScore1);

        LiveTask task = new LiveTask();
        task.execute();

        t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(10000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                LiveTask task = new LiveTask();
                                task.execute();


                                //team1.setText(res.get(0).getScore1());

                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t.start();
    }

    public class LiveTask extends AsyncTask<URL, Void, ArrayList<LiveScoreModel>>{

        @Override
        protected ArrayList<LiveScoreModel> doInBackground(URL... params) {
            ArrayList<LiveScoreModel> res = null;


            URL url = null;
            try {
                url = new URL("http://cs3.calstatela.edu:8080/cs3220ystu25/response");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            String json = null;
            try {
                json = LiveScoreUtil.getResponseFromHttpUrl(url);
                Log.d(TAG,"JSON>>>>>>>>>"+json);
                Log.d(TAG, "URL>>>>>>>>>>"+url);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                res = LiveScoreUtil.parseJsonData(json);
            } catch (JSONException e) {
                Log.d(TAG,"in catch main>>>>>>>>>>>>>");
                e.printStackTrace();
            }
            Log.d(TAG,"res in main>>>>>>"+res);


            return res;
        }

        @Override
        protected void onPostExecute(ArrayList<LiveScoreModel> jsonModels) {
            Log.d(TAG, "in post team1>>>>>>>>>>>"+jsonModels.get(0).getTeamName1());
            Log.d(TAG, "in post team2>>>>>>>>>>>"+jsonModels.get(0).getScore1());
            Log.d(TAG, "in post team3>>>>>>>>>>>"+jsonModels.get(0).getTeamName2());
            Log.d(TAG, "in post team4>>>>>>>>>>>"+jsonModels.get(0).getScore2());
            Log.d(TAG, "in post team3>>>>>>>>>>>"+jsonModels.get(0).getTeamName3());
            Log.d(TAG, "in post team4>>>>>>>>>>>"+jsonModels.get(0).getScore3());
            Log.d(TAG, "in post team3>>>>>>>>>>>"+jsonModels.get(0).getTeamName4());
            Log.d(TAG, "in post team4>>>>>>>>>>>"+jsonModels.get(0).getScore4());


            hometeam1.setText(jsonModels.get(0).getTeamName1());
            awayteam1.setText(jsonModels.get(0).getTeamName2());
            hometeam2.setText(jsonModels.get(0).getTeamName3());
            awayteam2.setText(jsonModels.get(0).getTeamName4());

            score1.setText(jsonModels.get(0).getScore1());
            score2.setText(jsonModels.get(0).getScore2());
            score3.setText(jsonModels.get(0).getScore3());
            score4.setText(jsonModels.get(0).getScore4());

            Toast.makeText(LiveScoreDemo.this,"Scores updated!",Toast.LENGTH_SHORT).show();



        }
    }
    @Override
    public void onBackPressed() {
        t.interrupt();
            super.onBackPressed();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
