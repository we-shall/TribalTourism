package com.example.vishalsingh.villageexpandedview;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

/**
 * Created by vishalsingh on 29/03/18.
 */

public class CustomDialog extends Dialog {

    Activity a;
    Button b;
    TextView tair,trail,tbus,tdist1,tdist3,tdist2;
    String s1,s2,s3,s4,s5,s6;

    public CustomDialog(Activity context,String s1,String s2, String s3, String s4, String s5, String s6) {
        super(context);
        a = context;
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.s4 = s4;
        this.s5 = s5;
        this.s6 = s6;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transtour);


        b = (Button)findViewById(R.id.button);

        tair = (TextView)findViewById(R.id.airportName);
        trail = (TextView)findViewById(R.id.railwayName);
        tbus = (TextView)findViewById(R.id.busName);
        tdist1 = (TextView)findViewById(R.id.textView6);
        tdist2 = (TextView)findViewById(R.id.distanceRail1);
        tdist3 = (TextView)findViewById(R.id.distanceBus1);


        tair.setText(s1);
        trail.setText(s2);
        tbus.setText(s3);
        tdist1.setText(s4);
        tdist2.setText(s5);
        tdist3.setText(s6);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


    }
}
