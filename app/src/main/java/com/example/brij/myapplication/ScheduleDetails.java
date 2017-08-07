package com.example.brij.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ScheduleDetails extends AppCompatActivity {

    String homeTeam;
    String awayTeam;
    String gameDate;
    String location;
    TextView homeTeamTv;
    TextView awayTeamTv;
    TextView gameDateTv;
    TextView locationTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_details);
        homeTeamTv = (TextView) findViewById(R.id.homeTeam);
        awayTeamTv = (TextView) findViewById(R.id.awayTeam);
        gameDateTv = (TextView) findViewById(R.id.gameDate);
        locationTv = (TextView) findViewById(R.id.location);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                homeTeam = null;
                awayTeam = null;
                gameDate = null;
                location = null;
            } else {
                homeTeam= extras.getString("homeTeam");
                awayTeam = extras.getString("awayTeam");
                gameDate = extras.getString("gameDate");
                location = extras.getString("location");

            }
        } else {
            homeTeam= (String) savedInstanceState.getSerializable("homeTeam");
            awayTeam= (String) savedInstanceState.getSerializable("awayTeam");
            gameDate = (String) savedInstanceState.getSerializable("gameDate");
            location = (String) savedInstanceState.getSerializable("location");
        }
        homeTeamTv.setText(homeTeam);
        awayTeamTv.setText(awayTeam);
        gameDateTv.setText(gameDate);
        locationTv.setText(location);



    }
    @Override
    public void onBackPressed() {


        super.onBackPressed();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
