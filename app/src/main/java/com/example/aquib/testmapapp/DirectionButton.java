package com.example.aquib.testmapapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class DirectionButton extends AppCompatActivity {

    private Button directionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direction_button);
        directionButton = (Button) findViewById(R.id.userLoc);
        directionButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                chnageActivity();
            }
        });
    }

    private void chnageActivity(){
        Intent intent = new Intent(this, GetUserLocation.class);
        startActivity(intent);
    }

}
