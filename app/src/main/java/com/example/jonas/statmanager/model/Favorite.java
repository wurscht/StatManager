package com.example.jonas.statmanager.model;

import com.example.jonas.statmanager.model.Profile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Favorite extends Profile{

    private static final String file = System.getProperty("user.dir") + "favorites.txt";
    BufferedWriter bw = null;
    FileWriter fw = null;
    BufferedReader br;
    {
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Favorite(String username) {
        super(username);
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
}
