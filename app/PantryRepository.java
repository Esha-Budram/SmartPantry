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
    public List<PantryItem> getAllItems(){
        List<PantryItem> items=new ArrayList<>();
        SQLiteDatabase db=dbHelper.getReadableDatabase();

        Cursor cursor=db.query(
                PantryDBHelper.TABLE_ITEMS,null,null,null,null,null,PantryDBHelper.COL_EXPIRY+"ASC"
        );
        // Cursor works like a pointer moving through each row of the result
        if (cursor.moveToFirst()) {
            do {
                PantryItem item = new PantryItem();
                item.setId(cursor.getLong(cursor.getColumnIndexOrThrow(PantryDbHelper.COL_ID)));
                item.setName(cursor.getString(cursor.getColumnIndexOrThrow(PantryDbHelper.COL_NAME)));
                item.setCategory(cursor.getString(cursor.getColumnIndexOrThrow(PantryDbHelper.COL_CATEGORY)));
                item.setQuantity(cursor.getInt(cursor.getColumnIndexOrThrow(PantryDbHelper.COL_QUANTITY)));
                item.setUnit(cursor.getString(cursor.getColumnIndexOrThrow(PantryDbHelper.COL_UNIT)));
                item.setExpiryDate(cursor.getString(cursor.getColumnIndexOrThrow(PantryDbHelper.COL_EXPIRY)));
                items.add(item);
            } while (cursor.moveToNext());
        }

        cursor.close();
        // Updates an existing pantry item. The item's ID tells us which row to change.
        public void updateItem(PantryItem item) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(PantryDbHelper.COL_NAME, item.getName());
            values.put(PantryDbHelper.COL_CATEGORY, item.getCategory());
            values.put(PantryDbHelper.COL_QUANTITY, item.getQuantity());
            values.put(PantryDbHelper.COL_UNIT, item.getUnit());
            values.put(PantryDbHelper.COL_EXPIRY, item.getExpiryDate());

            //only update the row matching this item's ID
            db.update(
                    PantryDbHelper.TABLE_ITEMS,values,PantryDbHelper.COL_ID+" = ?",
                    new String[]{String.valueOf(item.getId())}
            );
        }

        // Deletes a pantry item by its ID
        public void deleteItem(long id) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            db.delete(PantryDbHelper.TABLE_ITEMS,
                    PantryDbHelper.COL_ID+" = ?",
                    new String[]{String.valueOf(id)}
            );
        }
        db.close();
        return items;
    }
}