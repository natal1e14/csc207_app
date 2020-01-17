package com.example.game1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.game.GameListActivity;
import com.example.game.R;
import com.example.game.SwitchActivityInterface;
import com.example.game2.starting.Instructions2Activity;

/**
 * The activity that runs after the player finishes Game 1.
 **/
public class Game1OverActivity extends AppCompatActivity implements SwitchActivityInterface {

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
        setContentView(R.layout.activity_game1_over);

        Intent intent = getIntent();
        String score = intent.getStringExtra("score");
        String numLives = intent.getStringExtra("Lives");
        String numArtifacts = intent.getStringExtra("Arts");

        TextView textView = findViewById(R.id.showScore);
        String message = "Score: " + score + "\n" + "Lives Remaining: " + numLives + "\n"
                + "Artifacts: " + numArtifacts;
        textView.setText(message);

    }

    /**
     * This brings the user back to the main menu. This is still a little buggy, as
     * when MainActivity is called and I click on Game1, sometimes it shows the game over screen
     * instead.
     **/
    @Override
    public void nextActivity(View view) {
        int tag = view.getId();

        Intent intent;

        switch (tag) {

            case R.id.mainMenu:
                intent = new Intent(Game1OverActivity.this, GameListActivity.class);

                startActivity(intent);

                break;
            case R.id.nextGame:
                intent = new Intent(Game1OverActivity.this, Instructions2Activity.class);

                startActivity(intent);

                break;
            default:
                break;
        }
    }
}
