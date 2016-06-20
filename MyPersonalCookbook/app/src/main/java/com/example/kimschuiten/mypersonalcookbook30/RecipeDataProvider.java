package com.example.kimschuiten.mypersonalcookbook30;

import android.graphics.Bitmap;

/**
 * Created by kimschuiten on 03-06-16.
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
        if(recipeImageResource == null){
            this.recipeTitles = recipeTitles;
        }
        else {
            this.recipeTitles = recipeTitles;
            this.recipeImageResource = recipeImageResource;
        }
    }

    public RecipeDataProvider(String recipeTitles) {
        this.recipeTitles = recipeTitles;
    }
}
