package com.example.game3.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;


import com.example.game.ScoreManager;

import com.example.game3.View.LevelView;

public class Level2Activity extends Activity {

    private LevelView hyperView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);

        this.hyperView = new LevelView(this, 2);

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
                        if (hyperView.isGameOver())
                            break;
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
     * Start Intro3Activity.
     *
     * @param score
     */
    private void whenGameOver(int score) {
        Intent intent = new Intent(this, Intro3Activity.class);
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

}
