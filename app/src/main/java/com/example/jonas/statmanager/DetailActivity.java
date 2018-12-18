package com.example.jonas.statmanager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ImageView;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.jonas.statmanager.helper.FileManager;
import com.example.jonas.statmanager.helper.OverwatchApiParser;
import com.example.jonas.statmanager.model.Profile;
import com.squareup.picasso.Picasso;
import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

/**
 * Class to show the content for the detail activity. The stats of a players profile will be shown here.
 * This class handles stats for the game overwatch.
 *
 * @author Jonas Lehmann, Mirjam Doyon, Hava Fuga, Enis Badoglu
 */
public class DetailActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    Context context;
    String path;
    String filePath;
    String username;


    /**
     * Methode to create the activity and initialize all the needed designs for the gui and the logic
     * so the user can interact with this activity.
     *
     * @param savedInstanceState the savedInstanceState is a reference to a bundle object that is passed
     *                           into the onCreate method of every android activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        context = getApplicationContext();
        path = context.getFilesDir() + "/StatManager";
        filePath = path + "/fav.txt";
        Intent intent = getIntent();
        final String username = intent.getStringExtra("username");
        String overwatchApiUrl = "https://ow-api.com/v1/stats/pc/eu/";
        progressBar = (ProgressBar)findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);
        final ImageView favorite_user = (ImageView) findViewById(R.id.favorite);

        loadSpecificProfile(overwatchApiUrl + username + "/profile");

        File loadFile = new File(filePath);
        String[] loadedData = FileManager.Load(loadFile);

        if (loadedData != null){
            for (int i = 0; i < loadedData.length; i++){
                String[] parts = loadedData[i].split(";");
                if (parts[0].equals("Overwatch") && parts[1].equalsIgnoreCase(username)){
                    favorite_user.setImageDrawable(getResources().getDrawable(R.drawable.star_favorite));

                    favorite_user.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getApplicationContext(),"Favoriten entfernen noch nicht implementiert", Toast.LENGTH_SHORT).show();
                        }
                    });
                    return;
                } else{

                    favorite_user.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String[] stringInput = new String[1];
                            stringInput[0] = "Overwatch;"+username;

                            File dir = new File(path);
                            boolean s = dir.mkdirs();
                            File saveFile = new File(filePath);

                            if (!saveFile.exists()) {
                                try {
                                    saveFile.createNewFile();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else{
                                saveFile = saveFile.getAbsoluteFile();
                                String[] alreadyExistingEntries = FileManager.Load(saveFile);
                                String[] tempArray = new String[stringInput.length + alreadyExistingEntries.length];
                                for(int i = 0; i <alreadyExistingEntries.length; i++){
                                    tempArray[i] = alreadyExistingEntries[i];
                                }
                                tempArray[tempArray.length - 1] = stringInput[0];
                                stringInput = tempArray;
                            }

                            FileManager.Save(saveFile, stringInput);

                            Toast.makeText(getApplicationContext(),username +" zu deinen Favoriten hinzugefügt", Toast.LENGTH_SHORT).show();

                            favorite_user.setImageDrawable(getResources().getDrawable(R.drawable.star_favorite));
                            return;
                        }
                    });
                }
            }
        } else{
            favorite_user.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String[] stringInput = new String[1];
                    stringInput[0] = "Overwatch;"+username;

                    File dir = new File(path);
                    boolean s = dir.mkdirs();
                    File saveFile = new File(filePath);
                    if (!saveFile.exists()) {
                        try {
                            saveFile.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else{
                        saveFile = saveFile.getAbsoluteFile();
                    }

                    FileManager.Save(saveFile, stringInput);

                    Toast.makeText(getApplicationContext(),username +" zu deinen Favoriten hinzugefügt", Toast.LENGTH_SHORT).show();

                    favorite_user.setImageDrawable(getResources().getDrawable(R.drawable.star_favorite));
                    return;
                }
            });
        }



        ImageView logoBtn = (ImageView)findViewById(R.id.logo);
        logoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailActivity.this, MainActivity.class));
            }
        });
    }

    /**
     * Method to load a specific player profile from the api.
     *
     * @param url the url of the used api
     */
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
                            TextView games_played_field = (TextView) findViewById(R.id.games_played_field);

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
                            games_played_field.setText(String.format(Locale.getDefault(), "Games played: %s",
                                    profile.getGamesPlayed()
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
        });

        queue.add(jsonObjReq);
    }

    /**
     * Methode to show an alert dialog if the searched username doesn't exist.
     *
     * @param error message that should be shown if an error occurs
     */
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
