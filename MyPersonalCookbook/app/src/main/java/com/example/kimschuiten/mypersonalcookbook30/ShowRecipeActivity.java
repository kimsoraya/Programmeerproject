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

import org.w3c.dom.Text;

public class ShowRecipeActivity extends AppCompatActivity {
    TextView recipeText;
    TextView recipeTitleView;
    String mRecipeTitle;
    String mRecipeText;


    // Database objects
    SQLiteDatabase sqLiteDatabase;
    RecipeDatabaseHelper recipeDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_recipe);

        recipeText = (TextView) findViewById(R.id.recipeTextView);
        recipeTitleView = (TextView) findViewById(R.id.showRecipeTitle);

        // Initialize database
        recipeDatabaseHelper = new RecipeDatabaseHelper(getApplicationContext());
        sqLiteDatabase = recipeDatabaseHelper.getWritableDatabase();

        // Haal de titel en tekst op uit vorige activity
        Bundle extras = getIntent().getExtras();
        mRecipeTitle = extras.getString("id");
        mRecipeText = extras.getString("text");
        Log.d("TEST INDEX", "OPGEHAALDE INDEX = " + mRecipeTitle);

        recipeTitleView.setText(mRecipeTitle);
        recipeText.setText(mRecipeText);

        // TODO haal de tekst op die bij de titel hoort

/*
        if (extras != null) {
            String Value = extras.getString("id");


            // Analyze cursor object: Is there data available on the cursor object?
            Cursor cursor = recipeDatabaseHelper.getRecipeInfo(Value);
            index = Value;
            cursor.moveToFirst();

            String text = cursor.getString(2);

            }
*/
        }



    public void HomeButtonClick(View view) {
        // Go back to main screen
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }
}
