package com.example.kimschuiten.mypersonalcookbook30;


import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PhotoRecipeActivity extends AppCompatActivity {

    EditText photoTitleEditText;
    EditText photoCategoryEditText;
    Button takePhotoButton;
    ImageView recipeImageView;

    static final int CAM_REQUEST = 1;
    Context context = this;
    String mCurrentPhotoPath;
    RecipeDatabaseHelper recipeDatabaseHelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_recipe);

        photoTitleEditText = (EditText) findViewById(R.id.titleEditText2);
        photoCategoryEditText = (EditText) findViewById(R.id.categoryEditText2);
        takePhotoButton = (Button) findViewById(R.id.takePhoto);
        recipeImageView = (ImageView) findViewById(R.id.recipePictureImageView);

        Intent photoIntent = getIntent();
    }

    public void HomeButtonClick(View view) {
        // Go back to main screen
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }

    public void takePhotoClick(View view) {
        // Start camera intent
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // Ensure that there's a camera activity to handle the intent
        if(cameraIntent.resolveActivity(getPackageManager()) != null){
            // Create the file where the photo should go
            File photo = null;
            try{
                photo = createImageFile();
            }catch (IOException ex){
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if(photo != null){
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
                startActivityForResult(cameraIntent, CAM_REQUEST);
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Get the image and put it in the ImageView
        recipeImageView.setImageURI(Uri.parse(mCurrentPhotoPath));

    }

    /*
    Create a folder where the photos will be stored.
    Also a new file name for each new photo
   */
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        Log.e("PHOTO PATH1", mCurrentPhotoPath);

        return image;
    }

    /*
    Save title, category en text to Recipe Object
     */
    public void saveRecipeButtonClick(View view){
        // Get the information from the EditTexts
        String title = photoTitleEditText.getText().toString();
        String category = photoCategoryEditText.getText().toString();
        // Get the path of the photo as a String
        String photo = String.valueOf(Uri.parse(mCurrentPhotoPath));

        // Initialize recipe db object + sqlitedatabase object
        recipeDatabaseHelper = new RecipeDatabaseHelper(context);
        sqLiteDatabase = recipeDatabaseHelper.getWritableDatabase();

        // Perform database insertion
/*
        recipeDatabaseHelper.addRecipeInfo(title, category, photo, sqLiteDatabase);
*/

        // TODO: create a separate column for photo recipes????? Or make an if/else statement in which
        // you get a query for the text when it is a text recipe and else you get the photopath and
        // convert that to Bitmap????

        // Close the database
        Toast.makeText(getBaseContext(), "Recipe Saved!", Toast.LENGTH_SHORT).show();
        recipeDatabaseHelper.close();
    }

}
