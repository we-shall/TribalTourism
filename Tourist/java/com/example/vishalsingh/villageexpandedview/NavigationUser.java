package com.example.vishalsingh.villageexpandedview;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class NavigationUser extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterForVillages.OnItemClicked {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    AdapterForVillages adapter;
    ArrayList<Description> descriptions;
    ProgressDialog progressDialog;
    Bitmap m1;
    StorageReference reference;
    Button butt;

    ImageView img;
    int GET_FROM_GALLERY = 10;

    //firebase intialisation

    FirebaseDatabase database = null;
    DatabaseReference myRef = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);





        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data....");
        progressDialog.show();







        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        descriptions = new ArrayList<>();
        layoutManager = new LinearLayoutManager(NavigationUser.this);
        recyclerView.setLayoutManager(layoutManager);
        database = FirebaseDatabase.getInstance();




        myRef = database.getReference("VillageRelation");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snp : dataSnapshot.getChildren()) {
                    Description des = new Description(snp.child("DescriptionRelation").child("nameOfVillage").getValue().toString(),
                            snp.child("DescriptionRelation").child("description").getValue().toString(),
                            snp.child("DescriptionRelation").child("galleryImage1").getValue().toString(),
                            snp.child("DescriptionRelation").child("galleryImage2").getValue().toString(),
                            snp.child("DescriptionRelation").child("galleryImage3").getValue().toString(),
                            snp.child("DescriptionRelation").child("galleryImage4").getValue().toString(),
                            snp.child("DescriptionRelation").child("history").getValue().toString(),
                            snp.child("DescriptionRelation").child("culture").getValue().toString(),
                            Double.parseDouble(snp.child("DescriptionRelation").child("rating").getValue().toString()),
                            "Speciality " + snp.child("DescriptionRelation").child("speciality").getValue().toString());
                    descriptions.add(des);
                }
                adapter = new AdapterForVillages(NavigationUser.this, descriptions);
                adapter.setOnClick(NavigationUser.this);
                recyclerView.setAdapter(adapter);
                progressDialog.dismiss();
            }
            @Override
            public void onCancelled(DatabaseError error) {
                progressDialog.dismiss();
            }
        });

    }
//        void LoadData(Description description) {
//            //Toast.makeText(this, "Data retrieved", Toast.LENGTH_SHORT).show();
//
//            adapter = new AdapterForVillages(NavigationUser.this, descriptions);
//            adapter.setOnClick(this);
//            recyclerView.setAdapter(adapter);
//            progressBar.setVisibility(ProgressBar.INVISIBLE);
//        }



     @Override
        public void onItemClick(int position) {
            Intent i = new Intent(NavigationUser.this,MainActivity.class);
            i.putExtra("nameOfvillage",descriptions.get(position).getNameOfVillage().toString());
            startActivity(i);
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
        getMenuInflater().inflate(R.menu.navigation_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
