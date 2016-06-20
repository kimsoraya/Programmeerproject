package com.example.kimschuiten.mypersonalcookbook30;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CategoryActivity extends AppCompatActivity {

    ListView viewTitlesListView;

    // Create objects for information in the listview and the custom adapter
    String[] recipeTitles;
    String text;
    String title;
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
        if (cursor!=null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    // Get information from the cursor object
                    String imagePath;
                    String titles;
                    titles = cursor.getString(0);
                    imagePath = cursor.getString(1);

                    if (imagePath != null) {
                        // Get the titles and image paths from the database
                        RecipeDataProvider recipeDataProvider = new RecipeDataProvider(convertSrcToBitmap(imagePath), titles);
                        // Add them to the listview
                        adapter.add(recipeDataProvider);
                    }
                    else{
                        // Get the titles from the database
                        RecipeDataProvider recipeDataProvider = new RecipeDataProvider(titles);
                        // Add them to the listview
                        adapter.add(recipeDataProvider);
                    }
                }
                while (cursor.moveToNext());
            }
        }

        // Set onclicklistener for the recipe titles.
        viewTitlesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sqLiteDatabase = recipeDatabaseHelper.getReadableDatabase();

                // Get the index of the title in the database, use it to get the text/photo text
                String index = ((TextView) findViewById(R.id.recipeTitle)).getText().toString();
                String text = recipeDatabaseHelper.getRecipeText(index, sqLiteDatabase);
/*
                String photoPath = recipeDatabaseHelper.getRecipePhotoText(index, sqLiteDatabase);
*/

                Bundle dataBundle = new Bundle();
                dataBundle.putString("id", index);
                dataBundle.putString("text", text);
/*
                dataBundle.putString("photoPath", photoPath);
*/

                // Go to ShowRecipeActivity
                Intent showIntent = new Intent(view.getContext(), ShowRecipeActivity.class);
                showIntent.putExtras(dataBundle);
                startActivity(showIntent);

/*
                HashMap<String, String> hashmap = (HashMap)parent.getItemAtPosition(position);


                Intent intent = new Intent (parent.getContext(), ShowRecipeActivity.class);
                intent.putExtra("key", hashmap);
                startActivityForResult(intent, 0);*/
            }
        });
    }
/*
    public Uri getImageUri(Context inContext, Bitmap inImage, String imageName) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, imageName, null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }*/

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

    public void HomeButtonClick(View view) {
        // Go back to main screen
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }
}