package com.example.jchas.kingsdemise;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MiddleCombatActivity extends AppCompatActivity {

    public static Account userAccountCommid;

    private ProgressBar eHealth;
    private ProgressBar pHealth;

    private TextView eName;
    private TextView pName;

    private TextView eHealthNum;
    private TextView pHealthNum;

    private TextView gameText;

    private Button nextButton;

    private CombatScenario currScenario;
    private boolean gameOver;
    MediaPlayer mediaPlayer;

    /**
     * Sets the screen and defines all views
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_middle_combat);

        mediaPlayer = MediaPlayer.create(this, R.raw.clairdelune);
        mediaPlayer.start();
        userAccountCommid = PrologueActivity.userAccount;

        // GUI elements
        eHealth = (ProgressBar) findViewById(R.id.enemyHealth);
        pHealth = (ProgressBar) findViewById(R.id.playerHealth);
        eName = (TextView) findViewById(R.id.enemyName);
        pName = (TextView) findViewById(R.id.playerName);
        eHealthNum = (TextView) findViewById(R.id.enemyHealthNum);
        pHealthNum = (TextView) findViewById(R.id.playerHealthNum);
        gameText = (TextView) findViewById(R.id.updateText);
        nextButton = (Button) findViewById(R.id.buttonNext);

        // Game elements
        currScenario = new CombatScenario(userAccountCommid.getUsername(), 1);  //change this
        gameOver = false;

        // Setting the screen values
        eName.setText(currScenario.getPlayerName(false));
        pName.setText(currScenario.getPlayerName(true));
        gameText.setText("");
        nextButton.setText("");
        updateStats();
    }

    /**
     * Stops the current activity
     *
     * @param view
     */
    public void stopCombat(View view) {
        this.finish();
    }

    /**
     * Strikes the opponent, dealing damage. Can only be pressed once per turn
     *
     * @param view
     */
    public void pressStrike(View view) {
        Log.w("PA", "pressed strike");
        boolean success = false;
        if (!gameOver) {
            success = currScenario.strike(true);
            if (success) {
                gameText.setText(currScenario.getPlayerName(true) + " Struck");
                nextButton.setText("Next>");
            }
        }
        updateStats();
        checkWinner();
    }

    /**
     * Guards against the opponent's strike, negating damage. Vulnerable to specials. Can only be pressed once per turn
     *
     * @param view
     */
    public void pressGuard(View view) {
        Log.w("PA", "pressed guard");
        boolean success = false;
        if (!gameOver) {
            success = currScenario.guard(true);
            if (success) {
                gameText.setText(currScenario.getPlayerName(true) + " Guarded");
                nextButton.setText("Next>");
            }
        }
        updateStats();
        checkWinner();
    }

    /**
     * Performs special move. Deals double damage against guarded enemy, no damage otherwise. Can only be pressed once per turn
     *
     * @param view
     */
    public void pressSpec(View view) {
        Log.w("PA", "pressed special");
        boolean success = false;
        if (!gameOver) {
            success = currScenario.special(true);
            if (success) {
                gameText.setText(currScenario.getPlayerName(true) + " Used A Special");
                nextButton.setText("Next>");
            }
        }
        updateStats();
        checkWinner();
    }

    /**
     * Finishes the player's turn and proceeds to the opponent's turn. Only appears when a move has been taken
     *
     * @param view
     */
    public void nextTurn(View view) {
        Log.w("PA", "pressed next");
        gameText.setText("");
        int move = 0;
        if (!currScenario.isPlayerTurn()) {
            Log.w("PA", "Now Enemy Turn");
            move = currScenario.enemyTurn();
            enemyMoveText(move);
        }
        updateStats();
        nextButton.setText("");
        checkWinner();
    }

    /**
     * Updates the values on the screen to reflect the current game state
     */
    public void updateStats() {
        Log.w("PA", "Player update");

        // Health related updating
        int[] playerHealth = currScenario.getHealthStats(true);
        int[] enemyHealth = currScenario.getHealthStats(false);
        pHealth.setMax(playerHealth[1]);
        eHealth.setMax(enemyHealth[1]);
        pHealth.setProgress(playerHealth[1] - playerHealth[0]);
        eHealth.setProgress(enemyHealth[1] - enemyHealth[0]);
        pHealthNum.setText(currScenario.getHealthQuotient(true));
        eHealthNum.setText(currScenario.getHealthQuotient(false));
    }

    /**
     * Writes the correct text for the opponent's move
     *
     * @param choice
     */
    public void enemyMoveText(int choice) {
        switch (choice) {
            case 0:
                gameText.setText(currScenario.getPlayerName(false) + " Struck");
                break;
            case 1:
                gameText.setText(currScenario.getPlayerName(false) + " Guarded");
                break;
            case 2:
                gameText.setText(currScenario.getPlayerName(false) + " Used A Special");
                break;
        }
    }

    /**
     * Checks to see if there is a winner and who it is. Sets the game over variable accordingly
     */
    public void checkWinner() {



        int result = currScenario.winner();
        if (result == 1) {

            mediaPlayer.stop();
            gameText.setText(currScenario.getPlayerName(true) + " Wins!");
            gameOver = true;
            Intent intent = new Intent(this, MiddleEndActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        } else if (result == -1) {
            mediaPlayer.stop();

            gameText.setText(currScenario.getPlayerName(false) + " Win!");
            gameOver = true;
            Intent intent = new Intent(this, YouLoseActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        } else {
            gameOver = false;
        }
    }
}
