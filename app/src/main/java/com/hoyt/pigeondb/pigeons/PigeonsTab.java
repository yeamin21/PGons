package com.hoyt.pigeondb.pigeons;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hoyt.pigeondb.R;

import java.util.ArrayList;

public class PigeonsTab extends Fragment {
    private FloatingActionButton fb;
    private   Intent i;
    private  ArrayList<Pigeons> pg;
    private  PigeonAdapter pgAd;
    private RecyclerView rV;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View Root = inflater.inflate(R.layout.all_pigeons_fragment, container, false);
        DatabaseReference rf = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getUid()).child("Pigeons");
        rf.keepSynced(true);

        pg = new ArrayList<>();


        rV = Root.findViewById(R.id.rclr_pigeon);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        rV.setLayoutManager(mLayoutManager);
        rf.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pg.clear();
                for (DataSnapshot pigeonSnap : dataSnapshot.getChildren()) {
                    Pigeons p = pigeonSnap.child("Basic Info").getValue(Pigeons.class);
                    p.setFkey(pigeonSnap.getKey());
                    pg.add(p);
                }
                pgAd = new PigeonAdapter(pg, getActivity());
                rV.setAdapter(pgAd);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        fb = Root.findViewById(R.id.floatingActionButton3);

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(getActivity(), AddPigeon.class);
                startActivity(i);
            }
        });
        return Root;
    }


}
