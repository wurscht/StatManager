package com.example.jonas.statmanager.helper;


import com.example.jonas.statmanager.model.FortniteProfile;
import com.example.jonas.statmanager.model.Profile;

import org.json.JSONException;
import org.json.JSONObject;


public class FortniteApiParser {

    public static FortniteProfile parseSingleProfile(JSONObject jsonProfile) throws JSONException {
        // Create a new Profile object and fill the attributes from the API in.
        FortniteProfile profile = new FortniteProfile(jsonProfile.getString("epicUserHandle"));

        if (jsonProfile.getString("epicUserHandle") != ""){
            profile.setUsername(jsonProfile.getString("epicUserHandle"));
        }
        else {
            profile.setUsername("N/A");
        }
        if (jsonProfile.getJSONObject("stats").getJSONObject("p2").getJSONObject("trnRating").getString("displayValue") != ""){
            profile.setRating(jsonProfile.getJSONObject("stats").getJSONObject("p2").getJSONObject("trnRating").getString("displayValue"));
        }
        else{
            profile.setRating("N/A");
        }
        if (jsonProfile.getJSONObject("stats").getJSONObject("p2").getJSONObject("top1").getString("displayValue") != ""){
            profile.setGamesWon(jsonProfile.getJSONObject("stats").getJSONObject("p2").getJSONObject("top1").getString("displayValue"));
        }
        else{
            profile.setGamesWon("N/A");
        }
        if (jsonProfile.getJSONObject("stats").getJSONObject("p10").getJSONObject("kd").getString("displayValue") != ""){
            profile.setKD(jsonProfile.getJSONObject("stats").getJSONObject("p10").getJSONObject("kd").getString("displayValue"));
        }
        else{
            profile.setGamesWon("N/A");
        }
        return profile;
    }

    public static FortniteProfile parseSingleProfile(String stringProfile) throws JSONException {
        JSONObject jsonProfile = new JSONObject(stringProfile);

        return parseSingleProfile(jsonProfile);
    }
}
