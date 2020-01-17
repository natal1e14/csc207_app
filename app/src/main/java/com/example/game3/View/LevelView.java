package com.example.game3.View;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;

import com.example.game3.Entities.Constants;

import java.util.Random;

public class LevelView extends HyperView {

    /**
     * The list of colours that correspond to the galaxy colours.
     * **/
    private String[] colourList = Constants.getColourList();

    /**
     * The starting colour of the galaxy the player must tap on.
     * **/
    private String colour = colourList[1];

    /**
     * The level the player is playing on.
     * **/
    private int level;

    public LevelView(Context context, int level) {
        super(context);
        this.level = level;

        if (this.level == 2) {
            Random random = new Random();
            int index = random.nextInt(4) + 1;
            colour = colourList[index];
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getActionMasked();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                spaceManager.updateGalaxies((int) event.getX(), (int) event.getY(), colour);
                break;

            case MotionEvent.ACTION_OUTSIDE:
                break;
        }

        return super.onTouchEvent(event);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        if (canvas != null) {
            if (this.level == 3) {
                generateColour();
            }
            canvas.drawText("Color: " + colour, 50, 300, paint);
            canvas.drawText("Lives: " + spaceManager.getLives(), 50, 400, paint);
        }

    }

    /**
     * Randomly generates a new colour of galaxy to tap on.
     * **/
    private void generateColour() {
        Random random = new Random();
        int index = random.nextInt(4) + 1;

        float timeToChange = random.nextFloat();

        if (timeToChange <= 0.5){
            float changeColour = random.nextFloat();

            if (changeColour <= 0.01) {
                colour = colourList[index];
            }
        }

    }

    /**
     * Return the amount of lives the player has left.
     *
     * @return - the number of lives
     * **/
    public int getLives() {
        return Integer.parseInt(spaceManager.getLives());
    }

    /**
     * Returns the status of the game.
     *
     * @return - status of the game
     * **/
    @Override
    public boolean isGameOver() {
        return super.isGameOver() || getLives() <= 0;
    }
}
