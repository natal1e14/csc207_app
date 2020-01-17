package com.example.game3.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.game.R;
import com.example.game.SwitchActivityInterface;

import java.util.Objects;

public class Intro2Activity extends AppCompatActivity implements SwitchActivityInterface {

    /**
     * Game 3's score.
     */
    private int score;

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
        setContentView(R.layout.activity_intro2);

        String score = Objects.requireNonNull(
                Objects.requireNonNull(getIntent().getExtras()).get("threeScore")).toString();

        this.score = getIntent().getIntExtra("threeScore", 0);

        TextView displayScore = findViewById(R.id.displayScore);

        TextView gameOver = findViewById(R.id.levelOneOver);
        displayScore.setText(String.format("Score = %s", score));
        gameOver.setText("Level Complete!");
    }

    @Override
    protected void onPause() {
        super.onPause();

        finish();
    }

    @Override
    public void nextActivity(View view) {

        Intent intent = new Intent(Intro2Activity.this, Level2Activity.class);
        intent.putExtra("threeScore", this.score);

        startActivity(intent);

    }
}
