package com.example.jchas.kingsdemise;


import android.util.Log;

/**
 * Class for Player objects. Player objects control their health and damage statuses, as well as their name and current guarded state
 * @author Peter Alves
 * @version 0.2 12/5/18
 */
public class Player {

    // Default player values
    private int DEF_maxHealth = 100;
    private int DEF_currHealth = 100;
    private int DEF_dam = 20;

    // Player values
    private String name;
    private int maxHealth;
    private int currHealth;
    private int dam;

    private boolean isProtected;
    private boolean alive;

    /**
     * Creates a default player with the specified name
     *
     * @param newName
     */
    public Player(String newName) {
        name = newName;
        maxHealth = DEF_maxHealth;
        currHealth = DEF_currHealth;
        dam = DEF_dam;

        isProtected = false;
        alive = true;
    }

    /**
     * Creates a new player with the specified name, maximum health, current health, and the amount of damage they can deal
     *
     * @param newName
     * @param newMax
     * @param newCurr
     * @param newDam
     */
    public Player(String newName, int newMax, int newCurr, int newDam) {
        name = newName;
        maxHealth = newMax;
        currHealth = newCurr;
        dam = newDam;

        isProtected = false;
        alive = true;
    }

    /**
     * Deals the specified amount of damage to the player's current health
     *
     * @param damTaken
     */
    public void getHit(int damTaken) {
        currHealth = currHealth - damTaken;
    }

    /**
     * Checks to see if the player still has health remaining or not
     *
     * @return True if the player still has health remaining, False if the player has no health remaining
     */
    public boolean checkHealth() {
        if (currHealth <= 0) {
            alive = false;
        }
        return alive;
    }

    /**
     * Returns the possible damage capability of the player as an int
     *
     * @return The damage output of the player
     */
    public int getDam() {
        return dam;
    }
    /**
     * Flips the player from being guarded to guarded, or from not being guarded to guarded, depending on the player's current state
     */
    public void toggleGuard(){
        if(isProtected){
            isProtected = false;
        }else{
            isProtected = true;
        }
    }

    /**
     * Returns whether or not the player is currently guarding as is protected from damage
     * @return True if the player is currently guarded, false is the player is not guarding
     */
    public boolean isGuarded(){
        return isProtected;
    }

    /**
     * Returns the current health of the player as an int
     * @return Current health of the player
     */
    public int getCurrHealth(){
        return currHealth;
    }

    /**
     * Returns the maximum health of the player as an int
     * @return Maximum health of the player
     */
    public int getMaxHealth(){
        return maxHealth;
    }

    /**
     * Returns the name of the player as a string
     * @return Name of the player
     */
    public String getName(){
        return name;
    }

}