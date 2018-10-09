package com.example.jonas.statmanager;

// import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
// import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

public class SearchActivity extends AppCompatActivity {
    Button searchBtn;
    SearchView searchField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        searchBtn = (Button) findViewById(R.id.search_button);
        searchField = (SearchView) findViewById(R.id.search_field);


        
        //searchBtn.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
        //        String input = searchField.getQuery().toString();
        //        intent.putExtra("username", input);
        //        startActivity(intent);

        //    }
        //});
    }
}
