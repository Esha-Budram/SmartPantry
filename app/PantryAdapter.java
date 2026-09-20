package com.yourpackage.pantryapplication.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.yourpackage.pantryapplication.R;
import com.yourpackage.pantryapplication.data.PantryItem;

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
    override

}