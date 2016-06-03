package com.example.kimschuiten.mypersonalcookbook30;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class CategoryActivity extends AppCompatActivity {

    Button viewTitlesButton;
    TextView viewTitlesTextView;
    ListView viewTitlesListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        viewTitlesButton = (Button) findViewById(R.id.viewTitles);
        viewTitlesTextView = (TextView) findViewById(R.id.recipeList);
        viewTitlesListView = (ListView) findViewById(R.id.recipeListView);

    }

    /*
    When button is clicked, show the different recipes
     */
    public void viewTitlesClick(View view) {
        try {
            String titles;
            FileInputStream fileInputStream = openFileInput("saved_titles");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();

            // Fetch the information using the buffered reader
            while ((titles = bufferedReader.readLine())!= null){
                stringBuffer.append(titles + "\n");
            }

            // Show the titles in the TextView
            viewTitlesTextView.setText(stringBuffer.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void HomeButtonClick(View view) {
        // Go back to main screen
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }
}