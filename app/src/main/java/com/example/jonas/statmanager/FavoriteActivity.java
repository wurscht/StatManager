package com.example.jonas.statmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.jonas.statmanager.helper.Favorites;


public class FavoriteActivity extends AppCompatActivity {
    private ImageView favorite_user = (ImageView) findViewById(R.id.favorite);
    private ImageView logoBtn = (ImageView)findViewById(R.id.logo);
    private Favorites favorites = new Favorites();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        logoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FavoriteActivity.this, MainActivity.class));
            }
        });
    }
}
