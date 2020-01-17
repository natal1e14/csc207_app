package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity implements SwitchActivityInterface{

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
        setContentView(R.layout.activity_register);
    }

    /**Creates a new player in the game. In future versions of this method, it must create a
     * Player object and store it with the correct information.
     *
     * @param view - the view of the screen.
     * **/
    @Override
    public void nextActivity(View view){

        TextView n = findViewById(R.id.setUsername);
        TextView p = findViewById(R.id.setPassword);

        if (PlayerManager.checkExists(n.getText().toString())) {
            TextView errorMessage = findViewById(R.id.errorMessage);
            errorMessage.setText("This user already exists. Please log-in or choose a different username");
            errorMessage.setTextColor(Color.RED);

        } else if (n.getText().toString().length() == 0 || p.getText().toString().length() == 0) {
            TextView errorMessage = findViewById(R.id.errorMessage);
            errorMessage.setText("Usernames and passwords must contain at least one character");
            errorMessage.setTextColor(Color.RED);
        } else {
            PlayerManager.addPlayer(n.getText().toString(), p.getText().toString());

            Intent intent = new Intent(this, GameListActivity.class);
            startActivity(intent);
        }
    }
}
