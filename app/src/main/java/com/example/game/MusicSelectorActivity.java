package com.example.game;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MusicSelectorActivity extends AppCompatActivity implements SwitchActivityInterface {

    private static MediaPlayer player;

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
        setContentView(R.layout.activity_music_selector);
    }


    @Override
    public void nextActivity(View view) {
        int tag = view.getId();
        Intent intent;

        switch (tag) {
            case R.id.elevator:
                checkPlaying();
                player = MediaPlayer.create(this, R.raw.betterdays);
                break;
            case R.id.javaha:
                checkPlaying();
                player = MediaPlayer.create(this, R.raw.spacebattle);
                break;
            case R.id.trumpets:
                checkPlaying();
                player = MediaPlayer.create(this, R.raw.trumpets);
                break;
            case R.id.toSettings:
                intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
            default:
                break;
        }

        if (player != null) {
            player.start();
            player.setLooping(true);
        }
    }

    private void checkPlaying(){
        if (player != null){
            if (player.isPlaying()){
                player.stop();
            }
            player.reset();
            player.release();
        }
        player = null;
    }
}
