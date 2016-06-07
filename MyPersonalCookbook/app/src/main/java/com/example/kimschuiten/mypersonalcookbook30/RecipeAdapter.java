package com.example.kimschuiten.mypersonalcookbook30;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kimschuiten on 03-06-16.
 */
public class RecipeAdapter extends ArrayAdapter {
    List list = new ArrayList();

    public RecipeAdapter(Context context, int resource) {
        super(context, resource);
    }

    static class DataHandler{
        ImageView image;
        TextView title;
    }

    /*
   Save recipe objects in an array list
     */
    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    /*
    Method to return each item in the row
     */
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    /*
    A method that will return each row of data
     */

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row = convertView;
        DataHandler handler;

        // Check if row is existing or not
        if (convertView == null){
            // Inflate the layout
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.single_list_item, parent, false);

            // Initialize all the variables available in the data handler
            handler = new DataHandler();
            handler.image = (ImageView) row.findViewById(R.id.recipeImage);
            handler.title = (TextView) row.findViewById(R.id.recipeTitle);

            // Attach each of the view components to a row
            row.setTag(handler);
        }
        // If row is already available, create row again
        else{
            handler = (DataHandler) row.getTag();
        }

        // Get each object available on the recipe data provider class
        RecipeDataProvider recipeDataProvider = (RecipeDataProvider) this.getItem(position);

        // Set the data resources
        handler.image.setImageResource(recipeDataProvider.getRecipeImageResource());
        handler.title.setText(recipeDataProvider.getRecipeTitles());

        // Return the row
        return row;
    }
}
