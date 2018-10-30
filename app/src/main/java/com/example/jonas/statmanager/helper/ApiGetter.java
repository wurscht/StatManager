package com.example.jonas.statmanager.helper;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class ApiGetter {

    public enum Game {
        Fortnite,
        Overwatch
    }

    public Game gameName;

    public ApiGetter(String game){
        this.gameName = Game.valueOf(game);
    }

    public void GetApi(String username){
        HttpClient httpclient = new DefaultHttpClient();
        if (gameName == Game.Fortnite){
            String linkPartOne = "https://api.fortnitetracker.com/v1/profile/pc/";

            HttpPost post = new HttpPost( linkPartOne + username);
            post.addHeader( "TRN-Api-Key" , "888d5dd1-9cf5-41f9-a3cd-54d99edc079a" );
            try {
                HttpResponse response = httpclient.execute(post);
                HttpEntity entity = response.getEntity();

                if (entity != null) {
                    String retSrc = EntityUtils.toString(entity);
                    // parsing JSON
                    JSONObject result = new JSONObject(retSrc); //Convert String to JSON Object

                    JSONArray tokenList = result.getJSONArray("names");
                    JSONObject oj = tokenList.getJSONObject(0);
                    String token = oj.getString("name");

                    Log.d("tag", oj.toString(4));
                    Log.d("Tag", token);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else if (gameName == Game.Overwatch) {
            String linkPartOne = "https://ow-api.com/v1/stats/pc/eu/";
            String linkPartTwo = "/profile";

            HttpPost post = new HttpPost( linkPartOne + username + linkPartTwo);
            try {
                HttpResponse response = httpclient.execute(post);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            return;
        }
    }
}
