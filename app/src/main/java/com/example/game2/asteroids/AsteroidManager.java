package com.example.game2.asteroids;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Random;

/**
 * The manager that manages all the asteroids.
 **/
public class AsteroidManager {

    /**
     * The list of asteroids.
     **/
    private ArrayList<Asteroid> asteroids;

    /**
     * The image used for all of the asteroids
     */
    private Bitmap image;

    /**
     * the Maximum number of asteroids to be generated per row
     */
    private int maxAsteroids = 1;


    /**
     * Constructor
     *
     * @param asteroids - an arraylist containing all of the asteroids
     * @param image     - the image to be used for the asteroids.
     */
    public AsteroidManager(ArrayList<Asteroid> asteroids, Bitmap image) {
        this.asteroids = asteroids;
        this.image = image;
    }

    /**
     * Return the list of asteroids
     *
     * @return the list of asteroids
     */
    public ArrayList<Asteroid> getAsteroids() {
        return asteroids;
    }

    /**
     * Draws the asteroids on screen.
     **/
    public void draw(Canvas canvas) {


        for (Asteroid a : asteroids) {
            a.draw(canvas);
        }
    }

    /**
     * Set the velocity of the asteroids to the given value
     *
     * @param v - the new velocity
     */
    public void setVelocity(int v) {
        for (Asteroid a : asteroids) {
            a.setSpeed(v);
        }
    }

    /**
     * Get the current velocity of the asteroids
     *
     * @return the velocity of the asteroids
     */
    public int getVelocity() {
        return asteroids.get(0).getVelocity();
    }

    /**
     * Updates the asteroids and speeds up asteroids at 10 second intervals
     *
     * @param tenInterval - Whether ten seconds has passed
     **/
    public int update(boolean tenInterval) {


        int score = 0;

        //update the position of the asteroids
        for (Asteroid a : asteroids) {
            //if the level is over, speed up the asteroids
            if (tenInterval) {
                a.speedUp();
            }

            a.update();

            //if the asteroid went off-screen add points
            if (a.getOffScreen()) {
                score += 50;
            }


        }

        //make new asteroids if the level has upped
        if (tenInterval && maxAsteroids < 3) {
            levelUp();

        }


        removeOld();
        return score;
    }

    /**
     * Generate new asteroids for the new level
     */
    private void levelUp() {
        int numAsteroids = 5;

        for (Asteroid a : asteroids) {
            a.setOld(true);
        }

        int v = this.asteroids.get(0).getVelocity();
        this.asteroids.addAll(AsteroidFactory.create(image, numAsteroids, ++maxAsteroids));
        for (Asteroid a : asteroids) {
            a.setSpeed(v);
        }

    }


    /**
     * Returns a list containing all the collision points of every asteroid
     *
     * @return the list
     */
    public ArrayList<int[][]> getCollisionPoints() {

        ArrayList<int[][]> collisions = new ArrayList<>();

        for (Asteroid a : asteroids) {
            collisions.add(a.getCollisionPoints());
        }

        return collisions;
    }

    /**
     * Removes all the old asteroids from the last level or that have gone off screen
     */
    private void removeOld() {


        ArrayList<Asteroid> toRemove = new ArrayList<>();
        for (Asteroid a : asteroids) {
            if (a.getOld() && a.getOffScreen()) {
                toRemove.add(a);
            }

        }


        for (Asteroid b : toRemove) {
            asteroids.remove(b);
        }


    }
}






