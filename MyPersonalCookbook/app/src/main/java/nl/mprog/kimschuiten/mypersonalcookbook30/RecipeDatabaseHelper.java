// My Personal Cookbook
// Kim Schuiten

package nl.mprog.kimschuiten.mypersonalcookbook30;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Class for the database operations.
 */
public class RecipeDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "RECIPEINFO.DB";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_QUERY =
            "CREATE TABLE " + RecipeContract.NewRecipeInfo.TABLE_NAME + "(" +
                    RecipeContract.NewRecipeInfo.RECIPE_TEXT + " TEXT," +
                    RecipeContract.NewRecipeInfo.RECIPE_TITLE + " TEXT," +
                    RecipeContract.NewRecipeInfo.RECIPE_PHOTO_STYLE + " TEXT," +
                    RecipeContract.NewRecipeInfo.RECIPE_PHOTO + " TEXT);";


    // Constructor.
    public RecipeDatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.e("DATABASE OPERATIONS", "Database created /opened...");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("DATABASE OPERATIONS", "TEST");
        // Create a table inside the database.
        db.execSQL(CREATE_QUERY);
        Log.e("DATABASE OPERATIONS", "Table created");
    }

    /**
     * Define a method for inserting the data.
     */
    public void addRecipeInfo(String text, String title, String photo, SQLiteDatabase db){
        // Create object of contentValues to create map values.
        ContentValues contentValues = new ContentValues();
        // Specify key and the data.
        contentValues.put(RecipeContract.NewRecipeInfo.RECIPE_TITLE, title);
        contentValues.put(RecipeContract.NewRecipeInfo.RECIPE_PHOTO, photo);
        contentValues.put(RecipeContract.NewRecipeInfo.RECIPE_TEXT, text);

        // Put all this information in the database.
        db.insert(RecipeContract.NewRecipeInfo.TABLE_NAME, null, contentValues);
        Log.e("DATABASE OPERATIONS", "One row is inserted");

    }

    /**
     * Define a method for inserting data without a dish picture.
     */
    public void addRecipeInfoTwo(String text, String title, SQLiteDatabase db){
        // Create object of contentValues to create map values.
        ContentValues contentValues = new ContentValues();
        // Specify key and the data.
        contentValues.put(RecipeContract.NewRecipeInfo.RECIPE_TITLE, title);
        contentValues.put(RecipeContract.NewRecipeInfo.RECIPE_TEXT, text);

        // Put all this information in the database.
        db.insert(RecipeContract.NewRecipeInfo.TABLE_NAME, null, contentValues);
        Log.e("DATABASE OPERATIONS", "One row is inserted");
    }
    /**
     * Define a method for inserting the data with photo recipe.
     */
    public void addPhotoRecipeInfo(String recipePhoto, String title, String photo, SQLiteDatabase db){
        // Create object of contentValues to create map values.
        ContentValues contentValues = new ContentValues();
        // Specify key and the data.
        contentValues.put(RecipeContract.NewRecipeInfo.RECIPE_TITLE, title);
        contentValues.put(RecipeContract.NewRecipeInfo.RECIPE_PHOTO, photo);
        contentValues.put(RecipeContract.NewRecipeInfo.RECIPE_PHOTO_STYLE, recipePhoto);

        // Put all this information in the database.
        db.insert(RecipeContract.NewRecipeInfo.TABLE_NAME, null, contentValues);
        Log.e("DATABASE OPERATIONS", "One row is inserted");
    }

    /**
     * Define a method for inserting the data with photo recipe
     * Without Dish picture.
     */
    public void addPhotoRecipeInfoTwo(String recipePhoto, String title, SQLiteDatabase db){
        // Create object of contentValues to create map values.
        ContentValues contentValues = new ContentValues();
        // Specify key and the data.
        contentValues.put(RecipeContract.NewRecipeInfo.RECIPE_TITLE, title);
        contentValues.put(RecipeContract.NewRecipeInfo.RECIPE_PHOTO_STYLE, recipePhoto);

        // Put all this information in the database.
        db.insert(RecipeContract.NewRecipeInfo.TABLE_NAME, null, contentValues);
        Log.e("DATABASE OPERATIONS", "One row is inserted");
    }

    /**
     * Create a cursor to return the recipe titles and their images to the recipe listview.
     */
    public Cursor getRecipeInfo(SQLiteDatabase db){
        // Create object of Cursor.
        Cursor cursor;

        // Create some projections: the needed column names.
        String[] projections = {RecipeContract.NewRecipeInfo.RECIPE_TITLE,
                RecipeContract.NewRecipeInfo.RECIPE_PHOTO};
        cursor = db.query(RecipeContract.NewRecipeInfo.TABLE_NAME, projections, null, null, null, null, null);

        return cursor;
    }

    /**
     * This method is called when the user wants to view a photo recipe.
     */
    public String getRecipePhotoText(String photoRecipeTitle, SQLiteDatabase db){
        // Create object of Cursor.
        Cursor fourthCursor;

        // Write query to retrieve data using the recipe title.
        String selectQuery = "SELECT photo_recipe FROM " + RecipeContract.NewRecipeInfo.TABLE_NAME +
                " WHERE " + RecipeContract.NewRecipeInfo.RECIPE_TITLE + " = '" + photoRecipeTitle
                + "'";

        fourthCursor = db.rawQuery(selectQuery, null);
        String recipePhoto = null;
        if (fourthCursor.moveToFirst()) {
            recipePhoto = fourthCursor.getString(fourthCursor.getColumnIndexOrThrow
                    (RecipeContract.NewRecipeInfo.RECIPE_PHOTO_STYLE));
            fourthCursor.close();
        }
        return recipePhoto;
    }

    /**
     * This method is called when the user wants to view a text recipe.
     */
    public String getRecipeText(String recipeTitle, SQLiteDatabase db){
        // Create object of Cursor.
        Cursor secondCursor;

        // Write query to retrieve data using the recipe title.
        String selectQuery = "SELECT text FROM " + RecipeContract.NewRecipeInfo.TABLE_NAME +
                " WHERE " + RecipeContract.NewRecipeInfo.RECIPE_TITLE + " = \"" + recipeTitle + "\"";

        secondCursor = db.rawQuery(selectQuery, null);
        if (secondCursor != null){
            secondCursor.moveToFirst();
        }
        // Return a String with the text of the recipe.
        String recipeText = secondCursor.getString(secondCursor.getColumnIndexOrThrow
                (RecipeContract.NewRecipeInfo.RECIPE_TEXT));

        return recipeText;
    }

    /**
     * This method is called when the user wants to delete a recipe.
     */
    public void deleteTitle(String recipeTitle, SQLiteDatabase db) {
        // Create object of cursor
        Cursor fifthCursor;

        // Write query to delete a recipe, using the recipe title to find the correct row.
        String deleteQuery = "DELETE FROM " + RecipeContract.NewRecipeInfo.TABLE_NAME + " WHERE " +
                RecipeContract.NewRecipeInfo.RECIPE_TITLE + " = \"" + recipeTitle + "\"";

        fifthCursor = db.rawQuery(deleteQuery, null);
        if(fifthCursor != null){
            fifthCursor.moveToFirst();
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}