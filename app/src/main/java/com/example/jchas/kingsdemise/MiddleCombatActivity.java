package com.example.jchas.kingsdemise;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MiddleCombatActivity extends AppCompatActivity {

    public static Account userAccountCommid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_middle_combat);

        userAccountCommid = MiddleActivity.userAccountMid;

        //combat goes here
    }


    public void SKIPMiddle(View view){
        Intent intent = new Intent(this, MiddleEndActivity.class);
        startActivity(intent);
    }
}
