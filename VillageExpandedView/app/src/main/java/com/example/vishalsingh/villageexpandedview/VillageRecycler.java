package com.example.vishalsingh.villageexpandedview;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static com.example.vishalsingh.villageexpandedview.PermissionActivity.count;


public class VillageRecycler extends AppCompatActivity implements RecyclerViewAdapterCuisine.OnItemClicked{

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapterCuisine adapter;

    FirebaseDatabase database = null;
    DatabaseReference myRef = null;
    ArrayList<Cuisine> cuisines;


    ProgressDialog progressDialog ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_village_recycler);

        cuisines = new ArrayList<>();

        progressDialog = new ProgressDialog(VillageRecycler.this);

        progressDialog.setMessage("Loading Images From Firebase.");
        progressDialog.show();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewCharacteristic);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(VillageRecycler.this);
        recyclerView.setLayoutManager(layoutManager);
        database = FirebaseDatabase.getInstance();

        myRef = database.getReference("VillageRelation").child(getIntent().getExtras().getString("villageName")).child("CuisineRelation");
        Toast.makeText(this, myRef.toString(), Toast.LENGTH_SHORT).show();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snp : dataSnapshot.getChildren()) {
                    Toast.makeText(VillageRecycler.this, snp.child("images").getValue().toString(), Toast.LENGTH_SHORT).show();
                    cuisines.add(new Cuisine(snp.child("images").getValue().toString(),
                            snp.child("description").getValue().toString(),
                            Double.parseDouble(snp.child("rating").getValue().toString()),
                            snp.getKey().toString(),
                            "",
                            snp.child("price").getValue().toString(),
                            "",
                            "shopname",
                            "Katol Road",
                            Integer.parseInt(snp.child("category").getValue().toString()),
                            "",
                            ""));
                    cuisines.add(new Cuisine(snp.child("images").getValue().toString() ,snp.child("description").getValue().toString(),Double.parseDouble(snp.child("rating").getValue().toString()),snp.getKey().toString(),"",snp.child("price").getValue().toString(),"","shopname","Katol Road",Integer.parseInt(snp.child("category").getValue().toString()),"",""));
                }
                adapter = new RecyclerViewAdapterCuisine(getApplicationContext(), cuisines);
                adapter.setOnClick(VillageRecycler.this);
                recyclerView.setAdapter(adapter);
                progressDialog.dismiss();
            }
            @Override
            public void onCancelled(DatabaseError error) {
                progressDialog.dismiss();
            }
        });
    }


    @Override
    public void onItemClick(int position) {
        startActivity(new Intent(VillageRecycler.this,Main2Activity.class));
    }
}
