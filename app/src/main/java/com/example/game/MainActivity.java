package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.File;

/**
 * The starting screen of the game.
 * **/
public class MainActivity extends AppCompatActivity implements SwitchActivityInterface{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        File rootDir = getFilesDir();
        PlayerManager pm = new PlayerManager(this);
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Starts the sign up process or the sign in process.
     *
     * @param view - the view of the screen.
     * **/
    @Override
    public void nextActivity(View view) {
        int tag = view.getId();

        Intent intent;

        switch (tag) {
            case R.id.signInButton:
                intent = new Intent(this, LoginActivity.class);

                startActivity(intent);

                break;
            case R.id.signUpButton:
                intent = new Intent(this, RegisterActivity.class);

                startActivity(intent);

                break;
            default:
                break;
        }
    }
}
