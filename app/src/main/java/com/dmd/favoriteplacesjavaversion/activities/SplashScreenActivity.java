package com.dmd.favoriteplacesjavaversion.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

import com.dmd.favoriteplacesjavaversion.DatabaseHelper;
import com.dmd.favoriteplacesjavaversion.R;

public class SplashScreenActivity extends AppCompatActivity {
    private ImageView transistionImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        transistionImageView = findViewById(R.id.imageView4);
        //String  uniqueID = UUID.randomUUID().toString();

        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        databaseHelper.openConnection();

        //databaseHelper.dropDb();

        databaseHelper.createTableIfNotExists();
        if (databaseHelper.insertIntoLocations("deneme",50.8549541,4.3053507)){
            Log.i("DbTrackLog", "insertIntoLocations: success");
        } else {
            Log.i("DbTrackLog", "insertIntoLocations: failed");
        }
        databaseHelper.removeFromLocations(2);
        databaseHelper.trackAllData();
        databaseHelper.closeConnection();

        //this.deleteDatabase(getResources().getString(R.string.database_name));
        //openNewActivity();
        /*
        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{


                }
            }
        };
        timerThread.start();*/

    }

    private void openNewActivity(){
        Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(SplashScreenActivity.this, transistionImageView, ViewCompat.getTransitionName(transistionImageView));
        startActivity(intent, optionsCompat.toBundle());
    }
}
