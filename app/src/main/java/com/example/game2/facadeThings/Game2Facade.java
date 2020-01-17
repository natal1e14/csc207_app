package com.example.game2.facadeThings;

import android.graphics.Canvas;

import com.example.game2.spaceObjects.SpaceObjects;


public class Game2Facade {

    /**
     * Deals with objects in the game
     */
    private SpaceObjects spaceObjects;

    /**
     * Deals with how the game is display
     */
    private GameDisplay display;

    /**
     * Updates the game status
     */
    private GameStatusTracker statusTracker;

    /**
     * Updates the game logic
     */
    private GameUpdator updator;

    public Game2Facade(SpaceObjects spaceObj, GameDisplay display, GameStatusTracker statusTracker, GameUpdator updator) {

        this.spaceObjects = spaceObj;
        this.display = display;
        this.statusTracker = statusTracker;
        this.updator = updator;
    }

    /**
     * Update the game
     */
    public void update() {
        gameUpdate();
        gameStatusUpdate();
    }

    /**
     * Draw the game on canvas
     *
     * @param canvas
     */
    public void draw(Canvas canvas) {
        if (canvas != null) {
            display.drawObjects(canvas, updator.getLife(), updator.getScore());
        }
    }

    /**
     * Game update
     */
    private void gameUpdate() {
        updator.gameUpdate();
    }


    /**
     * Update game when tapped
     */
    public void onTap() {
        updator.onTap();
    }

    /**
     * Update game status
     */
    private void gameStatusUpdate() {
        statusTracker.gameStatusUpdate();
    }


    /**
     * Set the number of lives in game
     *
     * @param life - lives left
     */
    public void setLife(int life) {
        updator.setLife(life);
    }


    /**
     * @return number of artifacts collected
     */
    public int getNumArtifacts() {
        return updator.getNumArtifacts();
    }


    /**
     * @return if the game is finished
     */
    public boolean isGameDone() {
        return statusTracker.isGameDone();
    }


    /**
     * @return if the player lost the game
     */
    public boolean wasGameLost() {
        return statusTracker.wasGameLost();
    }


    /**
     * @return game score
     */
    public int getScore() {
        return updator.getScore();
    }

    /**
     * @return number of lives left
     */
    public int getLife() {
        return updator.getLife();
    }


    /**
     * @return the time left in the fame
     */
    public int getTime() {
        return spaceObjects.getTimeLeft();
    }


    /**
     * @return current velocity fo the game
     */
    public int getVelocity() {
        return spaceObjects.getVelocity();
    }

    /**
     * @return the current game level
     */
    public int getLevel() {
        return spaceObjects.getLevel();
    }
}
