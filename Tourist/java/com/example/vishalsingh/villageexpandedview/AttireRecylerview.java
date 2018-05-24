package com.example.vishalsingh.villageexpandedview;

import android.app.ProgressDialog;
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

import java.util.ArrayList;

public class AttireRecylerview extends AppCompatActivity //implements AdapterAttire.OnItemClicked
 {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    AdapterAttire adapter;

    FirebaseDatabase database = null;
    DatabaseReference myRef = null;
    ArrayList<Attire> attires;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_village_recycler);
        Toast.makeText(AttireRecylerview.this,"Clothing Of The Tribe here",Toast.LENGTH_LONG).show();

//        attires = new ArrayList<>();
//
//        progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Loading data...");
//        progressDialog.show();
//
//
//        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewAttire);
//        layoutManager = new LinearLayoutManager(AttireRecylerview.this);
//        recyclerView.setLayoutManager(layoutManager);
//        database = FirebaseDatabase.getInstance();
//
//        myRef = database.getReference("VillageRelation").child(getIntent().getExtras().getString("villageName")).child("ClothingRelation");
//
//
//        Toast.makeText(this, myRef.toString(), Toast.LENGTH_SHORT).show();
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot snp : dataSnapshot.getChildren()) {
//                    attires.add(new Attire(snp.getKey().toString(),
//                            snp.child("cost").getValue().toString(),
//                            snp.child("imageOfCloth").getValue().toString(),
//                            snp.child("fabricUsed").getValue().toString(),
//                            snp.child("shopName").getValue().toString(),
//                            "","",
//                            Double.parseDouble(snp.child("rating").getValue().toString())));
//                    }
//                adapter = new AdapterAttire(AttireRecylerview.this, attires);
//                adapter.setOnClick(AttireRecylerview.this);
//                recyclerView.setAdapter(adapter);
//                progressDialog.dismiss();
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                progressDialog.dismiss();
//            }
//        });
//    }
//
//    @Override
//    public void onItemClick(int position) {
//
//
//
    }
}
