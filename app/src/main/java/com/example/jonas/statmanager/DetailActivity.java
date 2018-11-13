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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.example.jonas.statmanager.helper.OverwatchApiParser;
import com.example.jonas.statmanager.model.Profile;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.Locale;

public class DetailActivity extends AppCompatActivity {
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        final String username = intent.getStringExtra("username");
        String overwatchApiUrl = "https://ow-api.com/v1/stats/pc/eu/";
        progressBar = (ProgressBar)findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);

        ImageView logoBtn = (ImageView)findViewById(R.id.logo);
        logoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailActivity.this, MainActivity.class));
            }
        });

        final ImageView favorite_user = (ImageView) findViewById(R.id.favorite);
        favorite_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FavoriteActivity favorite = new FavoriteActivity();
                favorite.saveFav(username, "Overwatch");
                favorite_user.setImageResource(R.drawable.star_favorite);

            }
        });

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

                            ImageView player_icon_field = (ImageView) findViewById(R.id.player_icon);
                            TextView username_field = (TextView) findViewById(R.id.username_field);
                            TextView level_field = (TextView) findViewById(R.id.level_field);
                            TextView rating_field = (TextView) findViewById(R.id.rating_field);
                            TextView games_won_field = (TextView) findViewById(R.id.games_won_field);
                            // TextView played_field = (TextView) findViewById(R.id.played_field);
                            // TextView won_field = (TextView) findViewById(R.id.won_field);

                            Picasso.get().load(profile.getIcon()).into(player_icon_field);

                            username_field.setText(String.format(Locale.getDefault(), "Username: %s",
                                    profile.getUsername()
                            ));
                            level_field.setText(String.format(Locale.getDefault(), "Level: %s",
                                    profile.getLevel()
                            ));
                            rating_field.setText(String.format(Locale.getDefault(), "Rating: %s",
                                    profile.getRating()
                            ));
                            games_won_field.setText(String.format(Locale.getDefault(), "Games won: %s",
                                    profile.getGamesWon()
                            ));
                            /*played_field.setText(String.format(Locale.getDefault(), "Competitive games played:\n%s",
                                    profile.getPlayed()
                            ));
                            won_field.setText(String.format(Locale.getDefault(), "Competitive games won:\n%s",
                                    profile.getWon()
                            ));*/

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
