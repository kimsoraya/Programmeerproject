package com.example.kimschuiten.mypersonalcookbook30;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button viewMenu;
    Button addRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewMenu = (Button) findViewById(R.id.menuButton);
        addRecipe = (Button) findViewById(R.id.addButton);

        Intent mainIntent = getIntent();


    }
    /*
    onClickListener to show the menu options
    */
    public void menuButtonClick(View view) {
        // Open new activity with all the recipe items
        Intent viewCategoryIntent = new Intent(this, CategoryActivity.class);
        startActivity(viewCategoryIntent);
    }

    /*
    onClickListener for add recipe button
    */
    public void addButtonClick(View view) {
        // Open new activity in which you can choose text or photo recipe
        Intent textPhotoIntent = new Intent(this, TextOrPhotoActivity.class);
        startActivity(textPhotoIntent);

    }
}