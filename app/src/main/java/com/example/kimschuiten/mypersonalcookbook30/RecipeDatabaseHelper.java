package com.example.kimschuiten.mypersonalcookbook30;

import android.content.ContentValues;
import android.content.Context;
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
            RecipeContract.NewRecipeInfo.RECIPE_TITLE + " TEXT," +
            RecipeContract.NewRecipeInfo.RECIPE_CATEGORY + " TEXT);";


    // Constructor
    public RecipeDatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.e("DATABASE OPERATIONS", "Database created /opened...");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a table inside the database
        db.execSQL(CREATE_QUERY);
        Log.e("DATABASE OPERATIONS", "Table created");
    }

    /*
    Define a method for inserting the data
     */
    public void addRecipeInfo(String title, String category, SQLiteDatabase db){
        // Create object of contentValues to create map values
        ContentValues contentValues = new ContentValues();
        // Specify key and the data
        contentValues.put(RecipeContract.NewRecipeInfo.RECIPE_TITLE, title);
        contentValues.put(RecipeContract.NewRecipeInfo.RECIPE_CATEGORY, category);

        // Put all this information in the database
        db.insert(RecipeContract.NewRecipeInfo.TABLE_NAME, null, contentValues);
        Log.e("DATABASE OPERATIONS", "One row is inserted");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
