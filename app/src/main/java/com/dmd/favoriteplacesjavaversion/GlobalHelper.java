package com.dmd.favoriteplacesjavaversion;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

import static androidx.core.content.ContextCompat.startActivity;

public class GlobalHelper {

    public static void openNewActivity(Context classFromContext, Class classToOpen){
        Intent myIntent = new Intent(classFromContext, classToOpen);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(classFromContext, myIntent,null);
    }

    public static void openNewActivity(AppCompatActivity classFromContext, Class classToOpen, ImageView transistionImageView){
        Intent intent = new Intent(classFromContext, classToOpen);
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(classFromContext, transistionImageView, ViewCompat.getTransitionName(transistionImageView));
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(classFromContext, intent, optionsCompat.toBundle());
    }
}
