package com.example.game2.spaceObjects;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.widget.Space;

/**
 * The timer that times the game.
 **/
public class Timer {

    private Paint paint;

    /**
     * Counts how many ticks have passed.
     **/
    private int counter;

    /**
     * The maximum amount of time allotted to the timer.
     **/
    private int maxTime;

    /**
     * Whether the timer is up (so game done?)
     */
    private boolean gameDone;

    public Timer(int maxTime, Paint paint) {

        this.maxTime = maxTime;
        this.paint = paint;

        new CountDownTimer(this.maxTime * 1000, 1000) {

            public void onTick(long millisUntilFinished) {
                counter += 1;
            }

            public void onFinish() {
                gameDone = true;
            }

        }.start();
    }

    /**
     * Draws the timer on the screen with level.
     *
     * @param canvas - the canvas of which to draw on
     * @param level  - game level
     **/
    public void draw(Canvas canvas, int level) {
        canvas.drawText("Time Left in",
                50, 100, paint);
        canvas.drawText("Lv " + level + ": " + (maxTime - counter - 10 * (3 - level)),
                50, 180, paint);

    }

    /**
     * Draws the timer on the sreen.
     *
     * @param canvas - the canvas of which to draw on
     **/
    public void draw(Canvas canvas) {
        canvas.drawText("Time Left: " + (maxTime - counter), 50, 100, paint);

    }

    /**
     * @return whether 60 seconds are up
     */
    public boolean isTimerDone() {
        return gameDone;
    }


    /**
     * @return whether 10 seconds are over
     */
    public boolean isTenSeconds() {
        return (maxTime - counter) % 10 == 0;
    }

    /**
     *  return seconds left
     * @return return time left
     */
    int getTimeLeft() {
        return maxTime - counter;
    }
}

