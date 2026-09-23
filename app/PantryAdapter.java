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

// This adapter connects our list of PantryItem objects to a ListView.
// ArrayAdapter already does most of the work — we only need to say
// "how should ONE row look" by overriding getView()

public class PantryAdapter extends ArrayAdapter<PantryItem>{
    public PantryAdapter(Context context,List<PantryItem>items){
        super(context,0,items)//o is inserted because row layout is being built
    }
    //android studio calls nonull once per row, to build the rows view
    @NonNull
    @override
    public View getView(int position,View convertView,@NonNull ViewGroup parent){
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.pantry_item, parent, false);
        }
    }
    PantryItem item=getItem(position);//getting the pantry item for this row

    //next find 3 text views inside the row layout
    TextView textName=convertView.findViewById(R.id.textName);
    TextView textDetails=convertView.findViewById(R.id.textDetails);
    TextView textExpiry=convertView.findViewById(R.id.textExpiry);

    //fill the items data in
    textName.setText(item.getName());
    textDetails.setText(item.getQuanity()+" "+item.getUnit()+"." + item.getCategory());
    textExpiry.setText("Expiry Date: "+item.getExpiryDate());
    return convertView;
    }
}

