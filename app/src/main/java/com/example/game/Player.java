package com.example.game;

import static java.lang.Integer.parseInt;

/**A player of the game. **/
class Player {
    /**
     * The player's username.
     * **/
    String userName;

    /**
     * The player's password.
     * **/
    String password;

    /**
     * The save status of the player.
     * **/
    String saved;

    /**
     * The score from the previous session
     *
     * NOTE: Score is not yet configured to be stored in the player class.
     * **/
    private int totalScore;

    private int game1Score;
    private int game2Score;
    private int game3Score;

    /**
     * Customization: The appearance of the player in-game.
     * **/
    private int appearance; // If 0, it is the red, if 1 it is the green

    /**
     * The score from the most immediate session.
     *
     * NOTE: Score is not yet configured to be stored in the player class.
     * **/
    private int currentScore = 0;

    /**
     * The number of lives remaining.
     *
     * NOTE: Lives is a statistic that has not yet been implemented.
     * **/
    private int lives = 3;

    public Player(String[] playerData) {
        userName = playerData[0];
        password = playerData[1];
        saved = playerData[2];
        totalScore = parseInt(playerData[3]);
//        game1Score = parseInt(playerData[4]);
//        game2Score = parseInt(playerData[5]);
//        game3Score = parseInt(playerData[6]);

    }

    /**
     * Returns the current player score.
     *
     * @return - the current score.
     * **/
    public int getCurrentScore() {return currentScore;}

    /**
     * Adds to the current score.
     *
     * @param newScore - the new score to be added.
     * **/
    public void increaseCurrentScore(int newScore) {currentScore = currentScore + newScore;}

    /**
     * Returns the total number of lives remaining.
     *
     * @return - the total number of lives.
     * **/
    public int getLives() {return lives;}

    /**
     * Takes away one life.
     * **/
    public void decreaseLives() {lives = lives - 1;}

    /**
     * Sets the appearance that the player has chosen.
     *
     * @param colour - the colour that the player has chosen.
     * **/
    public void setAppearance(int colour) {appearance = colour;}



}
