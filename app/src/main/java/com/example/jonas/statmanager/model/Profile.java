package com.example.jonas.statmanager.model;

public class Profile {
    private String icon;
    private String username;
    private Integer level;
    private Integer rating;
    private Integer gamesWon;
    private Integer damageDoneAvg;

    public Profile (String username) {
        this.username = username;
    }

    public void setIcon(String icon) { this.icon = icon; }

    public String getIcon() { return icon;}

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(Integer gamesWon) {
        this.gamesWon = gamesWon;
    }

    public Integer getDamageDoneAvg() { return damageDoneAvg; }

    public void setDamageDoneAvg(Integer damageDoneAvg) { this.damageDoneAvg = damageDoneAvg; }
}
