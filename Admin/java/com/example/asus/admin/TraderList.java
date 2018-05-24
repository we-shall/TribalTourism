package com.example.asus.admin;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TraderList extends AppCompatActivity {
    TraderAdapter ta;
    DatabaseReference databaseReference;
    ArrayList<TraderDetails> td;
    String vil1;
    RecyclerView mRecyclerView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        td=new ArrayList<>();//category contact approved
        setContentView(R.layout.activity_trader_list);

//        progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Loading data ...");
//        progressDialog.show();
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        Bundle b=getIntent().getExtras();
        if(b!=null)
            vil1=b.getString("village");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("VillageRelation").child(vil1).child("TraderRelation");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot )
            {
                for (DataSnapshot i : dataSnapshot.getChildren())
                {
                    td.add(new TraderDetails(i.getKey().toString(),i.child("ownername").getValue().toString(),i.child("category").getValue().toString(),i.child("contact").getValue().toString(),i.child("approved").getValue().toString(),i.child("village").getValue().toString()));
                }
                ta=new TraderAdapter(td,TraderList.this);
                mRecyclerView.setAdapter(ta);
                //progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
