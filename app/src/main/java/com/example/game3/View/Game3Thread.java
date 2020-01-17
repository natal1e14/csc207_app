package com.example.game3.View;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * The game thread that updates the surface display of the game.
 * **/
public class Game3Thread extends Thread {

    /**
     * The surface of the game.
     * **/
    private SurfaceHolder surfaceHolder;

    /**
     * The view of the game.
     * **/
    private HyperView gameView;

    /**
     * The status of the thread, if it is running or not.
     * **/
    private boolean running;

    /**
     * The canvas of which to draw bitmap images on.
     * **/
    private static Canvas canvas;

    public Game3Thread(SurfaceHolder surfaceHolder, HyperView gameView) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;

    }

    /**
     * Set the thread running status.
     *
     * @param isRunning - represents if the thread is running. If false, it is not running.
     * **/
    void setRunning(boolean isRunning) {
        running = isRunning;
    }

    /**
     * Run the thread.
     * **/
    @Override
    public void run() {
        while (running) {
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized(surfaceHolder) {
                    this.gameView.update();
                    this.gameView.draw(canvas);
                }
            } catch (Exception e) {} finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}