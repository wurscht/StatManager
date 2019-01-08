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
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.jonas.statmanager.helper.FileManager;
import com.example.jonas.statmanager.helper.FortniteApiParser;
import com.example.jonas.statmanager.model.FortniteProfile;
import com.squareup.picasso.Picasso;
import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Class to show the content for the detail activity. The stats of a players profile will be shown here.
 * This class handles stats for the game fortnite.
 *
 * @author Jonas Lehmann, Mirjam Doyon, Hava Fuga, Enis Badoglu
 */
public class DetailFortniteActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    Context context;
    String path;
    String filePath;
    String usernameFinal;


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
        setContentView(R.layout.activity_detail_fortnite);
        context = getApplicationContext();
        path = context.getFilesDir() + "/StatManager";
        filePath = path + "/fav.txt";
        Intent intent = getIntent();
        final String username = intent.getStringExtra("username");
        String FortniteApiUrl = "https://api.fortnitetracker.com/v1/profile/pc/";
        progressBar = (ProgressBar)findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);
        final ImageView favorite_user = (ImageView) findViewById(R.id.favorite);

        loadSpecificProfile(FortniteApiUrl + username);

        File loadFile = new File(filePath);
        File dir = new File(path);
        loadFile.mkdirs();
        String[] loadedData = FileManager.Load(loadFile);


        if (loadedData != null){
            if (loadedData.length != 0){
                for (int i = 0; i < loadedData.length; i++){
                    String[] parts = loadedData[i].split(";");
                    if (parts[0].equals("Fortnite") && parts[1].equalsIgnoreCase(username)){
                        favorite_user.setImageDrawable(getResources().getDrawable(R.drawable.star_favorite));

                        favorite_user.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(),"Favoriten entfernen noch nicht implementiert", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else{

                        favorite_user.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String[] stringInput = new String[1];
                                stringInput[0] = "Fortnite;"+usernameFinal;

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

                                Toast.makeText(getApplicationContext(),usernameFinal +" zu deinen Favoriten hinzugefügt", Toast.LENGTH_SHORT).show();

                                favorite_user.setImageDrawable(getResources().getDrawable(R.drawable.star_favorite));
                                return;
                            }
                        });
                    }
                }
            }else{
                favorite_user.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String[] stringInput = new String[1];
                        stringInput[0] = "Fortnite;"+usernameFinal;

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

                        Toast.makeText(getApplicationContext(),usernameFinal +" zu deinen Favoriten hinzugefügt", Toast.LENGTH_SHORT).show();

                        favorite_user.setImageDrawable(getResources().getDrawable(R.drawable.star_favorite));
                        return;
                    }
                });
            }

        } else{
            favorite_user.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String[] stringInput = new String[1];
                    stringInput[0] = "Fortnite;"+usernameFinal;

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

                    Toast.makeText(getApplicationContext(),usernameFinal +" zu deinen Favoriten hinzugefügt", Toast.LENGTH_SHORT).show();

                    favorite_user.setImageDrawable(getResources().getDrawable(R.drawable.star_favorite));
                    return;
                }
            });
        }

        ImageView logoBtn = (ImageView)findViewById(R.id.logo);
        logoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailFortniteActivity.this, MainActivity.class));
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
                            usernameFinal = profile.getUsername();
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
