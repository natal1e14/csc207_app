package com.example.game1.entities;

import android.graphics.Canvas;

/**
 * An object in the maze.
 **/
abstract class MazeObject {
    /**
     * x coordinate of this object.
     */
    private int x;
    /**
     * y coordinate of this object.
     */
    private int y;

    MazeObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Draws the object onto the screen
     *
     * @param canvas The canvas to draw on.
     */
    abstract void draw(Canvas canvas);

    /**
     * Returns the x coordinate of the target.
     *
     * @return - x coordinate
     **/
    int getX() {
        return this.x;
    }

    /**
     * Returns the y coordinate of the target.
     *
     * @return - the y coordinate
     **/
    int getY() {
        return this.y;
    }

    /**
     * Add num to the x coordinate
     *
     * @param num number to add
     */
    void addX(int num) {
        this.x += num;
    }

    /**
     * Add num to the y coordinate
     *
     * @param num number to add
     */
    void addY(int num) {
        this.y += num;
    }

    /**
     * Sets the x coordinate to num
     *
     * @param num number to set x coordinate
     */
    void setX(int num) {
        this.x = num;
    }

    /**
     * Sets the y coordinate to num
     *
     * @param num number to set y coordinate
     */
    void setY(int num) {
        this.y = num;
    }
}
