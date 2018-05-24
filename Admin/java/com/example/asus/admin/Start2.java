package com.example.asus.admin;

        import android.app.ActivityOptions;
        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;

public class Start2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start2);
    }
    public void Next(View view)
    {
        Intent i=new Intent(this,Start3.class);

        startActivity(i,
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
}

