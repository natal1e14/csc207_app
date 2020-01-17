package com.example.game3.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.game.ScoreManager;
import com.example.game3.View.HyperView;

/**
 * A game activity that handles the creation of the surface where the game will run.
 **/
public class Game3Activity extends Activity {

    private HyperView hyperView;

    /**
     * Sets the window type and runs a thread that times how long the game runs.
     **/
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);

        this.hyperView = new HyperView(this);
        this.hyperView.setScore(getIntent().getIntExtra("threeScore", 0));
        this.hyperView.setTime(getIntent().getIntExtra("threeTime", 20));

        setContentView(hyperView);
    }

    public void onStart() {
        super.onStart();

        Thread thread = new Thread() {

            @Override
            public void run() {
                do {
                    try {
                        if (hyperView.isGameOver()) {
                            break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } while (true);

                int score = hyperView.getScore();
                ScoreManager.setGame3Score(score);

                if (hyperView.isGameOver()) {
                    whenGameOver(score);
                } else {
                    whenGamePassed(score);
                }

            }

        };
        thread.start();
    }

    /**
     * Switch to Level 2 when Level 1 is over.
     *
     * @param score - the score so far
     */
    private void whenGameOver(int score) {
        Intent intent = new Intent(this, Intro2Activity.class);
        intent.putExtra("threeScore", score);

        startActivity(intent);
    }

    /**
     * Starts Game3PassedActivity after game has ended.
     *
     * @param score - the score
     */
    private void whenGamePassed(int score) {
        Intent intent = new Intent(this, Game3PassActivity.class);
        intent.putExtra("threeScore", score);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        if (!hyperView.isGameOver()) {
            super.onPause();

            Intent intent = new Intent(this, Game3PauseActivity.class);
            intent.putExtra("threeScore", hyperView.getScore());
            intent.putExtra("threeTime", hyperView.getTime());

            startActivity(intent);
        } else {
            Intent intent = new Intent(this, Intro2Activity.class);
            intent.putExtra("threeScore", hyperView.getScore());

            startActivity(intent);
        }
    }


}