package com.example.vishalsingh.villageexpandedview;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class Photo3 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_photo3);
        }
    public void Next(View view)
    {
        Intent i=new Intent(this,AnimationActivity.class);

        startActivity(i, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
}
