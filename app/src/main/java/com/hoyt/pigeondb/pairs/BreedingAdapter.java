package com.hoyt.pigeondb.pairs;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hoyt.pigeondb.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BreedingAdapter extends RecyclerView.Adapter<BreedingAdapter.BreedingHolder> {

    String pair;
    ArrayList<Eggs> eg;
    Context c;
    DatabaseReference rf;

    public BreedingAdapter(Context c, ArrayList<Eggs> eg, DatabaseReference rf, String pair) {
        this.c = c;
        this.eg = eg;
        this.rf = rf;
        this.pair = pair;
    }

    @NonNull
    @Override
    public BreedingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context c = parent.getContext();
        final View itemView = LayoutInflater.from(c).
                inflate(R.layout.pair_breeding_info, parent, false);
        return new BreedingHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BreedingHolder holder, int position) {
        holder.bind(eg.get(position));
    }


    @Override
    public int getItemCount() {
        return eg.size();
    }


    public class BreedingHolder extends RecyclerView.ViewHolder {
        Button expand, update,dlt;
        TextView breedingdate, hatchingdate, status;
        ConstraintLayout expandable;
        View itemView;
        EditText picker;
        Calendar cl;
        int mYear, mDay, mMonth;
        Spinner breedingstatus;

        public BreedingHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            expand = itemView.findViewById(R.id.expand);
            expandable = itemView.findViewById(R.id.expandable_breeding);
            breedingdate = itemView.findViewById(R.id.txt_LayingDate);
           dlt = itemView.findViewById(R.id.delete);
            picker = itemView.findViewById(R.id.txt_pickDate);
            cl = Calendar.getInstance();

            mYear = cl.get(Calendar.YEAR);
            mMonth = cl.get(Calendar.MONTH);
            mDay = cl.get(Calendar.DAY_OF_MONTH);
            update = itemView.findViewById(R.id.btn_update);
            breedingstatus = itemView.findViewById(R.id.spnr_breedingStatus);
            status = itemView.findViewById(R.id.status);

        }

        void bind(final Eggs eg) {



            breedingdate.setText(eg.getLaying());

            if(eg.getStatus().equals("Expected"))
            {
                status.setText("Expected Hatching"+ "  " + eg.getHatching());
            }
            else if(eg.getStatus().equals("Hatched"))
            {
                status.setText("Hatched on "+ eg.getHatching());
            }
            else if(eg.getStatus().equals("Infertile"))
            {
                status.setText("Infertile, Checked on "+eg.getHatching());
            }
            else if(eg.getStatus().equals("Did not Hatch"))
            {
                status.setText("Did not Hatch on "+eg.getHatching());
            }
dlt.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        rf.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(itemView.getContext(),"Deleted",Toast.LENGTH_SHORT).show();
            }
        });
    }
});

            expand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (expandable.getVisibility() == View.VISIBLE)
                        expandable.setVisibility(View.GONE);
                    else if (expandable.getVisibility() == View.GONE)
                        expandable.setVisibility(View.VISIBLE);
                }
            });
picker.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        new DatePickerDialog(itemView.getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                SimpleDateFormat simpleDateFormat=   new SimpleDateFormat("dd-MM-YYYY");
             Calendar cc=Calendar.getInstance();
             cc.set(year,month,dayOfMonth);

                picker.setText(simpleDateFormat.format(cc.getTime()));

            }
        }, mYear, mMonth, mDay).show();
    }
});

            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rf = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getUid()).child("Pairs").child(pair).child("Breeding").child(eg.getKey());


                  Map<String, Object> p = new HashMap<>();
                    p.put("/hatching", picker.getText().toString());
                    p.put("/status", breedingstatus.getSelectedItem().toString());
                    rf.updateChildren(p).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(itemView.getContext(), "Success", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(itemView.getContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            });
        }
    }
}
