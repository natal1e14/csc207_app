package com.example.game3.Entities;

import android.graphics.Bitmap;

/**
 * A planet, an artifact.
 * **/
class Planet extends Galaxy {

    Planet(Bitmap bmp, int x, int y, String colour) {
        super(bmp, x, y, colour);
        score = 100;
    }
}
