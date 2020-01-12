package com.hoyt.pigeondb.pairs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.hoyt.pigeondb.R;

import java.util.ArrayList;

public class BreedingAdapter extends RecyclerView.Adapter<BreedingAdapter.BreedingHolder> {

    ArrayList<Eggs> eg;
    Context c;

    public BreedingAdapter(Context c, ArrayList<Eggs> eg) {
        this.c = c;
        this.eg = eg;
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
        Button expand;
        TextView breedingdate;
        ConstraintLayout expandable;
        View itemView;

        public BreedingHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            expand = itemView.findViewById(R.id.expand);
            expandable = itemView.findViewById(R.id.expandable_breeding);
            breedingdate = itemView.findViewById(R.id.txt_LayingDate);

            expand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (expandable.getVisibility() == View.VISIBLE)
                        expandable.setVisibility(View.GONE);
                    else if (expandable.getVisibility() == View.GONE)
                        expandable.setVisibility(View.VISIBLE);
                }
            });

        }

        void bind(Eggs eg) {
            breedingdate.setText(eg.getLaying());
        }
    }
}
