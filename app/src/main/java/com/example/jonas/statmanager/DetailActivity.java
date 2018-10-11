package com.example.jonas.statmanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.support.v7.app.AlertDialog;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.jonas.statmanager.helper.OverwatchApiParser;
import com.example.jonas.statmanager.model.Profile;

import org.json.JSONException;

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
        //loadSpecificProfile(overwatchApiUrl +"/1");
    }

    private void loadSpecificProfile(String url) {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Profile specific_profile = OverwatchApiParser.parseSingleProfile(response);
                            TextView username_field = (TextView) findViewById(R.id.username_field);
                            username_field.setText(specific_profile.getUsername());

                        } catch (JSONException e) {
                            generateAlertDialog();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                generateAlertDialog();
            }
        });
        queue.add(stringRequest);
    }

    private void generateAlertDialog() {
        AlertDialog.Builder dialogBuilder;
        dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });

        dialogBuilder.setMessage("The profile could not be loaded. Try again later.").setTitle("Error");
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }
}
