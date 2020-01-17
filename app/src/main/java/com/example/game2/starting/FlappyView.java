package com.example.game2.starting;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.game2.facadeThings.Game2Facade;

/**
 * The view that handles everything shown on screen in the game.
 **/
public class FlappyView extends SurfaceView implements SurfaceHolder.Callback {

    /**
     * The thread that will run in parallel to the view
     **/
    private MainThread thread;

    private Game2Facade game2;

    /**
     * Creates playable game
     *
     * @param context Context object
     */
    public FlappyView(Context context, Game2Facade game2) {

        super(context);

        this.game2 = game2;

        this.thread = new MainThread(getHolder(), this);

        getHolder().addCallback(this);

        //Create a new thread
        setFocusable(true);
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    // Start the MainThread here
    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        thread.setRunning(true);
        thread.start();
    }


    //Stops the MainThread once the surface is destroyed
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        //It can take multiple attempts to stop a thread
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            retry = false;
        }

    }

    /**
     * Updates the game screen.
     **/
    public void update() {
        game2.update();
    }

    @Override
    /**
     * Draws the game onto the screen
     */
    public void draw(Canvas canvas) {
        super.draw(canvas);
        game2.draw(canvas);
    }


    /**
     * Defines actions when the screen is touched.
     *
     * @param event - the motionevent that occurs
     * @return - the status of the touch event
     **/
    public boolean onTouchEvent(MotionEvent event) {

        //If screen is tapped
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            game2.onTap();

        }
        return true;
    }


    /**
     * Returns the score.
     *
     * @return - the score
     **/
    int getScore() {
        return game2.getScore();
    }


    /**
     * returns the number of artifacts collected
     *
     * @return - number of artifacts collected
     */
    int getNumArtifacts() {
        return game2.getNumArtifacts();
    }

    /**
     * Returns the game over status of the game.
     *
     * @return - the game over status.
     **/
    boolean isGameOver() {
        return game2.wasGameLost();
    }

    /**
     * Returns whether the game is finished or not
     *
     * @return - the game is done
     */
    boolean isGameDone() {
        return game2.isGameDone();
    }

    /**
     * @return the number of lives left
     */
    int getLife() {
        return game2.getLife();
    }

    /**
     * @param life changing the number of lives
     */
    void setLife(int life) {
        game2.setLife(life);
    }

    /**
     * return the level (int)
     * @return level number
     */
    int getLevel() {
        return game2.getLevel();
    }

    /**
     * return time left in game
     * @return time left in game
     */
    int getTime() {
        return game2.getTime();
    }

    /**
     * get the velocity in game
     * @return velocity
     */
    int getVelocity() {
        return game2.getVelocity();
    }
}