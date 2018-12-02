package com.example.jchas.kingsdemise;

public class Account {

    //this object will be pasted through each activity


    //the user's name the put in in the beginning
    private String username;
    private int ending;

    private int userHP;
    private int opHP;


    public Account(){

        //the username will also be our persistent data
        username = "";
        ending = 0;
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
