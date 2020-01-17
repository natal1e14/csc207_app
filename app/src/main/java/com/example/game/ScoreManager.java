package com.example.game;

/**
 * Manages and stores the score of the most current session.
 * **/
public class ScoreManager {

    /**
     * Game 1 Score
     * **/
    private static int game1Score;

    /**
     * Game 2 Score
     * **/
    private static int game2Score;

    /**
     * Game 3 Score
     * **/
    private static int game3Score;

    /**
     * Total Score
     * **/
    private static int totalScore;
    /**
     * Total lives
     * **/
    private static int totalLives;

    private static int totalArtifacts;

    public static int theme = 0;

    /**
     * Sets Game 1's score.
     *
     * @param score - game 1's score.
     * **/
    public static void setGame1Score(int score) {game1Score = score;}

    /**
     * Sets Game 2's score.
     *
     * @param score - game 2's score.
     * **/
    public static void setGame2Score(int score) {game2Score = score;}

    /**
     * Sets Game 3's score.
     *
     * @param score - game 3's score.
     * **/
    public static void setGame3Score(int score) {game3Score = score;}

    /**
     * Sets lives .
     *
     * @param  - lives.
     * **/

    public static void setGame1Lives(int lives){totalLives = lives;}

    /**
     * Sets lives .
     *
     * @param  - lives.
     * **/

    public static void setGame2Lives(int lives){totalLives = lives;}

    public static void setGame2Artifacts(int artifacts) {totalArtifacts += artifacts;}

    /**
     * Return the lives left
     * @return lives
     */
    public static int getTotalLives() {return totalLives;}

    /**
     * Calculates the total score.
     * **/
    private static void calculateTotal(){totalScore = game1Score + game3Score + game2Score;}

    /**
     * Returns the total score.
     *
     * @return - the total score.
     * **/
    public static int getTotalScore(){
        calculateTotal();
        return totalScore;
    }

    /**
     * Returns Game 1's score.
     *
     * @return - Game 1 score
     * **/
    public static int getGame1Score(){return game1Score;}

    /**
     * Returns Game 2's score.
     *
     * @return - Game 2 score.
     * **/
    public static int getGame2Score(){return game2Score;}

    /**
     * Returns Game 3's score.
     *
     * @return - Game 3 score.
     * **/
    public static int getGame3Score(){return game3Score;}

    public static int getTotalArtifacts() {return totalArtifacts;}



}
