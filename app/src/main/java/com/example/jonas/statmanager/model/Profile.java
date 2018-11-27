package com.example.jonas.statmanager.model;

import java.util.ArrayList;
import java.util.List;

public class Profile {
    private String icon;
    private String username;
    private String level;
    private String rating;
    private String gamesWon;
    private String gamesPlayed;
    private List<Profile> favorites = new ArrayList<Profile>();

    public Profile (String username) {
        this.username = username;
    }

    public void setIcon(String icon) { this.icon = icon; }

    public String getIcon() { return icon;}

    public void setGamesPlayed(String gamesPlayed) { this.gamesPlayed = gamesPlayed; }

    public String getGamesPlayed() { return gamesPlayed;}

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(String gamesWon) {
        this.gamesWon = gamesWon;
    }

    public List<Profile> getFavorites() { return favorites; }

    public void setFavorites(List<Profile> favorites) { this.favorites = favorites; }
}
