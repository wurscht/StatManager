package com.example.jonas.statmanager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class FavouriteActivity extends AppCompatActivity {
    ImageView logoBtn;
    List<String> favoriteList;
    List<String> favourites;
    BufferedReader reader;
    EditText txtInput;
    TextView txtContent;
    final static String file = System.getProperty("user.dir") + "favorites.txt" ;
    final static String TAG = "sth";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        logoBtn = (ImageView) findViewById(R.id.logo);

        logoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("My app",file + "123");
                startActivity(new Intent(FavouriteActivity.this, MainActivity.class));
            }
        });

        Log.i("My app","sdf" + file + "LOREMMM IPSUUMM");
    }

    private List<String> getFavourites() throws IOException {
        try {
            reader = new BufferedReader(new FileReader(file));
            String text;
            List<Integer> list = new ArrayList<Integer>();

            while ((text = reader.readLine()) != null) {
                list.add(Integer.parseInt(text));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
            }
        }

    }

    public String ReadFile(){
        String line = null;
        try {
            FileInputStream fileInputStream = new FileInputStream (new File(file));
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String st = new String();

            while ( (line = bufferedReader.readLine()) != null )
            {
                stringBuilder.append(line + System.getProperty("line.separator"));
                System.out.println(line);
                favoriteList.add(line);
            }
            fileInputStream.close();
            line = stringBuilder.toString();

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            Log.d(TAG, ex.getMessage());
        }
        catch(IOException ex) {
            Log.d(TAG, ex.getMessage());
        }
        return line;
    }

    public void saveToFile(String userName, String game, Context context){
        String text = userName + ";" + game;

        File yourFile = new File(context.getFilesDir().getPath().toString() + file);
        if(!yourFile.exists()) {
            try {
                yourFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileOutputStream oFile = new FileOutputStream(yourFile, false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        /*PrintWriter writer = null;
        try {
            writer = new PrintWriter(context.getFilesDir().getPath().toString() + file, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        writer.println("The first line");
        writer.println("The second line");
        writer.close();*/

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("favorites.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(text);
            outputStreamWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean saveToFile_old( String userName, String game){
        String text = userName + ";" + game;
        try {
            File txt = new File(file);
            if (!txt.exists()) {
                txt.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(txt,true);
            fileOutputStream.write((text + System.getProperty("line.separator")).getBytes());

            return true;
        }  catch(FileNotFoundException ex) {
            Log.d(TAG, ex.getMessage());
        }  catch(IOException ex) {
            Log.d(TAG, ex.getMessage());
        }
        return  false;
    }
}
