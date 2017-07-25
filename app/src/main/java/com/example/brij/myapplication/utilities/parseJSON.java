package com.example.brij.myapplication.utilities;

import android.content.Context;
import android.util.Log;

import com.example.brij.myapplication.model.NBAData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Brij on 6/21/17.
 */

public class parseJSON {

    private  static  String TAG="hello";

    public static ArrayList<NBAData> parseJsonData(Context context, String json)throws JSONException{
        ArrayList<NBAData> result = new ArrayList<>();
        JSONObject mainResponse = new JSONObject(json);
//JSONArray gameScore = mainResponse.getJSONArray("gameScore");
        JSONObject scoreboard = mainResponse.getJSONObject("scoreboard");
        JSONArray gameScore = scoreboard.getJSONArray("gameScore");
        Log.d("gamescore", gameScore.toString());



        for(int i=0; i<gameScore.length(); i++){
            JSONObject gameObject = gameScore.getJSONObject(i); //contains data under first {
            JSONObject game = gameObject.getJSONObject("game");
            String id = game.getString("ID");
            JSONObject awayTeam = game.getJSONObject("awayTeam");
            JSONObject homeTeam = game.getJSONObject("homeTeam");
            Log.d(TAG ,"id is:"+ id);
            String homeTeamName = homeTeam.getString("Name");
            String awayTeamName = awayTeam.getString("Name");
            String homeTeamCity = homeTeam.getString("City");
            String awayTeamCity = awayTeam.getString("City");
            String homeScore = gameObject.getString("homeScore");
            String awayScore = gameObject.getString("awayScore");
            String location = game.getString("location");
            NBAData items = new NBAData(homeTeamName, awayTeamName, homeTeamCity, awayTeamCity, homeScore, awayScore, location);
            result.add(items);
            Log.v("data is:", items.getHomeTeam()+items.getAwayScore()+items.getHomeTeamCity()+items.getAwayTeamCity()+items.getHomeScore()+items.getAwayScore()+items.getLocation());
        }

        return result;
    }

}
