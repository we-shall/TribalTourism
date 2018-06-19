package com.example.vishalsingh.villageexpandedview;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;



public class AnimationActivity extends AppCompatActivity {

    ImageView tagline, tribalLogo;
    Animation fadein;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_animation);


            tagline = (ImageView) findViewById(R.id.imageTagline);
            tribalLogo = (ImageView) findViewById(R.id.tribalLogo);
            fadein = AnimationUtils.loadAnimation(this, R.anim.fade_in);

            tribalLogo.setAnimation(fadein);
            tagline.setAnimation(fadein);


            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(AnimationActivity.this, PermissionActivity.class);
                    startActivity(intent);
                }
            }, 4000);
        }
    }