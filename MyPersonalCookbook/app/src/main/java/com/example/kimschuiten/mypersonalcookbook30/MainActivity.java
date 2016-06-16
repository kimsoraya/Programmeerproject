package com.example.kimschuiten.mypersonalcookbook30;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button viewMenu;
    Button addRecipe;

    private ArrayAdapter<String> listAdapter;
    SQLiteDatabase sqLiteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewMenu = (Button) findViewById(R.id.menuButton);
        addRecipe = (Button) findViewById(R.id.addButton);

        Intent mainIntent = getIntent();

        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        RecipeDatabaseHelper recipeDatabaseHelper = new RecipeDatabaseHelper(MainActivity.this);
        sqLiteDatabase = recipeDatabaseHelper.getReadableDatabase();

        String[] spinnerLists = recipeDatabaseHelper.getCategories(sqLiteDatabase);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item, spinnerLists);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                return;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            // Open new activity with all the recipe items
            Intent viewCategoryIntent = new Intent(this, CategoryActivity.class);
            startActivity(viewCategoryIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*
    onClickListener to show the menu options
    */
    public void menuButtonClick(View view) {
        // Open new activity with all the recipe items
        Intent viewCategoryIntent = new Intent(this, CategoryActivity.class);
        startActivity(viewCategoryIntent);
    }

    /*
    onClickListener for add recipe button
    */
    public void addButtonClick(View view) {
        // Open new activity in which you can choose text or photo recipe
        Intent textPhotoIntent = new Intent(this, TextOrPhotoActivity.class);
        startActivity(textPhotoIntent);

    }
}