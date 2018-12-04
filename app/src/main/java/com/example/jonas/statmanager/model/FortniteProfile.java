package com.example.jonas.statmanager.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for the fortnite profile data of the user. This class defines the stats that are shown.
 *
 * @author Jonas Lehmann, Mirjam Doyon, Hava Fuga, Enis Badoglu
 */
public class FortniteProfile {
    private String icon;
    private String username;
    private String KD;
    private String rating;
    private String gamesWon;
    private String Kills;
    private String Winrate;
    private List<Profile> favorites = new ArrayList<Profile>();

    /**
     * The constructor of the class ForteniteProfile
     *
     * @param username  the name of the user
     */
    public FortniteProfile (String username) {
        this.username = username;
    }

    /**
     * {@link FortniteProfile#getIcon()}
     *
     */
    public void setIcon(String icon) { this.icon = icon; }

    /**
     * Get the icon.
     *
     * @return the icon of the player
     */
    public String getIcon() { return icon;}

    /**
     * {@link FortniteProfile#getWinrate()}
     *
     */
    public void setWinrate(String winrate) { this.Winrate = winrate; }

    /**
     * Get the winrate.
     *
     * @return the winrate of the player
     */
    public String getWinrate() { return Winrate;}

    /**
     * {@link FortniteProfile#getKills()}
     *
     */
    public void setKills(String kills) { this.Kills = kills; }

    /**
     * Get the kills.
     *
     * @return the kills of the player
     */
    public String getKills() { return Kills;}

    /**
     * {@link FortniteProfile#getUsername()}
     *
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the username.
     *
     * @return the username of the player
     */
    public String getUsername() {
        return username;
    }
    /**
     * Get the KD.
     *
     * @return the kill/death ratio of the player
     */
    public String getKD() {
        return KD;
    }

    /**
     * {@link FortniteProfile#getKD()}
     *
     */
    public void setKD(String KD) {
        this.KD = KD;
    }

    /**
     * Get the rating
     *
     * @return the rating of the player
     */
    public String getRating() {
        return rating;
    }

    /**
     * {@link FortniteProfile#getRating()}
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
     * {@link FortniteProfile#getGamesWon()}
     *
     */
    public void setGamesWon(String gamesWon) {
        this.gamesWon = gamesWon;
    }

    /**
     * Get the favorites.
     *
     * @return the list with the favorite profiles of the player
     */
    public List<Profile> getFavorites() { return favorites; }

    /**
     * {@link FortniteProfile#getFavorites()}
     *
     */
    public void setFavorites(List<Profile> favorites) { this.favorites = favorites; }
}
