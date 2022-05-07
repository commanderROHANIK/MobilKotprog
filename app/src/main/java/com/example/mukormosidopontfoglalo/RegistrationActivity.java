package com.example.mukormosidopontfoglalo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegistrationActivity extends AppCompatActivity {

    private static final String LOG_TAG = RegistrationActivity.class.getName();
    private static final String PREF_KEY = RegistrationActivity.class.getPackage().toString();
    private static final int SECRET_KEY = 666;

    private SharedPreferences preferences;
    private FirebaseAuth auth;
    private FirebaseFirestore firestore;
    private CollectionReference users;

    EditText userName;
    EditText userEmail;
    EditText password;
    EditText passwordConfirm;
    EditText phone;

    public void cancel(View view) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        checkSecretKey();

        userName = findViewById(R.id.userNameEditText);
        userEmail = findViewById(R.id.userEmailEditText);
        password = findViewById(R.id.passwordEditText);
        passwordConfirm = findViewById(R.id.passwordAgainEditText);
        phone = findViewById(R.id.phoneEditText);
        preferences = getSharedPreferences(PREF_KEY, MODE_PRIVATE);
        auth = FirebaseAuth.getInstance();

        this.userName.setText(preferences.getString("userName", ""));
        this.password.setText(preferences.getString("password", ""));
        passwordConfirm.setText(preferences.getString("password", ""));

        firestore = FirebaseFirestore.getInstance();
        users = firestore.collection("Users");
    }

    private void checkSecretKey() {
        if (getIntent().getIntExtra("SECRET_KEY", 0) != 666) {
            finish();
        }
    }

    public void register(View view) {
        String userName = this.userName.getText().toString();
        String email = userEmail.getText().toString();
        String password = this.password.getText().toString();
        String passwordConfirm = this.passwordConfirm.getText().toString();

        if (checkPassword(password, passwordConfirm)) return;
        Log.i(LOG_TAG, "Regisztrált: " + userName + ", e-mail: " + email);
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, getOncompleteListener());
    }

    private boolean checkPassword(String password, String passwordConfirm) {
        if (!password.equals(passwordConfirm)) {
            Log.e(LOG_TAG, "Nem egyenlő a jelszó és a megerősítése.");
            return true;
        }
        return false;
    }

    private OnCompleteListener getOncompleteListener() {
        return new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                registrate(task);
            }
        };
    }

    private void registrate(@NonNull Task<AuthResult> task) {
        if(task.isSuccessful()){
            registrationSuccess();
        } else {
            registrationFail(task);
        }
    }

    private void registrationFail(@NonNull Task<AuthResult> task) {
        Log.d(LOG_TAG, "User wasn't created successfully");
        Toast.makeText(
                RegistrationActivity.this,
                "User was't created successfully: " + task.getException().getMessage(),
                Toast.LENGTH_LONG
        ).show();
    }

    private void registrationSuccess() {
        Log.d(LOG_TAG, "User created successfully");
        users.add(new User(userEmail.getText().toString(), userName.getText().toString()));
        startShopping();
    }

    private void startShopping() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}