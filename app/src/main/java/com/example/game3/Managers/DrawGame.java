package com.example.game3.Managers;

import android.graphics.Canvas;
import android.util.Log;

import com.example.game.Globals;
import com.example.game3.Entities.Galaxy;

import java.util.ArrayList;
import java.util.Random;

public class DrawGame {

    /**
     * The galaxies to be drawn.
     */
    private ArrayList<Galaxy> galaxies;

    /**
     * The score manager.
     */
    private ScoreKeeper scoreKeeper;

    private DrawGame(){}

    public static class Builder {
        private ArrayList<Galaxy> galaxyList;
        private ScoreKeeper scoreKeeper;

        Builder setGalaxyList(ArrayList<Galaxy> galaxies) {
            this.galaxyList = galaxies;
            return this;
        }

        Builder setScoreKeeper(ScoreKeeper scoreKeeper) {
            this.scoreKeeper = scoreKeeper;
            return this;
        }

        DrawGame build(){
            DrawGame drawGame = new DrawGame();
            drawGame.galaxies = this.galaxyList;
            drawGame.scoreKeeper = this.scoreKeeper;

            return drawGame;
        }
    }

    /**
     * Updates the visibility of a Galaxy object if the touch occurred within the bounds
     * of that Galaxy instance's bounds on the screen.
     *
     * @param tapX - the x coordinate of the tap
     * @param tapY - the y coordinate of the tap
     */
    void updateGalaxies(int tapX, int tapY) {
        int count = 0;
        while (count < galaxies.size()) {
            if (galaxies.get(count).withinBounds(tapX, tapY) &&
                    !galaxies.get(count).getColour().equals("planet")) {
                scoreKeeper.addScore(galaxies.get(count).getScore());
                galaxies.remove(count);
                break;
            } else if (galaxies.get(count).withinBounds(tapX, tapY) &&
                    galaxies.get(count).getColour().equals("planet")){
                scoreKeeper.addScore(galaxies.get(count).getScore());
                Globals.addArtifact();
                galaxies.remove(count);
                break;
            }
            count++;
        }
    }

    /**
     * Updates the visibility of a Galaxy object if the touch occurred within the bounds
     * of that Galaxy instance's bounds on the screen.
     *
     * @param tapX - the x coordinate of the tap
     * @param tapY - the y coordinate of the tap
     */
    void updateGalaxies(int tapX, int tapY, String colour) {
        int count = 0;
        while (count < galaxies.size()) {
            if (galaxies.get(count).withinBounds(tapX, tapY)
                    && colour.equals(galaxies.get(count).getColour())) {
                scoreKeeper.addScore(galaxies.get(count).getScore());
                Log.d("mytag", galaxies.get(count).getColour());
                galaxies.remove(count);
                break;
            } else if (galaxies.get(count).withinBounds(tapX, tapY)
                    && galaxies.get(count).getColour().equals("wormhole")){
                scoreKeeper.addScore(galaxies.get(count).getScore());
                Log.d("mytag", galaxies.get(count).getColour());
                scoreKeeper.removeLife();
                galaxies.remove(count);
            } else if (galaxies.get(count).withinBounds(tapX, tapY)
                    && galaxies.get(count).getColour().equals("planet")){
                scoreKeeper.addScore(galaxies.get(count).getScore());
                Globals.addArtifact();
                galaxies.remove(count);
            } else if (galaxies.get(count).withinBounds(tapX, tapY)
                    && !colour.equals(galaxies.get(count).getColour())) {
                Log.d("mytag", galaxies.get(count).getColour());
                scoreKeeper.removeLife();
                galaxies.remove(count);
                break;
            }

            count++;
        }
    }

    /**
     * Draws all objects.
     *
     * @param canvas - the screen to be drawn on
     */
    public void draw(Canvas canvas) {
        if (canvas != null) {
            for (Galaxy obj : galaxies) {
                obj.draw(canvas);
            }
        }
    }

    /**
     * Updates galaxies to only include the objects that have not been tapped yet, and haven't yet
     * exceeded their time limit on the screen.
     */
    public void update(Galaxy newGalaxy) {
        Random random = new Random();

        int i = 0;

        while (i < galaxies.size()) {
            float updateGalaxy = random.nextFloat();

            if (updateGalaxy <= 0.1) {
                boolean delete = galaxies.get(i).update();

                if (delete) {
                    galaxies.remove(i);
                }
            }

            i = i + 1;
        }

        float addGalaxy = random.nextFloat();

        if (addGalaxy <= 0.02) {
            galaxies.add(newGalaxy);
        }
    }

    int returnScore() {
        return scoreKeeper.getScore();
    }

    /**
     * Sets the score
     * @param score - the score
     */
    void setTotalScore(int score) {
        scoreKeeper.setTotalScore(score);
    }

    /**
     * Returns the game over status.
     *
     * @return - the game over status
     * **/
    boolean gameOver() {
        return scoreKeeper.gameOver();
    }

    /**
     * Return the amount of lives in string format.
     *
     * @return - the amount of lives
     * **/
    String getLives() {
        return scoreKeeper.getLives();
    }
}
