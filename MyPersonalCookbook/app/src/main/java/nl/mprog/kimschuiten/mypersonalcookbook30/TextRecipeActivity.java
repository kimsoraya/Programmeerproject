// My Personal Cookbook
// Kim Schuiten

package nl.mprog.kimschuiten.mypersonalcookbook30;

/**
 * User can create a recipe by writing a title and a recipe text. It is also possible to add a
 * picture of your dish. This picture will be displayed in the recipe list next to the title.
 */
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TextRecipeActivity extends AppCompatActivity {

    EditText textTitleEditText;
    EditText textTextEditText;
    Button saveRecipeButton;
    ImageView showPhotoImageView;

    Context context = this;
    RecipeDatabaseHelper recipeDatabaseHelper;
    SQLiteDatabase sqLiteDatabase;

    static final int CAM_REQUEST = 1;
    private static int RESULT_LOAD_IMG = 2;
    String mCurrentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_recipe);

        textTitleEditText = (EditText) findViewById(R.id.titleEditText);
        textTextEditText = (EditText) findViewById(R.id.recipeText);
        saveRecipeButton = (Button) findViewById(R.id.saveButton);
        showPhotoImageView = (ImageView) findViewById(R.id.recipePhotoImageView);

        Intent textIntent = getIntent();
    }

    /**
     * Open pop up menu to choose between photo or picture gallery.
     */
    public void onAddPictureClick(View view) {
        PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.inflate(R.menu.picture_menu);
        popupMenu.show();

        // Set onclicklistener for the two options.
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Open camera when clicked on "Take Photo".
                if (item.getItemId() == R.id.item1) {
                    // Start camera intent
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    // Ensure that there's a camera activity to handle the intent.
                    if (cameraIntent.resolveActivity(getPackageManager()) != null) {
                        // Create the file where the photo should go.
                        File photo = null;
                        try {
                            photo = createImageFile();
                        } catch (IOException ex) {
                            // Error occurred while creating the File.
                        }
                        // Continue only if the File was successfully created.
                        if (photo != null) {
                            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
                            startActivityForResult(cameraIntent, CAM_REQUEST);
                        }
                    }
                }
                // Go to gallery when clicked on "Gallery".
                if (item.getItemId() == R.id.item2){
                    // Create intent to open Gallery.
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    // Start the Intent.
                    startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
                }
                return false;
            }
        });
    }

    /**
     * In this method the images are loaded into the screen by retrieveing the requestCode,
     * resultCode and the data that were send with the image intent.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            // When an Image is picked.
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data.
                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // Get the cursor.
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row.
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                mCurrentPhotoPath = cursor.getString(columnIndex);
                cursor.close();
                // Set the Image in ImageView after decoding the String.
                showPhotoImageView.setImageBitmap(BitmapFactory
                        .decodeFile(mCurrentPhotoPath));
            }
            // When a photo is taked with the camera.
            else if (requestCode == CAM_REQUEST && resultCode == RESULT_OK) {
                // Get the image and put it in the ImageView.
                showPhotoImageView.setImageURI(Uri.parse(mCurrentPhotoPath));

            } else{
                Toast.makeText(this, R.string.no_image,
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, R.string.something_wrong, Toast.LENGTH_LONG)
                    .show();
        }
    }

    /**
     * Create a folder where the photos will be stored.
     * Also a new file name for each new photo.
     */
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        // Save a file: create photo path
        mCurrentPhotoPath = image.getAbsolutePath();
        Log.e("PHOTO PATH1", mCurrentPhotoPath);

        return image;
    }
    /**
     * Save title, category en text to sqlite database.
     */
    public void saveRecipeButtonClick(View view){
        // Get the information from the EditTexts.
        String title = textTitleEditText.getText().toString();
        String text = textTextEditText.getText().toString();

        if (mCurrentPhotoPath != null){
            // Get the path of the photo as a String.
            String photo = String.valueOf(Uri.parse(mCurrentPhotoPath));

            // Initialize recipe db object + sqlitedatabase object.
            recipeDatabaseHelper = new RecipeDatabaseHelper(context);
            sqLiteDatabase = recipeDatabaseHelper.getWritableDatabase();

            // Perform database insertion.
            recipeDatabaseHelper.addRecipeInfo(text, title, photo, sqLiteDatabase);
        }
        else{
            // Initialize recipe db object + sqlitedatabase object.
            recipeDatabaseHelper = new RecipeDatabaseHelper(context);
            sqLiteDatabase = recipeDatabaseHelper.getWritableDatabase();

            // Perform database insertion.
            recipeDatabaseHelper.addRecipeInfoTwo(text, title, sqLiteDatabase);
        }
        // Close the database.
        Toast.makeText(getBaseContext(), R.string.recipe_saved, Toast.LENGTH_SHORT).show();
        recipeDatabaseHelper.close();

        // Empty the fields.
        textTitleEditText.setText("");
        textTextEditText.setText("");
        showPhotoImageView.setImageResource(0);
    }

    /**
     * Go back to main activity.
     */
    public void HomeButtonClick(View view) {
        // Go back to main screen.
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }
}