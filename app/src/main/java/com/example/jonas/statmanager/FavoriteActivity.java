package com.example.jonas.statmanager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.jonas.statmanager.helper.FileManager;

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

    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    ListView listItems;

    String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/StatManager";
    String filePath = path + "/fav.txt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);

        File dir = new File(path);
        File loadFile = new File(filePath);
        dir.mkdirs();

        String[] data = FileManager.Load(loadFile);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, data);

        listItems = (ListView) findViewById(R.id.list_all_favorites);
        listItems.setAdapter(adapter);

        logoBtn = (ImageView) findViewById(R.id.logo);
        logoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FavoriteActivity.this, MainActivity.class));
            }
        });

        //onclicklistener für jedes Item in den Favoriten noch zu erstellen

        progressBar.setVisibility(View.INVISIBLE);
    }

}
