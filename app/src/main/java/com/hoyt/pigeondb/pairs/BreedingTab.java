package com.hoyt.pigeondb.pairs;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hoyt.pigeondb.R;

import java.util.ArrayList;

public class BreedingTab extends AppCompatActivity {

    TextView pp;
    ArrayList<Eggs> eg = new ArrayList<>();
    RecyclerView rV;
    DatabaseReference rf;
    BreedingAdapter pad;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_eggs);

        Intent i = getIntent();

        pp = findViewById(R.id.txt_maleP);
        final String pair = i.getStringExtra("PAIR");

        rV = findViewById(R.id.recView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rV.setLayoutManager(mLayoutManager);
        rf = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getUid()).child("Pairs").child(pair).child("Breeding");

        rf.addValueEventListener(new ValueEventListener() {

            @Override

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                eg.clear();
                for (DataSnapshot breedingSnap : dataSnapshot.getChildren()) {
                    Eggs data = breedingSnap.getValue(Eggs.class);
                    data.setKey(breedingSnap.getKey());
                    eg.add(data);
                }
                pad = new BreedingAdapter(getParent(), eg,rf,pair);
                rV.setAdapter(pad);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
