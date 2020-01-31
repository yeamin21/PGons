package com.hoyt.pigeondb.pigeons;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.hoyt.pigeondb.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class AddPigeon extends AppCompatActivity {
    Button bAddPigeon, bAddPhoto;
    EditText txtPgnNo, txtPgnMID, txtPgnFID, txtPgnGrp;
    RadioGroup rgGender;
    Intent i;
    DatabaseReference ref;
    StorageReference mStorageRef;
    Uri imgui;
    CircleImageView imv;
    String url, PN, PG, MID, FID, GN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pgn_addnew);
        bAddPigeon = findViewById(R.id.b_addPigeon);
        txtPgnNo = findViewById(R.id.txt_pgnNo);
        txtPgnGrp = findViewById(R.id.txt_pgnGrp);
        txtPgnMID = findViewById(R.id.txt_pgnMother);
        txtPgnFID = findViewById(R.id.txt_pgnFather);
        rgGender = findViewById(R.id.rg_gender);
        imv = findViewById(R.id.pgnPic);
        final Intent i = new Intent(this, PigeonsTab.class);
        bAddPigeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mStorageRef = FirebaseStorage.getInstance().getReference(FirebaseAuth.getInstance().getUid()).child("PigeonPhotos");
                ref = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getUid()).child("Pigeons");
                ref.keepSynced(true);
                int rbID = rgGender.getCheckedRadioButtonId();
                RadioButton radioButtonSelected = findViewById(rbID);
                PN = txtPgnNo.getText().toString().trim();
                PG = txtPgnGrp.getText().toString().trim();
                MID = txtPgnMID.getText().toString().trim();
                FID = txtPgnFID.getText().toString().trim();
                GN = radioButtonSelected.getText().toString().trim();
                final StorageReference sref = mStorageRef.child(PN);


                sref.putFile(imgui).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        sref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                url = uri.toString();
                                Pigeons p = new Pigeons(PN, PG, GN, MID, FID, url);
                                Task t = ref.child(PN).child("Basic Info").setValue(p);
                                t.addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                                        finish();
                                        startActivity(i);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        });
                    }
                });


            }
        });
        imv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChooseImage();
            }
        });

    }

    private void ChooseImage() {
        Intent in = new Intent();
        in.setType("image/*");
        in.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(in, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imgui = data.getData();

            Picasso.get()
                    .load(imgui)
                    .fit()
                    .centerCrop()
                    .into(imv);

        }
    }

    private String getImageExtension(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }


}
