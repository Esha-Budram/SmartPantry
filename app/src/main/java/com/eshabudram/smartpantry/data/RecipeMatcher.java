package com.eshabudram.smartpantry.data;
import java.util.ArrayList;
import java.util.List;
//this class file decides wghich recipes can be used with only ingredients available in pantry
public class RecipeMatcher{
    //the recipes show only if all its ingredients are available in pantry
    public List<Recipe>getMatchingRecipes(List<Recipe> allRecipes,List<PantryItem>pantryItems){
        List<Recipe>matchingRecipes= new ArrayList<>();
        for (Recipe recipe:allRecipes){
            if (canMakeRecipe(recipe,pantryItems)){
                matchingRecipes.add(recipe);
            }
        }
        return matchingRecipes;
    }
    //checking if a recipe can be made, every ingredient it needs must be in the pantry
    private boolean canMakeRecipe(Recipe recipe,List<PantryItem>pantryItems){
        for (RecipeIngredient neededIngredient:recipe.getIngredients()){
            if (!pantryHasEnough(neededIngredient, pantryItems)){
                return false; // even if there is 1 missing ingredient it hides the recipe
            }
        }
        return true; // every ingredient was found in enough quantity in ;pantry
    }

    //checking if the pantry has enough of 1 specific ingredient
    private boolean pantryHasEnough(RecipeIngredient neededIngredient,List<PantryItem>pantryItems){
        for (PantryItem pantryItem : pantryItems){
            if (namesMatch(pantryItem.getName(), neededIngredient.getIngredientName())){
                //at this stage the ingredient in the pantry is foundd then checks enough quantity
                return pantryItem.getQuantity()>= neededIngredient.getQuantity();
            }
        }
        return false; // ingredient was not found in the pantry at all
    }

    //comparing 2 ingredient names, ignoring case and simple singular and plural difference
    private boolean namesMatch(String pantryName, String recipeName){
        String cleanedPantryName=normalize(pantryName);
        String cleanedRecipeName=normalize(recipeName);
        return cleanedPantryName.equals(cleanedRecipeName);
    }

    //cleaning up a name so Tomato,tomato and tomatoes all become the same thing
    private String normalize(String name){
        String result=name.trim().toLowerCase();

        //removing the plural endings
        if (result.endsWith("es")){
            result=result.substring(0,result.length()-2);
        } else if (result.endsWith("s")) {
            result=result.substring(0,result.length()-1);
        }

        return result;
    }
}
