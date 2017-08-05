package com.example.aquib.testmapapp;

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
    }
    private void getUserLocation(View view){
        directionButton.setText("Hello button");
    }
}
