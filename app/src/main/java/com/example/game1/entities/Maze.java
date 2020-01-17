package com.example.game1.entities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.game1.entities.Box;
import com.example.game1.entities.MazeArtifact;
import com.example.game1.entities.MazeBuilder;
import com.example.game1.entities.MazeRunner;
import com.example.game1.entities.Target;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * A class that represents the level 1 maze.*
 */
public class Maze {
    /**
     * Side length of box.
     */
    private int boxSize;
    /**
     * Margin of x and y.
     */
    private int margin;
    /**
     * An array of boxes that make up the maze.
     */
    private Box[][] boxes;

    /**
     * The maze runner in this maze.
     */
    private MazeRunner mazeRunner;
    /**
     * The target to get to in this maze.
     */
    private Target target;
    /**
     * Number of columns of the maze.
     */
    private static final int NUM_COLS = 5;
    /**
     * Number of rows of the maze.
     */
    private static final int NUM_ROWS = 8;
    /**
     * The artifacts in this maze.
     */
    private ArrayList<MazeArtifact> artifacts;

    /**
     * Create a 5 by 5 maze.
     *
     * @param builder the MazeBuilder
     */
    public Maze(MazeBuilder builder) {
        this.boxSize = builder.getBoxSize();
        this.margin = builder.getMargin();
        this.boxes = builder.getBoxes();
        this.mazeRunner = builder.getMazeRunner();
        this.target = builder.getTarget();
        this.artifacts = builder.getArtifact();
    }

    /**
     * Draws maze on canvas, by iterating through boxes and drawing them.
     *
     * @param canvas canvas to draw on.
     */
    public void draw(Canvas canvas) {
        for (int x = 0; x < NUM_COLS; x++) {
            for (int y = 0; y < NUM_ROWS; y++) {
                this.boxes[x][y].draw(canvas);
            }
        }
        mazeRunner.draw(canvas);
        target.draw(canvas);
        for (MazeArtifact artifact : artifacts) {
            if (artifact.getVisible())
                artifact.draw(canvas);
        }
    }

    /**
     * Updates the maze by moving player if user has touched the screen and is in a direction.
     */
    public void update(String direction) {
        switch (direction) {
            case "RIGHT":
                if (!checkRight(this.boxes[mazeRunner.getX()][mazeRunner.getY()])) {
                    mazeRunner.move(direction);
                }
                break;
            case "LEFT":
                if (!checkLeft(this.boxes[mazeRunner.getX()][mazeRunner.getY()])) {
                    mazeRunner.move(direction);
                }
                break;
            case "UP":
                if (!checkTop(this.boxes[mazeRunner.getX()][mazeRunner.getY()])) {
                    mazeRunner.move(direction);
                }
                break;
            default:
                if (!checkBottom(this.boxes[mazeRunner.getX()][mazeRunner.getY()])) {
                    mazeRunner.move(direction);
                }
                break;
        }
    }

    /**
     * Checks if the mazeRunner is in the same place as target.
     *
     * @return if game has ended.
     */
    public boolean checkGameEnd() {
        return ((this.mazeRunner.getX() == this.target.getX()) &&
                (this.mazeRunner.getY() == this.target.getY()));
    }

    /**
     * Checks if the mazeRunner has collected an artifact.
     *
     * @return if game has ended.
     */
    public boolean checkGetArtifact() {
        boolean found = false;
        for (int i = 0; i < this.artifacts.size(); i++) {
            if ((this.mazeRunner.getX() == this.artifacts.get(i).getX())
                    && (this.mazeRunner.getY() == this.artifacts.get(i).getY()))
                found = true;
        }
        return found;
    }

    /**
     * Removes an artifact that mazeRunner collected.
     */
    public void removeFoundArt() {
        MazeArtifact artFound = findArt();
        artFound.setVisible(false);
    }

    /**
     * Find the artifact that mazeRunner has collected
     *
     * @return MazeArtifact collected
     */
    private MazeArtifact findArt() {

        MazeArtifact found = artifacts.get(0);
        for (int i = 0; i < this.artifacts.size(); i++) {
            if ((this.mazeRunner.getX() == this.artifacts.get(i).getX())
                    && (this.mazeRunner.getY() == this.artifacts.get(i).getY())) {
                found = this.artifacts.get(i);
                this.artifacts.remove(i);
            }
        }
        return found;
    }

    /**
     * Checks if right wall of box is there.
     *
     * @param box box selected to check
     * @return true or false
     */
    private boolean checkRight(Box box) {
        return (box.getRight());
    }

    /**
     * Checks if left wall of box is there.
     *
     * @param box box selected to check
     * @return true or false
     */
    private boolean checkLeft(Box box) {
        return (box.getLeft());
    }

    /**
     * Checks if top wall of box is there.
     *
     * @param box box selected to check
     * @return true or false
     */
    private boolean checkTop(Box box) {
        return (box.getTop());
    }

    /**
     * Checks if bottom wall of box is there.
     *
     * @param box box selected to check
     * @return true or false
     */
    private boolean checkBottom(Box box) {
        return (box.getBottom());
    }

    /**
     * Returns the margin of this maze.
     */
    public int getMargin() {
        return this.margin;
    }

    /**
     * Returns the box size of this maze.
     */
    public int getBoxSize() {
        return this.boxSize;
    }

    /**
     * Returns the score of the mazeRunner of this maze at the end.
     *
     * @return mazeRunner's score
     */
    public int getScore() {
        return this.mazeRunner.getScore();
    }

    /**
     * Sets the score of the mazeRunner to score
     *
     * @param score mazeRunner's score
     */
    public void setScore(int score) {
        this.mazeRunner.setScore(score);
    }

    /**
     * Returns the lives of the mazeRunner of this maze at the end.
     *
     * @return mazeRunner's lives
     */
    public int getLives() {
        return this.mazeRunner.getLives();
    }

    /**
     * Sets the lives of the mazeRunner
     *
     * @param lives lives to set lives to
     */
    public void setLives(int lives) {
        this.mazeRunner.setLives(lives);
    }

    /**
     * Set game over
     */
    public void setGameEnd() {
        mazeRunner.setGameEnd();
    }

    /**
     * Get the x coordinate of mazeRunner
     *
     * @return x coordinate
     */
    public int getMazeRunnerX() {
        return mazeRunner.getX();
    }

    /**
     * Get the y coordinate of mazeRunner
     *
     * @return y coordinate
     */
    public int getMazeRunnerY() {
        return mazeRunner.getY();
    }
}

