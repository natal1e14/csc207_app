package com.example.game1.entities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.game1.view.MazeView;

/**
 * A class representing the the target of a maze runner.
 **/
class Target extends MazeObject {
    /**
     * How the target looks.
     */
    private Paint wallPaint = new Paint();

    Target() {
        super(4, 7);
        wallPaint.setColor(Color.YELLOW);
    }

    /**
     * Draws the target on the maze.
     *
     * @param canvas - the canvas of which to draw upon.
     **/
    void draw(Canvas canvas) {
        Maze game1Maze = MazeView.maze;
        int screenWidth = MazeView.screenWidth;
        int margin = game1Maze.getMargin();
        int size = (int) Math.floor(screenWidth * 0.8) / 5;
        canvas.drawRect((getX() * size) + margin + 20, (getY() * size) + margin + 20,
                (getX() + 1) * size + margin - 20, (getY() + 1) * size + margin - 20,
                wallPaint);
    }
}
