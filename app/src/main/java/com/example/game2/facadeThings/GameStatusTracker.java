package com.example.game2.facadeThings;

import com.example.game2.spaceObjects.SpaceObjects;

class GameStatusTracker {

    /**
     * Whether the game is done or not
     */
    private boolean gameDone;

    /**
     * Stores space objects
     */
    private SpaceObjects spaceObj;

    GameStatusTracker(SpaceObjects spaceObj) {
        this.spaceObj = spaceObj;
        gameDone = false;
    }

    /**
     * Determines whether the game is finished
     */
    void gameStatusUpdate() {

        if (spaceObj.getGameOver() || spaceObj.getTimer().isTimerDone()) {
            gameDone = true;
        }
    }

    /**
     * @return whether the game is done
     */
    boolean isGameDone() {
        return gameDone;
    }

    /**
     * @return whether the game is over because the player lost
     */
    boolean wasGameLost() {
        return spaceObj.getGameOver();
    }
}
