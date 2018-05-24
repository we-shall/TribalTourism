package com.example.vishalsingh.villageexpandedview;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    RatingBar rating;
    ImageView image2,image3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        //getIntent here and fill the activity for the same
        //


        image2 = (ImageView) findViewById(R.id.image2);
        image2.setOnClickListener(this);


        image3 = (ImageView)findViewById(R.id.location);
        image3.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.text3:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Recipe will be displayed here. ")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                setContentView(R.layout.activity_main2);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                            }
                        });

                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Recipe");
                alert.show();
                setContentView(R.layout.activity_main);
                break;

            case R.id.image2:
//                String url=editText1.getText().toString();
                String number = "9766893526";
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:+9766893526"));
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
                break;

            case R.id.location:
                Intent i = new Intent(this,MapsActivity.class);
                i.putExtra("lat","79.21");
                i.putExtra("long","21.3");
                startActivity(i);
                break;

//            case R.id.rating:
//                String rating1=String.valueOf(rating.getRating());
//                Toast.makeText(getApplicationContext(), rating1, Toast.LENGTH_LONG).show();
//                break;
        }
    }
}