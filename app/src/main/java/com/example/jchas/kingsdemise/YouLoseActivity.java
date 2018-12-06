package com.example.jchas.kingsdemise;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class YouLoseActivity  extends AppCompatActivity {

    //public static Account userAccountMidend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lose);



        TextView headline = findViewById(R.id.headlineLOSE);
        TextView start = findViewById(R.id.restart);

        Typeface font1 = Typeface.createFromAsset(getAssets(), "Sunset Boulevard.otf");
        Typeface font2 = Typeface.createFromAsset(getAssets(), "pixelflag.ttf");

        headline.setTypeface(font1);
        start.setTypeface(font2);

        Animation hover = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.super_hover);


        start.startAnimation(hover);

        //combat goes here
    }

    public void restart(View view){
        Intent intent = new Intent(YouLoseActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

}
