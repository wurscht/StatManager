package com.example.jonas.statmanager.model;

public class Profile {
    private String username;
    private Integer endorsement;
    private Integer level;
    private Integer gamesWon;
    private Integer prestige;
    private Integer rating;
    private Boolean priv;

    public Profile (String username) {
        this.username = username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public Integer getEndorsement() {
        return endorsement;
    }

    public void setEndorsement(Integer endorsement) {
        this.endorsement = endorsement;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(Integer gamesWon) {
        this.gamesWon = gamesWon;
    }

    public Integer getPrestige() {
        return prestige;
    }

    public void setPrestige(Integer prestige) {
        this.prestige = prestige;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Boolean getPriv() {
        return priv;
    }

    public void setPriv(Boolean priv) {
        this.priv = priv;
    }
}
