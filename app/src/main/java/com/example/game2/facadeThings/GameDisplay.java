package com.example.game2.facadeThings;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.game2.artifacts.ArtifactManager;
import com.example.game2.asteroids.AsteroidManager;
import com.example.game2.spaceObjects.SpaceObjects;
import com.example.game2.spaceObjects.Spaceship;
import com.example.game2.spaceObjects.Timer;

class GameDisplay {
    /**
     * The paint used to paint text on screen.
     **/
    private Paint paint;

    /**
     * The background image
     **/
    private Bitmap bg;

    /**
     * Stores space objects
     */
    private SpaceObjects spaceObj;

    GameDisplay(Paint paint, Bitmap bg, SpaceObjects spaceObj) {
        this.paint = paint;
        this.bg = bg;
        this.spaceObj = spaceObj;

    }

    /**
     * Draw everything on the canvas
     *
     * @param canvas
     * @param lives  - lives left
     * @param score  - game score
     */
    void drawObjects(Canvas canvas, int lives, int score) {
        //What needs to be drawn goes here, including
        //drawing sprites and things (call sprite.draw(canvas))
        canvas.drawBitmap(bg, 0, 0, null);

        Spaceship ship = spaceObj.getShip();
        AsteroidManager asteroidManager = spaceObj.getAsteroidManager();
        ArtifactManager artifactManager = spaceObj.getArtifactManager();

        ship.draw(canvas);
        asteroidManager.draw(canvas);
        artifactManager.draw(canvas);

        spaceObj.getTimer().draw(canvas, spaceObj.getLevel());

        canvas.drawText("Lives: " + lives, 750, 100, paint);
        canvas.drawText("Scores: " + score, 600, 2100, paint);
    }
}
