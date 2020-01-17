package com.example.game3.View;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import com.example.game3.Entities.Constants;
import com.example.game.R;
import com.example.game3.Managers.GameTimer;
import com.example.game3.Managers.HyperManager;

/**
 * A SurfaceView class that handles everything that occurs on the display surface.
 * **/
public class HyperView extends SurfaceView implements SurfaceHolder.Callback {

    /**
     * Screen screenWidth.
     */
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

    /**
     * Screen screenHeight.
     */
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    /**
     * The main thread for this instance of Game 3 (Hyper Jump)
     */
    private Game3Thread thread;

    /**
     * The manager for the game.
     */
    HyperManager spaceManager;

    /**
     * The background of the gameview.
     */
    private Bitmap bg;

    /**
     * The timer for the game.
     */
    private GameTimer timer;

    /**
     * The max amount of time the timer will show.
     */
    private final int MAX_TIME = 20;

    /**
     * The paint object that will show the score and timer.
     */
    Paint paint;

    /**
     * Contains the decoded bitmap images that will be used by GalaxyFactory.
     * **/
    private Bitmap[] galaxyImages;

    public HyperView(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread = new Game3Thread(getHolder(), this);
        setFocusable(true);

        setGalaxyImages(context);
        galaxyImages = Constants.getGalaxyImages();

        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(80);

        timer = new GameTimer(MAX_TIME);

        spaceManager = new HyperManager(screenWidth, screenHeight, galaxyImages);
    }

    private void setGalaxyImages(Context context){
        Bitmap[] galaxyImages = new Bitmap[]{BitmapFactory.decodeResource(context.getResources(),
                R.drawable.wormhole), BitmapFactory.decodeResource(context.getResources(),
                R.drawable.galaxydark2), BitmapFactory.decodeResource(context.getResources(),
                R.drawable.galaxylight), BitmapFactory.decodeResource(context.getResources(),
                R.drawable.galaxypurple2), BitmapFactory.decodeResource(context.getResources(),
                R.drawable.galaxyred2), BitmapFactory.decodeResource(context.getResources(),
                R.drawable.wormhole2), BitmapFactory.decodeResource(context.getResources(),
                R.drawable.planet)};
        Constants.setGalaxyImages(galaxyImages);
    }


    /**
     * When the surface is created, Game3Thread is called to run the game.
     * **/
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        bg = BitmapFactory.decodeResource(getResources(),
                R.drawable.deepspace);
//        spaceManager = new HyperManager(screenWidth, screenHeight, galaxyImages);
        spaceManager.createStarterGalaxies();
        thread.setRunning(true);
        thread.start();

    }

    /**
     * Changes when a significant structural change happens to the surface.
     *
     * Game 3 does not require this at the moment.
     * **/
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

    /**
     * Stops the thread from running when the surface is destroyed.
     * **/
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
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
     * Deals with any touch events that occur on screen
     * **/
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getActionMasked();
//        Log.d(TAG, "Touch Event!");
        switch (action) {
            case MotionEvent.ACTION_DOWN:
//                Log.d(TAG, "Action was DOWN");
                spaceManager.updateGalaxies((int) event.getX(), (int) event.getY());
                break;

            case MotionEvent.ACTION_OUTSIDE:
//                Log.d(TAG, "Movement occurred outside bounds of current screen element");
                break;
        }

        return super.onTouchEvent(event);
    }

    /**
     * Draws on the canvas.
     *
     * @param canvas - the canvas to draw on.
     * **/
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        if (canvas != null) {
            Rect rect = new Rect(0,0,screenWidth, screenHeight);

            canvas.drawBitmap(bg, null, rect, null);

            spaceManager.draw(canvas);

            timer.draw(canvas, paint);
            canvas.drawText("Score: " + getScore(), 50, 200, paint);
        }

    }

    /**
     * Update the screen.
     * **/
    public void update() {
        spaceManager.update();
    }

    /**
     * Returns the score.
     *
     * @return the score.
     * **/
    public int getScore(){return spaceManager.getTotalScore();}

    /**
     * Sets the score
     *
     * @param score - the score
     */
    public void setScore(int score) {
        spaceManager.setTotalScore(score);
    }

    /**
     * Returns the amount of time left in the game
     *
     * @return the time left
     */
    public int getTime() {
        return timer.getTimeLeft();
    }

    /**
     * Sets the amount of time left
     *
     * @param time - the amount of time left
     */
    public void setTime(int time) {
        timer.setTimeLeft(time);
    }

//    public int getLives() {return 0;}

    /**
     * Returns true if the game is over.
     *
     * @return
     */
    public boolean isGameOver() {
        return timer.getTimeLeft() <= 0;
    }

}
