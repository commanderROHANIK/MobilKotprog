package com.example.mukormosidopontfoglalo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getName();
    private static final String PREF_KEY = MainActivity.class.getPackage().toString();
    private static final int RC_SIGN_IN = 123;
    private static final int SECRET_KEY = 666;

    private SharedPreferences preferences;
    private FirebaseAuth auth;
    private GoogleSignInClient googleSignInClient;

    EditText userName;
    EditText password;

    public void loginAsGuest(View view) {
        auth.signInAnonymously().addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                login(task);
            }
        });
    }

    private void login(@NonNull Task<AuthResult> task) {
        if(task.isSuccessful()){
            anonymLoginSuccess();
        } else {
            anonymLoginFail(task);
        }
    }

    private void anonymLoginFail(@NonNull Task<AuthResult> task) {
        Log.d(LOG_TAG, "Anonym user log in fail");
        Toast.makeText(MainActivity.this, "User log in fail: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
    }

    private void anonymLoginSuccess() {
        Log.d(LOG_TAG, "Anonym user loged in successfully");
        startShopping();
    }

    private void startShopping() {
        Intent intent = new Intent(this, NailsListActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setUserName(findViewById(R.id.editTextUserName));
        setPassword(findViewById(R.id.editTextPassword));
        setPreferences(getSharedPreferences(PREF_KEY, MODE_PRIVATE));
        setAuth(FirebaseAuth.getInstance());
        setGoogleSignInClient();
    }

    public void register(View view) {
        Intent intent = new Intent(this, RegistrationActivity.class);
        intent.putExtra("SECRET_KEY", SECRET_KEY);
        startActivity(intent);
    }

    public void login(View view) {
    }

    public void loginWithGoogle(View view) {
    }

    public void setPreferences(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public void setAuth(FirebaseAuth auth) {
        this.auth = auth;
    }

    public void setGoogleSignInClient() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        this.googleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    public void setUserName(EditText userName) {
        this.userName = userName;
    }

    public void setPassword(EditText password) {
        this.password = password;
    }
}