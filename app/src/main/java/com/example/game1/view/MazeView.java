package com.example.game1.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.game.Globals;
import com.example.game1.entities.Maze;
import com.example.game1.entities.MazeManager;

/**
 * A class that represents the level 1 maze.*
 */
public class MazeView extends SurfaceView implements SurfaceHolder.Callback {

    /**
     * Screen width.
     */
    public static int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

    /**
     * The maze manager
     */
    public static MazeManager mazeManager;

    /**
     * The maze.
     */
    public static Maze maze;

    /**
     * The thread managing maze and maze object movements.
     */
    private Game1Thread thread;
    /**
     * The time l
     */
    private int counter = 0;
    /**
     * The timers paint attribute
     */
    private Paint paint;
    /**
     * The count down timer for this game1
     */
    private CountDownTimer countDownTimer;
    /**
     * Time finished (set to true if no time remaining)
     */
    private boolean timeFinished;
    /**
     * The # of mazes created/ completed
     */
    private int mazeCount = 0;
    /**
     * The # of artifacts collected.
     */
    private int artifactCount = 0;

    /**
     * Creates a new maze.
     *
     * @param context the context
     */
    public MazeView(Context context) {
        super(context);

        getHolder().addCallback(this);

        thread = new Game1Thread(getHolder(), this);
        setFocusable(true);

        // Timer initialization
        paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setTextSize(80);

        countDownTimer =
                new CountDownTimer(20 * 1000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        timeFinished = false;
                        counter += 1;
                        maze.setScore(100 * (mazeCount + (2 * artifactCount)));
                    }

                    public void onFinish() {
                        timeFinished = true;
                        maze.setScore(100 * (mazeCount + (2 * artifactCount)));
                    }
                };
        countDownTimer.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        int new_dim = (int) Math.floor(screenWidth * 0.8);
        mazeManager = new MazeManager(new_dim);
        maze = mazeManager.getMaze();

        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    /**
     * What happens after use input.
     *
     * @param event touch event
     * @return true or false
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            if (maze.checkGameEnd()) {
                return true;
            }

            float x_ = event.getX(); // user's coordinates
            float y_ = event.getY();

            // calculate x and y of center, by using x + 0.5 and y + 0.5 and scaling accordingly
            float mazeRunnerCenterX =
                    maze.getMargin() + (maze.getMazeRunnerX() + 0.5f) * maze.getBoxSize();
            float mazeRunnerCenterY =
                    maze.getMargin() + (maze.getMazeRunnerY() + 0.5f) * maze.getBoxSize();

            // difference from user to center
            float diff_x = x_ - mazeRunnerCenterX;
            float diff_y = y_ - mazeRunnerCenterY;

            // distance from user to center
            float dist_x = Math.abs(diff_x);
            float dist_y = Math.abs(diff_y);

            // check if player finger is outside of the box
            if (dist_x > maze.getBoxSize() || dist_y > maze.getBoxSize()) {
                // move horizontally
                if (dist_x > dist_y) {
                    if (diff_x > 0) {
                        this.update("RIGHT");
                    } else {
                        this.update("LEFT");
                    }

                }
                // move vertically
                else {
                    if (diff_y > 0) {
                        this.update("DOWN");
                    } else {
                        this.update("UP");
                    }
                }
            }
        }
        return true;
    }

    /**
     * Updates the maze view with player movements.
     */
    public void update(String direction) {
        maze.update(direction);
    }

    /**
     * Draws the screen.
     *
     * @param canvas drawing of the game.
     */
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        maze.draw(canvas);
        String lives = Integer.toString(maze.getLives());
        drawLives(canvas, lives);
        drawNumMazeCompleted(canvas, Integer.toString(mazeCount));
        drawNumArtifacts(canvas, Integer.toString(artifactCount));
        String sc = Integer.toString(getScore());
        drawScore(canvas, sc);

        if (counter <= 20) {
            canvas.drawText("Time Left: " + (20 - counter),
                    330, 1700, paint); // Print time left
        }
        if (checkGetArtifact()) {
            artifactCount += 1;
            Globals.addArtifact();
            maze.removeFoundArt();
        }
        if (timeFinished) {
            if (mazeCount < 4)
                maze.setLives(2); // lose a life when < 4 mazes completed
        } else if (checkGameEnd()) {
            mazeCount += 1;
            int new_dim = (int) Math.floor(screenWidth * 0.8);
            mazeManager = new MazeManager(new_dim);
            maze = mazeManager.getMaze(); // Generate new maze once end is reached
        }
    }

    /**
     * Draws the current score on screen
     *
     * @param canvas - the canvas of which to draw upon.
     * @param text   - the text to draw.
     */
    public void drawScore(Canvas canvas, String text) {
        canvas.drawText("Score: " + text, 330, 2000, paint);
    }

    /**
     * Draws the lives left on the screen
     *
     * @param canvas canvas to draw on
     * @param text   lives left
     */
    public void drawLives(Canvas canvas, String text) {
        canvas.drawText("Lives: " + text, 30, 2150, paint);
    }

    /**
     * Draws number of mazes created on the screen
     *
     * @param canvas canvas to draw on
     * @param text   number of mazes completed
     */
    public void drawNumMazeCompleted(Canvas canvas, String text) {
        canvas.drawText("Mazes: " + text, 730, 2150, paint);
    }

    /**
     * Draws number of artifacts collected on the screen
     *
     * @param canvas canvas to draw on
     * @param text   number of artifacts collected
     */
    public void drawNumArtifacts(Canvas canvas, String text) {
        canvas.drawText("Artifacts: " + text, 330, 1850, paint);
    }

    /**
     * Check if game has ended.
     *
     * @return if game has ended
     */
    public boolean checkGameEnd() {
        return maze.checkGameEnd();
    }

    /**
     * Return the score of the mazeRunner in the maze of this mazeview.
     */
    public int getScore() {
        return maze.getScore();
    }

    /**
     * Sets the score of the maze to score
     *
     * @param score maze's score
     */
    public void setScore(int score) {
        maze.setScore(score);
    }

    /**
     * Set the game to end
     */
    public void setGameEnd() {
        maze.setGameEnd();
    }

    /**
     * Return the lives left.
     *
     * @return lives left
     */
    public int getLives() {
        return maze.getLives();
    }

    /**
     * Returns true if there is no time left on the timer and false otherwise.
     *
     * @return time Finished
     */
    public boolean getTimeFinished() {
        return timeFinished;
    }

    /**
     * Check if artifact has been collected
     *
     * @return if artifact is collected
     */
    public boolean checkGetArtifact() {
        return maze.checkGetArtifact();
    }

    /**
     * Stop timer
     */
    public void cancelTimer() {
        countDownTimer.cancel();
    }

    public int getNumArtifacts() {
        return artifactCount;
    }
}
