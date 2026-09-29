package com.eshabudram.smartpantry.data;
//this class represents 1 ingredient that a recipe needs
public class RecipeIngredient{
    private long id;
    private long recipeId;
    private String ingredientName;
    private double quantity;
    private String unit;
    public RecipeIngredient(){
    }
    //a single recipe will have many ingredients
    //constructor used when adding a new ingredient with all its details at once
    public RecipeIngredient(String ingredientName,double quantity,String unit){
        this.ingredientName=ingredientName;
        this.quantity=quantity;
        this.unit=unit;

    }
    //getters and setters below let other classes read andchange these private fields safely

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id=id;
    }

    public long getRecipeId(){
        return recipeId;
    }

    public void setRecipeId(long recipeId){
        this.recipeId=recipeId;
    }

    public String getIngredientName(){
        return ingredientName;
    }

    public void setIngredientName(String ingredientName){
        this.ingredientName=ingredientName;
    }

    public double getQuantity(){
        return quantity;
    }

    public void setQuantity(double quantity){
        this.quantity=quantity;
    }

    public String getUnit(){
        return unit;
    }

    public void setUnit(String unit){
        this.unit=unit;
    }
}
