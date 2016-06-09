package com.example.kimschuiten.mypersonalcookbook30;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TextRecipeActivity extends AppCompatActivity {

    EditText textTitleEditText;
    EditText textCategoryEditText;
    EditText textTextEditText;
    Button saveRecipeButton;
    ImageView showPhotoImageView;


    Context context = this;
    RecipeDatabaseHelper recipeDatabaseHelper;
    SQLiteDatabase sqLiteDatabase;

    static final int CAM_REQUEST = 1;
    String mCurrentPhotoPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_recipe);

        textTitleEditText = (EditText) findViewById(R.id.titleEditText);
        textCategoryEditText = (EditText) findViewById(R.id.categoryEditText);
        textTextEditText = (EditText) findViewById(R.id.recipeText);
        saveRecipeButton = (Button) findViewById(R.id.saveButton);
        showPhotoImageView = (ImageView) findViewById(R.id.recipePhotoImageView);


        Intent textIntent = getIntent();

    }

    /*
   Add a photo of your dish
    */
    public void onAddPictureClick(View view) {
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
        showPhotoImageView.setImageURI(Uri.parse(mCurrentPhotoPath));


       /* // Show thumbnail of photo in the activity
        if (requestCode == CAM_REQUEST && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            showPhotoImageView.setImageBitmap(imageBitmap);
        }*/
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
        // Get the information from the EditText
        String title = textTitleEditText.getText().toString();
        String category = textCategoryEditText.getText().toString();
        // Get the path of the photo as a String
        String photo = String.valueOf(Uri.parse(mCurrentPhotoPath));

        // Initialize recipe db object + sqlitedatabase object
        recipeDatabaseHelper = new RecipeDatabaseHelper(context);
        sqLiteDatabase = recipeDatabaseHelper.getWritableDatabase();

        // Perform database insertion
        recipeDatabaseHelper.addRecipeInfo(title, category, photo, sqLiteDatabase);

        // Close the database
        Toast.makeText(getBaseContext(), "Recipe Saved!", Toast.LENGTH_SHORT).show();
        recipeDatabaseHelper.close();

  /*      // Send the information to the listview in CategoryActivity
        CategoryActivity.this.recipeTitles.add(title);
        // We notify the data model is changed
        MainActivity.this.adapter.notifyDataSetChanged();
        String category = textCategoryEditText.getText().toString();
        String text = textTextEditText.getText().toString();
        // Define filenames
        String fileName = "saved_titles";
        String fileName2 = "saved_categories";
        String fileName3 = "saved_texts";
        // Create an object of fileoutputstream
        try {
            FileOutputStream fileOutputStream = openFileOutput(fileName, MODE_PRIVATE);
            fileOutputStream.write(title.getBytes());
            fileOutputStream.close();
            Toast.makeText(getApplicationContext(), "Recipe Saved!", Toast.LENGTH_SHORT).show();
            textTitleEditText.setText("");
            textCategoryEditText.setText("");
            textTextEditText.setText("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public void HomeButtonClick(View view) {
        // Go back to main screen
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }
}