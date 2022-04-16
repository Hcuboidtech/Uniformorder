package com.uniformorder.uniformorderr.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.uniformorder.uniformorderr.R;

public class Splashsrceen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashsrceen);
        startLogin();

    }

    private void startLogin() {
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                if (UserPreference.getInstance(Splashsrceen.this).isUserLogin()) {
                    Intent mainIntent = new Intent(Splashsrceen.this, MainActivity.class);
                    startActivity(mainIntent);
                    finish();
                }
                else {
                    Intent mainIntent = new Intent(Splashsrceen.this, LoginActivity.class);
                    startActivity(mainIntent);
                    finish();
                }



            }
        }, 1000);


    }
}