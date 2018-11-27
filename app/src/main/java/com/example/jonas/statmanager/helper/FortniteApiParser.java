package com.example.jonas.statmanager.helper;


import com.example.jonas.statmanager.model.FortniteProfile;
import com.example.jonas.statmanager.model.Profile;

import org.json.JSONException;
import org.json.JSONObject;


public class FortniteApiParser {

    public static FortniteProfile parseSingleProfile(JSONObject jsonProfile) throws JSONException {
        // Create a new Profile object and fill the attributes from the API in.
        FortniteProfile profile = new FortniteProfile(jsonProfile.getString("epicUserHandle"));

        if (jsonProfile.getString("epicUserHandle") != null){
            profile.setUsername(jsonProfile.getString("epicUserHandle"));
        }
        else {
            profile.setUsername("N/A");
        }

        profile.setRating(jsonProfile.getJSONArray("lifeTimeStats").getJSONObject(6).getString("value"));

        profile.setKD(jsonProfile.getJSONArray("lifeTimeStats").getJSONObject(11).getString("value"));

        profile.setGamesWon(jsonProfile.getJSONArray("lifeTimeStats").getJSONObject(8).getString("value"));

        profile.setKills(jsonProfile.getJSONArray("lifeTimeStats").getJSONObject(10).getString("value"));

        profile.setWinrate(jsonProfile.getJSONArray("lifeTimeStats").getJSONObject(9).getString("value"));
        /*
        if (jsonProfile.getJSONObject("stats") != null){
            if (jsonProfile.getJSONObject("stats").getJSONObject("p2") != null){
                if (jsonProfile.getJSONObject("stats").getJSONObject("p2").getJSONObject("trnRating") != null){
                    profile.setRating(jsonProfile.getJSONObject("stats").getJSONObject("p2").getJSONObject("trnRating").getString("displayValue"));
                }
                else{
                    profile.setRating("N/A");
                }
                if (jsonProfile.getJSONObject("stats").getJSONObject("p2").getJSONObject("top1") != null){
                    profile.setGamesWon(jsonProfile.getJSONObject("stats").getJSONObject("p2").getJSONObject("top1").getString("displayValue"));
                }
                else{
                    profile.setGamesWon("N/A");
                }
            }
            else{
                profile.setRating("N/A");
                profile.setGamesWon("N/A");
            }
            if (jsonProfile.getJSONObject("stats").getJSONObject("p10") != null){
                if(jsonProfile.getJSONObject("stats").getJSONObject("p10").getJSONObject("kd") != null){
                    profile.setKD(jsonProfile.getJSONObject("stats").getJSONObject("p10").getJSONObject("kd").getString("displayValue"));
                }
                else{
                    profile.setKD("N/A");
                }
            }
            else{
                profile.setKD("N/A");
            }
        }


        else{
            profile.setRating("N/A");
            profile.setGamesWon("N/A");
            profile.setKD("N/A");
        }
        */
        return profile;
    }

    public static FortniteProfile parseSingleProfile(String stringProfile) throws JSONException {
        JSONObject jsonProfile = new JSONObject(stringProfile);

        return parseSingleProfile(jsonProfile);
    }
}
