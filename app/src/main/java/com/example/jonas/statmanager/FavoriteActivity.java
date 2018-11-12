package com.example.jonas.statmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.jonas.statmanager.model.Favorite;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FavoriteActivity extends AppCompatActivity {
    private ImageView favorite_user = (ImageView) findViewById(R.id.favorite);
    private ProgressBar progressBar;
    private ImageView logoBtn = (ImageView)findViewById(R.id.logo);
    private ListView allFavs;
    private List<String> allFavorites = new ArrayList<>();
    private BufferedWriter bw = null;
    private BufferedReader br;
    private List<String> listFavorites = new ArrayList<>();
    private static final String file = System.getProperty("user.dir") + "favorites.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);

        logoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FavoriteActivity.this, MainActivity.class));
            }
        });
        System.out.println("123");
        try {
            allFavorites  = getFavorites();
        } catch (IOException e) {
            System.out.println("WROONG dude");
            e.printStackTrace();
        }


        System.out.println("456");

        loadAllFavorites(allFavorites);
        System.out.println("789");
        allFavs = (ListView) findViewById(R.id.list_all_favorites);
        System.out.println("012");
        allFavs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent appInfo = new Intent(FavoriteActivity.this, DetailActivity.class);
                startActivity(appInfo);
            }
        });
    }

    private List<String> getFavorites() throws IOException {
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String st = new String();
        while ((st = br.readLine()) != null) {
            System.out.println(st);
            listFavorites.add(st);
        }
        return listFavorites;
    }

    private void loadAllFavorites(List<String> allFavorites)
    {
        final ArrayAdapter<String> favoritesArrayAdapter = new
                ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);

            favoritesArrayAdapter.addAll(allFavorites);

            ListView favoritesList = (ListView) findViewById(R.id.list_all_favorites);
            favoritesList.setAdapter(favoritesArrayAdapter);
            progressBar.setVisibility(View.GONE);
            AdapterView.OnItemClickListener itemListClickedHandler = new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                    Favorite selected = (Favorite)parent.getItemAtPosition(position);

                    // Send the id of the selected item with the intent
                    intent.putExtra("name", selected.getUsername());
                    startActivity(intent);
                }
            };
            // Set on item click listener to the list view
            favoritesList.setOnItemClickListener(itemListClickedHandler);
    }

}
