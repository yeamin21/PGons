package com.hoyt.pigeondb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;

public class LoadingActivity extends AppCompatActivity {
    FirebaseAuth fath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        fath = FirebaseAuth.getInstance();
        final Intent i = new Intent(this, TabbetdlayoutActivity.class);
        final Intent login = new Intent(this, Login.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (fath.getCurrentUser() != null) {
                    startActivity(i);
                    finish();
                } else {
                    startActivity(login);
                    finish();
                }
            }
        }, 1000);
    }
}



