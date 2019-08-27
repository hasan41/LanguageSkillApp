package com.example.hasannaseer.quizapp;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.animation.AlphaAnimation; //instance class
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;               //instance class

public class SplashscreenActivity extends AppCompatActivity {
//    private TextView mTitle;
//    private AlphaAnimation fadeOut;
//    LinearLayout l1, l2;
//    Animation uptodown, downtoup;
//
//
//
//    @Override
//    protected void onCreate( Bundle savedInstanceState ) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splashscreen);
//
//        fadeOut = new AlphaAnimation(0.0f, 1.0f);
//        mTitle = (TextView)findViewById(R.id.title);
//
//        mTitle.startAnimation(fadeOut);
//        fadeOut.setDuration(4500);
//
//        fadeOut.setFillAfter(true);
//        l1 = (LinearLayout) findViewById(R.id.l1);
//        l2 = (LinearLayout) findViewById(R.id.l2);
//        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);   //uptodown animation in the xml file
//        downtoup = AnimationUtils.loadAnimation(this,R.anim.downtoup);   //downtoup animation // // // ///
//        l1.setAnimation(uptodown);
//        l2.setAnimation(downtoup);
//
//        //                    duration,      interval
//        new CountDownTimer(5500, 1000) {
//            @Override
//            public void onTick( long millisUntilFinished ) {
//
//            }
//
//            @Override
//            public void onFinish() {
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);
//            }
//        }.start();
//    }
}
