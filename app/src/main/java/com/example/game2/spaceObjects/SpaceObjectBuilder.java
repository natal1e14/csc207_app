package com.example.game2.spaceObjects;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Paint;

import com.example.game.R;
import com.example.game2.artifacts.ArtifactManager;
import com.example.game2.asteroids.Asteroid;
import com.example.game2.asteroids.AsteroidFactory;
import com.example.game2.asteroids.AsteroidManager;

import java.util.ArrayList;

public class SpaceObjectBuilder {

    /**
     * Main ship
     */
    private Spaceship ship;

    /**
     * asteroid manager
     */
    private AsteroidManager asteroidManager;

    /**
     * artifact manager
     */
    private ArtifactManager artifactManager;

    /**
     * game timer
     */
    private Timer timer;

    /**
     * game context - the activity
     */
    private Context context;

    /**
     * level of the game
     */
    private int level;

    public SpaceObjectBuilder buildShip(Context context, int shipType) {
        this.context = context;

        if (shipType == 2) {
            ship = new Spaceship(BitmapFactory.decodeResource(context.getResources(), R.drawable.starship2));
        } else {
            ship = new Spaceship(BitmapFactory.decodeResource(context.getResources(), R.drawable.starship));
        }

        return this;
    }

    /**
     * builds the astroid manager
     * @return this (SpaceObjectBuilder)
     */
    public SpaceObjectBuilder buildAsteroidManager() {
        ArrayList<Asteroid> asteroids =
                AsteroidFactory.create(BitmapFactory.decodeResource(context.getResources(), R.drawable.asteroid), 5, 1);

        asteroidManager = new AsteroidManager(asteroids, BitmapFactory.decodeResource(context.getResources(), R.drawable.asteroid));


        return this;
    }


    /**
     * builds the astroid manager with velocity v
     * @param v - the velocity that we want
     * @return this (SpaceObjectBuilder)
     */
    public SpaceObjectBuilder buildAsteroidManager(int v) {
        buildAsteroidManager();
        asteroidManager.setVelocity(v);

        return this;
    }


    /**
     * builds the artifact manager
     * @return this (SpaceObjectBuilder)
     */
    public SpaceObjectBuilder buildArtifactManager() {

        artifactManager = new ArtifactManager(BitmapFactory.decodeResource(context.getResources(), R.drawable.galaxyred2));
        return this;
    }


    /**
     * builds the timer
     * @return this (SpaceObjectBuilder)
     */
    public SpaceObjectBuilder buildTimer(int maxTime, Paint paint) {
        timer = new Timer(maxTime, paint);
        return this;

    }

    /**
     * builds the level
     * @return this (SpaceObjectBuilder)
     */
    public SpaceObjectBuilder buildLevel(int level) {
        this.level = level;
        return this;
    }

    /**
     * builds the complete spaceObject
     * @return a spaceObject containing everythings
     */
    public SpaceObjects build() {
        return new SpaceObjects(ship, asteroidManager, artifactManager, timer, level);
    }
}
