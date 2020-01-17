package com.example.game2.asteroids;

import android.content.res.Resources;
import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Random;

/**
 * The factory that builds all the asteroids.
 **/
public class AsteroidFactory {

    /**
     * The ArrayList containing all the asteroids.
     *
     * @param image - the image file of the asteroid.
     **/
    public static ArrayList<Asteroid> create(Bitmap image, int numAsteroids, int maxNum) {

        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

        image = Bitmap.createScaledBitmap(image, 300, 300, false);

        ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();

        int initialX = 0;
        if (maxNum > 1) {
            initialX = screenWidth + image.getWidth();
        }

        for (int i = 0; i < numAsteroids; i++) {

            //Top and bottom row of asteroids (unchanging)
            asteroids.add(new Asteroid(i * image.getWidth() + initialX, 0, image));
            asteroids.add(new Asteroid(i * image.getWidth() + initialX, screenHeight - image.getHeight() / 2, image));

            //Randomized asteroids:
            Random num = new Random();

            //Top randomized asteroids
            int topAsteroids = num.nextInt(maxNum); //bound is exclusive
            for (int j = 1; j <= topAsteroids; j++) {
                asteroids.add(new Asteroid(i * image.getWidth() + initialX, j * image.getHeight(), image));
            }

            //Bottom randomized asteroids
            int bottomAsteroids = num.nextInt(maxNum);

            for (int j = 1; j <= bottomAsteroids; j++) {
                asteroids.add(new Asteroid(i * image.getWidth() + initialX,
                        screenHeight - (j) * image.getHeight() - image.getHeight() / 2, image));
            }

        }

        return asteroids;
    }

}
