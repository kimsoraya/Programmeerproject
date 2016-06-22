package com.example.kimschuiten.mypersonalcookbook30;

import android.graphics.Bitmap;

/**
 * Recipe object to provide the data needed for the custom recipe adapter
 */
public class RecipeDataProvider {
    private Bitmap recipeImageResource;
    private String recipeTitles;

    public Bitmap getRecipeImageResource() {
        return recipeImageResource;
    }

    public void setRecipeImageResource(Bitmap recipeImageResource) {
        this.recipeImageResource = recipeImageResource;
    }

    public String getRecipeTitles() {
        return recipeTitles;
    }

    public void setRecipeTitles(String recipeTitles) {
        this.recipeTitles = recipeTitles;
    }

    // Constructor
    public RecipeDataProvider(Bitmap recipeImageResource, String recipeTitles){
        this.recipeTitles = recipeTitles;
        this.recipeImageResource = recipeImageResource;
    }

    public RecipeDataProvider(String recipeTitles) {
        this.recipeTitles = recipeTitles;
    }
}
