package com.example.asus.admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
public class HostList extends AppCompatActivity {
    HostAdapter ha;
    DatabaseReference databaseReference;
    ArrayList<HostDetails> hd;
    String vil1;
    RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hd=new ArrayList<>();
        setContentView(R.layout.activity_host_list);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        Bundle b=getIntent().getExtras();
        if(b!=null)
            vil1=b.getString("village");

        databaseReference = FirebaseDatabase.getInstance().getReference().child("VillageRelation").child(vil1).child("HostRe" +
                "" +
                "lation");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                for (DataSnapshot i : dataSnapshot.getChildren())
                {

                    Address address = new Address(i.child("address").child("plotno").getValue().toString(),i.child("address").child("street").getValue().toString(),i.child("address").child("locality").getValue().toString(),i.child("address").child("district").getValue().toString());
                    loadData(new HostDetails(i.getKey().toString(),address,i.child("roomsAvailable").getValue().toString(),i.child("contact").getValue().toString(),i.child("approved").getValue().toString(),i.child("address").child("village").getValue().toString()));
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    void loadData(HostDetails hostDetails)
    {
        hd.add(hostDetails);
        ha = new HostAdapter(hd,HostList.this);
        mRecyclerView.setAdapter(ha);


    }


}
