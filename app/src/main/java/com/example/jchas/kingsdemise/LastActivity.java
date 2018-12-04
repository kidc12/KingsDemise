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

public class LastActivity extends AppCompatActivity {

    Account userAccount;

    private String userName;

    private String[] dialogue;
    private int count;

    private boolean ready;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);


        //userAccount = PrologueActivity.userAccount;

        count = 0;
        userName = "Farmer";
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
            Intent intent = new Intent(this, LastCombatActivity.class);
            startActivity(intent);

        }

    }

}
