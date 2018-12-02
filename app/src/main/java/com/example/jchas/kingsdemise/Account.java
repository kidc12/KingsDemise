package com.example.jchas.kingsdemise;

public class Account {

    //this object will be pasted through each activity


    //the user's name the put in in the beginning
    private String username;
    private int ending;

    public Account(){

        //the username will also be our persistent data
        username = "";
        ending = 0;
    }

    public String getUsername(){
        return username;
    }

    public int getEnding(){
        return ending;
    }


}
