package com.example.game3.Managers;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.CountDownTimer;

/**
 * A timer that times the game.
 */
public class GameTimer {

    /**
     * Counter that counts the amount of ticks that have passed
     * **/
    int counter;

    /**
     * The maximum amount of time allotted.
     * **/
    private int maxTime;

    /**
     * The amount of time left in the game.
     */
    private int timeLeft;

    private boolean timerFinished = false;


    public GameTimer(int maxTime) {

        this.maxTime = maxTime;

        new CountDownTimer(this.maxTime * 1000, 1000) {

            public void onTick(long millisUntilFinished) {
                counter += 1;
            }

            public void onFinish() {
                timerFinished = true;
            }

        }.start();
    }

    /**
     * Draws the timer on screen.
     *
     * @param canvas - the canvas of which to draw on
     * @param paint - the paint that will be used
     * **/
    public void draw(Canvas canvas, Paint paint){
        this.timeLeft = maxTime - counter;
        canvas.drawText("Time Left: " + timeLeft, 50, 100, paint);
    }

    /**
     * Get the amount of time left.
     *
     * @return - the time left
     */
    public int getTimeLeft() {
        return this.timeLeft;
    }

    /**
     * Set the amount of time left.
     *
     * @param time - the time left
     */
    public void setTimeLeft(int time) {
        this.maxTime = time;
        this.timeLeft = time;
    }
}
