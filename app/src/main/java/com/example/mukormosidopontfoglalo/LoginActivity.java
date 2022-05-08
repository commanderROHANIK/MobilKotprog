package com.example.mukormosidopontfoglalo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private static final String LOG_TAG = LoginActivity.class.getName();
    private static final String PREF_KEY = LoginActivity.class.getPackage().toString();
    private static final int RC_SIGN_IN = 123;
    private static final int SECRET_KEY = 666;
    public static boolean isAnonym = false;

    private SharedPreferences preferences;
    private FirebaseAuth auth;

    EditText userName;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.userName = findViewById(R.id.editTextUserName);
        this.password = findViewById(R.id.editTextPassword);
        this.preferences = getSharedPreferences(PREF_KEY, MODE_PRIVATE);
        this.auth = FirebaseAuth.getInstance();
    }

    public void login(View view) {
        String userName = this.userName.getText().toString();
        String password = this.password.getText().toString();

        auth.signInWithEmailAndPassword(userName, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    isAnonym = false;
                    startShopping();
                } else {
                    Toast.makeText(LoginActivity.this, "User log in fail: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void loginAsGuest(View view) {
        auth.signInAnonymously().addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    isAnonym = true;
                    startShopping();
                } else {
                    Toast.makeText(LoginActivity.this, "User log in fail: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void startShopping() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }


    public void register(View view) {
        Intent intent = new Intent(this, RegistrationActivity.class);
        intent.putExtra("SECRET_KEY", SECRET_KEY);
        startActivity(intent);
    }
}