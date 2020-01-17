package com.example.game2.artifacts;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;


/**
 * An artifact (collectible item) for Game 2
 */
public class FlappyArtifact {

    /**
     * The artifact's x-coordinate.
     **/
    private int x;
    /**
     * The artifact's y-coordinate.
     **/
    private int y;

    /**
     * The image of the artifact.
     **/
    private Bitmap image;

    /**
     * The rectangle that detects for collision
     **/
    private Rect detectRect;


    /**
     * Constructor
     *
     * @param x     - the initial x position of the artifact
     * @param y     - the initial x position of the artifact
     * @param image - the image to be used for the artifact
     */
    FlappyArtifact(int x, int y, Bitmap image) {
        this.x = x;
        this.y = y;
        this.image = image;

        detectRect = new Rect(x, y, x + this.image.getWidth(), y + this.image.getHeight());
    }

    /**
     * Returns the rectangle that detects collisions
     *
     * @return the collision detection rectangle.
     */
    Rect getDetectRect() {
        return detectRect;
    }


    /**
     * Updates the collision detection rectanle
     * to match the new coordinates of the artifact
     */
    private void updateRect() {
        detectRect.left = x;
        detectRect.right = x + image.getWidth();
    }


    /**
     * Draws the artifact onto the screen
     *
     * @param canvas The canvas to draw on.
     */
    void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);
    }

    /**
     * Updates the x position of the artifact
     * by adding the specified velocity to it
     *
     * @param v - the value to increase the x position of the artifact by (aka the velocity)
     */
    void update(int v) {
        x = x + v;
        updateRect();

    }

    /**
     * Checks if the artifact is off the screen
     *
     * @return returns whether the artifact is off the left side of the screen
     */
    boolean offScreen() {
        return x + image.getWidth() < 0;
    }

}
