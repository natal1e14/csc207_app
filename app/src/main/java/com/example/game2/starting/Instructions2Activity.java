package com.example.game2.starting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.Globals;
import com.example.game.R;
import com.example.game.SwitchActivityInterface;

/**
 * The instructions for Game 2.
 **/
public class Instructions2Activity extends AppCompatActivity implements SwitchActivityInterface {

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
        setContentView(R.layout.activity_instructions2);

        Globals global = (Globals) getApplication();

        if (global.getThemeType() == 0) {
            setContentView(R.layout.activity_instructions2);
        } else {
            setContentView(R.layout.activity_instructions2_starry);
        }
    }

    /**
     * Starts game 2.
     *
     * @param view - the view of the screen.
     **/
    @Override
    public void nextActivity(View view) {
        Intent intent = new Intent(this, ChooseShipActivity.class);
        this.onStop();
        startActivity(intent);
    }
}
