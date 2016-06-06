package com.example.kimschuiten.mypersonalcookbook30;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TextRecipeActivity extends AppCompatActivity {

    EditText textTitleEditText;
    EditText textCategoryEditText;
    EditText textTextEditText;
    Button saveRecipeButton;

    Context context = this;
    RecipeDatabaseHelper recipeDatabaseHelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_recipe);

        textTitleEditText = (EditText) findViewById(R.id.titleEditText);
        textCategoryEditText = (EditText) findViewById(R.id.categoryEditText);
        textTextEditText = (EditText) findViewById(R.id.recipeText);
        saveRecipeButton = (Button) findViewById(R.id.saveButton);

        Intent textIntent = getIntent();

    }

    /*
    Save title, category en text to Recipe Object
     */
    public void saveRecipeButtonClick(View view){
        // Get the information from the EditText
        String title = textTitleEditText.getText().toString();
        String category = textCategoryEditText.getText().toString();

        // Initialize recipe db object + sqlitedatabase object
        recipeDatabaseHelper = new RecipeDatabaseHelper(context);
        sqLiteDatabase = recipeDatabaseHelper.getWritableDatabase();

        // Perform database insertion
        recipeDatabaseHelper.addRecipeInfo(title, category, sqLiteDatabase);

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
