package com.example.brij.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_splash_screen);


            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {
                        sleep(1000);

                        Class activity = MainActivity.class;
                        startActivity(new Intent(SplashScreenActivity.this, activity));

                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }
                }
            };

            thread.start();
        }
    }

