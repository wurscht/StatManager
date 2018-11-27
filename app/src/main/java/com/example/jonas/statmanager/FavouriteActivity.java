package com.example.jonas.statmanager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Native;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.ArrayList;
import java.util.List;

public class FavouriteActivity extends AppCompatActivity {
    ImageView logoBtn;
    ArrayList<String> favoriteList = new ArrayList<String>();
    ArrayList<String> favoriteUsers = new ArrayList<String>();
    BufferedReader reader;
    final static String TAG = "sth";

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

        Log.i("My app","sdf" + "LOREMMM IPSUUMM");
    }

    private List<String> getFavourites() {

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
