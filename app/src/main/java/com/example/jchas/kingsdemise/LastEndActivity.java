package com.example.jchas.kingsdemise;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class LastEndActivity extends AppCompatActivity {

    public static Account userAccountLastEnd;

    private String userName;

    private String[] dialogue;
    private int count;

    private boolean ready;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_end);


        userAccountLastEnd = LastCombatActivity.userAccountComlast;

        count = 0;
        userName = userAccountLastEnd.getUsername();
        ready = true;


        dialogue = new String [] {
                userName + ": You're Finished King! ▸",
                userName + ": The people are saved from you!▸",
                ""

        };

        mediaPlayer = MediaPlayer.create(this, R.raw.fetes);
        mediaPlayer.start();


    }

    public void nextSen(View v){

        TextView speech = findViewById(R.id.last_speechEnd);
        speech.setText(dialogue[count]);

        if(count == 2){
            Context context = getApplicationContext();
            CharSequence text = "You Defeated the King";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.show();

        }


        count++;




        if(count == dialogue.length){
            //this.finish();

            mediaPlayer.stop();
            Intent intent = new Intent(LastEndActivity.this, MainActivity.class);
            startActivity(intent);

            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }



    }

}
