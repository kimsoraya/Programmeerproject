package com.example.kimschuiten.mypersonalcookbook30;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Class for the database operations
 */
public class RecipeDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "RECIPEINFO.DB";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_QUERY =
            "CREATE TABLE " + RecipeContract.NewRecipeInfo.TABLE_NAME + "(" +
                    RecipeContract.NewRecipeInfo.RECIPE_TEXT + " Text," +
                    RecipeContract.NewRecipeInfo.RECIPE_TITLE + " TEXT," +
                    RecipeContract.NewRecipeInfo.RECIPE_CATEGORY + " TEXT," +
                    RecipeContract.NewRecipeInfo.RECIPE_PHOTO + " TEXT);";


    // Constructor
    public RecipeDatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.e("DATABASE OPERATIONS", "Database created /opened...");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("DATABASE OPERATIONS", "TEST");
        // Create a table inside the database
        db.execSQL(CREATE_QUERY);
        Log.e("DATABASE OPERATIONS", "Table created");
    }

    /*
    Define a method for inserting the data
     */
    public void addRecipeInfo(String text, String title, String category, String photo, SQLiteDatabase db){
        // Create object of contentValues to create map values
        ContentValues contentValues = new ContentValues();
        // Specify key and the data
        contentValues.put(RecipeContract.NewRecipeInfo.RECIPE_TITLE, title);
        contentValues.put(RecipeContract.NewRecipeInfo.RECIPE_CATEGORY, category);
        contentValues.put(RecipeContract.NewRecipeInfo.RECIPE_PHOTO, photo);
        contentValues.put(RecipeContract.NewRecipeInfo.RECIPE_TEXT, text);

        // Put all this information in the database
        db.insert(RecipeContract.NewRecipeInfo.TABLE_NAME, null, contentValues);
        Log.e("DATABASE OPERATIONS", "One row is inserted");

    }

    /*
    Read table data from the database
     */
    public Cursor getRecipeInfo(SQLiteDatabase db){
        // Create object of Cursor
        Cursor cursor;

        // Create some projections: the needed column names.
        String[] projections = {RecipeContract.NewRecipeInfo.RECIPE_TITLE,
                RecipeContract.NewRecipeInfo.RECIPE_PHOTO, RecipeContract.NewRecipeInfo.RECIPE_TEXT};

/*
        String selectQuery = "SELECT RECIPE_TEXT FROM " + RecipeContract.NewRecipeInfo.TABLE_NAME +
                " WHERE " + RecipeContract.NewRecipeInfo.RECIPE_TITLE;
*/
/*
        mCursor = db.rawQuery(selectQuery, null );
*/
        cursor = db.query(RecipeContract.NewRecipeInfo.TABLE_NAME, projections, null, null, null, null, null);

        return cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}