package com.example.kimschuiten.mypersonalcookbook30;

import android.graphics.Bitmap;

/**
 * Created by kimschuiten on 03-06-16.
 */
public class RecipeDataProvider {
    private Bitmap recipeImageResource;
    private String recipeTitles;

/*
    private String recipeCategories;
*/

/*    public RecipeDataProvider(){

    }*/

 /*   String imageSrc;

    public void setUid(String uid) {
        this.uid = uid;
    }

    private String uid;

    public int getListItemPosition() {
        return listItemPosition;
    }

    public void setListItemPosition(int listItemPosition) {
        this.listItemPosition = listItemPosition;
    }

    int listItemPosition;

    public boolean isHaveImage() {
        return haveImage;
    }

    public void setHaveImage(boolean haveImage) {
        this.haveImage = haveImage;
    }

    boolean haveImage;

    public String getSubtext() {
        return subtext;
    }

    public void setSubtext(String subtext) {
        this.subtext = subtext;
    }

    String subtext;
    boolean status;*/


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

  /*  public String getRecipeCategories() {
        return recipeCategories;
    }

    public void setRecipeCategories(String recipeCategories) {
        this.recipeCategories = recipeCategories;
    }*/

    /*public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
*/

    // Constructor
    public RecipeDataProvider(Bitmap recipeImageResource, String recipeTitles/*String recipeCategories*/){
      /*  this.setRecipeImageResource(recipeImageResource);
        this.setRecipeTitles(recipeTitles);*/
        this.recipeTitles = recipeTitles;
        this.recipeImageResource = recipeImageResource;



/*
        this.recipeCategories = recipeCategories;
*/
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
