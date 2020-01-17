package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChooseThemeActivity extends AppCompatActivity implements SwitchActivityInterface {

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
        setContentView(R.layout.activity_choose_theme);
    }

    @Override
    public void nextActivity(View view){
        int tag = view.getId();

        Intent intent;
        Globals global = (Globals)getApplication();

        switch (tag) {
            case R.id.planetTheme:
                global.setThemeType(0);
                break;
            case R.id.starryTheme:
                global.setThemeType(1);
                break;
            case R.id.toSettings:
                intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
            default:
                break;
        }
    }

}
