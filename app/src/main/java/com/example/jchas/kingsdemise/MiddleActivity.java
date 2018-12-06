package com.example.jchas.kingsdemise;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MiddleActivity extends AppCompatActivity {

    public static Account userAccountMid;

    private String userName;

    private String[] dialogue;
    private int count;

    private boolean ready;

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_middle);


        userAccountMid = CombatActivity.userAccountCom;

        mediaPlayer = MediaPlayer.create(this, R.raw.clairdelune);
        mediaPlayer.start();

        count = 0;
        userName = userAccountMid.getUsername();
        ready = true;

        dialogue = new String [] {
                userName + ": These coins led me to town? ▸",
                userName + ": Does the King do this to everyone? ▸",
                "Old Lady: Surprised? ▸",
                "Old Lady: His greed knows no bounds, my boy. ▸",
                "Old Lady: His taxes made me close my shop. ▸",
                userName + ": This is getting out of hand! ▸",
                userName + ": Someone needs to stop him! ▸",
                "Policeman 1: You there! ▸",
                "Policeman 2: You're wanted for resisting arrest! ▸",
                "Policeman 1: You're coming with us! ▸",
                ""
        };


    }



    public void nextSpeech(View view){


        TextView speech = findViewById(R.id.middle_speech);
        speech.setText(dialogue[count]);


        if(count == 2){
            ImageView myImage = (ImageView) findViewById(R.id.old_MidID);
            myImage.setAlpha(1.0f);
        }

        if(count == 7){
            ImageView myImage = (ImageView) findViewById(R.id.pol_MidID);
            myImage.setAlpha(1.0f);
        }

        count++;



        if(count == dialogue.length){
            mediaPlayer.stop();
            Intent intent = new Intent(this, MiddleCombatActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }
}