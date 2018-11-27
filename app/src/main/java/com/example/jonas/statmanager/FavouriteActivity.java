package com.example.jonas.statmanager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.BufferedReader;
import java.util.ArrayList;

public class FavouriteActivity extends AppCompatActivity {
    ImageView logoBtn;
    ArrayList<String> favoriteList = new ArrayList<String>();
    ArrayList<String> favoriteUsers = new ArrayList<String>();
    BufferedReader reader;
    private final static String TAG = "sth";
    private ListView favoritesList = (ListView) findViewById(R.id.list_all_favorites);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        logoBtn = (ImageView) findViewById(R.id.logo);

        logoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("My app", "123");
                startActivity(new Intent(FavouriteActivity.this, MainActivity.class));
            }
        });


        //fillList();
        // Make the elements in the listview "show all" clickable and redirect to detail site
        favoritesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent appInfo = new Intent(FavouriteActivity.this, DetailActivity.class);
                startActivity(appInfo);
            }
        });
    }


    private void fillList(){
        ArrayList<String> userNames = getFavourites();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                userNames);
        favoritesList.setAdapter(arrayAdapter);

        arrayAdapter.addAll(favoriteUsers);
        favoritesList.setAdapter(arrayAdapter);

    }

    private ArrayList<String> getFavourites() {

        for ( String str : favoriteList) {
            String[] values = str.split(";");
            String username = values[0];
            favoriteUsers.add(username);
        }

        return favoriteUsers;
    }


    public void saveFavorites(String userName, String game){
        String text = userName + ";" + game;

        favoriteList.add(text);
   }

}
