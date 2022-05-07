package com.example.mukormosidopontfoglalo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MenuActivity extends AppCompatActivity {

    private static final String LOG_TAG = LoginActivity.class.getName();
    final Calendar myCalendar = Calendar.getInstance();
    int orak = 0;
    int percek = 0;
    FirebaseAuth auth;
    TextView tvDate;
    Button btPickDate;
    NotificationHandler notificationHandler;
    private FirebaseFirestore firestore;
    private CollectionReference idopontok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        notificationHandler = new NotificationHandler(this);
        tvDate = findViewById(R.id.textView);
        btPickDate = findViewById(R.id.idopontFoglalas);
        auth = FirebaseAuth.getInstance();

        firestore = FirebaseFirestore.getInstance();
        idopontok = firestore.collection("Idopontok");

        TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                orak = timePicker.getHour();
                percek = timePicker.getMinute();
                updateLabel();
            }
        };

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, day);

                TimePickerDialog timePickerDialog = new TimePickerDialog(MenuActivity.this, time, orak, percek, true);
                timePickerDialog.show();
            }
        };

        btPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MenuActivity.this,
                        date,
                        myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
            }
        });
    }

    private void updateLabel() {
        if (auth.getCurrentUser() != null){
            String myFormat = "yyyy-MM-dd";
            SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat);
            tvDate.setText(dateFormat.format(myCalendar.getTime()) + " " + orak + ":" + percek);
            notificationHandler.send(dateFormat.format(myCalendar.getTime()) + " " + orak + ":" + percek);
            idopontok.add(new Idopont(auth.getCurrentUser().getUid(), dateFormat.format(myCalendar.getTime()) + " " + orak + ":" + percek));
        } else {
            Toast.makeText(MenuActivity.this, "Hiba: Anonim bejelentkezés után nincs lehetőség időpontot foglalni", Toast.LENGTH_LONG).show();
        }

    }

    public void gotToIdopontFoglalo(View view) {
    }

    public void goToKatalogus(View view) {
        Intent intent = new Intent(this, NailsListActivity.class);
        intent.putExtra("SECRET_KEY", 69);

        startActivity(intent);
    }

    @Override
    protected void onPause() {
        FirebaseAuth.getInstance().signOut();
        super.onPause();
    }
}