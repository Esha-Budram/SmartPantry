package com.eshabudram.smartpantry.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class PantryRepository {
    private final PantryDBHelper dbHelper;

    public PantryRepository(Context context) {
        dbHelper = new PantryDBHelper(context);
    }

    public long insertItem(PantryItem item) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PantryDBHelper.COL_NAME, item.getName());
        cv.put(PantryDBHelper.COL_CATEGORY, item.getCategory());
        cv.put(PantryDBHelper.COL_UNIT, item.getUnit());
        cv.put(PantryDBHelper.COL_QUANTITY, item.getQuantity());
        cv.put(PantryDBHelper.COL_EXPIRY, item.getExpiryDate());
        long id = db.insert(PantryDBHelper.TABLE_ITEMS, null, cv);
        db.close();
        return id;
    }

    public List<PantryItem> getAllItems() {
        List<PantryItem> items = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                PantryDBHelper.TABLE_ITEMS, null, null, null, null, null,
                PantryDBHelper.COL_EXPIRY + " ASC"
        );

        if (cursor.moveToFirst()) {
            do {
                PantryItem item = new PantryItem();
                item.setId(cursor.getLong(cursor.getColumnIndexOrThrow(PantryDBHelper.COL_ID)));
                item.setName(cursor.getString(cursor.getColumnIndexOrThrow(PantryDBHelper.COL_NAME)));
                item.setCategory(cursor.getString(cursor.getColumnIndexOrThrow(PantryDBHelper.COL_CATEGORY)));
                item.setQuantity(cursor.getInt(cursor.getColumnIndexOrThrow(PantryDBHelper.COL_QUANTITY)));
                item.setUnit(cursor.getString(cursor.getColumnIndexOrThrow(PantryDBHelper.COL_UNIT)));
                item.setExpiryDate(cursor.getString(cursor.getColumnIndexOrThrow(PantryDBHelper.COL_EXPIRY)));
                items.add(item);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return items;
    }

    // Updates an existing pantry item. The item's ID tells us which row to change.
    public void updateItem(PantryItem item) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PantryDBHelper.COL_NAME, item.getName());
        values.put(PantryDBHelper.COL_CATEGORY, item.getCategory());
        values.put(PantryDBHelper.COL_QUANTITY, item.getQuantity());
        values.put(PantryDBHelper.COL_UNIT, item.getUnit());
        values.put(PantryDBHelper.COL_EXPIRY, item.getExpiryDate());

        // only update the row matching this item's ID
        db.update(
                PantryDBHelper.TABLE_ITEMS, values, PantryDBHelper.COL_ID + " = ?",
                new String[]{String.valueOf(item.getId())}
        );

        db.close();
    }

    // Deletes a pantry item by its ID
    public void deleteItem(long id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.delete(PantryDBHelper.TABLE_ITEMS,
                PantryDBHelper.COL_ID + " = ?",
                new String[]{String.valueOf(id)}
        );

        db.close();
    }
}