package com.example.kimschuiten.mypersonalcookbook30;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kimschuiten on 03-06-16.
 */
public class RecipeAdapter extends BaseAdapter {
    List<RecipeDataProvider> _data;
    Context _c;

    public RecipeAdapter(List<RecipeDataProvider> getData, Context context) {
        _data = getData;
        _c = context;
    }

    public RecipeAdapter(Context applicationContext, int single_list_item) {

    }

    static class DataHandler {
        ImageView image;
        TextView title;
    }


    @Override
    public int getCount() {
        return _data.size();
    }

    @Override
    public Object getItem(int position) {
        return _data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        DataHandler handler;

        // Check if row is existing or not
        if (view == null) {
            // Inflate the layout
            LayoutInflater li = (LayoutInflater) _c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = li.inflate(R.layout.single_list_item, null);
        } else {
            handler = (DataHandler) view.getTag();
        }

        // Initialize all the variables available in the data handler
        handler = new DataHandler();
        handler.image = (ImageView) view.findViewById(R.id.recipeImage);
        handler.title = (TextView) view.findViewById(R.id.recipeTitle);

        // Set data in listView
        final RecipeDataProvider dataSet = (RecipeDataProvider) _data.get(position);

        dataSet.setListItemPosition(position);

        if (!dataSet.isHaveImage()) {
            Bitmap icon = BitmapFactory.decodeResource(_c.getResources(), R.mipmap.ic_launcher);
            handler.image.setImageBitmap(icon);
        } else {
            handler.image.setImageBitmap(dataSet.getRecipeImageResource());
        }
        handler.title.setText(dataSet.getRecipeTitles());

        // Attach each of the view components to a row
        view.setTag(handler);

        // Return the row
        return view;
    }

    /**
     * @param position Get position of of object
     * @param imageSrc set image in imageView
     */
    public void setImageInItem(int position, Bitmap imageSrc, String imagePath) {
        RecipeDataProvider dataSet = (RecipeDataProvider) _data.get(position);
        dataSet.setRecipeImageResource(imageSrc);
        dataSet.setStatus(false);
        dataSet.setHaveImage(true);
        notifyDataSetChanged();
    }
}




/*    List list = new ArrayList();

    public RecipeAdapter(Context context, int resource) {
        super(context, resource);
    }

    static class DataHandler{
        ImageView image;
        TextView title;
    }

    *//*
   Save recipe objects in an array list
     *//*
    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    *//*
    Method to return each item in the row
     *//*
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    *//*
    A method that will return each row of data
     *//*

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
        // TODO: set image resource from path (Uri?)
        handler.image.setText(recipeDataProvider.getRecipeImageResource());
        handler.title.setText(recipeDataProvider.getRecipeTitles());

        // Return the row
        return row;
    }*/

