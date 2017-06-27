package com.example.brij.myapplication.utilities;

import android.util.Log;

import com.example.brij.myapplication.model.NewsItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Brij on 6/21/17.
 */

public class parseJSON {

    public static ArrayList<NewsItem> parseJsonData(String json)throws JSONException{
        ArrayList<NewsItem> result = new ArrayList<>();
        JSONObject mainResponse = new JSONObject(json);
        JSONArray articles = mainResponse.getJSONArray("articles");

        for(int i=0; i<articles.length(); i++){
            JSONObject article = articles.getJSONObject(i);
            String author = article.getString("author");
            String tiitle = article.getString("title");
            String description = article.getString("description");
            String url = article.getString("url");
            String date = article.getString("publishedAt");
            NewsItem items = new NewsItem(author, tiitle,description, url, date);
            result.add(items);
            Log.v("data is:", items.getAuthor()+items.getTittle()+items.getDescription()+items.getUrl()+items.getDate());
        }

        return result;
    }

}
