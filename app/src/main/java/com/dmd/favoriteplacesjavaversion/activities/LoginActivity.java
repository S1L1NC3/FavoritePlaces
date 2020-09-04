package com.dmd.favoriteplacesjavaversion.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.dmd.favoriteplacesjavaversion.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button loginButton;
    private  ImageButton showHidePasswordButton;
    private  EditText editTextEmail, editTextPassword;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.loginButton);
        showHidePasswordButton = findViewById(R.id.showHidePasswordButton);
        editTextEmail = findViewById(R.id.editTextTextEmail);
        editTextPassword = findViewById(R.id.editTextTextPassword);

        showHidePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextPassword.getTransformationMethod() == HideReturnsTransformationMethod.getInstance()){
                    editTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    showHidePasswordButton.setImageResource(R.drawable.show_password);
                } else {
                    editTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    showHidePasswordButton.setImageResource(R.drawable.hide_password);
                }
                editTextPassword.setSelection(editTextPassword.getText().length());
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = editTextEmail.getText().toString();
                final String password = editTextPassword.getText().toString();
                if (isInputsValidForLogin(email,password)){
                    loginViaEmailPassword(email,password);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
    }

    private Boolean isInputsValidForLogin(String email, String password){
        if (email == null || email.equals("")){
            editTextEmail.setError(getResources().getString(R.string.email_cant_be_empty));
            return false;
        } else if (!validEmail(email)){
            editTextEmail.setError(getResources().getString(R.string.email_wrong));
            return  false;
        } else if (password == null || password.equals("")){
            editTextPassword.setError(getResources().getString(R.string.password_empty));
            return  false;
        }
        return true;
    }

    private boolean validEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    private void createUserViaEmailPassword(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i("FirebaseAuthentication", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.i("FirebaseAuthentication", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void loginViaEmailPassword(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i("FirebaseAuthentication", "signInWithEmail:success");
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.i("FirebaseAuthentication", "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}
