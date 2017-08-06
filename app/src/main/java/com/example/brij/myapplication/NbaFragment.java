package com.example.brij.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Brij on 8/2/17.
 */

public class NbaFragment extends Fragment {


    private TextView homeTeamScore;
    private TextView awayTeamScore;
    private TextView homeTeam;
    private TextView homeTeamCity;
    private TextView awayTeamCity;
    private TextView awayTeam;
    private TextView Location;
    //Button mapLocation;

    public NbaFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.nba_fragment, container, false);
        homeTeamScore = (TextView) view.findViewById(R.id.homeTeamScore);
        awayTeamScore = (TextView) view.findViewById(R.id.awayTeamScore);
        homeTeam = (TextView) view.findViewById(R.id.homeTeam);
        awayTeam = (TextView) view.findViewById(R.id.awayTeam);
        homeTeamCity = (TextView) view.findViewById(R.id.hometeam_city);
        awayTeamCity = (TextView) view.findViewById(R.id.awayTeamCity);
        Location = (TextView) view.findViewById(R.id.Location);
        Button mapLocation = (Button) view.findViewById(R.id.maplocation);
        String hometeam = getArguments().getString("hometeam");
        String awayteam = getArguments().getString("awayteam");
        String hometeamcity= getArguments().getString("hometeamcity");
        String awayteamcity=getArguments().getString("awayteamcity");
        String location=getArguments().getString("location");
        String hometeamScore = getArguments().getString("hometeamscore");
        String awayteamScore = getArguments().getString("awayteamscore");
        mapLocation.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                chnageActivity(getArguments().getString("location"));
            }
        });


       // return inflater.inflate(R.layout.fragment, container, false);
        homeTeam.setText(hometeam);
        awayTeam.setText(awayteam);
        homeTeamScore.setText(hometeamScore);
        awayTeamScore.setText(awayteamScore);
        awayTeamCity.setText(awayteamcity);
        homeTeamCity.setText(hometeamcity);
        Location.setText(location);
        return view;
    }

    private void chnageActivity(String location){
        Intent intent = new Intent(getActivity(), GetUserLocation.class);
        intent.putExtra("location", location);
        startActivity(intent);
    }

}
