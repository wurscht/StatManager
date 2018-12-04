package com.example.jonas.statmanager.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for the profile data of the user. This class defines the stats that are shown.
 *
 * @author Jonas Lehmann, Mirjam Doyon, Hava Fuga, Enis Badoglu
 */
public class Profile {
    private String icon;
    private String username;
    private String level;
    private String rating;
    private String gamesWon;
    private String gamesPlayed;
    private List<Profile> favorites = new ArrayList<Profile>();

    /**
     * The constructor of the class Profile
     *
     * @param username  the name of the user
     */
    public Profile (String username) {
        this.username = username;
    }

    /**
     * {@link Profile#getIcon}
     *
     */
    public void setIcon(String icon) { this.icon = icon; }

    /**
     * Get the icon.
     *
     * @return the icon of the user
     */
    public String getIcon() { return icon;}

    /**
     * {@link Profile#getGamesPlayed()}
     *
     */
    public void setGamesPlayed(String gamesPlayed) { this.gamesPlayed = gamesPlayed; }

    /**
     * Get the games played.
     *
     * @return how much games the player did play
     */
    public String getGamesPlayed() { return gamesPlayed;}

    /**
     * {@link Profile#getUsername()}
     *
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the username.
     *
     * @return the name of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get the level.
     *
     * @return the level of the player
     */
    public String getLevel() {
        return level;
    }

    /**
     * {@link Profile#getLevel()}
     *
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * Get the rating.
     *
     * @return the rating of the player
     */
    public String getRating() {
        return rating;
    }

    /**
     * {@link Profile#getRating()}
     *
     */
    public void setRating(String rating) {
        this.rating = rating;
    }

    /**
     * Get the games won.
     *
     * @return the games the player won
     */
    public String getGamesWon() {
        return gamesWon;
    }

    /**
     * {@link Profile#getGamesWon()}
     *
     */
    public void setGamesWon(String gamesWon) {
        this.gamesWon = gamesWon;
    }

    /**
     * Get the favorites.
     *
     * @return a list with the favorite profiles
     */
    public List<Profile> getFavorites() { return favorites; }

    /**
     * {@link Profile#getFavorites()}
     *
     */
    public void setFavorites(List<Profile> favorites) { this.favorites = favorites; }
}
