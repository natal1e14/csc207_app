package com.example.game1.entities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * A class that represents the a square in the 2d maze.*
 */
class Box extends MazeObject {
    /**
     * Top wall, bottom wall, left wall, right wall exists or not.
     */
    private boolean top = true, bottom = true, left = true, right = true;
    /**
     * Boolean that is set to true when visited (in recursive back tracker algorithm)
     */
    private boolean visited = false;
    /**
     * Side length of box.
     */
    private int boxSize;
    /**
     * The space from the very edge of the screen to the edge of the maze
     */
    private int margin;
    /**
     * The colour of the wall.
     */
    private Paint wallPaint = new Paint();
    /**
     * The width of box lines.
     */
    private static final int LINE_WIDTH = 4;

    /**
     * Create a box at (x,y)
     *
     * @param x horizontal coordinate
     * @param y vertical coordinate
     */
    Box(int x, int y, int boxSize, int margin) {
        super(x, y);
        this.boxSize = boxSize;
        this.margin = margin;
        this.wallPaint.setColor(Color.GREEN);
        this.wallPaint.setStrokeWidth(LINE_WIDTH);
    }

    /**
     * Draws the box onto the canvas.
     *
     * @param canvas canvas to draw on.
     */
    void draw(Canvas canvas) {
        // Checks if each side is set to true or false and draws a that line along their
        // endpoints if set to true
        int size = boxSize;

        int x_ = getX() * size + margin; // x converted into screen size
        int xPlusOne = (getX() + 1) * size + margin; // x+1 converted into screen size
        int y_ = getY() * size + margin; // y converted into screen size
        int yPlusOne = (getY() + 1) * size + margin; // y+1 converted into screen size

        if (this.top) {
            // horizontal line between x and x+1 at height y
            canvas.drawLine(x_, y_, xPlusOne, y_, wallPaint);
        }
        if (this.bottom) {
            // horizontal line between x and x+1 at height y+1
            canvas.drawLine(x_, yPlusOne, xPlusOne, yPlusOne, wallPaint);
        }
        if (this.left) {
            // vertical line between y and y+1 at width x
            canvas.drawLine(x_, y_, x_, yPlusOne, wallPaint);
        }
        if (this.right) {
            // vertical line between y and y+1 at width x+1
            canvas.drawLine(xPlusOne, y_, xPlusOne, yPlusOne, wallPaint);
        }
    }

    /**
     * get boolean of Top wall
     *
     * @return top wall
     */
    public boolean getTop() {
        return top;
    }

    /**
     * get boolean of Bottom wall
     *
     * @return bottom wall
     */
    boolean getBottom() {
        return bottom;
    }

    /**
     * get boolean of left wall
     *
     * @return left wall
     */
    public boolean getLeft() {
        return left;
    }

    /**
     * get boolean of right wall
     *
     * @return right wall
     */
    boolean getRight() {
        return right;
    }

    /**
     * get visited boolean
     *
     * @return visited boolean
     */
    boolean getVisited() {
        return this.visited;
    }

    /**
     * set visited boolean to isVisited
     *
     * @param isVisited visited boolean
     */
    void setVisited(boolean isVisited) {
        this.visited = isVisited;
    }

    /**
     * Set the top wall to bool
     *
     * @param bool wall exists or not
     */
    public void setTop(boolean bool) {
        top = bool;
    }

    /**
     * Set the bottom wall to bool
     *
     * @param bool wall exists or not
     */
    void setBottom(boolean bool) {
        bottom = bool;
    }

    /**
     * Set the left wall to bool
     *
     * @param bool wall exists or not
     */
    public void setLeft(boolean bool) {
        left = bool;
    }

    /**
     * Set the right wall to bool
     *
     * @param bool wall exists or not
     */
    void setRight(boolean bool) {
        right = bool;
    }

}
