package com.example.jonas.statmanager.helper;

import com.example.jonas.statmanager.model.FortniteProfile;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class to parse the data from the Fortnite API to show it in the detail activity.
 *
 * @author Jonas Lehmann, Mirjam Doyon, Hava Fuga, Enis Badoglu
 */
public class FortniteApiParser {

    /**
     * A method to get a single FortniteProfile object from a JSONObject to show the stats.
     *
     * @param jsonProfile the profile of the player in JSON format
     * @return the profile of the player as an FortniteProfile object
     * @throws JSONException
     */
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

        return profile;
    }

    /**
     * A method to get a single FortniteProfile object from a string to show the stats.
     *
     * @param stringProfile the profile of the player as a string
     * @return the profile of the player as an FortniteProfile object
     * @throws JSONException
     */
    public static FortniteProfile parseSingleProfile(String stringProfile) throws JSONException {
        JSONObject jsonProfile = new JSONObject(stringProfile);

        return parseSingleProfile(jsonProfile);
    }
}
