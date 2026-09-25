package com.eshabudram.smartpantry.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

//this file will save and load recipes with their needed ingredients
public class RecipeRepository {
    private final PantryDBHelper dbHelper;
    public RecipeRepository(Context context){
        dbHelper=new PantryDBHelper(context);

        //when the app runs for the first tim thw database automatically gets loaded with the recipes
        if (isRecipeTableEmpty()){
            seedRecipes();
        }
    }
    //this method checks if no recipes are saved
    private boolean isRecipeTableEmpty(){
        return getAllRecipes().isEmpty();
    }
    //this method saves 1 recipe without ingredients and gives a new id
    private long insertRecipe(String name, String steps){
        SQLiteDatabase db=dbHelper.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(PantryDBHelper.COL_RECIPE_NAME, name);
        values.put(PantryDBHelper.COL_RECIPE_STEPS, steps);
        long id=db.insert(PantryDBHelper.TABLE_RECIPES,null,values);
        db.close();
        return id;
    }

    // this method saves 1 ingredient that belongs to a specific recipe
    private void insertIngredient(long recipeId, String ingredientName, double quantity, String unit){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(PantryDBHelper.COL_RI_RECIPE_ID, recipeId);
        values.put(PantryDBHelper.COL_RI_NAME,ingredientName);
        values.put(PantryDBHelper.COL_RI_QUANTITY, quantity);
        values.put(PantryDBHelper.COL_RI_UNIT,unit);

        db.insert(PantryDBHelper.TABLE_RECIPE_INGREDIENTS,null, values);
        db.close();
    }
    //this method saves a recipe with its ingredients together
    private void addRecipeWithIngredients(String name,String steps,Object[][]ingredientData){
        long recipeId=insertRecipe(name,steps);

        for (Object[] row : ingredientData){
            String ingredientName=(String) row[0];
            double quantity=(double)row[1];
            String unit=(String)row[2];
            insertIngredient(recipeId,ingredientName,quantity,unit);
        }
    }
    //returns all recipe with its list of ingredients.
    public List<Recipe> getAllRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(PantryDBHelper.TABLE_RECIPES, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Recipe recipe = new Recipe();
                recipe.setId(cursor.getLong(cursor.getColumnIndexOrThrow(PantryDBHelper.COL_RECIPE_ID)));
                recipe.setName(cursor.getString(cursor.getColumnIndexOrThrow(PantryDBHelper.COL_RECIPE_NAME)));
                recipe.setSteps(cursor.getString(cursor.getColumnIndexOrThrow(PantryDBHelper.COL_RECIPE_STEPS)));

                // load this recipe ingredients separat
                recipe.setIngredients(getIngredientsForRecipe(recipe.getId()));

                recipes.add(recipe);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return recipes;
    }

    //this method return the list of ingredients needed for 1 specific recipe
    private List<RecipeIngredient> getIngredientsForRecipe(long recipeId){
        List<RecipeIngredient> ingredients=new ArrayList<>();
        SQLiteDatabase db=dbHelper.getReadableDatabase();

        Cursor cursor=db.query(
                PantryDBHelper.TABLE_RECIPE_INGREDIENTS,
                null,
                PantryDBHelper.COL_RI_RECIPE_ID + "= ?",
                new String[]{String.valueOf(recipeId)},
                null,null,null);

        if (cursor.moveToFirst()){
            do{
                RecipeIngredient ingredient=new RecipeIngredient();
                ingredient.setId(cursor.getLong(cursor.getColumnIndexOrThrow(PantryDBHelper.COL_RI_ID)));
                ingredient.setRecipeId(recipeId);
                ingredient.setIngredientName(cursor.getString(cursor.getColumnIndexOrThrow(PantryDBHelper.COL_RI_NAME)));
                ingredient.setQuantity(cursor.getDouble(cursor.getColumnIndexOrThrow(PantryDBHelper.COL_RI_QUANTITY)));
                ingredient.setUnit(cursor.getString(cursor.getColumnIndexOrThrow(PantryDBHelper.COL_RI_UNIT)));
                ingredients.add(ingredient);
            }while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return ingredients;
    }
    // Fills the database with a set of recipes, only runs once
    private void seedRecipes(){
        addRecipeWithIngredients("Scrambled Eggs", "Beat eggs with salt, cook in a pan with butter, stir until set.",
                new Object[][]{
                        {"egg", 2.0, "pieces"},
                        {"butter", 1.0, "tbsp"},
                        {"salt", 1.0, "grams"}
                });

        addRecipeWithIngredients("Tomato Pasta", "Boil pasta, cook tomato with garlic and oil, mix together.",
                new Object[][]{
                        {"pasta", 500.0, "grams"},
                        {"tomato", 5.0, "pieces"},
                        {"garlic", 3.0, "cloves"},
                        {"olive oil", 3.0, "tbsp"}
                });

        addRecipeWithIngredients("Cheese sandwich", "Butter bread, add sliced cheese, lettuce and tomato",
                new Object[][]{
                        {"bread", 2.0, "slices"},
                        {"cheese", 2.0, "slices"},
                        {"butter", 1.0, "tsp"},
                        {"lettuce", 1.0, "leaf"}
                });

        addRecipeWithIngredients("Potato curry", "Chop vegetables, fry onion, chilli powder, tomato and garlic in oil, add salt and potato till soft.",
                new Object[][]{
                        {"onion", 1.0, "pieces"},
                        {"crushed garlic", 1.0, "tbsp"},
                        {"tomato", 1.0, "pieces"},
                        {"oil", 3.0, "tbsp"},
                        {"chilli powder", 3.0, "tbsp"},
                        {"salt", 1.0, "tbsp"},
                        {"Potato", 2.0, "kg"}
                });

        addRecipeWithIngredients("Banana Pancakes", "Mash banana, mix with flour and egg, fry in pan.",
                new Object[][]{
                        {"banana", 30.0, "grams"},
                        {"flour", 250.0, "grams"},
                        {"egg", 1.0, "pieces"},
                        {"milk", 125.0, "grams"}
                });

        addRecipeWithIngredients("Chicken strips and rice", "Cook rice, grill chicken and veg , combine with peri peri sauce.",
                new Object[][]{
                        {"rice", 250.0, "grams"},
                        {"chicken", 200.0, "g"},
                        {"carrot", 100.0, "grams"},
                        {"pepper", 50.0, "grams"}

                });

        addRecipeWithIngredients("Fruit Salad", "Chop all the fruit and mix together in a bowl.",
                new Object[][]{
                        {"apple", 200.0, "grams"},
                        {"banana", 150.0, "grams"},
                        {"orange", 250.0, "grams"},
                        {"strawberry", 400.0, "grams"},
                        {"pear", 80.0, "grams"},
                        {"blueberry", 60.0, "grams"},
                        {"kiwi", 60.0, "grams"},
                        {"mango", 400.0, "grams"}
                });

        addRecipeWithIngredients("Omelette", "Beat eggs with salt and black pepper, pour into buttered pan, add tomato, fold over.",
                new Object[][]{
                        {"egg", 2.0},
                        {"tomato", 10.0, "grams"},
                        {"butter", 1.0, "tbsp"},
                        {"salt", 2.0, "gram"},
                        {"black pepper", 1.0, "gram"}
                });

        addRecipeWithIngredients("chicken and mayo Sandwich", "fry chicken fillet in oil,mix with mayo and spread on bread.",
                new Object[][]{
                        {"chicken", 100.0, "gram"},
                        {"mayo", 1.0, "tbsp"},
                        {"bread", 2.0, "slices"},
                        {"oil", 3.0, "tbsp"}
                });

        addRecipeWithIngredients("Vegetable Soup", "Boil chopped vegetables with salt in stock until soft.",
                new Object[][]{
                        {"carrot", 10.0, "grams"},
                        {"potato", 100.0, "grams"},
                        {"onion", 30.0, "grams"},
                        {"stock", 2.0, "cup"},
                        {"salt", 1.0, "tbsp"}
                });

        addRecipeWithIngredients("Mashed Potatoes", "Boil potatoes, mash with butter and milk.",
                new Object[][]{
                        {"potato", 500.0, "grams"},
                        {"butter", 3.0, "tbsp"},
                        {"milk", 0.25, "cup"}
                });

        addRecipeWithIngredients("Garlic Bread", "Mix butter, pepper and garlic, spread on bread, toast.",
                new Object[][]{
                        {"bread", 2.0, "slices"},
                        {"butter", 1.0, "tbsp"},
                        {"garlic", 1.0, "cloves"},
                        {"Black pepper", 1.0, "grams"}
                });

        addRecipeWithIngredients("Punch", "mix ingredients and serve with mint garnish.",
                new Object[][]{
                        {"crushed pineapple", 1.0, "can"},
                        {"crushed granadilla", 1.0, "can"},
                        {"passion fruit squash", 1.0, "litre"},
                        {"ginger ale", 1.0, "litre"},
                        {"lemonade", 1.0, "litre"},
                        {"banana",30.0,"grams"}
                });

        addRecipeWithIngredients("Mango and orange juice", "liquidize fruit with sugar, serve.",
                new Object[][]{
                        {"mango", 2.4, "kilograms"},
                        {"orange", 1.0, "kilograms"},
                        {"sugar", 4.0, "tbsp"}
                });

        addRecipeWithIngredients("Cheese Toast", "Toast bread, top with cheese, grill until melted.",
                new Object[][]{
                        {"bread", 2.0, "slices"},
                        {"cheese", 2.0, "slices"}
                });

        addRecipeWithIngredients("Vermicilli", "brown cardamon, cinnamon and vermicilli, add water and milk, thicken with condensed milk, butter and sugar.",
                new Object[][]{
                        {"vermicilli", 250.0, "gram"},
                        {"cinnamon stick", 2.0, "pieces"},
                        {"cardamon", 5.0, "grams"},
                        {"water", 350.0, "millilitres"},
                        {"milk", 350.0, "millitres"},
                        {"butter", 3.0, "tbsp"},
                        {"condensed milk", 1.0, "can"},
                        {"sugar", 125.0, "grams"}
                });
    }
}
