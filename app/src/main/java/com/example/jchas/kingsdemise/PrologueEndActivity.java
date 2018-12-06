package com.example.jchas.kingsdemise;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PrologueEndActivity extends AppCompatActivity {

    public static Account userAccountPend;

    private String userName;

    private String[] dialogue;
    private int count;

    MediaPlayer mediaPlayer;

    private boolean ready;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prologue_end);


        userAccountPend = CombatActivity.userAccountCom;

        count = 0;
        userName = userAccountPend.getUsername();
        ready = true;


        dialogue = new String [] {
                userName + ": This king is geting out of hand! ▸",
                userName + ": Wait! He's gone!  ▸",
                userName + ": Huh? What's this? ▸",
                userName + ": Don't think you're getting away King! ▸",
                ""

        };
        mediaPlayer = MediaPlayer.create(this, R.raw.passepied);
        mediaPlayer.start();

    }

    public void nextLine(View v){

        TextView speech = findViewById(R.id.prologue_speechEnd);
        speech.setText(dialogue[count]);

        if(count == 3){
            Context context = getApplicationContext();
            CharSequence text = "You Found a Trail of Gold Coins";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.show();

        }


        count++;




        if(count == dialogue.length){
            mediaPlayer.stop();
            Intent intent = new Intent(this, MiddleActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }



    }




}
