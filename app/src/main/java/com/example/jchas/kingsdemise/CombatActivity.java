package com.example.jchas.kingsdemise;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class CombatActivity extends AppCompatActivity {

    private Account userAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combat);
        //combat goes here
    }



    public void SKIP(View view){
        Intent intent = new Intent(this, PrologueEndActivity.class);
        startActivity(intent);
    }
}
