package com.example.game1.entities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import com.example.game1.view.MazeView;

/**
 * A class representing an artifact in a maze
 **/
class MazeArtifact extends MazeObject {

    /**
     * The artifact's colour.
     **/
    private Paint wallPaint = new Paint();
    /**
     * The artifact's visibility
     **/
    private boolean visible = true;

    /**
     * Make a Maze Artifact
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    MazeArtifact(int x, int y) {
        super(x, y);
        wallPaint.setColor(Color.WHITE);
    }

    /**
     * Draws the artifact onto the canvas.
     *
     * @param canvas The canvas to draw on.
     */
    @Override
    void draw(Canvas canvas) {
        Maze game1Maze = MazeView.maze;
        int screenWidth = MazeView.screenWidth;
        int margin = game1Maze.getMargin();
        int size = (int) Math.floor(screenWidth * 0.8) / 5;

        canvas.drawOval((getX() * size) + margin + 20, (getY() * size) + margin + 20,
                (getX() + 1) * size + margin - 20, (getY() + 1) * size + margin - 20,
                wallPaint);
    }

    /**
     * Get visibility boolean of MazeArtifact.
     *
     * @return if MazeArtifact is visible
     */
    boolean getVisible() {
        return visible;
    }

    /**
     * Set the visibility boolean of MazeArtifact to true or false
     *
     * @param bool visibility boolean
     */
    void setVisible(boolean bool) {
        visible = bool;
    }

}
