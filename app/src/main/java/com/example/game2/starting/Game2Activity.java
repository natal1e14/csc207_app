package com.example.game2.starting;

import android.content.Intent;
import android.os.Bundle;

import com.example.game.GameActivity;
import com.example.game.Globals;
import com.example.game.ScoreManager;
import com.example.game2.endgame.Game2OverActivity;
import com.example.game2.endgame.Game2PassActivity;
import com.example.game2.facadeThings.FacadeBuilder;
import com.example.game2.facadeThings.Game2Facade;

/**
 * The activity that runs game 2, Avoid the Asteroids.
 **/
public class Game2Activity extends GameActivity {

    /**
     * The view used to run game2
     */
    private FlappyView view;

    /**
     * The thread used to run game2
     */
    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Makes window full screen
        super.onCreate(savedInstanceState);

        int strSize = 80;
        Game2Facade facade = new FacadeBuilder().setPaint(strSize).setObjects(this,
                getIntent().getIntExtra("ship", 1),
                getIntent().getIntExtra("time", 30),
                getIntent().getIntExtra("level", 1), getIntent().getIntExtra("speed", -1)).
                setDisplay().setStatusTracker().setGameUpdator(getIntent().getIntExtra("lives", 3), getIntent().getIntExtra("score", 0)).build();

        this.view = new FlappyView(this, facade);
        setContentView(view);



        thread = new Thread() {
            @Override
            public void run() {
                do {
                    try {
                        if (view.isGameDone())
                            break;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } while (true);

                int score = view.getScore();
                int lives = view.getLife();
                int artifacts = view.getNumArtifacts();
                ScoreManager.setGame2Score(score);
                ScoreManager.setGame2Lives(lives);
                //ScoreManager.setGame2Artifacts(artifacts);
                Globals.addArtifacts(artifacts);

                //Uncomment these to get GameOver screen up
                if (view.isGameOver()) {
                    whenGameOver(score, lives, artifacts);
                } else {
                    whenGamePassed(score, lives, artifacts);
                }
            }

        };
    }

    /**
     * Starts the thread
     */
    public void onStart() {
        super.onStart();

        thread.start();
    }

    /**
     * When the game is paused, save game2 data and move onto the game2pauseActivity
     */
    public void onPause() {
        super.onPause();

        if (!view.isGameOver() && !view.isGameDone()) {
            Intent intent = new Intent(this, Game2PauseActivity.class);
            intent.putExtra("score", view.getScore());
            intent.putExtra("lives", view.getLife());
            intent.putExtra("time", view.getTime());
            intent.putExtra("ship", getIntent().getIntExtra("ship", 1));
            intent.putExtra("speed", view.getVelocity());
            intent.putExtra("artifacts", view.getNumArtifacts());
            intent.putExtra("level", view.getLevel());
            startActivity(intent);
        }

    }

    /**
     * Starts Game2OverActivity after Game 2 ends.
     **/
    void whenGameOver(int score, int lives, int artifacts) {
        onStop();
        Intent intent = new Intent(this, Game2OverActivity.class);
        intent.putExtra("score", score);
        intent.putExtra("lives", lives);
        intent.putExtra("artifacts", artifacts);
        startActivity(intent);
        finish();
    }

    /**
     * Starts game passed activity after game 2 ends
     */
    void whenGamePassed(int score, int lives, int artifacts) {
        onStop();
        Intent intent = new Intent(this, Game2PassActivity.class);
        intent.putExtra("score", score);
        intent.putExtra("lives", lives);
        intent.putExtra("artifacts", artifacts);
        startActivity(intent);
        finish();
    }
}

