package com.example.game3.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.game.Globals;
import com.example.game.R;
import com.example.game.SwitchActivityInterface;

/**
 * Displays the instructions of Game 3.
 * **/
public class InstructionActivity extends AppCompatActivity implements SwitchActivityInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        setContentView(R.layout.activity_instruction);
        Globals global = (Globals)getApplication();

        if (global.getThemeType() == 0){
            setContentView(R.layout.activity_instruction);
        }
        else {
            setContentView(R.layout.activity_instruction_starry);
        }

        TextView instructions = findViewById(R.id.editText);
        instructions.setText("Tap as many galaxies as you can!");
    }

    /**
     * Starts Game 3.
     * **/
    @Override
    public void nextActivity(View view){
        Intent intent = new Intent(InstructionActivity.this, Game3Activity.class);

        startActivity(intent);
    }

}
