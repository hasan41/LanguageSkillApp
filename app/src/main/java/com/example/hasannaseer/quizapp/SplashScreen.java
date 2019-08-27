package com.example.hasannaseer.quizapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EasySplashScreen config = new EasySplashScreen(SplashScreen.this)
                .withFullScreen()
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(3000)
                .withBackgroundColor(Color.parseColor("#074E72"))
                .withLogo(R.drawable.ic_coding02)
                .withAfterLogoText("QUIZ APP")
                .withFooterText("Copyright 2019");

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
