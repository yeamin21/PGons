package com.hoyt.pigeondb.pairs;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hoyt.pigeondb.R;

import java.util.Calendar;

public class AddPair extends AppCompatActivity {
    FirebaseDatabase f;

    Button addpair, getpair;
    EditText pF, pM, asD;
    Calendar clndr;
    int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pair);
        final DatabaseReference rf = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getUid()).child("Pairs");


        clndr = Calendar.getInstance();
        mYear = clndr.get(Calendar.YEAR);
        mDay = clndr.get(Calendar.DAY_OF_MONTH);
        mMonth = clndr.get(Calendar.MONTH);

        addpair = findViewById(R.id.addPair);
        pF = findViewById(R.id.txt_pgnFather);
        pM = findViewById(R.id.txt_pgnMother);
        asD = findViewById(R.id.assDate);
        asD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddPair.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        asD.setText(year + "-" + month + 1 + "-" + dayOfMonth);
                    }
                }, mYear, mMonth, mDay).show();
            }
        });

        addpair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sF = pF.getText().toString().trim();
                String sM = pM.getText().toString().trim();
                String dA = asD.getText().toString();
                Pairs p = new Pairs(sF, sM, dA);
                rf.child(sF + "+" + sM).child("Info").setValue(p);

            }
        });


    }


}




