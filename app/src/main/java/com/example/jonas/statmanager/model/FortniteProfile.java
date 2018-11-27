package com.example.jonas.statmanager.model;

import java.util.ArrayList;
import java.util.List;

public class FortniteProfile {
    private String icon;
    private String username;
    private String KD;
    private String rating;
    private String gamesWon;
    private String Kills;
    private String Winrate;
    private List<Profile> favorites = new ArrayList<Profile>();

    public FortniteProfile (String username) {
        this.username = username;
    }

    public void setIcon(String icon) { this.icon = icon; }

    public String getIcon() { return icon;}

    public void setWinrate(String winrate) { this.Winrate = winrate; }

    public String getWinrate() { return Winrate;}

    public void setKills(String kills) { this.Kills = kills; }

    public String getKills() { return Kills;}

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getKD() {
        return KD;
    }

    public void setKD(String KD) {
        this.KD = KD;
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
