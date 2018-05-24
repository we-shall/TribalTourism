package com.example.vishalsingh.villageexpandedview;

import android.*;
import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PermissionActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 20;
    private static final int MY_PERMISSIONS_REQUEST_INTERNET = 30;
    private static final int MY_PERMISSIONS_REQUEST_SMS_PERMISSION = 40;
    private static final int MY_PERMISSIONS_REQUEST_COARSE_LOCATION = 60;
    private static final int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 70;
    private static final int MY_PERMISSIONS_REQUEST_CALL = 80;
    Button b1;
    static int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        b1= (Button)findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PermissionActivity.this,LoginActivity.class));
            }
        });

        if(haveNetworkConnection() == true) {
            Toast.makeText(this, "Internet is On", Toast.LENGTH_SHORT).show();
        }
        else if (haveNetworkConnection() == false) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("You need to switch on internet to use the application ");
            alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    Toast.makeText(PermissionActivity.this,"You Clicked Okay",Toast.LENGTH_LONG).show();
                }
            });

            alertDialogBuilder.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(PermissionActivity.this, "You clicked Cancel", Toast.LENGTH_SHORT).show();
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_SMS_PERMISSION);
            }
        } else {
            // Permission has already been granted
            Toast.makeText(this, "Read Contacts Feature", Toast.LENGTH_SHORT).show();
        }

            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.CAMERA)) {
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
                }
            } else {
                // Permission has already been granted
                Toast.makeText(this, "Read Contacts Feature", Toast.LENGTH_SHORT).show();
            }

            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.INTERNET)) {
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.INTERNET}, MY_PERMISSIONS_REQUEST_INTERNET);
                }
            } else {
                // Permission has already been granted
                Toast.makeText(this, "Read Contacts Feature", Toast.LENGTH_SHORT).show();
            }
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSIONS_REQUEST_COARSE_LOCATION);
                }
            } else {
                // Permission has already been granted
                Toast.makeText(this, "Read Contacts Feature", Toast.LENGTH_SHORT).show();
            }

            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_FINE_LOCATION)) {
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_FINE_LOCATION);
                }
            } else {
                // Permission has already been granted
                Toast.makeText(this, "Read Contacts Feature", Toast.LENGTH_SHORT).show();
            }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL);
            }
        } else {
            // Permission has already been granted
            Toast.makeText(this, "Read Contacts Feature", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String [] permissions, @NonNull int [] grantResults) {
        switch(requestCode) {
            case MY_PERMISSIONS_REQUEST_SMS_PERMISSION:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permissions have been granted", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {
                        new AlertDialog.Builder(this)
                                .setTitle("Internet permissions denied")
                                .setMessage("Permission ").show();
                    }
                    else {
                        new AlertDialog.Builder(this)
                                .setTitle("Grant Internet permissions")
                                .setMessage("You need to grant internet permissions to use the application").show();
                    }
                }
                break;

            case MY_PERMISSIONS_REQUEST_CAMERA:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permissions have been granted", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                        new AlertDialog.Builder(this)
                                .setTitle("Internet permissions denied")
                                .setMessage("Permission").show();
                    }
                    else {
                        new AlertDialog.Builder(this)
                                .setTitle("Grant Internet permissions")
                                .setMessage("You need to grant internet permissions to use the application").show();
                    }
                }
                break;

            case MY_PERMISSIONS_REQUEST_COARSE_LOCATION:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permissions have been granted", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                        new AlertDialog.Builder(this)
                                .setTitle("Location permissions denied")
                                .setMessage("Permission").show();
                    }
                    else {
                        new AlertDialog.Builder(this)
                                .setTitle("Grant Internet permissions")
                                .setMessage("You need to grant internet permissions to use the application").show();
                    }
                }
                break;

            case MY_PERMISSIONS_REQUEST_FINE_LOCATION:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permissions have been granted", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                        new AlertDialog.Builder(this)
                                .setTitle("Location permissions denied")
                                .setMessage("Permission").show();
                    }
                    else {
                        new AlertDialog.Builder(this)
                                .setTitle("Grant Internet permissions")
                                .setMessage("You need to grant internet permissions to use the application").show();
                    }
                }
                break;

            case MY_PERMISSIONS_REQUEST_CALL:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permissions have been granted", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
                        new AlertDialog.Builder(this)
                                .setTitle("Location permissions denied")
                                .setMessage("Permission").show();
                    }
                    else {
                        new AlertDialog.Builder(this)
                                .setTitle("Grant Internet permissions")
                                .setMessage("You need to grant internet permissions to use the application").show();
                    }
                }
                break;

        }
    }


    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }
}

