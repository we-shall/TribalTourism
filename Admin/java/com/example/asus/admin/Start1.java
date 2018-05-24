package com.example.asus.admin;



        import android.app.ActivityOptions;
        import android.content.Intent;
        import android.os.Build;
        import android.support.annotation.RequiresApi;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.view.Window;
        import android.view.WindowManager;

public class Start1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start1);
    }
    public void Next(View view)
    {

        Intent i=new Intent(this,Start2.class);

        startActivity(i,
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }



}
