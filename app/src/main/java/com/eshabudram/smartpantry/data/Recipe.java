package com.eshabudram.smartpantry.data;
import java.util.ArrayList;
import java.util.List;
import com.eshabudram.smartpantry.data.RecipeIngredient;

// This class represents one recipe.
// A recipe has a name, some preparation steps, and a list of ingredients it needs.
public class Recipe{
    private long id;
    private String name;
    private String steps;//instructions

    private List<RecipeIngredient>ingredients=new ArrayList<>();
    public Recipe(){

    }
    public Recipe(String name,String steps){
        this.name=name;
        this.steps=steps;
    }
    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id=id;

    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getSteps(){
        return steps;
    }
    public void setSteps(String steps){
        this.steps=steps;
    }
    public List<RecipeIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<RecipeIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    //this method adds one ingredient to this recipes list
    public void addIngredient(RecipeIngredient ingredient) {
        this.ingredients.add(ingredient);
    }
}
