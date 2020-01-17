package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game2.starting.Instructions2Activity;
import com.example.game1.activities.Instructions1Activity;
import com.example.game3.Activities.InstructionActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * The activity that allows the player to choose which game to play.
 * **/
public class GameListActivity extends AppCompatActivity implements SwitchActivityInterface {

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
        setContentView(R.layout.activity_game_list);
    }

    /** Starts the games depending on which button is clicked.
     *
     * @param view - the view of the game.
     * **/
    @Override
    public void nextActivity(View view) {
        int tag = view.getId();

        Intent intent;

        switch (tag) {
            case R.id.GameOneButton:
                intent = new Intent(GameListActivity.this, Instructions1Activity.class);

                startActivity(intent);
                break;
            case R.id.GameTwoButton:
                intent = new Intent(GameListActivity.this, Instructions2Activity.class);

                startActivity(intent);

                break;
            case R.id.GameThreeButton:
                intent = new Intent(GameListActivity.this, InstructionActivity.class);

                startActivity(intent);

                break;
            case R.id.checkScores:
                intent = new Intent(GameListActivity.this, ShowScoreActivity.class);

                startActivity(intent);

                break;

            case R.id.settings:
                intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
                

            default:
                break;
        }
    }
}
