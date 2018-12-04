package com.example.jchas.kingsdemise;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Account {

    //this object will be pasted through each activity


    //the user's name the put in in the beginning
    private String username;
    private int ending;

    private int userHP;
    private int opHP;

    private static final String PREFERENCE_NAME = "Farmer";


    public Account(Context context){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        //the username will also be our persistent data
        username = "";
        ending = 0;
    }

    public void setPreferenceName(Context context){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PREFERENCE_NAME, username);
        editor.commit();
    }


    public String getUsername(){
        return username;
    }

    //good ending bad ending?
    public int getEnding(){
        return ending;
    }


    public void gameOver(){
        //reset HP
    }

}
