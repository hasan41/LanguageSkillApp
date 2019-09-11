package com.example.hasannaseer.quizapp;

import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EasySplashScreen config = new EasySplashScreen(SplashScreen.this)
                .withFullScreen()
                .withTargetActivity(SignupActivity.class)
                .withSplashTimeOut(3000)
                .withBackgroundColor(Color.parseColor("#3b5998"))
                .withLogo(R.drawable.ic_coding02)
                .withAfterLogoText("QUIZ APP")
                .withFooterText("Version 2.0.1");

        //Set Text Color
        config.getFooterTextView().setTextColor(android.graphics.Color.WHITE);
        config.getAfterLogoTextView().setTextColor(Color.WHITE);
        //config.getBeforeLogoTextView().setTextColor(android.graphics.Color.WHITE);
        // config.getHeaderTextView().setTextColor(android.graphics.Color.WHITE);


        View view = config.create();

        //Set view to content view
        setContentView(view);
    }
}
