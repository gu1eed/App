package com.example.vaibhav.upworkfirstproject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.twitter.sdk.android.core.models.User;

public class SplashActivity extends Activity {
    private FirebaseAuth.AuthStateListener mAuthStateLIstener;
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(SplashActivity.this);
                boolean state=sharedPreferences.getBoolean("sharedstate",false);
                if (state==true){
                    startActivity(new Intent(SplashActivity.this,MainNavi.class));
                    finish();

                }else{
                startActivity(new Intent(SplashActivity.this,Login.class));
                finish();}

            }
        },SPLASH_TIME_OUT);




    }
}
