package com.example.game3.Entities;

import android.graphics.Bitmap;

import java.util.Random;

/**
 * A tappable blackhole.
 * **/
public class Blackhole extends Galaxy {

    /**
     * The score associated with the black hole.
     * **/

    public Blackhole(Bitmap bmp, int x, int y, String colour) {
        super(bmp, x, y, colour);

        Random random = new Random();
        score = -2000;
    }
}
