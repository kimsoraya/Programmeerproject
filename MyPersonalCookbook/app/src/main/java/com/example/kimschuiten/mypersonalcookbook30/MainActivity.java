package com.example.kimschuiten.mypersonalcookbook30;

/**
 * MainActivity in which the user gets to choose whether he wants to view the recipes or add a new
 * recipe
 */

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button viewMenu;
    ImageView addRecipe;

    private ArrayAdapter<String> listAdapter;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewMenu = (Button) findViewById(R.id.menuButton);
        addRecipe = (ImageView) findViewById(R.id.addButton);

        Intent mainIntent = getIntent();

        RecipeDatabaseHelper recipeDatabaseHelper = new RecipeDatabaseHelper(MainActivity.this);
        sqLiteDatabase = recipeDatabaseHelper.getReadableDatabase();
    }

    /**
     *onClickListener to show the menu options
    **/
    public void menuButtonClick(View view) {
        // Open new activity with all the recipe items
        Intent viewCategoryIntent = new Intent(this, RecipeListActivity.class);
        startActivity(viewCategoryIntent);
    }

    /**
     * onClickListener for add recipe button
    **/
    public void addButtonClick(View view) {
        // Open new activity in which you can choose text or photo recipe
        Intent textPhotoIntent = new Intent(this, TextOrPhotoActivity.class);
        startActivity(textPhotoIntent);
    }
}