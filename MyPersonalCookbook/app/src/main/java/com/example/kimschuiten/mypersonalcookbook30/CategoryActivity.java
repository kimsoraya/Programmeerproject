package com.example.kimschuiten.mypersonalcookbook30;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;

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
    Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        // Initialize
        viewTitlesTextView = (TextView) findViewById(R.id.recipeList);
        viewTitlesListView = (ListView) findViewById(R.id.recipeListView);
        adapter = new RecipeAdapter(getApplicationContext(), R.layout.single_list_item);
        recipeTitles = getResources().getStringArray(R.array.recipe_titles);

        Intent viewCategoryIntent = getIntent();

        // Set up the adapter
        viewTitlesListView.setAdapter(adapter);

        // Initialize database
        recipeDatabaseHelper = new RecipeDatabaseHelper(getApplicationContext());
        sqLiteDatabase = recipeDatabaseHelper.getWritableDatabase();

        // Get the information from the database
        cursor = recipeDatabaseHelper.getRecipeInfo(sqLiteDatabase);

        // Analyze cursor object: Is there data available on the cursor object?
        if (cursor.moveToFirst()){
            do {
                // Get information from the cursor object
                String imagePath;
                String titles;
                titles = cursor.getString(0);
                imagePath = cursor.getString(2);

                // Get the titles and image paths from the database
                RecipeDataProvider recipeDataProvider = new RecipeDataProvider(imagePath, titles);
                adapter.add(recipeDataProvider);

                // TODO: convert image path to actual thumbnail image in imageview
                long selectedImageUri = ContentUris.parseId(Uri.fromFile(new File(imagePath)));
                Bitmap bm = MediaStore.Images.Thumbnails.getThumbnail(
                        mContext.getContentResolver(), selectedImageUri,MediaStore.Images.Thumbnails.MICRO_KIND,
                        null );

            }
            while(cursor.moveToNext());
        }

        // Set onclicklistener for the recipe titles.
        viewTitlesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Go to ShowRecipeActivity
                Intent showIntent = new Intent(view.getContext(), ShowRecipeActivity.class);
                startActivity(showIntent);
            }
        });

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