package com.example.jonas.statmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class FavouriteActivity extends AppCompatActivity {
    private ImageView logoBtn;
    private List<String> favoriteList;
    private List<String> favourites;
    private static String file;
    private BufferedReader br;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        logoBtn = (ImageView) findViewById(R.id.logo);
        file = System.getProperty("user.dir") + "favorites.txt";

        logoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FavouriteActivity.this, MainActivity.class));
            }
        });

        try {
            favourites = getFavourites();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private List<String> getFavourites() throws IOException {
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String st = new String();
        while ((st = br.readLine()) != null) {
            System.out.println(st);
            favoriteList.add(st);
        }
        return favoriteList;

    }
}
