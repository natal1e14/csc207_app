package com.example.game2.spaceObjects;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * A spaceship.
 **/
public class Spaceship {

    /**
     * The image of the spaceship on screen.
     **/
    private Bitmap image;

    /**
     * The x and y coordinates
     **/
    private int x, y;

    /**
     * The vertical velocity of the ship.
     **/
    final private int yVelocity = 10;

    /**
     * The default jump number
     **/
    final private int defaultJump = 200;

    /**
     * The amount the ship has jumped
     **/
    private int amountJumped;

    /**
     * The status of the ship if it is jumping
     **/
    private boolean jumping;

    /**
     * Screen width
     **/
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

    /**
     * Screen height
     **/
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    /**
     * Collision rectangle
     **/
    private Rect detectRect;

    /**
     * If the ship has gone below the screen
     */
    private boolean shipOffScreen;


    /**
     * Create a new spaceship
     *
     * @param bmp the image to use as the spaceship
     */
    public Spaceship(Bitmap bmp) {


//        float aspectRatio = bmp.getWidth()/ bmp.getHeight();
//        int w  = Math.round(screenWidth/aspectRatio);
//        int h = 100; //Math.round(aspectRatio*screenHeight);

        image = Bitmap.createScaledBitmap(bmp, 200, 200, false);

        jumping = false;

        x = 20;
        y = screenHeight / 2 - 80;

        detectRect = new Rect(x, y, x + image.getWidth(), y + image.getHeight());
    }

    /**
     * Draws the spaceship.
     *
     * @param canvas - the canvas of which to draw upon.
     **/
    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);

    }

    /**
     * Updates the ship on screen.
     **/
    void update() {
        shipMovement();

        updateRect();
    }

    /**
     * Deals with the movement of the ship on screen.
     **/
    private void shipMovement() {
        if (jumping) {
            if (y <= 10) {
                jumping = false;
            } else {
                y = y - yVelocity - 3;

                if (amountJumped >= defaultJump) {
                    amountJumped = 0;
                    jumping = false;
                }

                amountJumped += yVelocity * 2;
            }
        } else {
            y += yVelocity;

            if (y > screenHeight) {
                shipOffScreen = true;
            }
        }
    }

    /**
     * Returns the collision rectangle.
     *
     * @return - collision rectangle
     **/
    public Rect getDetectRect() {
        return detectRect;
    }

    /**
     * Updates the collision rectangle on screen.
     **/
    private void updateRect() {
        detectRect.left = x;
        detectRect.top = y;
        detectRect.right = x + image.getWidth();
        detectRect.bottom = y + image.getHeight();
    }

    /**
     * Changes the jumping status of the ship.
     **/
    void onTap() {
        if (!jumping)
            jumping = true;
    }

    boolean getShipOffScreen() {
        return shipOffScreen;
    }
}

