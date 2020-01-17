package com.example.game3.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.game.GameListActivity;
import com.example.game.R;
import com.example.game.ShowScoreActivity;
import com.example.game.SwitchActivityInterface;

import java.util.Objects;

public class Game3PassActivity extends AppCompatActivity implements SwitchActivityInterface {

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
        setContentView(R.layout.activity_game3_pass);

        String score = Objects.requireNonNull(
                Objects.requireNonNull(getIntent().getExtras()).get("score")).toString();

        TextView displayScore = findViewById(R.id.displayScore);

        displayScore.setText(String.format("Score = %s", score));
    }

    /**
     * Returns to the main menu or starts Game Over.
     *
     * @param view - the view of the display area.
     **/
    @Override
    public void nextActivity(View view) {
        int tag = view.getId();

        Intent intent;

        switch (tag) {
            case R.id.mainMenu:
                intent = new Intent(Game3PassActivity.this, GameListActivity.class);

                startActivity(intent);

                break;
            case R.id.nextGame:
                intent = new Intent(Game3PassActivity.this, ShowScoreActivity.class);

                startActivity(intent);

                break;
            default:
                break;
        }
    }
}
