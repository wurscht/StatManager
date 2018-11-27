package com.example.jonas.statmanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ImageView;
import android.support.v7.app.AlertDialog;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.example.jonas.statmanager.helper.FortniteApiParser;
import com.example.jonas.statmanager.helper.OverwatchApiParser;
import com.example.jonas.statmanager.model.FortniteProfile;
import com.example.jonas.statmanager.model.Profile;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DetailFortniteActivity extends AppCompatActivity {
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_fortnite);
        Intent intent = getIntent();
        final String username = intent.getStringExtra("username");
        String FortniteApiUrl = "https://api.fortnitetracker.com/v1/profile/pc/";
        progressBar = (ProgressBar)findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);

        ImageView logoBtn = (ImageView)findViewById(R.id.logo);
        logoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailFortniteActivity.this, MainActivity.class));
            }
        });

        final ImageView favorite_user = (ImageView) findViewById(R.id.favorite);
        favorite_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FavoriteActivity favorite = new FavoriteActivity();
                favorite.saveFav(username, "Fortnite");
                favorite_user.setImageResource(R.drawable.star_favorite);

            }
        });

        loadSpecificProfile(FortniteApiUrl + username);
    }

    private void loadSpecificProfile(String url) {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            FortniteProfile profile = FortniteApiParser.parseSingleProfile(response);

                            ImageView player_icon_field = (ImageView) findViewById(R.id.player_icon);
                            TextView username_field = (TextView) findViewById(R.id.username_field);
                            TextView rating_field = (TextView) findViewById(R.id.rating_field);
                            TextView games_won_field = (TextView) findViewById(R.id.games_won_field);
                            TextView kd_field = (TextView) findViewById(R.id.kd_field);
                            TextView kills_field = (TextView) findViewById(R.id.kills_field);
                            TextView winrate_field = (TextView) findViewById(R.id.winrate_field);
                            // TextView played_field = (TextView) findViewById(R.id.played_field);
                            // TextView won_field = (TextView) findViewById(R.id.won_field);

                            String imageUri = "https://yt3.ggpht.com/a-/AN66SAxDjbNwg3tpSPzoLCBB_4jhfYfTNxp8706NaQ=s50-mo-c-c0xffffffff-rj-k-no";
                            Picasso.get().load(imageUri).into(player_icon_field);

                            username_field.setText(String.format(Locale.getDefault(), "Username: %s",
                                    profile.getUsername()
                            ));
                            rating_field.setText(String.format(Locale.getDefault(), "Score: %s",
                                    profile.getRating()
                            ));
                            games_won_field.setText(String.format(Locale.getDefault(), "Games won: %s",
                                    profile.getGamesWon()
                            ));
                            kd_field.setText(String.format(Locale.getDefault(), "KD: %s",
                                    profile.getKD()
                            ));
                            kills_field.setText(String.format(Locale.getDefault(), "Kills: %s",
                                    profile.getKills()
                            ));
                            winrate_field.setText(String.format(Locale.getDefault(), "Win rate: %s",
                                    profile.getWinrate()
                            ));
                            progressBar.setVisibility(View.INVISIBLE);

                        } catch (Exception e) {
                            generateAlertDialog(e.toString());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                generateAlertDialog(error.toString());
            }
        }){

            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("TRN-Api-Key", "888d5dd1-9cf5-41f9-a3cd-54d99edc079a");
                return headers;
            }
        };

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
