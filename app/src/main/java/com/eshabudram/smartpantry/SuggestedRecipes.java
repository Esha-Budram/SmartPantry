package com.eshabudram.smartpantry;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.eshabudram.smartpantry.data.PantryItem;
import com.eshabudram.smartpantry.data.PantryRepository;
import com.eshabudram.smartpantry.data.Recipe;
import com.eshabudram.smartpantry.data.RecipeMatcher;
import com.eshabudram.smartpantry.data.RecipeRepository;
import com.eshabudram.smartpantry.ui.RecipeAdapter;
import java.util.List;

//this class show only the recipes the user can currently make with what's in their pantry.
public class SuggestedRecipes extends AppCompatActivity{
    private ListView listRecipes;
    private TextView textEmptyMessage;
    private PantryRepository pantryRepository;
    private RecipeRepository recipeRepository;
    private RecipeMatcher recipeMatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suggested_recipes);

        listRecipes=findViewById(R.id.listRecipes);
        textEmptyMessage=findViewById(R.id.textEmptyMessage);

        pantryRepository =new PantryRepository(this);
        recipeRepository=new RecipeRepository(this);
        recipeMatcher=new RecipeMatcher();

        showMatchingRecipes();
        listRecipes.setOnItemClickListener((parent, view, position, id) -> {
            Recipe tappedRecipe = (Recipe) listRecipes.getItemAtPosition(position);

            Intent intent = new Intent(SuggestedRecipes.this, RecipeDetail.class);
            intent.putExtra("recipeId", tappedRecipe.getId());
            startActivity(intent);
        });
    }

    //Refresh every time this screen is shown in case the pantry changed
    @Override
    protected void onResume(){
        super.onResume();
        showMatchingRecipes();
    }

    // Run matching logic and display the outcome
    private void showMatchingRecipes(){
        List<PantryItem> pantryItems=pantryRepository.getAllItems();
        List<Recipe> allRecipes=recipeRepository.getAllRecipes();

        List<Recipe> matchingRecipes=recipeMatcher.getMatchingRecipes(allRecipes, pantryItems);

        if (matchingRecipes.isEmpty()){
            //if there is no matches it show the message instead of an empty list
            textEmptyMessage.setVisibility(View.VISIBLE);
            listRecipes.setVisibility(View.GONE);
        } else {
            textEmptyMessage.setVisibility(View.GONE);
            listRecipes.setVisibility(View.VISIBLE);
            RecipeAdapter adapter=new RecipeAdapter(this,matchingRecipes);
            listRecipes.setAdapter(adapter);
        }
    }
}
