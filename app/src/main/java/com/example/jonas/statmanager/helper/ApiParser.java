package com.example.jonas.statmanager.helper;

import org.json.JSONObject;

public class ApiParser {

    public enum Game {
        Fortnite,
        Overwatch
    }

    public Game gameName;
    public JSONObject overwatchResponse;
    public String FortniteResponse;

    public ApiParser(String game){
        this.gameName = Game.valueOf(game);
    }

    public JSONObject Parse(){
        if (gameName == Game.Overwatch){
            JSONObject json = overwatchResponse;
            return json;
        }
        else if (gameName == Game.Fortnite){
            JSONObject json = new JSONObject();
            return json;
        }
        else{
            JSONObject json = new JSONObject();
            return json;
        }
    }
}
