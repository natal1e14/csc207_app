package com.example.game2.facadeThings;

import android.graphics.Rect;

import com.example.game.ScoreManager;
import com.example.game2.artifacts.ArtifactManager;
import com.example.game2.asteroids.AsteroidManager;
import com.example.game2.spaceObjects.SpaceObjects;
import com.example.game2.spaceObjects.Timer;

import java.util.ArrayList;

class GameUpdator {

    /**
     * Stores space objects
     */
    private SpaceObjects spaceObj;

    /**
     * Whether the previous second was a multiple of 10
     */
    private boolean prevTimer;

    /**
     * Game score
     */
    private int score;

    /**
     * Lives
     */
    private int lives;

    /**
     * Number of artifacts collecteed
     */
    private int numArtifacts;

    /**
     * Whether a life was lost
     */
    private boolean lifeLost;

    /**
     * Used to delay the frame and give a period of immunity after losing a life
     */
    private int frameDelay;


    GameUpdator(SpaceObjects spaceObj) {
        this.spaceObj = spaceObj;
        lifeLost = false;
        lives = ScoreManager.getTotalLives();
        frameDelay = 0;
    }

    /**
     * Update the game
     */
    void gameUpdate() {

        //Get whether 10 seconds has passed
        Timer timer = spaceObj.getTimer();
        boolean tenInterval = timer.isTenSeconds() && !prevTimer;
        prevTimer = timer.isTenSeconds();

        if (tenInterval) {
            spaceObj.increaseLevel();
        }

        spaceObjectUpdate(tenInterval);
        collisionUpdate();
    }

    /**
     * Update asteroid, artifact, ship, and lives
     *
     * @param tenInterval whether 10 seconds have passed
     */
    private void spaceObjectUpdate(boolean tenInterval) {
        //Update asteroid
        AsteroidManager asteroidManager = spaceObj.getAsteroidManager();
        score += asteroidManager.update(tenInterval);

        //Update artifact
        ArtifactManager artifactManager = spaceObj.getArtifactManager();
        artifactManager.update(asteroidManager.getVelocity());

        //Update ship
        spaceObj.updateShip();
        if (spaceObj.getShipOffScreen()) {
            shipCrashed();
        }

        livesUpdate();
        generateArtifacts();
    }

    /**
     * Detect collisions within game and update accordingly
     */
    private void collisionUpdate() {

        Rect shipp = spaceObj.getDetectRect();

        artifactCollision(shipp);
        asteroidCollision(shipp);
    }

    /**
     * Detects artifact collisions with the ship and update the score accordingly
     *
     * @param shipp the ship rectangle
     */
    private void artifactCollision(Rect shipp) {
        ArtifactManager artifacts = spaceObj.getArtifactManager();
        int bonusPoints = artifacts.checkCollision(shipp);
        numArtifacts += bonusPoints;
        int SCORE_BONUS = 200;
        score = score + (SCORE_BONUS * bonusPoints);
    }

    /**
     * Detects asteroid collisions with the ship and update the game + lives accordingly
     *
     * @param shipp the ship rectangle
     */
    private void asteroidCollision(Rect shipp) {

        AsteroidManager asteroidManager = spaceObj.getAsteroidManager();

        ArrayList<int[][]> collisions = asteroidManager.getCollisionPoints();

        for (int i = 0; i < collisions.size(); i++) {
            //If the ship collides with an asteroid
            int[][] points = collisions.get(i);

            if ((shipp.contains(points[0][0], points[0][1]))
                    || shipp.contains(points[1][0], points[1][1]) ||
                    shipp.contains(points[2][0], points[2][1]) ||
                    shipp.contains(points[3][0], points[3][1])) {
                shipCrashed();
            }
        }
    }

    /**
     * 1/200 chance of generating an artifact
     */
    private void generateArtifacts() {
        int rand = getRandomNum();
        if (rand == 1) {
            spaceObj.addArtifact();
            System.out.println("Generated");
        }
    }

    /**
     * Update space objects when screen is tapped
     */
    void onTap() {
        spaceObj.onTap();
    }

    /**
     * @return number of artifacts collected
     */
    int getNumArtifacts() {
        return numArtifacts;
    }

    /**
     * Update the live processing when the ship crashes into somethings
     */
    private void shipCrashed() {
        if (!lifeLost) {
            if (lives <= 1) {
                spaceObj.setGameOver(true);
            } else {
                spaceObj.setGameOver(false);
            }

            lives = lives - 1;
            lifeLost = true;
        }
    }

    /**
     * Update lives during game
     */
    private void livesUpdate() {
        if (lifeLost) {

            int maxFrameDelay = 100;
            if (frameDelay == maxFrameDelay) {
                lifeLost = false;
                frameDelay = 0;
            } else {
                frameDelay = frameDelay + 1;
            }
        }
    }

    /**
     * returns the score
     *
     * @return score
     */
    int getScore() {
        return score;
    }


    /**
     * @param life changing the number of lives
     */

    void setLife(int life) {
        lives = life;
    }

    /**
     * @return the number of lives left
     */

    int getLife() {
        return lives;
    }

    /**
     * Set the score
     *
     * @param score - the score we want
     */
    void setScore(int score) {
        this.score = score;
    } //spaceObj.getShip().setScore(score);}

    /**
     * @return random number
     */
    private int getRandomNum() {
        return (int) (Math.random() * 201);
    }
}
