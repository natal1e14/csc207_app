package com.example.game3.Entities;

import android.graphics.Bitmap;

import com.example.game3.Entities.Galaxy;

import java.util.Random;

/**
 * A tappable wormhole.
 **/
public class Wormhole extends Galaxy {

    /**
     * The unique score assigned to a wormhole.
     * **/

    public Wormhole(Bitmap bmp, int x, int y, String colour) {
        super(bmp, x, y, colour);

        score = 1000;
    }
}
