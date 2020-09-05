package com.dmd.favoriteplacesjavaversion.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.dmd.favoriteplacesjavaversion.R;
import com.dmd.favoriteplacesjavaversion.fragments.ListFragment;
import com.dmd.favoriteplacesjavaversion.fragments.MapsFragment;

public class MainActivity extends AppCompatActivity {

    private Fragment listFragment = new ListFragment();
    private Fragment mapFragment = new MapsFragment();
    private ImageButton imageButton;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_place){

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_place,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        changeFragment(listFragment);

        //ListView listView = (ListView) findViewById(R.id.listView);
        imageButton = (ImageButton) findViewById(R.id.imageButton);

    }

    public void floatingButtonOnClick(View view){
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragmentContainer);
        if (currentFragment instanceof ListFragment){
            imageButton.setImageResource(android.R.drawable.ic_dialog_dialer);
            changeFragment(mapFragment);
        } else {
            imageButton.setImageResource(android.R.drawable.ic_dialog_map);
            changeFragment(listFragment);
        }
        imageButton.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite), android.graphics.PorterDuff.Mode.MULTIPLY);

        Toast.makeText(getApplicationContext(),"Floating button Click",Toast.LENGTH_SHORT).show();
    }

    private void changeFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer,fragment);
        fragmentTransaction.commit();
    }
}
