package com.example.game2.spaceObjects;

import android.graphics.Rect;
import com.example.game2.artifacts.ArtifactManager;
import com.example.game2.artifacts.FlappyArtifact;
import com.example.game2.asteroids.AsteroidManager;

import java.util.ArrayList;

public class SpaceObjects {
    /**
     * The spaceship the player controls
     **/
    private Spaceship ship;

    /**
     * The asteroid manager that helps with the asteroids on screen
     **/
    private AsteroidManager asteroidManager;

    /**
     * The artifact manager
     **/
    private ArtifactManager artifactManager;

    /**
     * The timer that times the game.
     **/
    private Timer timer;

    /**
     * If the game was lost
     **/
    private boolean gameOver;

    /**
     * Game level
     */
    private int level;

    public SpaceObjects(Spaceship ship, AsteroidManager asteroidManager, ArtifactManager artifactManager, Timer timer, int level) {
        this.ship = ship;
        this.asteroidManager = asteroidManager;
        this.artifactManager = artifactManager;
        this.timer = timer;
        this.level = level;
    }

    /**
     * Sets whether the game is over
     *
     * @param gameOver - whether the game is over
     */
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    /**
     * @return time left in game
     */
    public int getTimeLeft() {
        return timer.getTimeLeft();
    }

    /**
     * @return velocity of game
     */
    public int getVelocity() {
        return asteroidManager.getVelocity();
    }

    /**
     * @return the timer
     */
    public Timer getTimer() {
        return timer;
    }

    /**
     * @return the astroidManager
     */
    public AsteroidManager getAsteroidManager() {
        return asteroidManager;
    }

    /**
     * @return the artifactManager
     */
    public ArtifactManager getArtifactManager() {
        return artifactManager;
    }

    /**
     * @return the spaceship
     */
    public Spaceship getShip() {
        return this.ship;
    }

    /**
     * @return whether the game is over or not
     */
    public boolean getGameOver() {
        return gameOver;
    }

    /**
     * @return level of game
     */
    public int getLevel() {
        return level;
    }

    /**
     * @return the list of flappy artifacts
     */
    public ArrayList<FlappyArtifact> getArtifacts() {
        return artifactManager.getArtifacts();
    }

    /**
     * updates the ship
     */
    public void updateShip() {
        ship.update();
    }

    /**
     * Returns true when the ship does off screen (so under the screen)
     *
     * @return whether the ship went off screen
     */
    public boolean getShipOffScreen() {
        return ship.getShipOffScreen();
    }

    /**
     * @return a rectangle representing ship
     */
    public Rect getDetectRect() {
        return ship.getDetectRect();
    }

    /**
     * Adds an artifact to the list
     */
    public void addArtifact() {
        artifactManager.addArtifact();
    }

    /**
     * increment level by 1
     */
    public void increaseLevel() {
        level = level + 1;
    }

    /**
     * Update ship when screen is tapped
     */
    public void onTap() {
        ship.onTap();
    }

}
