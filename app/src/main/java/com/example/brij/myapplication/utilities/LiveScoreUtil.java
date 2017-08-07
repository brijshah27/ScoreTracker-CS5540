package com.example.brij.myapplication.utilities;

import android.util.Log;

import com.example.brij.myapplication.model.LiveScoreModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import static com.google.android.gms.wearable.DataMap.TAG;

/**
 * Created by Brij on 8/6/17.
 */

public class LiveScoreUtil {

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();



            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    public static ArrayList<LiveScoreModel> parseJsonData(String json) throws JSONException {
        ArrayList<LiveScoreModel> result = new ArrayList<>();
        JSONObject mainResponse = new JSONObject(json);
        String teamName1 = mainResponse.getString("Team1");
        String teamName2 = mainResponse.getString("Team2");
        String teamName3 = mainResponse.getString("Team3");
        String teamName4 = mainResponse.getString("Team4");

        Log.d(TAG, "in parse data team name>>>>>" + teamName1);
        String score1 = mainResponse.getString("Score1");
        String score2 = mainResponse.getString("Score2");
        String score3 = mainResponse.getString("Score3");
        String score4 = mainResponse.getString("Score4");


        LiveScoreModel resResult = new LiveScoreModel(teamName1, score1, teamName2, score2, teamName3, score3, teamName4, score4);
        result.add(resResult);


        Log.d(TAG, ">>>>>>>>>>" + result);
        return result;
    }
}
