package com.example.jonas.statmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;

public class SearchFortniteActivity extends AppCompatActivity {
    SearchView searchField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_fortnite);
        ImageView logoBtn = (ImageView)findViewById(R.id.logo);
        searchField = (SearchView) findViewById(R.id.search_field);
        Button searchBtn = (Button) findViewById(R.id.search_button);
        Button favoriteBtn = (Button)findViewById(R.id.favorite_button);

        logoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchFortniteActivity.this, MainActivity.class));
            }
        });


        searchBtn.setText("search");

        // add a default query for the sake of not having to enter it every time.
        searchField.setQuery("ninja", false);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DetailFortniteActivity.class);
                String input = searchField.getQuery().toString();
                intent.putExtra("username", input);
                startActivity(intent);
            }
        });

        favoriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchFortniteActivity.this, FavouriteActivity.class));
            }
        });
    }
}