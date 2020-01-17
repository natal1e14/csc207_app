package com.example.game1.entities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.game1.view.MazeView;

/**
 * The maze player class.
 **/
class MazeRunner extends MazeObject {

    /**
     * The runners colour .
     **/
    private Paint wallPaint = new Paint();
    /**
     * The runners score.
     **/
    private int score;
    /**
     * The number of lives
     **/
    private int lives = 3;

    /**
     * Maze Runner Constructor.
     **/
    MazeRunner() {
        //All runners start at point (0,0)
        super(0, 0);
        this.score = 0;
        this.wallPaint.setColor(Color.MAGENTA);
    }

    /**
     * Move player in direction
     **/
    void move(String direction) {
        switch (direction) {
            case "RIGHT":
                addX(1);
                break;
            case "LEFT":
                addX(-1);
                break;
            case "UP":
                addY(-1);
                break;
            default:
                addY(1);
                break;
        }
    }

    /**
     * Gets the score of the game.
     *
     * @return - the score.
     **/
    public int getScore() {
        return this.score;
    }

    /**
     * Sets the score of the game.
     *
     * @param score - the score to be set.
     **/
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Draws the maze and the player.
     *
     * @param canvas - the canvas of which to draw upon.
     **/
    public void draw(Canvas canvas) {
        Maze game1Maze = MazeView.maze;
        int screenWidth = MazeView.screenWidth;
        int margin = game1Maze.getMargin();
        int size = (int) Math.floor(screenWidth * 0.8) / 5;
        canvas.drawRect((getX() * size) + margin + 20, (getY() * size) + margin + 20,
                (getX() + 1) * size + margin - 20, (getY() + 1) * size + margin - 20,
                wallPaint);
    }

    /**
     * Resets the game after the player reaches the exit.
     **/
    void setGameEnd() {
        setX(0);
        setY(0);
    }

    /**
     * Returns the number of lives of the player
     **/
    public int getLives() {
        return this.lives;
    }

    /**
     * Sets lives of player to num (used to be 2)
     **/
    public void setLives(int num) {
        this.lives = num;
    }
}

