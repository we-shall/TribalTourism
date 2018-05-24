package com.example.asus.admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ImageApproval extends AppCompatActivity {
    String vill;
    ImageApprovalAdapter ia;
    DatabaseReference databaseReference;
    ArrayList<ImageApprovalDetails> iad;
    RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iad=new ArrayList<>();

        setContentView(R.layout.activity_image_approval);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        Bundle b=getIntent().getExtras();
        if(b!=null)
            vill=b.getString("village");

        databaseReference = FirebaseDatabase.getInstance().getReference().child("VillageRelation").child(vill).child("notapprovedimg");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                for (DataSnapshot i : dataSnapshot.getChildren())
                {
                    loadData(new ImageApprovalDetails(i.getKey().toString(),i.getValue().toString(),vill));
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {





            }
        });
    }
    void loadData(ImageApprovalDetails imageApprovalDetails)
    {
        iad.add(imageApprovalDetails);
        ia = new ImageApprovalAdapter(iad,ImageApproval.this);
        mRecyclerView.setAdapter(ia);


    }


}

