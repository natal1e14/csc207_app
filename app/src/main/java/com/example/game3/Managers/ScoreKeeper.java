package com.example.game3.Managers;

/**
 * A class that locally stores the score for game 3.
 * **/
class ScoreKeeper {

    /**
     * The score.
     * **/
    private int score = 0;

    /**
     * The number of lives remaining.
     * **/
    private int lives = 3;

    /**
     * Adds the score to the current amount of scores.
     *
     * @param newScore - the score to be added
     * **/
    void addScore(int newScore) {
        this.score += newScore;
    }

    /**
     * Returns the score.
     *
     * @return - score
     * **/
    public int getScore() {
        return score;
    }

    /**
     * Set the player's current score
     *
     * @param score - the score
     */
    void setTotalScore(int score) {
        this.score = score;
    }

    /**
     * Removes a life from the total amount of lives.
     * **/
    void removeLife() {
        if (lives > 0) {
            lives -= 1;
        }
    }

    /**
     * Returns if the game is over.
     *
     * @return the game over status.
     * **/
    boolean gameOver() {
        if (lives == 0) {
            return true;
        }
        return false;
    }

    /**
     * Returns the amount of lives, in string format.
     *
     * @return - amount of lives
     * **/
    String getLives() {
        return String.valueOf(lives);
    }
}
