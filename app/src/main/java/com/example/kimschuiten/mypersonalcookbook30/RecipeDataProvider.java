package com.example.kimschuiten.mypersonalcookbook30;

/**
 * Created by kimschuiten on 03-06-16.
 */
public class RecipeDataProvider {
    private int recipeImageResource;
    private String recipeTitles;

    // Constructor
    public RecipeDataProvider(int recipeImageResource, String recipeTitles){
        this.setRecipeImageResource(recipeImageResource);
        this.setRecipeTitles(recipeTitles);
    }

    public String getRecipeTitles() {
        return recipeTitles;
    }

    public void setRecipeTitles(String recipeTitles) {
        this.recipeTitles = recipeTitles;
    }

    public int getRecipeImageResource() {
        return recipeImageResource;
    }

    public void setRecipeImageResource(int recipeImageResource) {
        this.recipeImageResource = recipeImageResource;
    }
}
