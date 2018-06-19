package com.example.vishalsingh.villageexpandedview;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.barcode.Barcode;
import java.util.ArrayList;


public class DataScannerActivity extends AppCompatActivity {


    String s;
    Button btnScan;
    TextView uid, name, gender, yob, house;
    public static final int REQUEST_CODE = 100;
    public static final int PERMISSION_CODE = 200;
    ArrayList<String> arra;
    ArrayList<String> temp;
    int i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_scanner);
        arra = new ArrayList<>();

        uid = (TextView) findViewById(R.id.uid);
        name = (TextView) findViewById(R.id.name);
        gender = (TextView) findViewById(R.id.gender);
        yob = (TextView) findViewById(R.id.yob);
        house = (TextView) findViewById(R.id.house);
        btnScan = (Button) findViewById(R.id.btnScan);


        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSION_CODE);
        }
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DataScannerActivity.this, AadharScanner.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, final int resultCode, final Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                final Barcode barcode = data.getParcelableExtra("barcode");
                s = barcode.displayValue;
                String[] strArrays = s.split("=");

                temp = new ArrayList<>();

                for (int i = 0; i < strArrays.length; i++) {
                    String space[] = strArrays[i].split(" ");
                    for (int j = 0; j < space.length; j++) {
                        temp.add(space[j]);
                    }
                }

                for (i = 0; i < temp.size(); i++) {
                    //System.out.println(temp.get(i));
                    if (temp.get(i).equals("uid")) {
                                uid.setText(temp.get(i + 1));
                    }
                    if (temp.get(i).equals("name"))
                        name.setText(temp.get(i + 1) + " " + temp.get(i + 2) + " " + temp.get(i + 3));
                }
                        gender.setText(s);
            }
        }
    }
}
