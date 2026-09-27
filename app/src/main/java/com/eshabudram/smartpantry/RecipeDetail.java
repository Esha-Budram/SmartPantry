package com.eshabudram.smartpantry;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.eshabudram.smartpantry.data.Recipe;
import com.eshabudram.smartpantry.data.RecipeIngredient;
import com.eshabudram.smartpantry.data.RecipeRepository;

import java.util.List;

// Shows the full details (ingredients + steps) for ONE recipe
public class RecipeDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_detail);

        // Get the recipe's ID that was passed in from the Suggested Recipes screen
        long recipeId = getIntent().getLongExtra("recipeId", -1);

        // Load that one recipe from the database
        RecipeRepository recipeRepository = new RecipeRepository(this);
        Recipe recipe = findRecipeById(recipeRepository.getAllRecipes(), recipeId);

        if (recipe != null) {
            displayRecipe(recipe);
        }
    }

    // Simple search through the list to find the recipe with a matching ID
    private Recipe findRecipeById(List<Recipe> recipes, long id) {
        for (Recipe recipe : recipes) {
            if (recipe.getId() == id) {
                return recipe;
            }
        }
        return null;
    }

    // Fills in the screen with the recipe's details
    private void displayRecipe(Recipe recipe) {
        TextView textDetailName = findViewById(R.id.textDetailName);
        TextView textDetailIngredients = findViewById(R.id.textDetailIngredients);
        TextView textDetailSteps = findViewById(R.id.textDetailSteps);

        textDetailName.setText(recipe.getName());
        textDetailSteps.setText(recipe.getSteps());

        // Build one block of text listing every ingredient
        StringBuilder ingredientsText = new StringBuilder();
        for (RecipeIngredient ingredient : recipe.getIngredients()) {
            ingredientsText.append(ingredient.getQuantity())
                    .append(" ")
                    .append(ingredient.getUnit())
                    .append(" ")
                    .append(ingredient.getIngredientName())
                    .append("\n");
        }
        textDetailIngredients.setText(ingredientsText.toString());
    }
}
