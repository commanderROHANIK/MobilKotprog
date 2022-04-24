package com.example.mukormosidopontfoglalo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MenuActivity extends AppCompatActivity {

    private static final String LOG_TAG = LoginActivity.class.getName();

    final Calendar myCalendar = Calendar.getInstance();
    int orak = 0;
    int percek = 0;
    TextView tvDate;
    Button btPickDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        tvDate = findViewById(R.id.textView);
        btPickDate = findViewById(R.id.idopontFoglalas);

        TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                orak = i;
                percek = i1;
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
        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat);
        tvDate.setText(dateFormat.format(myCalendar.getTime()) + " " + orak + ":" + percek);
    }

    public void gotToIdopontFoglalo(View view) {
    }

    public void goToKatalogus(View view) {
    }
}