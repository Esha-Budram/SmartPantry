package com.eshabudram.smartpantry.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.eshabudram.smartpantry.R;
import com.eshabudram.smartpantry.data.Recipe;
import java.util.List;

//this class connects a list of Recipe objects to a list view
public class RecipeAdapter extends ArrayAdapter<Recipe>{

    public RecipeAdapter(Context context, List<Recipe> recipes){
        super(context, 0, recipes);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent){
        if (convertView==null) {
            convertView=LayoutInflater.from(getContext())
                    .inflate(R.layout.recipe_item, parent,false);
        }
        Recipe recipe=getItem(position);
        TextView textRecipeName=convertView.findViewById(R.id.textRecipeName);
        textRecipeName.setText(recipe.getName());
        return convertView;
    }
}
