package com.example.jchas.kingsdemise;


import android.util.Log;

import java.util.Random;


/**
 * Combat Scenario controls players and their interaction with each other.
 * @author Peter Alves
 * @version 0.2 12/5/18
 */
public class CombatScenario {

    private Player enemy;
    private Player user;

    private int turn;
    private int prevChoice;

    /**
     * Creates default combat scenario.
     */
    public CombatScenario(){
        turn = 0;
        user = new Player("Atlas");
        enemy = new Player("Bad Dude");
        prevChoice = -1;
    }

    /**
     * Creates new combat scenario using the given player name and the corresponding scene number.
     * Scene number determines enemy status.
     * @param userName
     * @param sceneNum
     */
    public CombatScenario(String userName, int sceneNum){

        turn = 0;
        user = new Player(userName);
        prevChoice = -1;

        switch (sceneNum){

            case 0:
                enemy = new Player("Cowboys", 150, 150, 15);
                break;

            case 1:
                enemy = new Player("Guards", 200, 200, 30);
                break;
        }
    }

    /**
     * Determines if it is the user's turn.
     * @return True if it is currently the user's turn, false if not
     */
    public boolean isPlayerTurn(){
        return turn%2 == 0;
    }

    /**
     * Strike functionality. Attempts to deal damage to other player. Can be negated if the other player guarded.
     * @param isUser
     * @return True if strike was successful, false if not. Returns true also if the other player was guarded, and the strike dealt no damage
     */
    public boolean strike(boolean isUser){
        if(isPlayerTurn() && isUser){
            if(enemy.isGuarded()){
                Log.w("PA", "Enemy was guarding");
                enemy.toggleGuard();
                turn ++;
                return true;
            }
            enemy.getHit(user.getDam());
            turn ++;
            return true;
        }

        if(!isUser && !isPlayerTurn()){
            if(user.isGuarded()){
                Log.w("PA", "User was guarding");
                turn ++;
                user.toggleGuard();
                return true;
            }
            user.getHit(enemy.getDam());
            turn ++;
            return true;
        }
        return false;
    }

    /**
     * Guard functionality. Protects player from damage
     * @param isUser
     * @return True if the operation was successful, false if not
     */
    public boolean guard(boolean isUser){
        if(isPlayerTurn() && isUser){
            user.toggleGuard();
            turn ++;
            return true;
        }

        if(!isPlayerTurn() && !isUser){
            enemy.toggleGuard();
            turn ++;
            return true;
        }
        return false;
    }


    /**
     * Gives the designated player's health information as a formatted string.
     * @param isUser
     * @return String of current health/max health
     */
    public String getHealthQuotient(boolean isUser){
        String quotient = "";
        if(isUser){
            quotient = Integer.toString(user.getCurrHealth()) + "/" + Integer.toString(user.getMaxHealth());
            return quotient;
        }else{
            quotient = Integer.toString(enemy.getCurrHealth()) + "/" + Integer.toString(enemy.getMaxHealth());
            return quotient;
        }
    }


    /**
     *  Gives the health status of the character as an integer array. Arr[0] = current player health. Arr[1] = max player health.
     * @param isUser
     * @return int[] with health numbers.
     */
    public int[] getHealthStats(boolean isUser){
        int[] healthStats = new int[2];
        if(isUser){
            healthStats[0] = user.getCurrHealth();
            healthStats[1] = user.getMaxHealth();
        }else{
            healthStats[0] = enemy.getCurrHealth();
            healthStats[1] = enemy.getMaxHealth();
        }
        return healthStats;
    }

    /**
     * Returns the designated player's name.
     * @param isUser
     * @return Player's name
     */
    public String getPlayerName(boolean isUser){
        if(isUser){
            return user.getName();
        }else{
            return enemy.getName();
        }
    }

    /**
     * The "special" move. If the other player is guarding, deals double damage. Deals no damage otherwise
     * @param isUser
     * @return True if the operation was successful, false if not. Will return true in the case where the opponent was not guarding but the move was otherwise valid
     */
    public boolean special(boolean isUser){
        if(isPlayerTurn() && isUser){
            if(enemy.isGuarded()){
                Log.w("PA", "Enemy was guarding, double damage");
                enemy.getHit(2 * user.getDam());
                enemy.toggleGuard();
                turn ++;
                return true;
            }
            turn ++;
            return true;
        }

        if(!isUser && !isPlayerTurn()){
            if(user.isGuarded()){
                Log.w("PA", "User was guarding, double damage");
                user.getHit(2 * enemy.getDam());
                turn ++;
                user.toggleGuard();
                return true;
            }
            turn ++;
            return true;
        }
        return false;
    }

    /**
     * Enemy AI. Determines what move to take next randomly. Cannot make the same move twice in a row.
     * @return 0 if the enemy chose strike, 1 if guard, 2 if special
     */
    public int enemyTurn(){

        Log.w("PA", "Enemy Turn");
        Random rand = new Random();
        int choice = rand.nextInt(3);
        while (choice == prevChoice){
            choice = rand.nextInt(3);
            Log.w("PA", "Bad Move");
        }
        prevChoice = choice;

        Log.w("PA", "rand =" + choice);
        switch(choice){
            case 0:
                strike(false);
                Log.w("PA", "Enemy Chose Strike");
                break;
            case 1:
                guard(false);
                Log.w("PA", "Enemy Chose Strike");
                break;
            case 2:
                special(false);
                Log.w("PA", "Enemy Chose Strike");
                break;
        }
        Log.w("PA", "Enemy Turn Finished");
        return choice;
    }

    /**
     * Determines if someone won and who the winner is.
     * @return -1 if enemy is the winner, 0 if no winner, 1 if user is the winner
     */
    public int winner(){
        if(!user.checkHealth()){
            return -1;
        }else if(!enemy.checkHealth()){
            return 1;
        }else{
            return 0;
        }
    }
}