package com.example.game3.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.game.R;
import com.example.game.SwitchActivityInterface;

public class Game3PauseActivity extends AppCompatActivity implements SwitchActivityInterface {

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
        setContentView(R.layout.activity_game3_pause);
    }

    /**
     * Starts game 3.
     *
     * @param view - the view of the screen.
     **/
    @Override
    public void nextActivity(View view) {
        Intent intent = new Intent(this, Game3Activity.class);
        intent.putExtra("threeScore", getIntent().getIntExtra("threeScore", 0));
        intent.putExtra("threeTime", getIntent().getIntExtra("threeTime", 20));

        startActivity(intent);
    }
}
