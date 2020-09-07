package com.dmd.favoriteplacesjavaversion.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dmd.favoriteplacesjavaversion.R;
import com.dmd.favoriteplacesjavaversion.model.FavoritePlace;

import java.util.ArrayList;

public class FavoritesPlacesAdapter extends ArrayAdapter<FavoritePlace> {
    private ArrayList<FavoritePlace> favoritePlaces;
    private Activity context;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View customView = layoutInflater.inflate(R.layout.row_favorite_place, null, true);

        TextView textViewRowName = customView.findViewById(R.id.textViewRowName);
        textViewRowName.setText(favoritePlaces.get(position).getName());
        return customView;
    }

    public FavoritesPlacesAdapter(ArrayList<FavoritePlace> favoritePlaces, Activity context) {
        super(context, R.layout.row_favorite_place, favoritePlaces);
        this.favoritePlaces = favoritePlaces;
        this.context = context;
    }

}


