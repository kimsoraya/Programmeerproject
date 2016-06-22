package com.example.kimschuiten.mypersonalcookbook30;

/**
 * Contract class to act as a container for all the constants needed for working with the database.
 * Defines overall structure of database
 */
public class RecipeContract {

    /**
     * Define needed constants
     **/
    public static abstract class NewRecipeInfo {
        // column names
        public static final String RECIPE_TITLE = "title";
        public static final String RECIPE_PHOTO = "photo";
        public static final String RECIPE_TEXT = "text";
        public static final String RECIPE_PHOTO_STYLE = "photo_recipe";

        // Table name
        public static final String TABLE_NAME = "recipe_info";
    }
}
