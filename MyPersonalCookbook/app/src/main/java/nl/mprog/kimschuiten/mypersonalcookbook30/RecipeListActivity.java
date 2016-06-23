// My Personal Cookbook
// Kim Schuiten

package nl.mprog.kimschuiten.mypersonalcookbook30;

/**
 * In this Activity the user gets to see a list of all the recipes he created
 * All the recipes are clickable, if you click on a title you go to a new activity to see all the details
 * If you longclick a title you can delete the recipe
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class RecipeListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,
        AdapterView.OnItemLongClickListener{

    ListView viewTitlesListView;
    String[] recipeTitles;
    RecipeAdapter adapter;

    SQLiteDatabase sqLiteDatabase;
    RecipeDatabaseHelper recipeDatabaseHelper;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        viewTitlesListView = (ListView) findViewById(R.id.recipeListView);
        adapter = new RecipeAdapter(getApplicationContext(), R.layout.single_list_item);
        recipeTitles = getResources().getStringArray(R.array.recipe_titles);

        Intent viewCategoryIntent = getIntent();

        // Delete recipes when you long click.
        viewTitlesListView.setOnItemLongClickListener(this);

        // Set onclicklistener for the recipe titles.
        viewTitlesListView.setOnItemClickListener(this);

        // Call the getRecipeList method to create the list.
        getRecipeList();

    }

    /**
     * Creates the list by setting up the adapter and return information from the sqlite database.
     */
    public void getRecipeList() {
        // Set up the adapter
        viewTitlesListView.setAdapter(adapter);

        // Initialize database.
        recipeDatabaseHelper = new RecipeDatabaseHelper(getApplicationContext());
        sqLiteDatabase = recipeDatabaseHelper.getWritableDatabase();

        // Get the information from the database.
        cursor = recipeDatabaseHelper.getRecipeInfo(sqLiteDatabase);

        // Analyze cursor object: Is there data available on the cursor object?
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    // Get information from the cursor object.
                    String imagePath;
                    String titles;
                    titles = cursor.getString(0);
                    imagePath = cursor.getString(1);

                    if (imagePath != null) {
                        // Get the titles and image paths from the database.
                        RecipeDataProvider recipeDataProvider = new RecipeDataProvider(convertSrcToBitmap(imagePath), titles);
                        // Add them to the listview.
                        adapter.add(recipeDataProvider);
                    } else {
                        // Get the titles from the database.
                        RecipeDataProvider recipeDataProvider = new RecipeDataProvider(titles);
                        // Add them to the listview.
                        adapter.add(recipeDataProvider);
                    }
                }
                while (cursor.moveToNext());
            }
        }
    }

    /**
     * Set an OnLongClick to delete items in the listview.
     */
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, long id) {
        // Ask if user really wants to delete this recipe.
        AlertDialog.Builder adb=new AlertDialog.Builder(RecipeListActivity.this);
        adb.setTitle("Delete?");
        adb.setMessage(R.string.delete_check);
        adb.setNegativeButton("Cancel", null);

        // If user presses ok, delete item.
        adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                sqLiteDatabase = recipeDatabaseHelper.getReadableDatabase();

                // Get the index of the title in the database.
                String index = ((TextView) view.findViewById(R.id.recipeTitle)).getText().toString();
                recipeDatabaseHelper.deleteTitle(index, sqLiteDatabase);
                Toast.makeText(RecipeListActivity.this, R.string.recipe_deleted, Toast.LENGTH_SHORT).show();

                // Go back to main screen.
                Intent mainIntent = new Intent(RecipeListActivity.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });
        adb.show();
        return true;
    }

    /**
     * Set an OnClick to view the whole recipe.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        sqLiteDatabase = recipeDatabaseHelper.getReadableDatabase();
        // Get the index of the title in the database, use it to get the text/photo text.
        String index = ((TextView) view.findViewById(R.id.recipeTitle)).getText().toString();

        if (recipeDatabaseHelper.getRecipePhotoText(index, sqLiteDatabase) != null) {
            String photoPath = recipeDatabaseHelper.getRecipePhotoText(index, sqLiteDatabase);

            // Create Bundle.
            Bundle dataBundle = new Bundle();
            dataBundle.putString("id", index);
            dataBundle.putString("photoPath", photoPath);

            // Go to ShowRecipeActivity.
            Intent showIntent = new Intent(view.getContext(), ShowRecipeActivity.class);
            showIntent.putExtras(dataBundle);
            startActivity(showIntent);
        } else{
            String text = recipeDatabaseHelper.getRecipeText(index, sqLiteDatabase);

            // Create Bundle.
            Bundle dataBundle = new Bundle();
            dataBundle.putString("id", index);
            dataBundle.putString("text", text);

            // Go to ShowRecipeActivity.
            Intent showIntent = new Intent(view.getContext(), ShowRecipeActivity.class);
            showIntent.putExtras(dataBundle);
            startActivity(showIntent);
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