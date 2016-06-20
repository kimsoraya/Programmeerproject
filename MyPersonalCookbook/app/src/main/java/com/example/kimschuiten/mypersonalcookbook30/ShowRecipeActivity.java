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
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

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
/*
        photoPath = extras.getString("photoPath");
*/
        Log.d("TEST INDEX", "OPGEHAALDE INDEX = " + mRecipeTitle);

        // Show the title en text
        recipeTitleView.setText(mRecipeTitle);
        recipeText.setText(mRecipeText);
/*
        recipePhoto.setImageBitmap(convertSrcToBitmap(photoPath));
*/
        }

    public Bitmap convertSrcToBitmap(String imageSrc) {
        Bitmap myBitmap = null;
        File imgFile = new File(imageSrc);
        if (imgFile.exists()) {
            myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
        }
        return myBitmap;
    }

    public void HomeButtonClick(View view) {
        // Go back to main screen
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }
}
