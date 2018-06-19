package com.example.vishalsingh.villageexpandedview;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FirebaseGallery extends AppCompatActivity {

    DatabaseReference database;
    StorageReference mstorage;
    ProgressDialog progressDialog;

    ImageView i1,i2,i3,i4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_gallery);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data.....");
        progressDialog.show();

        i1 = (ImageView)findViewById(R.id.galleryImage1);
        i2 = (ImageView)findViewById(R.id.galleryImage2);
        i3 = (ImageView)findViewById(R.id.galleryImage3);
        i4 = (ImageView)findViewById(R.id.galleryImage4);




        FirebaseDatabase f = FirebaseDatabase.getInstance();
        database = f.getReference().child("VillageRelation").child(getIntent().getExtras().getString("villageName")).child("DescriptionRelation");


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mstorage= FirebaseStorage.getInstance().getReference();
                Glide.with(FirebaseGallery.this).using(new FirebaseImageLoader())
                        .load(mstorage.child(dataSnapshot.child("galleryImage1").getValue().toString()))
                        .into(i1);
                Glide.with(FirebaseGallery.this).using(new FirebaseImageLoader())
                        .load(mstorage.child(dataSnapshot.child("galleryImage2").getValue().toString()))
                        .into(i2);
                Glide.with(FirebaseGallery.this).using(new FirebaseImageLoader())
                        .load(mstorage.child(dataSnapshot.child("galleryImage3").getValue().toString()))
                        .into(i3);
                Glide.with(FirebaseGallery.this).using(new FirebaseImageLoader())
                        .load(mstorage.child(dataSnapshot.child("galleryImage4").getValue().toString()))
                        .into(i4);

                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });


    }
}
