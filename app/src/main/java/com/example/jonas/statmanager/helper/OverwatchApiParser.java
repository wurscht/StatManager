package com.example.jonas.statmanager.helper;


import com.example.jonas.statmanager.model.Profile;

import org.json.JSONException;
import org.json.JSONObject;


public class OverwatchApiParser {

    public static Profile parseSingleProfile(JSONObject jsonProfile) throws JSONException {
        // Create a new Profile object and fill the attributes from the API in.
        Profile profile = new Profile(jsonProfile.getString("name"));

        profile.setIcon(jsonProfile.getString("icon"));
        profile.setUsername(jsonProfile.getString("name"));
        profile.setLevel(Integer.toString(jsonProfile.getInt("level")));
        profile.setRating(Integer.toString(jsonProfile.getInt("rating")));
        profile.setGamesWon(Integer.toString(jsonProfile.getInt("gamesWon")));
        try{
            profile.setGamesPlayed(Integer.toString(jsonProfile.getJSONObject("competitiveStats").getJSONObject("games").getInt("played")));
        }catch (Exception e){
            profile.setGamesPlayed("N/A");
        }
        try{
            profile.setGamesWon(Integer.toString(jsonProfile.getJSONObject("competitiveStats").getJSONObject("games").getInt("won")));
        }catch (Exception e) {
            profile.setGamesWon("N/A");
        }

        return profile;
    }

    public static Profile parseSingleProfile(String stringProfile) throws JSONException {
        JSONObject jsonProfile = new JSONObject(stringProfile);

        return parseSingleProfile(jsonProfile);
    }
}
