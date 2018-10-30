package com.example.jchas.kingsdemise;

import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.headline);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "Blox.ttf");
        textView.setTypeface(typeface);

        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.redbone);
        mediaPlayer.start();

    }
}
