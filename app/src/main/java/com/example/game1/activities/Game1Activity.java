package com.example.game1.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.game.ScoreManager;
import com.example.game1.view.MazeView;

/**
 * The activity that runs Game 1, the Maze of Doom.
 **/
public class Game1Activity extends Activity {

    private MazeView mazeView;

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
        mazeView = new MazeView(this);

        setContentView(mazeView);

        Thread thread = new Thread() {
            @Override
            public void run() {
                do { // This will continually run until checkGameEnd() is true and the while loop
                    // is broken
                    try {
//                        if (mazeView.checkGameEnd() )
                        if (mazeView.getTimeFinished())
                            break; // broken here
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } while (true);

                // After that, we switch to the GameOverActivity.

                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                int score = mazeView.getScore();
                int lives = mazeView.getLives();
                int numArtifacts = mazeView.getNumArtifacts();

                ScoreManager.setGame1Score(score);
                ScoreManager.setGame1Lives(lives);

                String scoreString = Integer.toString(mazeView.getScore());
                String livesString = Integer.toString(mazeView.getLives());
                String artString = Integer.toString(mazeView.getNumArtifacts());

                Intent intent = new Intent(Game1Activity.this,
                        Game1OverActivity.class);

                intent.putExtra("score", scoreString);
                intent.putExtra("Lives", livesString);
                intent.putExtra("Arts", artString);

                startActivity(intent);

                mazeView.setGameEnd();
            }

        };
        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mazeView.getTimeFinished()) {
            finish();
        } else {
            mazeView.cancelTimer();
            Intent intent = new Intent(this, Game1PauseActivity.class);
            startActivity(intent);
        }
    }
}
