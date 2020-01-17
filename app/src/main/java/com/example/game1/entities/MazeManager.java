package com.example.game1.entities;

import android.graphics.Canvas;

import com.example.game1.entities.Maze;
import com.example.game1.entities.MazeBuilder;

/**
 * A Manager with a Maze class.
 */
public class MazeManager {
    /**
     * The Maze
     */
    private Maze maze;

    /**
     * Make the MazeManager
     *
     * @param width width of the maze
     */
    public MazeManager(int width) {
        MazeBuilder builder = new MazeBuilder();
        builder.setBoxSize(width).setMargin(width).setBoxes().setMazeRunner().setTarget().setArtifacts();
        this.maze = builder.build();
    }

    /**
     * Draws maze on canvas, by iterating through boxes and drawing them.
     *
     * @param canvas canvas to draw on.
     */
    public void draw(Canvas canvas) {
        this.maze.draw(canvas);
    }

    /**
     * Updates the maze by moving player if user has touched the screen and is in a direction.
     */
    public void update(String direction) {
        this.maze.update(direction);
    }

    /**
     * Get the maze in this manager
     *
     * @return the maze
     */
    public Maze getMaze() {
        return maze;
    }
}
