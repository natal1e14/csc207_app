package com.example.game2.asteroids;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * An asteroid.
 **/
public class Asteroid {

    /**
     * X - position of the asteroid
     **/
    private int xPos;

    /**
     * Y - position of the asteroid
     **/
    private int yPos;

    /**
     * The x velocity of the asteroid.
     **/
    private int xVelocity = -1;

    /**
     * The status of the asteroid, whether it is on or off screen.
     **/
    private boolean offScreen = false;

    /**
     * The screen width.
     **/
    private static int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

    /**
     * The screen height.
     **/
    private static int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    /**
     * The image of the asteroid
     **/
    private Bitmap image;

    /**
     * All the points that can collide with another object.
     */
    private int[][] collisionPoints = new int[4][2];

    /**
     * Whether or not this asteroid is from the last level or not
     */
    private boolean old = false;


    /**
     * Create a new Asteroid with the given params
     *
     * @param x     the initial x position of the asteroid
     * @param y     the initial y position of the asteroid
     * @param image the visual representation of the asteroid object
     */
    Asteroid(int x, int y, Bitmap image) {
        this.xPos = x;
        this.yPos = y;
        this.image = image;

        //Collision Points:
        //top point
        collisionPoints[0][0] = xPos + image.getWidth() / 2;
        collisionPoints[0][1] = yPos;

        //left
        collisionPoints[1][0] = xPos;
        collisionPoints[1][1] = yPos + image.getHeight() / 2;

        //right
        collisionPoints[3][0] = xPos + image.getWidth();
        collisionPoints[3][1] = yPos + image.getHeight() / 2;


        //bottom
        collisionPoints[2][0] = xPos + image.getWidth() / 2;
        collisionPoints[2][1] = yPos + image.getHeight();

    }

    /**
     * Sets the off screen status of the asteroid
     *
     * @param newValue - the new status (true = asteroid is off screen)
     **/
    void setOffScreen(boolean newValue) {
        offScreen = newValue;
    }

    /**
     * Returns the off screen status of the asteroid.
     *
     * @return - the status of the asteroid.
     **/
    boolean getOffScreen() {
        return offScreen;
    }

    /**
     * Returns the value of old - if the asteroid is old.
     *
     * @return true if the asteroid is from the last level, false otherwise
     */
    boolean getOld() {
        return old;
    }

    /**
     * Sets the value of old to the given value.
     *
     * @param isOld - whether or not this asteroid is now old.
     */
    void setOld(boolean isOld) {
        old = isOld;
    }

    /**
     * Speeds up the velocity of an astroid
     */
    void speedUp() {
        xVelocity = xVelocity - 5;
    }

    /**
     * Updates the asteroid on screen.
     **/
    void update() {

        //update location
        xPos += xVelocity;
        offScreen = false;

        //If the asteroid goes off screen on the left, put it off screen on the right
        if (xPos + image.getWidth() < 0) {
            xPos = screenWidth + image.getWidth() / 3;
            offScreen = true;
        }

        updatePoints();
    }


    /**
     * Getter for the list containing the collision points of this asteroid
     *
     * @return the array of collision points
     */
    int[][] getCollisionPoints() {
        return collisionPoints;
    }


    /**
     * Updates the collision points based on the asteroid's new x position
     */
    void updatePoints() {

        collisionPoints[0][0] = xPos + image.getWidth() / 2;
        collisionPoints[1][0] = xPos;
        collisionPoints[2][0] = xPos + image.getWidth() / 2;
        collisionPoints[3][0] = xPos + image.getWidth();
    }


    /**
     * Draws the asteroid on the screen.
     *
     * @param canvas - the canvas of which to draw upon.
     **/
    void draw(Canvas canvas) {

        canvas.drawBitmap(image, xPos, yPos, null);

    }

    /**
     * Sets the speed of the asteroid to the given value
     *
     * @param v - the new velocity of the asteroid
     */
    void setSpeed(int v) {
        xVelocity = v;
    }

    /**
     * Returns the current velocity of the asteroid
     *
     * @return - the current velocity of the asteroid
     */
    int getVelocity() {
        return xVelocity;
    }

}