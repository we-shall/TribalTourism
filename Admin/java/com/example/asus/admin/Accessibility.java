package com.example.asus.admin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Accessibility extends AppCompatActivity {


    EditText air_name,air_dist,station_name,station_dist,bus_name,bus_dist;
    Button update;
    String key1;
    //ImageView image;
    public static final int GET_FROM_GALLERY = 3;
    Uri selectedImage;
    DatabaseReference mref;
    Bitmap m1;
    String url;
    int flag = 0;
    String n1;
    int count;
    String c, n;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accessibility);

        Bundle b = getIntent().getExtras();
        if (b != null)
        {
            n1 = b.getString("village");
        }

        air_name = (EditText)findViewById(R.id.textAirportName);
        air_dist = (EditText)findViewById(R.id.textAirportDistance);

        station_name = (EditText)findViewById(R.id.textStationName);
        station_dist = (EditText)findViewById(R.id.textStationDistance);

        bus_name = (EditText)findViewById(R.id.textBusName);
        bus_dist = (EditText)findViewById(R.id.textBusDistance);


        update = (Button) findViewById(R.id.btn_signup);
        //cancel = (Button) findViewById(R.id.btn_cancel);



        mref = FirebaseDatabase.getInstance().getReference().child("VillageRelation").child(n1).child("AccesibilityRelation");
        update.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String a_n = air_name.getText().toString();
                String a_d = air_dist.getText().toString();

                String b_n = bus_name.getText().toString();
                String b_d = bus_dist.getText().toString();

                String s_n = station_name.getText().toString();
                String s_d = station_dist.getText().toString();

                if(!a_n.equals(null))
                    count++;
                if(!a_d.equals(null))
                    count++;
                if(!b_n.equals(null))
                    count++;
                if(!b_d.equals(null))
                    count++;
                if(!s_n.equals(null))
                    count++;
                if(!s_d.equals(null))
                    count++;
                if(count==6)
                {
                    mref.child("Airport").child("airportDistance").setValue(a_d);
                    mref.child("Airport").child("nameAirport").setValue(a_n);


                    mref.child("Busstop").child("busDistance").setValue(b_d);
                    mref.child("Busstop").child("nameBusstop").setValue(b_n);


                    mref.child("Railway").child("nameRailway").setValue(s_n);
                    mref.child("Railway").child("railwayDistance").setValue(s_d);

                    Toast.makeText(Accessibility.this, "Done Updating", Toast.LENGTH_SHORT).show();
                    Intent inte = new Intent(Accessibility.this, Village_Present.class);
                    startActivity(inte);
                }
                else
                    {
                    Snackbar snack = Snackbar.make(station_dist, "All fields are Mandatory", Snackbar.LENGTH_LONG);
                        Toast.makeText(Accessibility.this, "If fields are not known then enter 'NA'", Toast.LENGTH_SHORT).show();
                    snack.show();
                }
            }
        });

    }
}
