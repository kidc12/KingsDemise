package com.example.jchas.kingsdemise;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MiddleCombatActivity extends AppCompatActivity {

    private Account userAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_middle_combat);
        //combat goes here
    }


    public void SKIPMiddle(View view){
        Intent intent = new Intent(this, MiddleEndActivity.class);
        startActivity(intent);
    }
}
