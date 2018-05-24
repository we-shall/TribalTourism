package com.example.asus.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class No_Village extends AppCompatActivity {

    TextView desc,cuisine,clothing,accessibility;
    Button done;
    DatabaseReference mref;String v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no__village);

        desc = ( TextView) findViewById(R.id.description);
        cuisine = ( TextView) findViewById(R.id.cuisine);
        clothing = ( TextView) findViewById(R.id.clothing);
        accessibility = ( TextView) findViewById(R.id.accessibility);

        done = (Button)findViewById(R.id.done);



        Bundle b = getIntent().getExtras();
        if(b!=null) {
            v = b.getString("village");
        }
//

        desc.setOnClickListener(new View.OnClickListener()
        {
            String vill;
            @Override
            public void onClick(View v)
            {
                Bundle b = getIntent().getExtras();
                if(b!=null) {
                     vill = b.getString("village");
                }
                    Intent i = new Intent(No_Village.this, Description.class);
                i.putExtra("village",vill);
                    startActivity(i);

            }
        });

        cuisine.setOnClickListener(new View.OnClickListener()
        {
            String vill;
            @Override
            public void onClick(View v)
            {
                Bundle b = getIntent().getExtras();
                if(b!=null) {
                    vill = b.getString("village");
                }


                Intent i = new Intent(No_Village.this,Cuisine.class);
                i.putExtra("village",vill);
                startActivity(i);
            }
        });

        clothing.setOnClickListener(new View.OnClickListener()
        {
            String vill;
            @Override
            public void onClick(View v)
            {
                Bundle b = getIntent().getExtras();
                if(b!=null) {
                    vill = b.getString("village");
                }

                Intent i = new Intent(No_Village.this,Clothing.class);
                i.putExtra("village",vill);
                startActivity(i);
            }
        });

        accessibility.setOnClickListener(new View.OnClickListener()
        {
            String vill;
            @Override
            public void onClick(View v)
            {
                Bundle b = getIntent().getExtras();
                if(b!=null) {
                    vill = b.getString("village");
                }



                Intent i = new Intent(No_Village.this,Accessibility.class);
                i.putExtra("village",vill);
                startActivity(i);
            }
        });

        done.setOnClickListener(new View.OnClickListener()
        {
            String vill;
            @Override
            public void onClick(View v)
            {
                Bundle b = getIntent().getExtras();
                if(b!=null) {
                    vill = b.getString("village");
                }


                Intent i = new Intent(No_Village.this,Village_Present.class);
                i.putExtra("village",vill);
                startActivity(i);
            }
        });

    }
    public void onBackPressed()
    {
        ;
    }
}
