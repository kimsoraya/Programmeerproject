package com.example.kimschuiten.mypersonalcookbook30;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ShowRecipeActivity extends AppCompatActivity {
    TextView recipeText;


    // Database objects
    SQLiteDatabase sqLiteDatabase;
    RecipeDatabaseHelper recipeDatabaseHelper;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_recipe);

        recipeText = (TextView) findViewById(R.id.recipeTextView);

        Intent viewCategoryIntent = getIntent();

        // Get the information from the database
        cursor = recipeDatabaseHelper.getRecipeInfo(sqLiteDatabase);

        // TODO haal de tekst op die bij de titel hoort



        // Analyze cursor object: Is there data available on the cursor object?
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    // Get information from the cursor object
                    String text;
                    text = cursor.getString(2);


                    // Get the titles and image paths from the database
/*
                    RecipeDataProvider recipeDataProvider = new RecipeDataProvider(text);
*/

                    // Add them to the listview
/*
                    recipeText.setText(recipeDataProvider);
*/
                }
                while (cursor.moveToNext());
            }

        }
    }

    public void HomeButtonClick(View view) {
        // Go back to main screen
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }
}
