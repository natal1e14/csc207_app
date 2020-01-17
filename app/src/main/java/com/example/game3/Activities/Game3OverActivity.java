package com.example.game3.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.game.R;
import com.example.game.ShowScoreActivity;
import com.example.game.SwitchActivityInterface;

import java.util.Objects;

/**
 * The activity that shows up after the game is done.
 **/
public class Game3OverActivity extends AppCompatActivity implements SwitchActivityInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        String score = Objects.requireNonNull(
                Objects.requireNonNull(getIntent().getExtras()).get("threeScore")).toString();

        TextView displayScore = findViewById(R.id.displayScore);

        TextView gameOver = findViewById(R.id.gameOverText);
        displayScore.setText(String.format("Score = %s", score));
        gameOver.setText("Game Over");

    }

    /**
     * Returns to the main menu (game selection screen)
     *
     * @param view - the view of the display area.
     **/
    @Override
    public void nextActivity(View view){
        Intent intent = new Intent(Game3OverActivity.this, ShowScoreActivity.class);

        startActivity(intent);

    }



}
