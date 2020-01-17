package com.example.game3.Entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;

import java.util.Random;

/**
 * A tappable galaxy.
 * **/
public class Galaxy extends BitmapDrawable {

    /**
     * This galaxy's appearance.
     */
    private Bitmap image;

    /**
     * The coordinates of the top leftmost corner of this image.
     */
    private int x, y;

    /**
     * Determines the visibility of the galaxy.
     * **/
    private boolean markForDelete = false;

    /**
     * The score that is associated with the galaxy.
     * **/
    int score;

    String colour;

    public Galaxy(Bitmap bmp, int x, int y, String colour) {
        image = bmp;
        this.x = x;
        this.y = y;
        this.colour = colour;

        Random random = new Random();
        score = random.nextInt(100) + 100;
    }

    /**
     * Returns the width of the BitMap instance in Galaxy
     * @return the width of the galaxy image
     */
    public int getWidth() {
        return image.getWidth();
    }

    /**
     * Returns the height of the BitMap instance in Galaxy
     * @return the height of the galaxy image
     */
    public int getHeight() {
        return image.getHeight();
    }

    public int getScore() {return score;}

    /**
     * Return the status of the galaxy's visibility.
     *
     * @return @return the status of the galaxy's visibility modifier, true when the galaxy should be
     *     removed from the display.
     */
    public boolean getVisibilityStatus() {
        return this.markForDelete;
    }

    /**
     * Return the status of the galaxy's visibility after checking if it should be removed from the
     * display surface. This method is called within HyperManager's update method.
     *
     * @return the status of the galaxy's visibility modifier, true when the galaxy should be removed
     *     from the display. *
     */
    public boolean update() {
        checkForDelete();
        return getVisibilityStatus();
    }

    /**
     * Draws this galaxy.
     *
     * @param canvas - the screen to draw the object on
     */
    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);
    }

    /**
     * Returns whether the tap (MotionEvent) that occurred is within the bounds of the current
     * galaxy's image.
     *
     * @param tapX - the x coordinate of the tap
     * @param tapY - the y coordinate of the tap
     * @return true if the tap is within the bounds of the galaxy's image.
     */
    public boolean withinBounds(int tapX, int tapY) {
        int x2 = this.x + getWidth();
        int y2 = this.y + getHeight();

        return (x <= tapX && tapX <= x2) && (y <= tapY && tapY <= y2);
    }


    /** Randomly checks whether or not the galaxy should be removed from the display surface.
     * **/
    public void checkForDelete(){
        Random random = new Random();
        float checkNum = random.nextFloat();

        if (checkNum < 0.05) {
            this.markForDelete = true;
        }

    }

    public String getColour() {
        return this.colour;
    }
}
