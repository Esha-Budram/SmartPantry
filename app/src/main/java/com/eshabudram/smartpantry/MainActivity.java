package com.eshabudram.smartpantry;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.content.Intent;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import com.eshabudram.smartpantry.R;
import com.eshabudram.smartpantry.data.PantryItem;
import com.eshabudram.smartpantry.data.PantryRepository;
import com.eshabudram.smartpantry.ui.PantryAdapter;
import com.eshabudram.smartpantry.data.RecipeIngredient;
import com.eshabudram.smartpantry.data.RecipeRepository;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private ListView listView;
    private PantryRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect to the database
        repository = new PantryRepository(this);

        // Find the ListView from the layout
        listView = findViewById(R.id.listView);

        //tapping thr item to edit
        listView.setOnItemClickListener((parent,view,position,id)->{
                PantryItem selectedItem=(PantryItem) listView.getItemAtPosition(position);

                Intent intent=new Intent(MainActivity.this,AddEditItem.class);
                intent.putExtra("id",selectedItem.getId());
                intent.putExtra("name",selectedItem.getName());
                intent.putExtra("category",selectedItem.getCategory());
                intent.putExtra("quantity",selectedItem.getQuantity());
                intent.putExtra("unit",selectedItem.getUnit());
                intent.putExtra("expiryDate",selectedItem.getExpiryDate());
                startActivity(intent);
        });
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_recipes) {
                startActivity(new Intent(MainActivity.this, SuggestedRecipes.class));
                return true;
            } else if (id == R.id.nav_settings) {
                startActivity(new Intent(MainActivity.this, Settings.class));
                return true;
            }

            // nav_pantry is this screen already, so nothing to do
            return true;
        });
        // Show the current items
        loadItems();
        com.google.android.material.floatingactionbutton.FloatingActionButton fabAddItem = findViewById(R.id.fabAddItem);
        fabAddItem.setOnClickListener(v -> {
            android.content.Intent intent = new android.content.Intent(MainActivity.this, AddEditItem.class);
            startActivity(intent);
        });    }

    // Fetches items from the database and displays them in the list
    private void loadItems(){
        List<PantryItem> items = repository.getAllItems();
        PantryAdapter adapter = new PantryAdapter(this, items);
        listView.setAdapter(adapter);
    }

    // Refresh the list every time this screen becomes visible again
    // (important once we add items from another screen)
    @Override
    protected void onResume(){
        super.onResume();
        loadItems();
    }
}