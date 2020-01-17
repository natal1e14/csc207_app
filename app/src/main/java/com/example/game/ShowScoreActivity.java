package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Shows the scoreboard for all three games, and the total score.
 * **/
public class ShowScoreActivity extends AppCompatActivity implements SwitchActivityInterface{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_score);

        String game1Score = Integer.toString(ScoreManager.getGame1Score());
        String game2Score = Integer.toString(ScoreManager.getGame2Score());
        String game3Score = Integer.toString(ScoreManager.getGame3Score());
        String totalScore = Integer.toString(ScoreManager.getTotalScore());


        String lives = Integer.toString(ScoreManager.getTotalLives());

        TextView displayGame1 = findViewById(R.id.displayScore1);
        displayGame1.setText("Maze of Doom: " + game1Score);

        TextView displayGame2 = findViewById(R.id.displayScore2);
        displayGame2.setText("Avoid the Asteroids: " + game2Score);

        TextView displayGame3 = findViewById(R.id.displayScore3);
        displayGame3.setText("Galaxy Hyperjump: " + game3Score);

        TextView displayTotal = findViewById(R.id.displayTotalScore);
        displayTotal.setText("Total: " + totalScore);

        TextView displayLives = findViewById(R.id.displayLives);
        displayLives.setText("Lives: " + lives);

        TextView displayArtifacts = findViewById(R.id.displayArtifacts);
        displayArtifacts.setText("Artifacts: " + Globals.getArtifacts());
    }

    /**
     * Returns to the main menu (game selection screen)
     *
     * @param view - the view of the display area.
     **/
    @Override
    public void nextActivity(View view){
        Intent intent = new Intent(ShowScoreActivity.this, GameListActivity.class);

        startActivity(intent);
    }
}
