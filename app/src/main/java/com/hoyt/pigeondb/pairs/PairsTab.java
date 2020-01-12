package com.hoyt.pigeondb.pairs;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
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

public class PairsTab extends Fragment {
    Intent i;
    FloatingActionButton fb;


    //TextView tPF,tPM;
    RecyclerView rV;
    ArrayList<Pairs> pair = new ArrayList<>();
    PairsAdapter pad;


    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View Root = inflater.inflate(R.layout.all_pairs_fragment, container, false);
        DatabaseReference rf = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getUid()).child("Pairs");
        rf.keepSynced(true);
        i = new Intent(getActivity(), AddPair.class);
        rV = Root.findViewById(R.id.rView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rV.setLayoutManager(mLayoutManager);

        fb = Root.findViewById(R.id.addBreeding);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(i);
            }
        });


        rf.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pair.clear();
                for (DataSnapshot pairSnapshot : dataSnapshot.getChildren()) {
                    Pairs infoSnapshot = pairSnapshot.child("Info").getValue(Pairs.class);
                    pair.add(infoSnapshot);

                }
                pad = new PairsAdapter(getActivity(), pair);
                rV.setAdapter(pad);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return Root;
    }


}
