package com.example.jonas.statmanager.helper;


import com.example.jonas.statmanager.model.Profile;

import org.json.JSONException;
import org.json.JSONObject;


public class OverwatchApiParser {

    private static Profile parseSingleProfile(JSONObject jsonProfile) throws JSONException {
        // Create a new Profile object and fill the attributes from the API in.
        Profile profile = new Profile(Integer.parseInt(jsonProfile.getString("id")), jsonProfile.getString("username"));

        return profile;
    }
}
