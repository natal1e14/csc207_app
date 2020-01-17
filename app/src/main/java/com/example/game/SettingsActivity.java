package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SettingsActivity extends AppCompatActivity implements SwitchActivityInterface{

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
        setContentView(R.layout.activity_settings);
    }

    @Override
    public void nextActivity(View view) {
        int tag = view.getId();

        Intent intent;

        switch (tag) {
            case R.id.changeMusic:
                intent = new Intent(this, MusicSelectorActivity.class);
                startActivity(intent);
                break;
            case R.id.changeTheme:
                intent = new Intent(this, ChooseThemeActivity.class);
                startActivity(intent);
                break;
            case R.id.mainMenu:
                intent = new Intent(this, GameListActivity.class);
                startActivity(intent);
            default:
                break;
        }
    }
}
