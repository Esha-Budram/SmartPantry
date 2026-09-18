package com.yourpackage.pantryapplication.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class PantryRepository{
    private final PantryDBHelper dbHelper;
    public PantryRepository(Context context){
        dbHelper= new PantryDBHelper(context);
    }
    public long insertItem(PantryItem item){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PantryDBHelper.COL_NAME, item.getname);
        cv.put(PantryDBHelper.COL_CATEGORY,item.getcategory);
        cv.put(PantryDBHelper.COL_UNIT,item.getunit);
        cv.put(PantryDBHelper.COL_QUANTITY,item.getquantity);
        cv.put(PantryDBHelper.COL_EXPIRYDATE,item.getexpiryDate);
        long id=db.insert(PantryDBHelper.TABLE_ITEMS,null,cv);
        db.close();
        return id;
    }
    }uwujw
}