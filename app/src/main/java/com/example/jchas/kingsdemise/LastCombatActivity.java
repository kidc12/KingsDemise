package com.example.jchas.kingsdemise;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class LastCombatActivity extends AppCompatActivity {

    private Account userAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combat_last);
        //combat goes here
    }


    public void SKIPLast(View view){
        Intent intent = new Intent(this, LastEndActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
