package com.eshabudram.smartpantry.data;

// creating private variables for pantry items
public class PantryItem{
    private long id;
    private String name;
    private String category;
    private int quantity;
    private String unit;
    private String expiryDate;

    public PantryItem() {}//creating method holder
    //declaring and initialising variables
    public PantryItem(String name,String category,int quantity,String unit, String expiryDate){
        this.name=name;
        this.category = category;
        this.quantity= quantity;
        this.unit=unit;
        this.expiryDate = expiryDate;
    }
    //get and set methods
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
    public String getCategory(){
        return category;
    }
    public void setCategory(String category){
        this.category=category;
    }
    public int getQuantity(){
        return quantity;
    }
    public void setQuantity(int quantity){
        this.quantity=quantity;
    }
    public String getUnit(){
        return unit;
    }
    public void setUnit(String unit){
        this.unit=unit;
    }
    public String getExpiryDate(){
        return expiryDate;
    }
    public void setExpiryDate(String expiryDate){
        this.expiryDate = expiryDate;
    }
}

