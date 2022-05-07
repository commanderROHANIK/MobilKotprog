package com.example.mukormosidopontfoglalo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

public class IdopontFoglaloActivity extends AppCompatActivity {

    DatePicker datum;
    TimePicker idopont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idopont_foglalo);

//        userEmail = findViewById(R.id.userEmailEditText);
    }

    public void allocate(View view) {

    }

    public void cancel(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}