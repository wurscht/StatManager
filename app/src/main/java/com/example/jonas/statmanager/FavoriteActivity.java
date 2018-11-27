package com.example.jonas.statmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FavoriteActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private ImageView logoBtn;
    private ListView allFavs;
    private List<String> allFavorites = new ArrayList<>();
    private BufferedWriter bw = null;
    private BufferedReader br;
    private FileWriter fw = null;
    private List<String> listFavorites;
    private static File file = new File("C:\\Users\\Fuga\\Desktop\\test.txt");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        logoBtn = (ImageView)findViewById(R.id.logo);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);

        //file = System.getProperty("user.dir") + "favorites.txt";

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

        //loadAllFavorites(allFavorites);
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

    public void saveFav(String userName, String game){
        String favoritesContent = game + ";" + userName + "\n";
        System.out.println(favoritesContent);
        System.out.println(file);

        try {
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            bw.write(favoritesContent);
            System.out.println("Done writing");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }

    //Load the favorites in list
    /*private void loadAllFavorites(List<String> allFavorites)
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
    }*/

}
