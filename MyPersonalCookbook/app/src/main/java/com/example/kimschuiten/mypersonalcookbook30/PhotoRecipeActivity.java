package com.example.kimschuiten.mypersonalcookbook30;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;

public class PhotoRecipeActivity extends AppCompatActivity {

    EditText photoTitleEditText;
    EditText photoCategoryEditText;
    Button takePhotoButton;
    ImageView recipeImageView;

    static final int CAM_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_recipe);

        photoTitleEditText = (EditText) findViewById(R.id.titleEditText2);
        photoCategoryEditText = (EditText) findViewById(R.id.categoryEditText2);
        takePhotoButton = (Button) findViewById(R.id.takePhoto);
        recipeImageView = (ImageView) findViewById(R.id.recipePictureImageView);

        Intent photoIntent = getIntent();


        // setOnClickListener for take photo button, launch camera app
        takePhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start camera intent
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                // Place the picture in the File folder
                File file = getFile();
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));

                startActivityForResult(cameraIntent, CAM_REQUEST);
            }
        });


    }

    /*
    Create a folder where the photos will be stored.
     */
    private File getFile(){
        File folder = new File("sdcard/camera_app");

        // Check if folder exists, if not, make it
        if(!folder.exists()){
            folder.mkdir();
        }

        // Set up file inside the folder to save the image
        File image_file = new File(folder, "cam_image.jpg");

        return image_file;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Get the image and put it in the ImageView
        String path = "sdcard/camera_app/cam_image.jpg";

        recipeImageView.setImageDrawable(Drawable.createFromPath(path));

    }

    public void HomeButtonClick(View view) {
        // Go back to main screen
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }


    /*
    Load picture that was taken in the Take Photo class.
     */

    /*
    Save title, category and photo to Recipe Object
     */
}
