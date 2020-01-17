package com.example.game2.starting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.example.game.R;
import com.example.game.SwitchActivityInterface;

/**
 * Activity for selecting the kind of spaceship you want
 */
public class ChooseShipActivity extends AppCompatActivity implements SwitchActivityInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Make window full screen
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_spaceship);
    }

    @Override
    public void nextActivity(View view) {

        //Get the selected button
        RadioGroup selectedShip = findViewById(R.id.selectedShip);

        int shipId = selectedShip.getCheckedRadioButtonId();

        //determine which ship was selected
        int ship = 0;
        if (shipId == R.id.ship2click) {
            ship = 2;
        } else {
            ship = 1;
        }

        //go to start game activity
        Intent intent = new Intent(this, Game2Activity.class);
        intent.putExtra("ship", ship);
        startActivity(intent);
    }

}
