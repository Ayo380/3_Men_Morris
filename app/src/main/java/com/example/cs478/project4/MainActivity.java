package com.example.cs478.project4;

//Ayokunle Olugboyo


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//
//    }
    private static int SPLASH_TIME_OUT = 2000; // time in milliseconds
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start your app's main activity
                Intent i = new Intent(MainActivity.this, MainGame.class);

                startActivity(i);
                //Close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}





