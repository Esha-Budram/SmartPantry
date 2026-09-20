package com.yourpackage.pantryapplication.data;

// creating private variables for pantry items
public class PantryItem{
    private long id;
    private String name;
    private String category;
    private int quantity;
    private String unit;
    private String expiryDate

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
    public long getid(){
        return id;
    }
    public void setid(long id){
        this.id=id;
    }
    public String getname(){
        return name;
    }
    public void setname(String name){
        this.name=name;
    }
    public String getcategory(){
        return category;
    }
    public void setcategory(String category){
        this.category=category;
    }
    public int getquantity(){
        return quantity;
    }
    public void setquantity(int quantity){
        this.quantity=quantity;
    }
    public String getunit(){
        return unit;
    }
    public void setunit(String unit){
        this.unit=unit;
    }
    public String getexpiryDate(){
        return expiryDate;
    }
    public void setexpiryDate(String expiryDate){
        this.expiryDate = expiryDate;
    }
}

