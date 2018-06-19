package com.example.vishalsingh.villageexpandedview;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class RecyclerAccomodation extends AppCompatActivity implements RecyclerViewAdapterCuisine.OnItemClicked {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapterAccomodation adapter;

    StorageReference mStorageRef;
    FirebaseDatabase database = null;
    DatabaseReference myRef = null;
    ArrayList<Accomodation> accomodations;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_village_recycler);

        accomodations = new ArrayList<>();

        progressDialog = new ProgressDialog(RecyclerAccomodation.this);

        progressDialog.setMessage("Loading Firebase...");
        progressDialog.show();


        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewCharacteristic);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(RecyclerAccomodation.this);
        recyclerView.setLayoutManager(layoutManager);
        database = FirebaseDatabase.getInstance();

        myRef = database.getReference("VillageRelation").child(getIntent().getExtras().getString("villageName")).child("HostRelation");


        Toast.makeText(this, myRef.toString(), Toast.LENGTH_SHORT).show();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snp : dataSnapshot.getChildren()) {

                    Address a = new Address( snp.child("address").child("plotno").getValue().toString(),
                            snp.child("address").child("locality").getValue().toString(),
                            snp.child("address").child("street").getValue().toString(),
                            snp.child("address").child("district").getValue().toString(),
                            snp.child("address").child("pincode").getValue().toString(),
                            snp.child("address").child("state").getValue().toString());

                    accomodations.add(new Accomodation(a,
                            snp.child("price").getValue().toString(),
                            snp.getKey().toString(),
                            snp.child("contact").getValue().toString(),
                            Integer.parseInt(snp.child("capacityEachRoom").getValue().toString()),
                            snp.child("image1").getValue().toString(),
                            snp.child("image2").getValue().toString(),
                            Integer.parseInt(snp.child("roomsAvailable").getValue().toString()),
                            snp.child("email").getValue().toString(),
                            Double.parseDouble(snp.child("rating").getValue().toString()),
                            snp.child("nameOfplace").getValue().toString()));
                }
                adapter = new RecyclerViewAdapterAccomodation(getApplicationContext(), accomodations);
                adapter.setOnClick(RecyclerAccomodation.this);
                recyclerView.setAdapter(adapter);
                progressDialog.dismiss();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public void onItemClick(int position) {

    }
}