package com.example.game2.facadeThings;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.game.R;
import com.example.game2.artifacts.ArtifactManager;
import com.example.game2.asteroids.AsteroidManager;
import com.example.game2.spaceObjects.SpaceObjectBuilder;
import com.example.game2.spaceObjects.Spaceship;
import com.example.game2.spaceObjects.Timer;
import com.example.game2.spaceObjects.SpaceObjects;

public class FacadeBuilder {

    /**
     * Contains space objects
     */
    private SpaceObjects spaceObj;

    /**
     * Deals with how the game is displayed on screen
     */
    private GameDisplay display;

    /**
     * Updates the game staturs
     */
    private GameStatusTracker statusTracker;

    /**
     * Updates the game logic
     */
    private GameUpdator updator;

    /**
     * The current context
     */
    private Context context;

    /**
     * Used to draw objects on screen
     */
    private Paint paint;

    public FacadeBuilder setPaint(int strSize) {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(strSize);
        this.paint = paint;
        return this;
    }

    /**
     * @param context
     * @param shipType - Type of ship
     * @param maxTime  - Time to count down from
     * @param level    - Game level
     * @param velocity - speed of asteroids
     * @return This FacadeBuilder with spaceObjects
     */
    public FacadeBuilder setObjects(Context context, int shipType, int maxTime, int level, int velocity) {
        this.context = context;
        this.spaceObj = new SpaceObjectBuilder().buildShip(context, shipType).
                buildAsteroidManager(velocity).buildArtifactManager().buildTimer(maxTime, paint).buildLevel(level).
                build();

        return this;

    }

    /**
     * @return This facadeBuilder with a gameDisplay
     */
    public FacadeBuilder setDisplay() {
        display = new GameDisplay(paint, BitmapFactory.decodeResource(context.getResources(), R.drawable.background), spaceObj);
        return this;


    }

    /**
     * @return This facadeBuilder with a statusTracker
     */
    public FacadeBuilder setStatusTracker() {
        statusTracker = new GameStatusTracker(spaceObj);
        return this;
    }

    /**
     * @param lives - lives in game
     * @param score - game score
     * @return the facadeBuilder with a gameUpdator
     */
    public FacadeBuilder setGameUpdator(int lives, int score) {
        updator = new GameUpdator(spaceObj);
        updator.setLife(lives);
        updator.setScore(score);
        return this;
    }

    /**
     * Builds a complete game2facade
     *
     * @return a complete game2facade
     */
    public Game2Facade build() {
        return new Game2Facade(spaceObj, display, statusTracker, updator);
    }
}

