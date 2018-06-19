package com.example.vishalsingh.villageexpandedview;

import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String latitude,longitude,marker;

    ArrayList<Double> latitude1 = new ArrayList<>();
    ArrayList<Double> longitude1 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        marker = getIntent().getExtras().getString("markerName");
        latitude = getIntent().getExtras().getString("lat");
        longitude = getIntent().getExtras().getString("long");

        Toast.makeText(this, latitude +"      "+ longitude, Toast.LENGTH_SHORT).show();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(Double.valueOf(latitude),Double.valueOf(longitude));
        mMap.addMarker(new MarkerOptions().position(sydney).title(marker));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(12.0f));

        LatLng guwahati2 = new LatLng(Double.valueOf(26.532),Double.valueOf(91.622));
        mMap.addMarker(new MarkerOptions().position(guwahati2).title(marker));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(guwahati2));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(guwahati2));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(12.0f));

        LatLng guwahati3 = new LatLng(Double.valueOf(26.539),Double.valueOf(91.622));
        mMap.addMarker(new MarkerOptions().position(guwahati3).title(marker));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(guwahati3));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(guwahati3));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(12.0f));

        LatLng guwahati4 = new LatLng(Double.valueOf(26.512),Double.valueOf(91.632));
        mMap.addMarker(new MarkerOptions().position(guwahati4).title(marker));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(guwahati4));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(guwahati4));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(12.0f));


        LatLng guwahati5 = new LatLng(Double.valueOf(26.537),Double.valueOf(91.627));
        mMap.addMarker(new MarkerOptions().position(guwahati5).title(marker));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(guwahati5));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(guwahati5));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(12.0f));








    }
}

