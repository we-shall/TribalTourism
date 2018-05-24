package com.example.asus.admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FAQActivity extends AppCompatActivity
{
    TextView tv1,tv2,tv3,tv4;
    Button b;
    int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        tv1=(TextView)findViewById(R.id.qu1);
        tv2=(TextView)findViewById(R.id.qu2);
        tv3=(TextView)findViewById(R.id.qu3);
        tv4=(TextView)findViewById(R.id.tit);


        b=(Button)findViewById(R.id.button3);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                tv4.setText(R.string.Title);
//                if(flag==0)
//                {
//                    //tv4.setText();
//                    tv4.setText(R.string.Title);
//                    tv1.setText(R.string.quu1 + "/n/n" + R.string.aaa1);
//                    tv2.setText(R.string.q2 + "/n/n" + R.string.a2);
//                    tv3.setText(R.string.q3 + "/n/n" + R.string.a3);
//                    flag = 1;
//                }
//                else
//                {
//                    tv4.setText("FAQ");
//                    tv4.setTextSize(30.0f);
//                    tv1.setText("1) What is tribe vibe?\n" +
//                            "\n" +
//                            " ans) Tribe vibe is designed to explore tribal heritage of india. More importnatly, it is a platform for tribal population to to promote their own heritage and culture.\"");
//                    tv2.setText("2) How to start tribe vibe?\n"+
//                            "\n"+
//                            "ans) Using tribe vibe is quite easy. If you are a tourist, then simply sign in and start the app. If you do not understand how to sign in, then simply provide phone  number for OTP login");
//                    tv3.setText("3) Can someone upload own images on tribe vibe?\n" +
//                            "\n" +
//                            "ans) Yes! Tourists and villagers both can upload images from their gallery onto the app but on Admin's consent");
//                    flag = 0;
//                }
//               // tv1.setText(R.string.quu1+"/n/n"+R.string.aaa1);
            }
        });
    }
}
