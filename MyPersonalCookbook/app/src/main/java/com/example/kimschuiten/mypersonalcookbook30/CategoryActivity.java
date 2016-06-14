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
import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    TextView viewTitlesTextView;
    ListView viewTitlesListView;

    // Create objects for information in the listview and the custom adapter
/*
    int[] recipeImageResource = {R.drawable.paella, R.drawable.pastapesto};
*/
    String[] recipeTitles;
    RecipeAdapter adapter;
/*
    ArrayList<String> getSets;
*/

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

                  /*  // Get the Bitmap
                    Uri uri = Uri.parse("file://" + imagePath);
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/

                    // Get the titles and image paths from the database
                    RecipeDataProvider recipeDataProvider = new RecipeDataProvider(convertSrcToBitmap(imagePath), titles);

                    // Add them to the listview
                    adapter.add(recipeDataProvider);


                    /*long selectedImageUri = ContentUris.parseId(Uri.fromFile(new File(imagePath)));
                    Bitmap bm = MediaStore.Images.Thumbnails.getThumbnail(
                            mContext.getContentResolver(), selectedImageUri, MediaStore.Images.Thumbnails.MICRO_KIND,
                            null);*/

                }
                while (cursor.moveToNext());
            }
        }

        // Set onclicklistener for the recipe titles.
        viewTitlesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the index of the title in the database
                String index = ((TextView) findViewById(R.id.recipeTitle)).getText().toString();
                String indexText = cursor.getString(2);


                Bundle dataBundle = new Bundle();
                dataBundle.putString("id", index);
                dataBundle.putString("text", indexText);

                // Go to ShowRecipeActivity
                Intent showIntent = new Intent(view.getContext(), ShowRecipeActivity.class);
                showIntent.putExtras(dataBundle);
                startActivity(showIntent);
            }
        });
    }

    /*public Uri getImageUri(Context inContext, Bitmap inImage, String imageName) {
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