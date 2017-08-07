package com.example.brij.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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
    Button maplocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_details);
        homeTeamTv = (TextView) findViewById(R.id.homeTeamScore);
        awayTeamTv = (TextView) findViewById(R.id.awayTeamScore);
       // gameDateTv = (TextView) findViewById(R.id.gameDate);
        locationTv = (TextView) findViewById(R.id.Location);
        maplocation=(Button)findViewById(R.id.maplocation);




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
       // gameDateTv.setText(gameDate);
        locationTv.setText(location);

        maplocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chnageActivity(location);

            }
        });




    }

    public void chnageActivity(String location){
        Intent intent = new Intent(ScheduleDetails.this, GetUserLocation.class);
        intent.putExtra("location", location);
        startActivity(intent);

    }
    @Override
    public void onBackPressed() {


        super.onBackPressed();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
