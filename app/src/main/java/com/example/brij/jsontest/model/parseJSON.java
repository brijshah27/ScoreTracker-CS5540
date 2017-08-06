package com.example.brij.jsontest.model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Brij on 8/5/17.
 */

public class parseJSON {
    private final static String TAG = "ParseJson";

    public static ArrayList<JsonModel> parseJsonData(String json) throws JSONException{
        ArrayList<JsonModel> result = new ArrayList<>();
        JSONObject mainResponse = new JSONObject(json);
        String teamName1 =  mainResponse.getString("Team1");
        String teamName2 =  mainResponse.getString("Team2");
        String teamName3 =  mainResponse.getString("Team3");
        String teamName4 =  mainResponse.getString("Team4");

        Log.d(TAG, "in parse data team name>>>>>"+teamName1);
        String score1 =  mainResponse.getString("Score1");
        String score2 =  mainResponse.getString("Score2");
        String score3 =  mainResponse.getString("Score3");
        String score4 =  mainResponse.getString("Score4");


        JsonModel resResult = new JsonModel(teamName1, score1, teamName2, score2);

        result.add(resResult);


        Log.d(TAG, ">>>>>>>>>>"+result);
        return result;



    }
}
