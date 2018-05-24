package com.example.asus.admin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

public class Village_Present extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{
    String vil1;


    TextView desc,cuisine,clothing,accessibility;
    //Button done;
    DatabaseReference mref;
    String v;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_village__present);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        desc = ( TextView) findViewById(R.id.description);
        cuisine = ( TextView) findViewById(R.id.cuisine);
        clothing = ( TextView) findViewById(R.id.clothing);
        accessibility = ( TextView) findViewById(R.id.accessibility);

        //done = (Button)findViewById(R.id.done);

        Bundle b = getIntent().getExtras();
        if(b!=null)
        {
            v = b.getString("village");
        }

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
                Intent i = new Intent(Village_Present.this, Description.class);
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


                Intent i = new Intent(Village_Present.this,Cuisine.class);
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

                Intent i = new Intent(Village_Present.this,Clothing.class);
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
                Intent i = new Intent(Village_Present.this,Accessibility.class);
                i.putExtra("village",vill);
                startActivity(i);
            }
        });

//        done.setOnClickListener(new View.OnClickListener()
//        {
//            String vill;
//            @Override
//            public void onClick(View v)
//            {
//                Bundle b = getIntent().getExtras();
//                if(b!=null) {
//                    vill = b.getString("village");
//                }
//
//
//                Intent i = new Intent(Village_Present.this,Village_Present.class);
//                i.putExtra("village",vill);
//                startActivity(i);
//            }
//        });



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.village__present, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.h_details)
        {
            Intent i = new Intent(this,HostList.class);
            Bundle b=getIntent().getExtras();
            if(b!=null)
            {
                vil1=b.getString("village");
            }
            i.putExtra("village",vil1);
            startActivity(i);
        }
        else if (id == R.id.g_details)
        {
            Intent i = new Intent(this,GuideList.class);
            Bundle b=getIntent().getExtras();
            if(b!=null)
            {
                vil1=b.getString("village");
            }
            i.putExtra("village",vil1);
            startActivity(i);
        }
        else if (id == R.id.t_details)
        {
            Intent i = new Intent(this,TraderList.class);
            Bundle b=getIntent().getExtras();
            if(b!=null)
            {
                vil1=b.getString("village");
            }
            i.putExtra("village",vil1);

            startActivity(i);
        }
        else if (id == R.id.schemes)
        {
            String url="https://tribal.nic.in/Schemes.aspx";
            Intent i=new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url));
            startActivity(i);
        }
        else if(id == R.id.logout)
        {
            Intent i = new Intent(this,LoginActivity.class);
            startActivity(i);
        }
        else if(id == R.id.faq)
        {
            Intent i = new Intent(this,FAQActivity.class);
            startActivity(i);
        }
        else if(id==R.id.event_details)
        {
            Intent i= new Intent(Village_Present.this,Events.class);
            startActivity(i);
        }
        else if(id==R.id.gallery)
        {
            Intent i= new Intent(Village_Present.this,ImageApproval.class);
            Bundle b=getIntent().getExtras();
            if(b!=null)
            {
                vil1=b.getString("village");
            }
            i.putExtra("village",vil1);

            startActivity(i);

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;


    }
}
