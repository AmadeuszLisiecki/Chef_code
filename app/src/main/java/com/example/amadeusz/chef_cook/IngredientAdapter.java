package com.example.amadeusz.chef_cook;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class IngredientAdapter extends ArrayAdapter<Ingredient> {

    private Context context;
    private int layoutResourceId;
    private ArrayList<Ingredient> ingredients;
    private String mode;
    private TextView txtTitle;

    public IngredientAdapter(Context context, int layoutResourceId, ArrayList<Ingredient> ingredients, String mode) {
        super(context, layoutResourceId, ingredients);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.ingredients = ingredients;
        this.mode = mode;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if(row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            txtTitle = (TextView) row.findViewById(R.id.ingredient_text);
        }

        Ingredient object = ingredients.get(position);
        switch(mode) {
            case "Składniki": txtTitle.setText(object.toString());
                break;
            case "Zamienniki": txtTitle.setText(object.showSubstituties());
                break;
            default: txtTitle.setText(object.showPrice());
        }
        return row;
    }

}
