package com.example.vishalsingh.villageexpandedview;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ScrollView scroll;
    private static ViewPager mPager;
    StorageReference ref;;
    Button butt;
    FloatingActionButton fab1;
    int GET_FROM_GALLERY = 10;

    private static int currentPage = 0;

    private ArrayList<String> galleryArray;
    //FirebaseDatabase database;
    DatabaseReference myRef,database;
    Uri selectedImage;


    ProgressDialog progressDialog;

    //adding listeners to image button
    ImageView accessibility, location, accomodation, cuisine, clothing, description,shopping,gallery;


    //for description
    TextView desc,speciality,history,culture;


    ImageView imgForgallery;
    StorageReference reference;
    String key;

    String s1 = "",s2 = "",s3 = "",s4  = "",s5 = "",s6 = "";

    String lat = "",longi = "", marker;
    static int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        galleryArray = new ArrayList<String>();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Image Loading from firebase");
        progressDialog.show();
        butt = (Button)findViewById(R.id.butt);
        init();
        ref= FirebaseStorage.getInstance().getReference().child("VillageRelation").child(getIntent().getExtras().getString("nameOfvillage"));
        Toast.makeText(this,ref.toString(), Toast.LENGTH_LONG).show();
        database=FirebaseDatabase.getInstance().getReference().child("VillageRelation").child(getIntent().getExtras().getString("nameOfvillage")).child("notapprovedimg");

        key="VillageRelation/"+getIntent().getExtras().getString("nameOfvillage")+"/notapprovedimg/img"+i+".jpg";
//        fab1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
//
//
//                ref.child("notapprovedimg").putFile(selectedImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                        database.child("img"+i).setValue(key);
//                        i++;
//                    }
//                });
//
//
//
//
//
//            }
//        });


        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
            }
        });


        cuisine = (ImageView) findViewById(R.id.cuisine);
        accessibility = (ImageView) findViewById(R.id.accessibility);
        description = (ImageView) findViewById(R.id.description);
        clothing = (ImageView) findViewById(R.id.clothing);
        accomodation = (ImageView) findViewById(R.id.accomodation);
        location = (ImageView) findViewById(R.id.location);
        shopping = (ImageView)findViewById(R.id.trade);
        gallery = (ImageView)findViewById(R.id.gallery);


        cuisine.setOnClickListener(this);
        accessibility.setOnClickListener(this);
        description.setOnClickListener(this);
        clothing.setOnClickListener(this);
        accomodation.setOnClickListener(this);
        location.setOnClickListener(this);
        shopping.setOnClickListener(this);
        gallery.setOnClickListener(this);


        scroll = (ScrollView) findViewById(R.id.scroll);
        scroll.fullScroll(ScrollView.FOCUS_UP);
        scroll.smoothScrollTo(0, 0);



        desc = (TextView)findViewById(R.id.textDescription);
        speciality = (TextView)findViewById(R.id.textHistory);
        history = (TextView)findViewById(R.id.textSpeciality);
        culture = (TextView)findViewById(R.id.textCulture);

 }


    private void init() {

        marker = getIntent().getExtras().getString("nameOfvillage");

        imgForgallery = (ImageView)findViewById(R.id.frame);
        myRef = FirebaseDatabase.getInstance().getReference().child("VillageRelation").child(getIntent().getExtras().getString("nameOfvillage"));

        Toast.makeText(this, myRef.toString(), Toast.LENGTH_SHORT).show();


        myRef.child("DescriptionRelation").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                desc.setText(dataSnapshot.child("description").getValue().toString());
                speciality.setText(dataSnapshot.child("speciality").getValue().toString());
                history.setText(dataSnapshot.child("history").getValue().toString());
                culture.setText(dataSnapshot.child("culture").getValue().toString());

                galleryArray.add(dataSnapshot.child("galleryImage2").getValue().toString());
                //galleryArray.add(dataSnapshot.child("galleryImage1").getValue().toString());
                //galleryArray.add(dataSnapshot.child("galleryImage3").getValue().toString());
                //galleryArray.add(dataSnapshot.child("galleryImage4").getValue().toString());

                reference = FirebaseStorage.getInstance().getReference().child(galleryArray.get(0));
                Glide.with(MainActivity.this).using(new FirebaseImageLoader())
                        .load(reference)
                        .into(imgForgallery);
                progressDialog.dismiss();


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });


        myRef.child("AccesibilityRelation").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                 s1 = dataSnapshot.child("Airport").child("nameAirport").getValue().toString();
                 s2 = dataSnapshot.child("Railway").child("nameRailway").getValue().toString();
                 s6 = dataSnapshot.child("Busstop").child("busDistance").getValue().toString();
                 s4 = dataSnapshot.child("Airport").child("airportDistance").getValue().toString();
                 s3 = dataSnapshot.child("Busstop").child("nameBusstop").getValue().toString();
                 s5 = dataSnapshot.child("Railway").child("railwayDistance").getValue().toString();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

               lat = dataSnapshot.child("latVillage").getValue().toString();
               longi = dataSnapshot.child("longVillage").getValue().toString();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        mPager = (ViewPager) findViewById(R.id.pager);
//        mPager.setAdapter(new AdapterForGallery(MainActivity.this, galleryArray));
//        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
//        indicator.setViewPager(mPager);
//
//        // Auto start of viewpager
//        final Handler handler = new Handler();
//        final Runnable Update = new Runnable() {
//            public void run() {
//                if (currentPage == 4) {
//                    currentPage = 0;
//                }
//                mPager.setCurrentItem(currentPage++, true);
//            }
//        };
//        Timer swipeTimer = new Timer();
//        swipeTimer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                handler.post(Update);
//            }
//        }, 2500,4000);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {

            case R.id.cuisine:
                Intent i = new Intent(this,VillageRecycler.class);
                i.putExtra("villageName",getIntent().getExtras().getString("nameOfvillage"));
                startActivity(i);
                break;

            case R.id.accessibility:
                Toast.makeText(this, "ACCESSIBILTY", Toast.LENGTH_SHORT).show();
                (new CustomDialog(this,s1,s2,s3,s4,s5,s6)).show();
                break;

            case R.id.accomodation:
                Toast.makeText(this, "ACCOMODATION", Toast.LENGTH_SHORT).show();
                Intent i1 = new Intent(this,RecyclerAccomodation.class);
                i1.putExtra("villageName",getIntent().getExtras().getString("nameOfvillage"));
                startActivity(i1);
                break;

            case R.id.location:
                Toast.makeText(this, "LOCATION", Toast.LENGTH_SHORT).show();
                Intent map = new Intent(this,MapsActivity.class);
                map.putExtra("lat",lat);
                map.putExtra("long",longi);
                map.putExtra("markerName",marker);
                startActivity(map);
                break;

            case R.id.clothing:
                Toast.makeText(this, "CLOTHING", Toast.LENGTH_SHORT).show();
                Intent i4 = new Intent(this,AttireRecylerview.class);
                i4.putExtra("villageName",getIntent().getExtras().getString("nameOfvillage"));
                startActivity(i4);
                break;

            case R.id.description:
                Toast.makeText(this, "Description", Toast.LENGTH_SHORT).show();
                break;


            case R.id.gallery:
                Toast.makeText(this, "GALLERY", Toast.LENGTH_SHORT).show();
                Intent i2 = new Intent(MainActivity.this,FirebaseGallery.class);
                i2.putExtra("villageName",getIntent().getExtras().getString("nameOfvillage"));
                startActivity(i2);
                break;
            case R.id.trade:
                Toast.makeText(this, "TRADE", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        //Detects request codes
        if (requestCode == GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {

            selectedImage = data.getData();
            Toast.makeText(this, "selected image", Toast.LENGTH_SHORT).show();

            try {
                MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);

                StorageReference ref = FirebaseStorage.getInstance().getReference();
                final String key = "VillageRelation/" +  getIntent().getExtras().getString("nameOfvillage") + "/notapprovedimg/" + Calendar.getInstance().getTimeInMillis() + ".jpg";
                StorageReference riversRef = ref.child(key);
                riversRef.putFile(selectedImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        @SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot.getDownloadUrl();

                        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("VillageRelation").child(getIntent().getExtras().getString("nameOfvillage")).child("notapprovedimg");
                        DatabaseReference postsRef = myRef.child("" + Calendar.getInstance().getTimeInMillis());
                        postsRef.setValue(key);
                        Toast.makeText(MainActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
                    }
                });

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();

            }
        }
    }
}