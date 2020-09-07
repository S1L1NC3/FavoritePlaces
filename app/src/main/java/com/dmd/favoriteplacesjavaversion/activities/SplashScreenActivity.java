package com.dmd.favoriteplacesjavaversion.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

import com.dmd.favoriteplacesjavaversion.R;

public class SplashScreenActivity extends AppCompatActivity {
    private ImageView transistionImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        transistionImageView = findViewById(R.id.imageView4);

        /*
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        databaseHelper.openConnection();

        databaseHelper.createTableIfNotExists();
        if (databaseHelper.insertIntoLocations("deneme",50.8549541,4.3053507)){
            Log.i("DbTrackLog", "insertIntoLocations: success");
        } else {
            Log.i("DbTrackLog", "insertIntoLocations: failed");
        }
        databaseHelper.removeFromLocations(2);
        databaseHelper.trackAllData();
        databaseHelper.closeConnection();
        */

        //openNewActivity();
/*
        transistionImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity();

            }
        });*/

        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                openNewActivity();
            }
        },3000);

    }

    private void openNewActivity(){
        Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(SplashScreenActivity.this, transistionImageView, ViewCompat.getTransitionName(transistionImageView));
        startActivity(intent, optionsCompat.toBundle());
    }
}
