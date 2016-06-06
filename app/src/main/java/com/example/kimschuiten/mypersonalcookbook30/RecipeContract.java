package com.example.kimschuiten.mypersonalcookbook30;

/**
 * Contract class to act as a container for all the constants needed for working with the database.
 * Defines overall structure of database
 */
public class RecipeContract {

    public static abstract class NewRecipeInfo{
        // Define needed constants
        public static final String RECIPE_TITLE = "title";
        public static final String RECIPE_CATEGORY = "category";
        public static final String TABLE_NAME = "recipe_info";
    }
}
