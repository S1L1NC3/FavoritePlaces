package com.dmd.favoriteplacesjavaversion.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dmd.favoriteplacesjavaversion.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreenActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        /*
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);

        mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i("FirebaseAuthentication", "signInAnonymously:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.i("FirebaseAuthentication", "signInAnonymously:success");
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.i("FirebaseAuthentication", "signInAnonymously:failure", task.getException());
                            Toast.makeText(SplashScreenActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });

         */
    }
}
