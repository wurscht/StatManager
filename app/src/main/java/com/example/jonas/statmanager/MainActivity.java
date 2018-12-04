package com.example.jonas.statmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Class to show the content for the main activity. The available games will be listed here with images.
 *
 * @author Jonas Lehmann, Mirjam Doyon, Hava Fuga, Enis Badoglu
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Method to create the activity and initialize all the needed designs for the gui and the logic
     * so the user can interact with this activity.
     *
     * @param savedInstanceState the savedInstanceState is a reference to a bundle object that is passed
     *                           into the onCreate method of every android activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView logoBtn = (ImageView)findViewById(R.id.logo);
        logoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });

        ImageButton fnBtn = (ImageButton)findViewById(R.id.fortnite_button);
        fnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SearchFortniteActivity.class));
            }
        });

        ImageButton owBtn = (ImageButton)findViewById(R.id.ow_button);
        owBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
            }
        });
    }
}
