package com.example.jonas.statmanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class FavouriteActivity extends AppCompatActivity {
    ImageView logoBtn;
    List<String> favoriteList;
    List<String> favourites;
    BufferedReader br;
    EditText txtInput;
    TextView txtContent;
    final static String file = System.getProperty("user.dir") + "favorites.txt" ;
    final static String TAG = "sth";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        logoBtn = (ImageView) findViewById(R.id.logo);

        logoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("My app",file + "123");
                startActivity(new Intent(FavouriteActivity.this, MainActivity.class));
            }
        });

        Log.i("My app","sdf" + file + "LOREMMM IPSUUMM");
        /*try {
            favourites = getFavourites();
        } catch (IOException e) {
            e.printStackTrace();
        }*/


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

    public String ReadFile(){
        String line = null;
        try {
            FileInputStream fileInputStream = new FileInputStream (new File(file));
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String st = new String();

            while ( (line = bufferedReader.readLine()) != null )
            {
                stringBuilder.append(line + System.getProperty("line.separator"));
                System.out.println(line);
                favoriteList.add(line);
            }
            fileInputStream.close();
            line = stringBuilder.toString();

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            Log.d(TAG, ex.getMessage());
        }
        catch(IOException ex) {
            Log.d(TAG, ex.getMessage());
        }
        return line;
    }

    public static boolean saveToFile( String userName, String game){
        String text = userName + ";" + game;
        try {
            File txt = new File(file);
            if (!txt.exists()) {
                txt.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(txt,true);
            fileOutputStream.write((text + System.getProperty("line.separator")).getBytes());

            return true;
        }  catch(FileNotFoundException ex) {
            Log.d(TAG, ex.getMessage());
        }  catch(IOException ex) {
            Log.d(TAG, ex.getMessage());
        }
        return  false;


    }
}
