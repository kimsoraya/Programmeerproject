// My Personal Cookbook
// Kim Schuiten

package nl.mprog.kimschuiten.mypersonalcookbook30;

/**
 * In this activity the user gets to choose whether he wants to create a recipe by writing the
 * text himself (a Text Recipe), or if he wants to create a recipe by taking a picture of an already
 * existing recipe (a Photo Recipe).
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class TextOrPhotoActivity extends AppCompatActivity {

    ImageView addText;
    ImageView addPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_or_photo);

        addText = (ImageView) findViewById(R.id.documentImageView);
        addPhoto = (ImageView) findViewById(R.id.photoImageView);

        Intent textPhotoIntent = getIntent();
    }

    /**
     * onClickListener to make a recipe by writing it by hand.
     */
    public void makeTextRecipeButton(View view) {
        // Open new activity in which you can write a recipe.
        Intent textIntent = new Intent(this, TextRecipeActivity.class);
        startActivity(textIntent);
    }

    /**
     * onClickListener to make a recipe by taking a picture.
     */
    public void makePhotoRecipeButton(View view) {
        // Open new activity in which you can write a recipe.
        Intent photoIntent = new Intent(this, PhotoRecipeActivity.class);
        startActivity(photoIntent);
    }

    /**
     * Go back to MainActivity.
     */
    public void HomeButtonClick(View view) {
        // Go back to main screen.
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }
}