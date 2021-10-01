package com.example.portfolio.stylescostumdatetimepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.portfolio.MainActivity;
import com.example.portfolio.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CustomDateTimePicker extends AppCompatActivity {

    private EditText datePick;
    private EditText timePick;
    private EditText dateAndTimePick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_date_time_picker);

        datePick = findViewById(R.id.datePickerId);
        timePick = findViewById(R.id.timePickerId);
        dateAndTimePick = findViewById(R.id.dateAndTimePickerId);

        dateAndTimePick.setInputType(InputType.TYPE_NULL);
        datePick.setInputType(InputType.TYPE_NULL);
        timePick.setInputType(InputType.TYPE_NULL);

        datePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(datePick);
            }
        });

        timePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog(timePick);
            }
        });

        dateAndTimePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimeDialog(dateAndTimePick);
            }
        });

    }

    private void showDateTimeDialog(EditText dateAndTimePick) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
                        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm dd-MM-yyyy");

                        dateAndTimePick.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                };
                new TimePickerDialog(CustomDateTimePicker.this,
                        timeSetListener,
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        true
                ).show();
            }
        };

        new DatePickerDialog(CustomDateTimePicker.this,
                R.style.MyAppTheme,
                dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MINUTE),
                calendar.get(Calendar.DAY_OF_MONTH)
        ).show();
    }


    private void showTimeDialog(EditText timePick) {
        Calendar calendar = Calendar.getInstance();

        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                timePick.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };

        new TimePickerDialog(CustomDateTimePicker.this,
                timeSetListener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
        ).show();
    }


    private void showDateDialog(EditText datePick) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                datePick.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };
        new DatePickerDialog(CustomDateTimePicker.this,
                R.style.MyAppTheme,
                dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        ).show();
    }

}