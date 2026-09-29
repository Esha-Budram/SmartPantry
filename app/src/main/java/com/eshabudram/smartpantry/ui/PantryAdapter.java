package com.eshabudram.smartpantry.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.eshabudram.smartpantry.R;
import com.eshabudram.smartpantry.data.PantryItem;

import java.util.List;

//connects PantryItem objects to a ListView.

public class PantryAdapter extends ArrayAdapter<PantryItem> {
    public PantryAdapter(Context context, List<PantryItem> items) {
        super(context, 0, items); // 0 is passed because we build the row layout ourselves below
    }

    //android calls this once per row to build that row view or t0 reuse it
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.pantry_item, parent, false);
        }

        PantryItem item=getItem(position); // getting the pantry item for this row

        // find the 3 text views inside the row layout
        TextView textName=convertView.findViewById(R.id.textName);
        TextView textDetails=convertView.findViewById(R.id.textDetails);
        TextView textExpiry=convertView.findViewById(R.id.textExpiry);

        // fill the item data in
        textName.setText(item.getName());
        textDetails.setText(item.getQuantity() + " " + item.getUnit());
        textExpiry.setText("Expiry Date: " + item.getExpiryDate());

        return convertView;
    }
}