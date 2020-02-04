package com.project.projectapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences("launch", Context.MODE_PRIVATE);
        if(sharedPreferences.getBoolean("activity_executed",false)){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent splashIntent = new Intent(SplashActivity.this, CategoryActivity.class);
                    startActivity(splashIntent);
                    finish();
                    overridePendingTransition(0, 0);
                }
            },3000);
        }
        else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent splashIntent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(splashIntent);
                    finish();
                    overridePendingTransition(0, 0);
                }
            },3000);
        }

    }
}
