package com.eshabudram.smartpantry.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//the pantry will enherit the sql database which is abstract
public class PantryDBHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME="pantry.db";
    private static final int DATABASE_VERSION=2;// more tables are added
    //create the columns for pantry item table
    public static final String TABLE_ITEMS="items";
    public static final String COL_ID="id";
    public static final String COL_NAME="name";
    public static final String COL_CATEGORY="category";
    public static final String COL_QUANTITY="quantity";
    public static final String COL_UNIT="unit";
    public static final String COL_EXPIRY="expiry_date";

    // recipe table for ingredients and steps
    public static final String TABLE_RECIPES="recipes";
    public static final String COL_RECIPE_ID="id";
    public static final String COL_RECIPE_NAME="name";
    public static final String COL_RECIPE_STEPS="steps";

    //recipe for ingredients available table
    public static final String TABLE_RECIPE_INGREDIENTS = "recipe_ingredients";
    public static final String COL_RI_ID="id";
    public static final String COL_RI_RECIPE_ID="recipe_id"; // links back to a recipe
    public static final String COL_RI_NAME ="ingredient_name";
    public static final String COL_RI_QUANTITY= "quantity";
    public static final String COL_RI_UNIT="unit";

    // create the pantry helper method
    public PantryDBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    //override the abstract create database method
    //create the table
    @Override
    public void onCreate(SQLiteDatabase db) {
        //pantry items table
        String createItemsTable = "CREATE TABLE " + TABLE_ITEMS + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " TEXT NOT NULL, " +
                COL_CATEGORY + " TEXT, " +
                COL_QUANTITY + " INTEGER, " +
                COL_UNIT + " TEXT, " +
                COL_EXPIRY + " TEXT)";
        db.execSQL(createItemsTable);

        //recipes table
        String createRecipesTable = "CREATE TABLE " + TABLE_RECIPES + " (" +
                COL_RECIPE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_RECIPE_NAME + " TEXT NOT NULL, " +
                COL_RECIPE_STEPS + " TEXT)";
        db.execSQL(createRecipesTable);

        // recipe  ingredients table
        // each row is ONE ingredient needed by ONE recipe
        String createRecipeIngredientsTable = "CREATE TABLE " + TABLE_RECIPE_INGREDIENTS + " (" +
                COL_RI_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_RI_RECIPE_ID + " INTEGER NOT NULL, " +
                COL_RI_NAME + " TEXT NOT NULL, " +
                COL_RI_QUANTITY + " REAL, " +
                COL_RI_UNIT + " TEXT)";
        db.execSQL(createRecipeIngredientsTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPE_INGREDIENTS);
        onCreate(db);
    }
}