package com.hoyt.pigeondb.pairs;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hoyt.pigeondb.R;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddPair extends AppCompatActivity {
ArrayList<Suggest_Pigeon>sgP=new ArrayList<>();
EditText asD;
    Button addpair;
    Calendar clndr;
    int mYear, mMonth, mDay;
    AutoCompleteTextView pF,pM;
    DatabaseReference rf;
    DatabaseReference rc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pair);


      rf = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getUid()).child("Pairs");
rc = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getUid()).child("Pigeons");

         final ArrayAdapter<String> adptrHen = new ArrayAdapter<>(this, R.layout.autocomplete_suggestionlist);
        final ArrayAdapter<String> adptrCock= new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item);

        clndr = Calendar.getInstance();
        mYear = clndr.get(Calendar.YEAR);
        mDay = clndr.get(Calendar.DAY_OF_MONTH);
        mMonth = clndr.get(Calendar.MONTH);
        addpair = findViewById(R.id.addPair);
        pF = findViewById(R.id.txt_pgnFather);
        pM = findViewById(R.id.txt_pgnMother);
        asD = findViewById(R.id.assDate);
asD.setOnTouchListener(new View.OnTouchListener() {
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            new DatePickerDialog(AddPair.this, new DatePickerDialog.OnDateSetListener() {
                @Override

                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    asD.setText(dayOfMonth + "-" + (month + 1) + "-" + year);



                }
            }, mYear, mMonth, mDay).show();
        }
        return false;
    }
});

        addpair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sF = pF.getText().toString().trim();
                String sM = pM.getText().toString().trim();
                String dA = asD.getText().toString();
                Pairs p = new Pairs(sF, sM, dA);
                rf.child(sF + sM).child("Info").setValue(p).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }
        });


        rc.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String gender = ds.child("Basic Info").child("gender").getValue(String.class).trim();
                    String sug = ds.child("Basic Info").child("pigeonID").getValue(String.class).trim();
                    String imgurl=ds.child("Basic Info").child("picURL").getValue(String.class).trim();
                    if(gender.equals("Hen"))
                    {
                        Suggest_Pigeon sg=new Suggest_Pigeon();
                        sg.setGender(gender);
                        sg.setId(sug);
                        sg.setUrl(imgurl);
                        sgP.add(sg);

                    }
                    else if (gender.equals("Cock"))
                    {
                       adptrCock.add(sug);
                    }

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        pM.setAdapter(adptrHen);
        pF.setAdapter(adptrCock);

    }
}
class suggs extends ArrayAdapter{

    public suggs(@NonNull Context context, int resource) {
        super(context, resource);
    }

}






