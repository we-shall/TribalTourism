package com.example.asus.admin;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InitialActivity extends AppCompatActivity
{

    int flag;
    String vil,vil1;
    Button create,update;
    String created,uname;
    DatabaseReference databaseReference,mref;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);
        Bundle b=getIntent().getExtras();
        if(b!=null)
        {
            vil=b.getString("village");
            vil1=vil;
            created=b.getString("created");
            uname = b.getString("uname");
        }
        create=(Button)findViewById(R.id.create);
        update=(Button)findViewById(R.id.update);
        if(created.equals("0"))
        {
            create.setEnabled(true);
            update.setEnabled(false);
        }
        else
        {
            create.setEnabled(false);
        }

        create.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder alert = new AlertDialog.Builder(InitialActivity.this);
                alert.setMessage("Village has been created")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                create.setEnabled(false);
                                update.setEnabled(true);


                                mref = FirebaseDatabase.getInstance().getReference().child("VillageRelation").child(vil1);

                                mref.child("DescriptionRelation").child("culture").setValue("0");
                                mref.child("DescriptionRelation").child("description").setValue("0");
                                mref.child("DescriptionRelation").child("galleryImage1").setValue("VillageRelation/no-image-available-md.png");
                                mref.child("DescriptionRelation").child("galleryImage2").setValue("VillageRelation/no-image-available-md.png");
                                mref.child("DescriptionRelation").child("galleryImage3").setValue("VillageRelation/no-image-available-md.png");
                                mref.child("DescriptionRelation").child("galleryImage4").setValue("VillageRelation/no-image-available-md.png");
                                mref.child("DescriptionRelation").child("history").setValue("0");
                                mref.child("DescriptionRelation").child("nameOfVillage").setValue("0");
                                mref.child("DescriptionRelation").child("rating").setValue("0");
                                mref.child("DescriptionRelation").child("shortdes").setValue("0");
                                mref.child("DescriptionRelation").child("speciality").setValue("0");

                                mref.child("CuisineRelation").child("Dish1").child("category").setValue("0");
                                mref.child("CuisineRelation").child("Dish1").child("description").setValue("0");
                                mref.child("CuisineRelation").child("Dish1").child("images").setValue("VillageRelation/no-image-available-md.png");
                                mref.child("CuisineRelation").child("Dish1").child("ingredients").setValue("0");
                                mref.child("CuisineRelation").child("Dish1").child("price").setValue("0");
                                mref.child("CuisineRelation").child("Dish1").child("rating").setValue("0");
                                mref.child("CuisineRelation").child("Dish1").child("recipe").setValue("0");
                                mref.child("CuisineRelation").child("Dish1").child("shopName").setValue("0");
                                mref.child("CuisineRelation").child("Dish1").child("shortDescription").setValue("0");

                                mref.child("ClothingRelation").child("kapda").child("cost").setValue("0");
                                mref.child("ClothingRelation").child("kapda").child("fabricUsed").setValue("0");
                                mref.child("ClothingRelation").child("kapda").child("imageOfCloth").setValue("VillageRelation/no-image-available-md.png");
                                mref.child("ClothingRelation").child("kapda").child("rating").setValue("0");
                                mref.child("ClothingRelation").child("kapda").child("shopName").setValue("0");

                                mref.child("AccesibilityRelation").child("Airport").child("airportDistance").setValue("0");
                                mref.child("AccesibilityRelation").child("Airport").child("nameAirport").setValue("0");
                                mref.child("AccesibilityRelation").child("Railway").child("railwayDistance").setValue("0");
                                mref.child("AccesibilityRelation").child("Railway").child("nameRailway").setValue("0");
                                mref.child("AccesibilityRelation").child("Busstop").child("busDistance").setValue("0");
                                mref.child("AccesibilityRelation").child("Busstop").child("nameBusstop").setValue("0");



                                mref.child("HostRelation").setValue("0");
                                mref.child("TraderRelation").setValue("0");
                                mref.child("GuideRelation").setValue("0");

                                databaseReference = FirebaseDatabase.getInstance().getReference().child("AdminRelation").child(uname).child("created");
                                databaseReference.setValue("1");
                            }
                        });
                        AlertDialog d = alert.create();
                        d.show();


            }
        });

        update.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent i = new Intent(InitialActivity.this,Village_Present.class);
                i.putExtra("village",vil1);
                startActivity(i);

            }
        });

    }
    public void onBackPressed()
    {
        ;
    }
}
