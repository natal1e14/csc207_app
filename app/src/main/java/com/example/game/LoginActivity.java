package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Activity that allows the player to log in.
 * **/
public class LoginActivity extends AppCompatActivity implements SwitchActivityInterface {

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
        setContentView(R.layout.activity_login);

    }

    /**Logs the user in. In future versions, this method must also check if the user has
     * entered the correct username / password combination.
     *
     * @param view - the view of the screen.
     * **/
    @Override
    public void nextActivity(View view) {
        TextView user = findViewById(R.id.username);
        TextView pass = findViewById(R.id.password);

        if (PlayerManager.authenticate(user.getText().toString(), pass.getText().toString())) {

            Intent intent = new Intent(this, GameListActivity.class);
            intent.putExtra("username", user.getText().toString());
            startActivity(intent);
        }
        else {

            TextView errorMessage = findViewById(R.id.errorMessage);
            errorMessage.setText("Error incorrect name or password");
            errorMessage.setTextColor(Color.RED);


        }
    }
}
