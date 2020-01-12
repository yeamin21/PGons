package com.hoyt.pigeondb;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;


public class AddBreedingHistory extends AppCompatActivity {
    Calendar clndr;
    EditText layDay, hatchDate, bPgnNo;
    Button b;
    CheckBox c;
    RadioGroup rg;
    int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_breeding_history);

        clndr = Calendar.getInstance();
        mYear = clndr.get(Calendar.YEAR);
        mDay = clndr.get(Calendar.DAY_OF_MONTH);
        mMonth = clndr.get(Calendar.MONTH);
        bPgnNo = findViewById(R.id.txt_Bpgn);
        hatchDate = findViewById(R.id.txt_HDate);
        layDay = findViewById(R.id.txt_LDate);
        rg = findViewById(R.id.rg_gender);
        int x;
        c = findViewById(R.id.cb);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (c.isChecked()) {
                    hatchDate.setVisibility(View.VISIBLE);

                    bPgnNo.setVisibility(View.VISIBLE);
                    rg.setVisibility(View.VISIBLE);
                } else {
                    hatchDate.setVisibility(View.INVISIBLE);
                    bPgnNo.setVisibility(View.INVISIBLE);
                    rg.setVisibility(View.INVISIBLE);
                }

            }
        });


        layDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddBreedingHistory.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        layDay.setText(year + "-" + month + "-" + dayOfMonth);
                    }
                }, mYear, mMonth, mDay).show();
            }
        });
        hatchDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddBreedingHistory.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        hatchDate.setText(year + "-" + month + "-" + dayOfMonth);
                    }
                }, mYear, mMonth, mDay).show();
            }
        });

    }
}





