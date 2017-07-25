package com.example.brij.myapplication.utilities;

import android.annotation.TargetApi;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Build;
import android.util.Base64;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


/**
 * Created by Brij on 6/21/17.
 */

public class NetworkUtils {

    final static String TAG = "NetworkUtills";


    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static String getResponseFromHttpUrl()  {
        String data = " ";
        StringBuilder builder = new StringBuilder();
        try {

            URL url = new URL("https://api.mysportsfeeds.com/v1.1/pull/nba/2017-playoff/scoreboard.json?fordate=20170424");
            String testValue = "brijshah27:brij1234";
            byte[] data1=testValue.getBytes(StandardCharsets.UTF_8);
           String encoding=Base64.encodeToString(data1 ,Base64.DEFAULT);
           //String  encoding="Basic " + new String(android.util.Base64.encode(testValue.getBytes(), android.util.Base64.NO_WRAP));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
           // connection.setDoOutput(true);
            connection.setRequestProperty("Authorization", "Basic "+ encoding );
            InputStream content = (InputStream)connection.getInputStream();
//            BufferedReader in   =
//                    new BufferedReader (new InputStreamReader(content));
//            String line;
//
//            while ((line = in.readLine()) != null)
//            {
//                builder.append(line);
//
//            }

            Scanner scanner = new Scanner(content);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                data=data+scanner.next();
            } else {
                data=null;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Log.d(TAG ,"hello");

        }
        Log.d(TAG, data);
        return  data;

    }
}
