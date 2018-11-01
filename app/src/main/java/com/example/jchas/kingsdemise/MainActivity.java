package com.example.jchas.kingsdemise;

import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView headline = findViewById(R.id.headline);
        TextView start = findViewById(R.id.start);
        TextView settings = findViewById(R.id.settings);
        TextView credits = findViewById(R.id.credits);

        Typeface font1 = Typeface.createFromAsset(getAssets(), "Blox.ttf");
        Typeface font2 = Typeface.createFromAsset(getAssets(), "pixelflag.ttf");

        headline.setTypeface(font1);
        start.setTypeface(font2);
        settings.setTypeface(font2);
        credits.setTypeface(font2);


        Animation hover = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.super_hover);


        start.startAnimation(hover);

        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.redbone);
        mediaPlayer.start();

        credits.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
            }
        });


    }

    public void credits(View view) {

    }

    public void settings(View view) {
    }

    public void start(View view) {
    }
}
