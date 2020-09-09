package com.dmd.favoriteplacesjavaversion.activities;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.dmd.favoriteplacesjavaversion.GlobalHelper;
import com.dmd.favoriteplacesjavaversion.R;
import com.dmd.favoriteplacesjavaversion.fragments.ListFragment;
import com.dmd.favoriteplacesjavaversion.fragments.MapsFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private Fragment listFragment = new ListFragment();
    private Fragment mapFragment = new MapsFragment();
    private ImageButton imageButton;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.sign_out){
            mAuth.signOut();
            finish();
            GlobalHelper.openNewActivity(getApplicationContext(), LoginActivity.class);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.sign_out,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        changeFragment(listFragment);
        mAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
        Uri uri = Uri.parse("android.resource://" + getApplicationContext().getPackageName()+"/drawable/show_password");
        UUID uuid = UUID.randomUUID();
        String imageName = "images/profile_images/" +  uuid + ".jpg";
        storageReference.child(imageName).putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Log.i("storageReferenceUpload", "onSuccess: success");
            }
        }). addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("storageReferenceUpload", "onSuccess: fail");
            }
        });

        StorageReference newRe = FirebaseStorage.getInstance().getReference();
        newRe.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String download = uri.toString();
            }
        });

        FirebaseUser user = mAuth.getCurrentUser();

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

    @Override
    public void onBackPressed() {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("ÇIKIŞ?");
        alertDialog.setMessage("Çıkmak istiyor musun?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "tamam", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();    }
}
