package com.hoyt.pigeondb.pigeons;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hoyt.pigeondb.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PigeonAdapter extends RecyclerView.Adapter<PigeonAdapter.PigeonHolder> {
    ArrayList<Pigeons> pgn;
    Context cont;

    public PigeonAdapter(ArrayList<Pigeons> pgn, Context cont) {
        this.pgn = pgn;
        this.cont = cont;
    }

    public void onBindViewHolder(@NonNull PigeonHolder holder, final int position) {
        holder.bind(pgn.get(position));
    }

    @NonNull
    @Override
    public PigeonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context x = parent.getContext();
        final View itemView = LayoutInflater.from(x)
                .inflate(R.layout.pigeon_cardpigeon, parent, false);
        return new PigeonHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return pgn.size();
    }

    public class PigeonHolder extends RecyclerView.ViewHolder {
        CircleImageView imvw;
        View itemView;
        TextView pgnNo, pgnF, pgnM, pgnG, pgnGr,pgnClr;
        Button delete;
        ConstraintLayout expandable;

        public PigeonHolder(@NonNull final View itemView) {

            super(itemView);
            this.itemView = itemView;
            pgnNo = itemView.findViewById(R.id.pgnNO);
            imvw = itemView.findViewById(R.id.circlImg);
            pgnF = itemView.findViewById(R.id.txt_ftpgn);
            pgnM = itemView.findViewById(R.id.txt_mtr);
            pgnG = itemView.findViewById(R.id.txt_gender);
            pgnGr = itemView.findViewById(R.id.txt_grp);
            delete=itemView.findViewById(R.id.delete);

    pgnClr=itemView.findViewById(R.id.pgn_clr);
           /*
            expand = itemView.findViewById(R.id.expand_pigeon);
            expandable = itemView.findViewById(R.id.expandable_pigeon);

            expand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (expandable.getVisibility() == View.VISIBLE)
                        expandable.setVisibility(View.GONE);
                    else if (expandable.getVisibility() == View.GONE)
                        expandable.setVisibility(View.VISIBLE);
                }
            }); */
        }


        void bind(final Pigeons pn) {
            pgnF.setText(pn.getFathersID());
            pgnM.setText(pn.getMothersID());
            pgnG.setText(pn.getGender());
            pgnGr.setText(pn.getGroup());
            pgnClr.setText(pn.getColor());
            pgnNo.setText(pn.getPigeonID());
            System.out.println(pn.getFkey());
            Picasso.get()
                    .load(pn.getPicURL())
                    .fit()
                    .centerCrop()
                    .into(imvw);


            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseReference rf = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getUid()).child("Pigeons").child(pn.getFkey());

                    rf.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(itemView.getContext(),"Deleted",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

        }



    }


}




