package com.example.jchas.kingsdemise;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LastActivity extends AppCompatActivity {

    public static Account userAccountLast;

    private String userName;

    private String[] dialogue;
    private int count;

    private boolean ready;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);

        userAccountLast = MiddleEndActivity.userAccountMidend;

        count = 0;
        userName = userAccountLast.getUsername();
        ready = true;

        dialogue = new String [] {
                userName + ": I'm finally going to avenge my father...▸",
                userName + ": And stop this greedy King! ▸",
                "King: Greedy, huh?▸",
                "King: Taxes are only fair my boy. ▸",
                "King: There's nothing greedy about it. ▸",
                userName + ": Lies! You abuse your power! ▸",
                 "King: Why you! ▸",
                "King: I'll have to take care of you myself! ▸",
                ""
        };

        mediaPlayer = MediaPlayer.create(this, R.raw.fetes);
        mediaPlayer.start();

    }

    public void nextSen(View view){

        TextView speech = findViewById(R.id.last_speech);
        speech.setText(dialogue[count]);

        if(count == 2){
            ImageView myImage = (ImageView) findViewById(R.id.king_LastID);
            myImage.setAlpha(1.0f);
        }


        count++;


        if(count == dialogue.length){
            mediaPlayer.stop();
            Intent intent = new Intent(this, LastCombatActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }

    }

}
