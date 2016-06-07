package com.example.kimschuiten.mypersonalcookbook30;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

    TextView viewTitlesTextView;
    ListView viewTitlesListView;

    // Create objects for information in the listview and the custom adapter
    int[] recipeImageResource = {R.drawable.paella, R.drawable.pastapesto};
    String[] recipeTitles;
    RecipeAdapter adapter;

    // Database objects
    SQLiteDatabase sqLiteDatabase;
    RecipeDatabaseHelper recipeDatabaseHelper;
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        // Initialize
        viewTitlesTextView = (TextView) findViewById(R.id.recipeList);
        viewTitlesListView = (ListView) findViewById(R.id.recipeListView);
        adapter = new RecipeAdapter(getApplicationContext(), R.layout.single_list_item);
        recipeTitles = getResources().getStringArray(R.array.recipe_titles);

        // Set up the adapter
        viewTitlesListView.setAdapter(RecipeAdapter);

        // Initialize database
        recipeDatabaseHelper = new RecipeDatabaseHelper(getApplicationContext());
        sqLiteDatabase = recipeDatabaseHelper.getWritableDatabase();

        // Get the information from the database
        cursor = recipeDatabaseHelper.getRecipeInfo(sqLiteDatabase);

        // Analyze cursor object: Is there data available on the cursor object?
        if (cursor.moveToFirst()){
            do {
                // Get information from the cursor object
                int image;
                String category;
                category = cursor.getString(1);

                // TODO: 06-06-16
                image =

                RecipeDataProvider recipeDataProvider = new RecipeDataProvider(image, category);

                RecipeAdapter.add(recipeDataProvider)
            }
            while(cursor.moveToNext());
        }

/*
        // Pass objects from the RecipeDataProvider into the ListView
        viewTitlesListView.setAdapter(adapter);
        int i = 0;
        for (String titles: recipeTitles){
            RecipeDataProvider newDataProvider = new RecipeDataProvider(recipeImageResource[i],
                    titles);
            // Add recipe objects using the add method
            adapter.add(newDataProvider);
            i++;
        }


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
        }*/


    }

    public void HomeButtonClick(View view) {
        // Go back to main screen
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }
}