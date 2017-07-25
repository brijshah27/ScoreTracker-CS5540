package com.example.brij.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.brij.myapplication.model.NBAData;
import com.example.brij.myapplication.utilities.NBAAdapter;
import com.example.brij.myapplication.utilities.NetworkUtils;
import com.example.brij.myapplication.utilities.parseJSON;

import org.json.JSONException;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static final String TAG = "mainactivity";

    private TextView errorMessgaeTextView;

    private ProgressBar progressIndicator;

    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        progressIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);
        errorMessgaeTextView = (TextView) findViewById(R.id.error_message_display);
        rv = (RecyclerView) findViewById(R.id.nba_response_result);

        rv.setLayoutManager(new LinearLayoutManager(this));

    }

    private void showErrorMessage() {

        errorMessgaeTextView.setVisibility(View.VISIBLE);
    }

    public class NewsTask extends AsyncTask<String, Void, ArrayList<NBAData>> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressIndicator.setVisibility(View.VISIBLE);


        }



        @Override
        protected ArrayList<NBAData> doInBackground(String... params) {
            ArrayList<NBAData> news= null;
           // URL newsURL = NetworkUtils.buildUrl();
            //Log.d(TAG, "url: " + newsURL.toString());

            try {
                String json = NetworkUtils.getResponseFromHttpUrl();
                news = parseJSON.parseJsonData(MainActivity.this , json);
            }
             catch(JSONException e) {
                e.printStackTrace();
            }
            return news;
        }

        @Override
        protected void onPostExecute(final ArrayList<NBAData> data) {
            super.onPostExecute(data);
            progressIndicator.setVisibility(View.INVISIBLE);
            errorMessgaeTextView.setVisibility(View.INVISIBLE);
            if (data!=null) {
                Log.d(TAG, "g");
                NBAAdapter adapter = new NBAAdapter(data);
                        rv.setAdapter(adapter);

            } else {
                showErrorMessage();

            }
            //return data;
        }

    }

//    public void openWebPage(String url) {
//        Uri webpage = Uri.parse(url);
//        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemSelected = item.getItemId();
        if (itemSelected == R.id.action_search) {
            NewsTask task = new NewsTask();
            task.execute();

        }

        return true;
    }
}
