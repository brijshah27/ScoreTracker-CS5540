package com.example.brij.myapplication.utilities;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Brij on 6/21/17.
 */

public class NetworkUtils {
    final static String NEWS_BASE_URL = "https://newsapi.org/v1/articles";

    final static String PARAM_SOURCE = "source";

    final static String PARAM_SORT = "sortBy";

    final static String PARAM_API = "apiKey";

    final static String sortBy = "latest";

    final static String source = "the-next-web";

    final static String apiKey = "d526a960882941848faf5fd5d868ccd8";


    public static URL buildUrl(){
        Uri builtUri = Uri.parse(NEWS_BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_SOURCE,source)
                .appendQueryParameter(PARAM_SORT,sortBy)
                .appendQueryParameter(PARAM_API, apiKey)
                .build();

        URL url = null;

        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
