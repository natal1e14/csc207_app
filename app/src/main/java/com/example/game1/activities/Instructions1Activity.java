package com.example.game1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.Globals;
import com.example.game.R;
import com.example.game.SwitchActivityInterface;
import com.example.game1.activities.Game1Activity;

public class Instructions1Activity extends AppCompatActivity implements SwitchActivityInterface {
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
        setContentView(R.layout.activity_instructions1);

        Globals global = (Globals)getApplication();

        if (global.getThemeType() == 0){
            setContentView(R.layout.activity_instructions1);
        }
        else {
            setContentView(R.layout.activity_instructions1_starry);
        }
    }

    /**
     * Starts game 1.
     *
     * @param view - the view of the screen.
     **/
    @Override
    public void nextActivity(View view) {
        Intent intent = new Intent(this, Game1Activity.class);

        startActivity(intent);
    }
}
