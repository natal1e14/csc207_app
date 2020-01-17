package com.example.game3.Entities;

import android.app.Application;
import android.graphics.Bitmap;

/**
 * A class that stores local constants.
 * **/
public class Constants extends Application {

    /**
     * The list of image files.
     * **/
    private static Bitmap[] galaxyImages;

    /**
     * The list of colour names for galaxies.
     * **/
    private static String[] colourList = {"wormhole", "black", "yellow", "purple", "red"};

    /**
     * Sets the galaxy images.
     * **/
    public static void setGalaxyImages(Bitmap[] lst) {
        galaxyImages = lst;
    }

    /**
     * Returns the list of galaxy images.
     * **/
    public static Bitmap[] getGalaxyImages() {
        return galaxyImages;
    }

    /**
     * Returns the colour list.
     * **/
    public static String[] getColourList(){return colourList;}
}
