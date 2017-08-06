package com.example.brij.jsontest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.brij.jsontest.model.JsonModel;
import com.example.brij.jsontest.model.networkUtl;
import com.example.brij.jsontest.model.parseJSON;

import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String TAG = "Main";
    private TextView team1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        team1 = (TextView)findViewById(R.id.team1);

        NewsTask task = new NewsTask();
        task.execute();
    }


    public class NewsTask extends AsyncTask<URL, Void, ArrayList<JsonModel>> {

        @Override
        protected ArrayList<JsonModel> doInBackground(URL... params) {
            ArrayList<JsonModel> res = null;


            URL url = null;
            try {
                url = new URL("http://cs3.calstatela.edu:8080/cs3220ystu25/response");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            String json = null;
            try {
                json = networkUtl.getResponseFromHttpUrl(url);
                Log.d(TAG,"JSON>>>>>>>>>"+json);
                Log.d(TAG, "URL>>>>>>>>>>"+url);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                res = parseJSON.parseJsonData(json);
            } catch (JSONException e) {
                Log.d(TAG,"in catch main>>>>>>>>>>>>>");
                e.printStackTrace();
            }
            Log.d(TAG,"res in main>>>>>>"+res);


            return res;
        }

        @Override
        protected void onPostExecute(ArrayList<JsonModel> jsonModels) {
            Log.d(TAG, "in post team1>>>>>>>>>>>"+jsonModels.get(0).getTeamName1());
            Log.d(TAG, "in post team2>>>>>>>>>>>"+jsonModels.get(0).getScore1());
            Log.d(TAG, "in post team3>>>>>>>>>>>"+jsonModels.get(0).getTeamName2());
            Log.d(TAG, "in post team4>>>>>>>>>>>"+jsonModels.get(0).getScore2());

            team1.setText(jsonModels.get(0).getTeamName1());
        }
    }


}