package com.example.game1.view;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Thread that controls Game 1.
 */
class Game1Thread extends Thread {
    /**
     * The canvas container.
     */
    private SurfaceHolder surfaceHolder;
    /**
     * Where the maze is drawn.
     */
    private MazeView mazeView;
    /**
     * Whether the thread is running.
     */
    private boolean isRunning;
    /**
     * The canvas on which to draw the maze.
     */
    private static Canvas canvas;

    /**
     * Construct the thread.
     *
     * @param surfaceHolder the canvas container.
     * @param mazeView      where the maze is drawn
     */
    Game1Thread(SurfaceHolder surfaceHolder, MazeView mazeView) {

        super();
        this.surfaceHolder = surfaceHolder;
        this.mazeView = mazeView;
    }

    /**
     * Sets the running of the thread.
     *
     * @param isRunning - true if it is running, false if it is not.
     **/
    void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    @Override
    public void run() {
        while (isRunning) {
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.mazeView.draw(canvas);
                }
            } catch (Exception e) {
                e.printStackTrace();
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
    }
}
