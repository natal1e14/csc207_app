package com.example.game2.starting;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

//Thread class allows you to run stuff
//simultaneously as in the Main class
//Can have multiple Thread classes

/**
 * The thread that runs in parallel to the game.
 **/
public class MainThread extends Thread {

    //This class creates a separate thread
    //that runs as an offshoot of the main thing

    //Want to create GameView in the MainThread

    /**
     * The surface holder.
     **/
    private SurfaceHolder surfaceHolder;

    /**
     * The view of the game.
     **/
    private FlappyView flappyView;

    /**
     * The status of the thread.
     **/
    private boolean running;

    /**
     * The canvas of which to draw on.
     **/
    public static Canvas canvas;

    public MainThread(SurfaceHolder surfaceHolder, FlappyView flappyView) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.flappyView = flappyView;
    }

    @Override
    public void run() {


        while (running) {

            canvas = null;

            try {
                //freezes the canvas so we can draw on it
                //Important otherwise you might have
                //multiple threads trying to draw on the canvas at once
                canvas = this.surfaceHolder.lockCanvas();

                synchronized (surfaceHolder) {
                    this.flappyView.update();
                    this.flappyView.draw(canvas);
                }

            } catch (Exception e) {
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }

        System.out.println("GAME OVER");
    }

    /**
     * Starts or stops the running of the game.
     *
     * @param isRunning - the status of the game
     **/
    void setRunning(boolean isRunning) {
        running = isRunning;
    }

}
