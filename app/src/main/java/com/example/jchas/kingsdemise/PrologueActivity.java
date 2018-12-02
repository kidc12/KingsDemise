package com.example.jchas.kingsdemise;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PrologueActivity extends  AppCompatActivity{

    private String[] dialogueKing;
    private int countKing;

    private String[] dialogueFarm;
    private int countFarm;

    private String[] dialogueKing2;
    private int countKing2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prologue);
        countKing = 0;
        dialogueKing = new String[] {"Yet another run down farm... ▸", "Then I say this ▸", "Along with this ▸", "Abd U can keep talking ▸"};
        dialogueFarm = new String[] {"Hey what do you think you're doing! ▸"};
        dialogueKing2 = new String[] {"You there! What is your name!? "};

    }


    public void nextLine(View v){
        //CHANGE text of the thing we just clicked on
        TextView speech = findViewById(R.id.prologue_speech);

        //the king's speech
        if(countKing < dialogueKing.length){
            //set title of person speaking to king
            speech.setText(dialogueKing[countKing]);
            countKing++;
        }

        if(countFarm < dialogueFarm.length && countKing >= dialogueKing.length){
            speech.setText(dialogueFarm[countFarm]);
            countFarm++;
        }

        if(countKing < dialogueKing2.length){
            speech.setText(dialogueKing2[countKing2]);
            countKing2++;

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

            int i = 0;
            for( String s : results ) {
                if ( scores != null && i < scores.length ) {
                    Log.w( "VoiceDump", s + ": " + scores[i] );
                }
                i++;
            }

        }
    }

}
