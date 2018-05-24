package com.example.asus.admin;

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

public class GuideList extends AppCompatActivity {
    GuideAdapter ga;
    DatabaseReference databaseReference;
    ArrayList<GuideDetails> gd;
    String vil1;
    RecyclerView mRecyclerView;
    //int a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gd=new ArrayList<>();
        setContentView(R.layout.activity_guide_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(false);
        Bundle b=getIntent().getExtras();
        if(b!=null)
            vil1=b.getString("village");
        //Toast.makeText(GuideList.this,vil1,Toast.LENGTH_LONG).show();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("VillageRelation").child(vil1).child("GuideRelation");
        databaseReference.addValueEventListener(new ValueEventListener()
        {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot)
            {
                for (DataSnapshot i : dataSnapshot.getChildren())
                {
                    loadData(new GuideDetails(i.getKey().toString(),i.child("contact").getValue().toString(),i.child("approved").getValue().toString(),i.child("village").getValue().toString()));
                }
                ga=new GuideAdapter(gd,GuideList.this);
                mRecyclerView.setAdapter(ga);
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });
    }
    void loadData(GuideDetails guideDetails)
    {
       // if(guideDetails.guide_approved.equals("APPROVED")) {
            gd.add(guideDetails);





    }
}
