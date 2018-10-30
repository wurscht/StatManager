package com.example.jonas.statmanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.ImageView;
import android.support.v7.app.AlertDialog;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.example.jonas.statmanager.helper.OverwatchApiParser;
import com.example.jonas.statmanager.model.Profile;

import org.json.JSONObject;
import java.util.Locale;
import java.net.URL;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        //String overwatchApiUrl = "https://api.punkapi.com/v2/beers";
        String overwatchApiUrl = "https://ow-api.com/v1/stats/pc/eu/";

        loadSpecificProfile(overwatchApiUrl + username + "/profile");
    }

    private void loadSpecificProfile(String url) {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Profile profile = OverwatchApiParser.parseSingleProfile(response);
                    TextView username_field = (TextView) findViewById(R.id.username_field);
                    username_field.setText(String.format(Locale.getDefault(), "Username: %s\nEndorsement: %d\nLevel: %d\nGames won: %d\nPrestige: %d\nPrivate: %s",
                            profile.getUsername(),
                            profile.getEndorsement(),
                            profile.getLevel(),
                            profile.getGamesWon(),
                            profile.getPrestige(),
                            profile.getPriv()
                    ));
                } catch (Exception e) {
                    generateAlertDialog(e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                generateAlertDialog(error.toString());
            }
        });

        queue.add(jsonObjReq);
    }

    private void generateAlertDialog(String error) {
        AlertDialog.Builder dialogBuilder;
        dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        dialogBuilder.setMessage("The profile could not be loaded: " + error).setTitle("Error");
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }
}
