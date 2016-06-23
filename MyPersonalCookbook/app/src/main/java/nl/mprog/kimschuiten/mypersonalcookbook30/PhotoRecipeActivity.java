// My Personal Cookbook
// Kim Schuiten

package nl.mprog.kimschuiten.mypersonalcookbook30;

/**
 * User gets to create a Photo Recipe. A photo can be taken with the camere or choosen from the gallery.
 * It is also possible to add an extra image (just like you can in the Text Recipe) which will be
 * displayed in the recipe listview.
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

public class PhotoRecipeActivity extends AppCompatActivity {

    EditText photoTitleEditText;
    Button takePhotoButton;
    ImageView extraPictureButton;
    ImageView recipeImageView;
    ImageView extraPictureImageView;

    static final int CAM_REQUEST = 1;
    private static int RESULT_LOAD_IMG = 2;
    static final int CAM_REQUEST2 = 3;
    private static int RESULT_LOAD_IMG2 = 4;
    Context context = this;
    String mCurrentPhotoPath;
    String mDishPicturePath;
    RecipeDatabaseHelper recipeDatabaseHelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_recipe);

        photoTitleEditText = (EditText) findViewById(R.id.titleEditText2);
        takePhotoButton = (Button) findViewById(R.id.takePhoto);
        recipeImageView = (ImageView) findViewById(R.id.recipePictureImageView);
        extraPictureButton = (ImageView) findViewById(R.id.addExtraPictureButton);
        extraPictureImageView = (ImageView) findViewById(R.id.showExtraPicture);

        Intent photoIntent = getIntent();
    }

    /**
     * Choose an extra image that will be shown in the listview.
     * Open pop up menu to choose between photo or picture gallery.
     */
    public void onAddPictureClick(View view) {
        // Create popup menu
        PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.inflate(R.menu.picture_menu);
        popupMenu.show();

        // Set onclicklistener for the two options.
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Open camera when clicked on "Take Photo".
                if (item.getItemId() == R.id.item1) {
                    // Start camera intent.
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    // Ensure that there's a camera activity to handle the intent.
                    if (cameraIntent.resolveActivity(getPackageManager()) != null) {
                        // Create the file where the photo should go.
                        File photo = null;
                        try {
                            photo = createImageFileTwo();
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
                else if (item.getItemId() == R.id.item2) {
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
     * User gets to take an image of his recipe text. User can choose between an image from the
     * gallery or take a photo
     */
    public void takePhotoClick(View view) {
        PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.inflate(R.menu.gallery_menu);
        popupMenu.show();

        // Set onclicklistener for the two options.
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Open camera when clicked on "Take Photo".
                if (item.getItemId() == R.id.item1_1) {
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
                            startActivityForResult(cameraIntent, CAM_REQUEST2);
                        }
                    }
                }
                // Go to gallery when clicked on "Gallery".
                else if (item.getItemId() == R.id.item2_1) {
                    // Create intent to open Gallery.
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    // Start the Intent.
                    startActivityForResult(galleryIntent, RESULT_LOAD_IMG2);
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
            // When an Image is picked for the extra dish picture.
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data
                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // Get the cursor.
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row.
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                mDishPicturePath = cursor.getString(columnIndex);
                cursor.close();

                // Set the Image in ImageView after decoding the String.
                extraPictureImageView.setImageBitmap(BitmapFactory
                        .decodeFile(mDishPicturePath));
            }
            // When a photo is taken with the camera for the extra dish picture.
            else if (requestCode == CAM_REQUEST && resultCode == RESULT_OK) {
                // Get the image and put it in the ImageView
                extraPictureImageView.setImageURI(Uri.parse(mDishPicturePath));
            }
            // When an Image is picked from the gallery as recipe image.
            else if (requestCode == RESULT_LOAD_IMG2 && resultCode == RESULT_OK
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
                recipeImageView.setImageBitmap(BitmapFactory
                        .decodeFile(mCurrentPhotoPath));
            }
            // When a photo for the recipe image is taken with the camera.
            else if (requestCode == CAM_REQUEST2 && resultCode == RESULT_OK) {
                // Get the image and put it in the ImageView.
                recipeImageView.setImageURI(Uri.parse(mCurrentPhotoPath));

            } else{
                Toast.makeText(this, R.string.no_image,
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, R.string.something_wrong, Toast.LENGTH_LONG)
                    .show();
            System.out.println(e.getClass());
        }
    }

    /**
     * Create a folder where the photos for the recipe text will be stored.
     * Also a new file name for each new photo.
     */
    private File createImageFile() throws IOException {
        // Create an image file name.
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for later use.
        mCurrentPhotoPath = image.getAbsolutePath();
        Log.e("PHOTO PATH1", mCurrentPhotoPath);

        return image;
    }

    /**
     * Create a folder where the extra photos of the recipe will be stored.
     * Also a new file name for each new photo.
     */
    private File createImageFileTwo() throws IOException {
        // Create an image file name.
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for later use.
        mDishPicturePath = image.getAbsolutePath();
        Log.e("PHOTO PATH1", mDishPicturePath);

        return image;
    }

    /**
     * Save title, category en text to sqlite database.
     */
    public void saveRecipeButtonClick(View view) {
        // Initialize recipe db object + sqlitedatabase object.
        recipeDatabaseHelper = new RecipeDatabaseHelper(context);
        sqLiteDatabase = recipeDatabaseHelper.getWritableDatabase();

        if (mCurrentPhotoPath == null) {
            Toast.makeText(this, "You need an image to continue!", Toast.LENGTH_LONG).show();
            return;
        } else {
            if (mDishPicturePath != null) {
                // Get the information from the EditText and the photopath.
                String title = photoTitleEditText.getText().toString();
                String photoRecipe = String.valueOf(Uri.parse(mCurrentPhotoPath));

                // Get the path of the dish photo as a String.
                String photo = String.valueOf(Uri.parse(mDishPicturePath));

                // Perform database insertion.
                recipeDatabaseHelper.addPhotoRecipeInfo(photoRecipe, title, photo, sqLiteDatabase);
            } else {
                // Get the information from the EditTexts.
                String title = photoTitleEditText.getText().toString();
                String photoRecipe = String.valueOf(Uri.parse(mCurrentPhotoPath));

                // Perform database insertion.
                recipeDatabaseHelper.addPhotoRecipeInfoTwo(photoRecipe, title, sqLiteDatabase);
                Log.d("DATABASE INSERTION 2", photoRecipe);
            }
                // Close the database.
                Toast.makeText(getBaseContext(), R.string.recipe_saved, Toast.LENGTH_SHORT).show();
                recipeDatabaseHelper.close();

                // Clear the fields.
                photoTitleEditText.setText("");
                recipeImageView.setImageResource(0);
                extraPictureImageView.setImageResource(0);

        }
    }

    /**
     * Go back to main activity
     */
    public void HomeButtonClick(View view) {
        // Go back to main screen
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }
}