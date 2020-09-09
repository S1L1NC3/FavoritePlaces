package com.dmd.favoriteplacesjavaversion.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dmd.favoriteplacesjavaversion.GlobalHelper;
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

        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                GlobalHelper.openNewActivity(SplashScreenActivity.this, LoginActivity.class, transistionImageView);
            }
        },3000);

    }
}
