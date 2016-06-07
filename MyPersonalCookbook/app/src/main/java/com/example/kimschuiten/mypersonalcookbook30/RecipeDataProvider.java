package com.example.kimschuiten.mypersonalcookbook30;

/**
 * Created by kimschuiten on 03-06-16.
 */
public class RecipeDataProvider {
    private int recipeImageResource;
    private String recipeTitles;
    private String recipeCategories;

    public int getRecipeImageResource() {
        return recipeImageResource;
    }

    public void setRecipeImageResource(int recipeImageResource) {
        this.recipeImageResource = recipeImageResource;
    }

    public String getRecipeTitles() {
        return recipeTitles;
    }

    public void setRecipeTitles(String recipeTitles) {
        this.recipeTitles = recipeTitles;
    }

    public String getRecipeCategories() {
        return recipeCategories;
    }

    public void setRecipeCategories(String recipeCategories) {
        this.recipeCategories = recipeCategories;
    }

    // Constructor
    public RecipeDataProvider(int recipeImageResource, String recipeTitles, String recipeCategories){
      /*  this.setRecipeImageResource(recipeImageResource);
        this.setRecipeTitles(recipeTitles);*/
        this.recipeTitles = recipeTitles;
        this.recipeImageResource = recipeImageResource;
        this.recipeCategories = recipeCategories;
    }







/*
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
    }*/
}
