package com.example.game3.Managers;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.game3.Entities.Galaxy;
import com.example.game3.Entities.GalaxyFactory;

import java.util.ArrayList;

public class HyperManager {

    /**
     * The class that will create all galaxy objects to be drawn.
     * **/
    private GalaxyFactory galaxyFactory;

    /**
     * The class that draws the game.
     */
    private DrawGame drawGame;

    private boolean gameOver = false;

    public HyperManager(int width, int height, Bitmap[] galaxyImages) {
        this.galaxyFactory = new GalaxyFactory.Builder(width, height).setImages(galaxyImages).build();
        this.drawGame = new DrawGame.Builder().setGalaxyList(createStarterGalaxies()).
                setScoreKeeper(new ScoreKeeper()).build();
    }

    /**
     * Draws all objects.
     *
     * @param canvas - the screen to be drawn on
     */
    public void draw(Canvas canvas) {
        drawGame.draw(canvas);
    }

    /**
     * Updates galaxies to only include the objects that have not been tapped yet, and haven't yet
     * exceeded their time limit on the screen.
     */
    public void update() {
        Galaxy galaxy = createNewGalaxy();

        drawGame.update(galaxy);
    }

    /**
     * Updates the visibility of a Galaxy object if the touch occurred within the bounds
     * of that Galaxy instance's bounds on the screen.
     *
     * @param tapX - the x coordinate of the tap
     * @param tapY - the y coordinate of the tap
     */
    public void updateGalaxies(int tapX, int tapY) {
        drawGame.updateGalaxies(tapX, tapY);
    }

    public void updateGalaxies(int tapX, int tapY, String colour) {
        drawGame.updateGalaxies(tapX, tapY, colour);
        checkGameOver();
    }

    /**
     * Return the total score of the player's current session.
     *
     * @return the total score of the player.
     */
    public int getTotalScore(){return drawGame.returnScore();}

    /**
     * Set the score
     *
     * @param score - the score
     */
    public void setTotalScore(int score) {
        drawGame.setTotalScore(score);
    }

    /**
     * Calls galaxyFactory to create a new galaxy object, and add it to the galaxy array list.
     * @return
     */
    public Galaxy createNewGalaxy(){
        Galaxy newGalaxy = galaxyFactory.createRandomGalaxy();

        return newGalaxy;
    }

    /**
     * Creates the first galaxies that will be shown on screen.
     * **/
    public ArrayList<Galaxy> createStarterGalaxies(){
        return galaxyFactory.createStarterGalaxies();
    }

    /**
     * Checks if the game is over.
     * **/
    private void checkGameOver() {
        this.gameOver = drawGame.gameOver();
    }

    /**
     * Returns the number of lives.
     *
     * @return - the number of lives in string format
     * **/
    public String getLives() {
        return drawGame.getLives();
    }
}

