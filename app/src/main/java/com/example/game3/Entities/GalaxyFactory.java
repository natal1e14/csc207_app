package com.example.game3.Entities;

import android.graphics.Bitmap;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.Random;

/**
 * A factory that creates galaxy objects.
 * **/
public class GalaxyFactory {

    /**
     * The max width that an object can be drawn in.
     */
    private int screenWidth;

    /**
     * the max height that an object can be drawing in.
     */
    private int screenHeight;

    /**
     * The available bitmap files that a galaxy can be created with.
     * **/
    private Bitmap[] galaxyImages;

    /**
     * The image for a wormhole and a blackhole.
     * **/
    private Bitmap wormHole;

    private GalaxyFactory() {}

    private String[] colourList = {"wormhole", "black", "yellow", "purple", "red"};

    public static class Builder {

        private static int screenHeight;
        private static int screenWidth;
        private static Bitmap[] galaxyImages;

        public Builder(int width, int height){
            screenHeight = height;
            screenWidth = width;
        }

        public Builder setImages(Bitmap[] imageList){
            galaxyImages = imageList;

            return this;
        }

        public GalaxyFactory build() {
            GalaxyFactory factory = new GalaxyFactory();
            factory.screenHeight = screenHeight;
            factory.screenWidth = screenWidth;
            factory.galaxyImages = galaxyImages;

            return factory;
        }
    }

    /**
     * Creates the first few galaxies that will appear on screen.
     *
     * @return - the list of galaxies.
     * **/
    public ArrayList<Galaxy> createStarterGalaxies() {

        ArrayList<Galaxy> galaxies = new ArrayList<>();

        int i = 0;

        while (i < 5) {

            Galaxy newGalaxy = createRandomGalaxy();
            galaxies.add(newGalaxy);

            i += 1;
        }

        return galaxies;
    }

    private int[] checkBoundaries(int width, int height){
        /* The following code creates a rectangle (as large as possible) that the galaxies
        can spawn at. This is to ensure that no galaxies will appear off-screen.
        * */
        Random random = new Random();

        int xValue = random.nextInt(screenWidth);
        int yValue = random.nextInt(screenHeight);


        Rect rect = new Rect(0, 0, screenWidth - width,
                screenHeight - height);

        while (!(rect.contains(xValue, yValue))) {
            xValue = random.nextInt(screenWidth);
            yValue = random.nextInt(screenHeight);
        }

        return new int[]{xValue, yValue};
    }

    /**
     * Randomly chooses which type of galaxy to create, and creates it.
     *
     * @return - the galaxy.
     * **/
    public Galaxy createRandomGalaxy(){
        Random random = new Random();

        int i = random.nextInt(4);

        Galaxy galaxy;

        int[] coords;

        switch(i) {
            case 0:
                int index = random.nextInt(4) + 1;
                Bitmap galaxyImage = galaxyImages[index];

                coords = checkBoundaries(galaxyImage.getWidth(), galaxyImage.getHeight());
                galaxy = new Galaxy(galaxyImages[index], coords[0], coords[1], colourList[index]);

                break;
            case 1:
                coords = checkBoundaries(galaxyImages[0].getWidth(), galaxyImages[0].getHeight());

                galaxy = new Blackhole(galaxyImages[0], coords[0], coords[1], "blackhole");

                break;
            case 2:
                coords = checkBoundaries(galaxyImages[0].getWidth(), galaxyImages[0].getHeight());

                galaxy = new Wormhole(galaxyImages[5], coords[0], coords[1], colourList[0]);

                break;
            case 3:
                coords = checkBoundaries(galaxyImages[0].getWidth(), galaxyImages[0].getHeight());

                galaxy = new Planet(galaxyImages[6], coords[0], coords[1], "planet");

                break;
            default:
                throw new IllegalStateException("Unexpected value: " + i);
        }

        return galaxy;
    }
}
