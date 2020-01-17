package com.example.game2.artifacts;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import java.util.ArrayList;

/**
 * ArtifactManager manages the actions of Artifact objects
 */
public class ArtifactManager {

    /**
     * The list of artifacts.
     **/
    private ArrayList<FlappyArtifact> artifacts;

    /**
     * The image used for the artifacts
     */
    private Bitmap image;

    /**
     * The screen width.
     **/
    private static int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;


    /**
     * ArtifactManager constructor
     *
     * @param image - the image to be used for the artifacts.
     */
    public ArtifactManager(Bitmap image) {
        artifacts = new ArrayList<>();
        ;
        this.image = image;
    }

    /**
     * Getter for the artifacts.
     *
     * @return returns the list of all the artifacts
     */
    public ArrayList<FlappyArtifact> getArtifacts() {
        return artifacts;
    }

    /**
     * Creates a new artifact and adds it to the artifacts list
     */
    public void addArtifact() {
        int BUFFER = 40;
        FlappyArtifact newArtifact = new FlappyArtifact(screenWidth + BUFFER, 900 + BUFFER, image);
        artifacts.add(newArtifact);
    }


    /**
     * Checks for collisions between the spaceship + artifacts
     *
     * @param ship - the rectangle object containing all the points on/around the spaceship
     * @return returns the number of artifacts that the ship collided with.
     */
    public int checkCollision(Rect ship) {
        ArrayList<FlappyArtifact> toRemove = new ArrayList<FlappyArtifact>();
        int num = 0;

        for (FlappyArtifact a : artifacts) {

            if (Rect.intersects(ship, a.getDetectRect())) {
                toRemove.add(a);
                num++;

            }
        }

        artifacts.removeAll(toRemove);
        return num;


    }


    /**
     * Updates the velocity+position of the artifacts and removes
     * artifacts that move off the screen.
     *
     * @param velocity - the new velocity of the artifacts
     */
    public void update(int velocity) {

        ArrayList<FlappyArtifact> toRemove = new ArrayList<FlappyArtifact>();

        for (FlappyArtifact a : artifacts) {
            a.update(velocity);


            if (a.offScreen()) {
                toRemove.add(a);
            }

        }

        artifacts.removeAll(toRemove);

    }

    /**
     * Removes the specified artifact from the list artifacts
     *
     * @param art - the artifact to be removed
     */
    public void remove(FlappyArtifact art) {
        artifacts.remove(art);
    }

    /**
     * Draws the Artifacts on screen.
     **/
    public void draw(Canvas canvas) {

        for (FlappyArtifact a : artifacts) {
            a.draw(canvas);
        }
    }
}
