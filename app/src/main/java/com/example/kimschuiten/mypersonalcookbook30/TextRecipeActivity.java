package com.example.kimschuiten.mypersonalcookbook30;

import android.content.Intent;
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
        }
    }

}
