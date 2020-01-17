package com.example.game2.starting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.R;
import com.example.game.SwitchActivityInterface;

/**
 * The instructions for Game 2.
 **/
public class Game2PauseActivity extends AppCompatActivity implements SwitchActivityInterface {

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
        setContentView(R.layout.activity_pause2);
    }

    /**
     * Starts game 2.
     *
     * @param view - the view of the screen.
     **/
    @Override
    public void nextActivity(View view) {
        Intent intent = new Intent(this, Game2Activity.class);
        intent.putExtra("score", getIntent().getIntExtra("score", 0));
        intent.putExtra("lives", getIntent().getIntExtra("lives", 3));
        intent.putExtra("time", getIntent().getIntExtra("time", 30));
        intent.putExtra("ship", getIntent().getIntExtra("ship", 1));
        intent.putExtra("speed", getIntent().getIntExtra("speed", -1));
        intent.putExtra("artifacts", getIntent().getIntExtra("artifacts", 0));
        intent.putExtra("level", getIntent().getIntExtra("level", 1));
        startActivity(intent);
        finish();
    }
}
