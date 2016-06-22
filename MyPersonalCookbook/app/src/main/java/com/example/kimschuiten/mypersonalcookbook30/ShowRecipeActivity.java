package com.example.kimschuiten.mypersonalcookbook30;
/**
 * Show all the information of the recipe. This means the title on top and below the text of the
 * recipe of the image of the recipe.
 */

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class ShowRecipeActivity extends AppCompatActivity {
    TextView recipeText;
    TextView recipeTitleView;
    ImageView recipePhoto;
    String mRecipeTitle;
    String mRecipeText;
    String photoPath;

    // Database objects
    SQLiteDatabase sqLiteDatabase;
    RecipeDatabaseHelper recipeDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_recipe);

        recipeText = (TextView) findViewById(R.id.recipeTextView);
        recipeTitleView = (TextView) findViewById(R.id.showRecipeTitle);
        recipePhoto = (ImageView) findViewById(R.id.recipeImageView);

        // Initialize database
        recipeDatabaseHelper = new RecipeDatabaseHelper(getApplicationContext());
        sqLiteDatabase = recipeDatabaseHelper.getWritableDatabase();

        // Get the title and text from the previous activity
        Bundle extras = getIntent().getExtras();
        mRecipeTitle = extras.getString("id");
        mRecipeText = extras.getString("text");
        photoPath = extras.getString("photoPath");

        // If it is a photorecipe, show title and image.
        if (photoPath != null) {
            recipeTitleView.setText(mRecipeTitle);
            recipePhoto.setImageBitmap(convertSrcToBitmap(photoPath));
        } else{
            // Show the title en text
            recipeTitleView.setText(mRecipeTitle);
            recipeText.setText(mRecipeText);
        }
    }

    /**
     * Method that can be called to convert an image path to a bitmap.
     * imageSrc has to be a String of the image path
     */
    public Bitmap convertSrcToBitmap(String imageSrc) {
        Bitmap myBitmap = null;
        File imgFile = new File(imageSrc);

        if (imgFile.exists()) {
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 8;
            myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath(), options);
        }
        return myBitmap;
    }

    /**
     * Go back to MainActivity
     */
    public void HomeButtonClick(View view) {
        // Go back to main screen
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }
}
