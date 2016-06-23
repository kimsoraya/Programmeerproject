// My Personal Cookbook
// Kim Schuiten

package nl.mprog.kimschuiten.mypersonalcookbook30;

import android.graphics.Bitmap;

/**
 * Recipe object to provide the data needed for the custom recipe adapter.
 */
public class RecipeDataProvider {
    private Bitmap recipeImageResource;
    private String recipeTitles;

    /**
     * Getter for recipe image.
     */
    public Bitmap getRecipeImageResource() {
        return recipeImageResource;
    }

    /**
     * Setter for recipe image
     */
    public void setRecipeImageResource(Bitmap recipeImageResource) {
        this.recipeImageResource = recipeImageResource;
    }

    /**
     * Getter for recipe title.
     */
    public String getRecipeTitles() {
        return recipeTitles;
    }

    /**
     * Setter for recipe title.
     */
    public void setRecipeTitles(String recipeTitles) {
        this.recipeTitles = recipeTitles;
    }

    /**
     * Constructor
     */
    public RecipeDataProvider(Bitmap recipeImageResource, String recipeTitles){
        this.recipeTitles = recipeTitles;
        this.recipeImageResource = recipeImageResource;
    }

    /**
     * Constructor for listitem without image.
     */
    public RecipeDataProvider(String recipeTitles) {
        this.recipeTitles = recipeTitles;
    }
}
