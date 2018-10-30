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
        profile.setLevel(jsonProfile.getInt("level"));
        profile.setRating(jsonProfile.getInt("rating"));
        profile.setGamesWon(jsonProfile.getInt("gamesWon"));
        profile.setDamageDoneAvg(jsonProfile.getJSONObject("competitiveStats").getInt("damageDoneAvg"));

        return profile;
    }

    public static Profile parseSingleProfile(String stringProfile) throws JSONException {
        JSONObject jsonProfile = new JSONObject(stringProfile);

        return parseSingleProfile(jsonProfile);
    }
}
