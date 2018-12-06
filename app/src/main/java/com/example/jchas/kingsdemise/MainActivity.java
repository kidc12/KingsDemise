package com.example.jchas.kingsdemise;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    private InterstitialAd interstitialAd;
    public static Account userAccount;
    Boolean adClicked = false;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //this will be passed through the the game.
        userAccount = new Account(this);

        TextView headline = findViewById(R.id.headline);
        TextView start = findViewById(R.id.start);

        Typeface font1 = Typeface.createFromAsset(getAssets(), "Sunset Boulevard.otf");
        Typeface font2 = Typeface.createFromAsset(getAssets(), "pixelflag.ttf");


        if(!userAccount.getUsername().equals("farmer")){
            TextView saved = findViewById(R.id.savedName);
            saved.setText("Last Played as: " + userAccount.getUsername());
        }



        headline.setTypeface(font1);
        start.setTypeface(font2);

        Animation hover = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.super_hover);


        start.startAnimation(hover);

        //this causes the app to crash and slow down if on a linux machine
        mediaPlayer = MediaPlayer.create(this, R.raw.redbone);
        mediaPlayer.start();



        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.ad_unit_id));

        if (!interstitialAd.isLoading() && !interstitialAd.isLoaded()) {
            AdRequest adRequest = new AdRequest.Builder().build();
            interstitialAd.loadAd(adRequest);
        }

    }



    private void showInterstitial() {
        if (interstitialAd != null && interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {
            AdRequest adRequest = new AdRequest.Builder().build();
            interstitialAd.loadAd(adRequest);
            interstitialAd.show();
            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
        }
    }

    public void start(View view) {
        if(adClicked == false){
            showInterstitial();
            adClicked = true;
        }else{
            mediaPlayer.stop();
            intent = new Intent(MainActivity.this, PrologueActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }
}
