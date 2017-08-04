package com.example.brij.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class GameDetails extends AppCompatActivity {

    private TextView homeTeamDeatils;

    private final String TAG ="Game Details";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);

        homeTeamDeatils = (TextView) findViewById(R.id.homeTeamDetails);

        String gameName;

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                gameName= null;
            } else {
                gameName= extras.getString("homeTeam");
            }
        } else {
            gameName= (String) savedInstanceState.getSerializable("homeTeam");
        }
        Log.d(TAG, "Game name is:"+gameName);

        homeTeamDeatils.setText(gameName);

    }
}
