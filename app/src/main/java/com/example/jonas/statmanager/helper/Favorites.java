package com.example.jonas.statmanager.helper;

import com.example.jonas.statmanager.model.Profile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Favorites {

    private PrintWriter writer;
    private ArrayList<String> favorites = new ArrayList<String>();
    private File file = new File("favorites.txt");
    private BufferedReader br;

    public Favorites(){
        try {
            writer = new PrintWriter(file, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void saveFav(Profile user, ApiGetter.Game game) throws FileNotFoundException, UnsupportedEncodingException {
        String favorites = game + "; " + user.getUsername() + "\n";
        writer.println(favorites);
        System.out.println(favorites);
        writer.close();
    }

    public ArrayList<String> getFavorites() throws IOException {
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String st = new String();
        while ((st = br.readLine()) != null) {
            favorites.add(st);
        }

        return favorites;
    }
}
