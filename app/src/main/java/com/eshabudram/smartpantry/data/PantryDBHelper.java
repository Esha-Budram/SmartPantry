package com.eshabudram.smartpantry.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//the pantry will enherit the sql database which is abstract
public class PantryDBHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME="pantry.db";
    private static final int DATABASE_VERSION=1;
    //create the columns
    public static final String TABLE_ITEMS="items";
    public static final String COL_ID="id";
    public static final String COL_NAME="name";
    public static final String COL_CATEGORY="category";
    public static final String COL_QUANTITY="quantity";
    public static final String COL_UNIT="unit";
    public static final String COL_EXPIRY="expiry_date";

    // create the pantry helper method
    public PantryDBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    //override the abstract create database method
    //create the table
    @Override
    public void onCreate(SQLiteDatabase db){
        String createTable="CREATE TABLE "+TABLE_ITEMS +"(" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " TEXT NOT NULL, " +
                COL_CATEGORY+ " TEXT, " +
                COL_QUANTITY+" INTEGER, " +
                COL_UNIT + " TEXT, " +
                COL_EXPIRY + " TEXT)";
        db.execSQL(createTable);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_ITEMS);
        onCreate(db);
    }
}