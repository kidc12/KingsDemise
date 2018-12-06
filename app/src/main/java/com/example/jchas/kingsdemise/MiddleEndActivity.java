package com.example.jchas.kingsdemise;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MiddleEndActivity extends AppCompatActivity {

    public static Account userAccountMidend;


    private String userName;

    private String[] dialogue;
    private int count;

    private boolean ready;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_middle_end);
        //combat goes here

        userAccountMidend = MiddleCombatActivity.userAccountCommid;

        count = 0;
        userName = userAccountMidend.getUsername();
        ready = true;


        dialogue = new String [] {
                "Old Lady: Hey you rowdy young men! ▸", // toats of woman pushes a man
                "Old Lady: Go on Farmer! I'll distract them. ▸",
                "Old Lady: You can find the King just up the road. ▸",
                "Old Lady: save us from his greed...▸",
                ""
        };


    }



    public void nextSpeech(View view){


        TextView speech = findViewById(R.id.middle_speech_end);
        speech.setText(dialogue[count]);


        if(count == 2){
            Context context = getApplicationContext();
            CharSequence text = "The Old Woman Stood in front of Them";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.show();

        }



        count++;



        if(count == dialogue.length){
            Intent intent = new Intent(this, LastActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }


}
