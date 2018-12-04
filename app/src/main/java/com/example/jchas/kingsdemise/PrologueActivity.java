package com.example.jchas.kingsdemise;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PrologueActivity extends  AppCompatActivity{

    Account userAccount;

    private String userName;

    private String[] dialogue;
    private int count;

    private boolean ready;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prologue);


        userAccount = MainActivity.userAccount;

        count = 0;
        userName = "Farmer";
        ready = true;

        dialogue = new String [] {
                "King: Yet another run down farm... ▸",
                "King: I thought my rule would have fixed this. ▸",
                "King: Do I have to teach you farmers a lesson!?▸",
                "King: I did so once I will do it again!▸",
                "Farmer: Its the king!▸",
                "Farmer: You killed my father over taxes! ▸",
                "King: You there! What is your name!? ▸",
                "King: Yes, that was me. ▸",
                "King: And you look like your father too. ▸",
                userName + ": You horrible king!▸",
                userName + ": You will pay for this!▸",
                "King: Tsk. Gaurds! Finish what I have started! ▸",
                ""

        };
    }

    public void nextLine(View v){
        //CHANGE text of the thing we just clicked on



        TextView speech = findViewById(R.id.prologue_speech);
        speech.setText(dialogue[count]);

        if(count == 7 && ready){
            speech.setText("Oh, is your name " + userName + "?");
            Button yes = (Button) findViewById(R.id.yes_ProID);
            Button no = (Button) findViewById(R.id.no_ProID);

            yes.setEnabled(true);
            no.setEnabled(true);
            yes.setAlpha(1.0f);
            no.setAlpha(1.0f);
            return;
        }


        if(count == 6 ){
            getVoice();
            count++;
            return;
        }

        if(count == 4){
            ImageView myImage = (ImageView) findViewById(R.id.farmer_ProID);
            myImage.setAlpha(1.0f);
        }

        count++;



        if(count == dialogue.length){
            Intent intent = new Intent(this, CombatActivity.class);
            startActivity(intent);

        }

    }


    public void saveName(View button){
        Context context = getApplicationContext();
        CharSequence text = "Your name is " + userName;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.show();

        Button no = (Button) findViewById(R.id.no_ProID);
        button.setEnabled(false);
        no.setEnabled(false);
        button.setAlpha(0);
        no.setAlpha(0);
        ready = false;

        dialogue = new String [] {
                "King: Yet another run down farm... ▸",
                "King: I thought my rule would have fixed this. ▸",
                "King: Do I have to teach you farmers a lesson!?▸",
                "King: I did so once I will do it again!▸",
                "Farmer: Its the king!▸",
                "Farmer: You killed my father over taxes! ▸",
                "King: You there! What is your name!? ▸",
                "King: Yes, that was me. ▸",
                "King: And you look like your father too. ▸",
                userName + ": You horrible king!▸",
                userName + ": You will pay for this!▸",
                "King: Tsk. Gaurds! Finish what I have started! ▸",
                ""
        };

    }

    public void newName(View button){
        count = 6;
        userName = "Farmer";
        Button yes = (Button) findViewById(R.id.yes_ProID);
        button.setEnabled(false);
        yes.setEnabled(false);
        button.setAlpha(0);
        yes.setAlpha(0);


    }

    public void getVoice(){
        //voice
        PackageManager manager = getPackageManager();
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        List<ResolveInfo> list = manager.queryIntentActivities( intent, 0);
        if( list.size() > 0){
            //listen and report
            listen(intent);
        }else {
            Log.w("VoiceDump", "Error");
        }
    }

    public void listen( Intent intent){

        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "What is your name?");
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5);

        //start an acticity for that intent
        startActivityForResult(intent, 1);
    }

    protected void onActivityResult( int requestCode, int resultCode, Intent data ) {
        if( requestCode == 1 && resultCode == RESULT_OK ) {
            ArrayList<String> results = data.getStringArrayListExtra( RecognizerIntent.EXTRA_RESULTS );
            float [] scores = data.getFloatArrayExtra( RecognizerIntent.EXTRA_CONFIDENCE_SCORES );

            userName = results.get(0);

            int i = 0;
            for( String s : results ) {
                if ( scores != null && i < scores.length ) {
                    Log.w( "VoiceDump", s + ": " + scores[i] );
                }
                i++;
            }

        }
    }

    private void updateAccount(){
        userAccount.setPreferenceName(this);
    }


}
