package com.eshabudram.smartpantry;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.eshabudram.smartpantry.data.PantryRepository;
import com.eshabudram.smartpantry.data.PantryItem;
//screen is used foor editing anf and adding items
public class AddEditItem extends AppCompatActivity{
    private EditText editName,editQuantity,editUnit,editExpiry;
    private PantryRepository repository;
    private long currentItemId = -1 ; // adding new item so its -1, else editing the id
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit_item);
        //connecting to the database
        repository=new PantryRepository(this);
        //finding input fields
        editName=findViewById(R.id.editName);
        editQuantity=findViewById(R.id.editQuantity);
        editUnit=findViewById(R.id.editUnit);
        editExpiry=findViewById(R.id.editExpiry);

        Button saveButton=findViewById(R.id.saveButton);
        saveButton.setOnClickListener(v->saveItem());
        Button deleteButton=findViewById(R.id.deleteButton);

        //checking if tge program was sent to edit and existing item
        if(getIntent().hasExtra("id")){
            currentItemId = getIntent().getLongExtra("id", -1);
            //auto fill the form with the existing item's data
            editName.setText(getIntent().getStringExtra("name"));
            editQuantity.setText(String.valueOf(getIntent().getLongExtra("quantity", 0)));
            editUnit.setText(getIntent().getStringExtra("unit"));
            editExpiry.setText(getIntent().getStringExtra("expiryDate"));

            saveButton.setText("Update Item");
            deleteButton.setVisibility(android.view.View.VISIBLE); // show delete only when editing
        }
        saveButton.setOnClickListener(v -> saveItem());

        deleteButton.setOnClickListener(v -> {
            repository.deleteItem(currentItemId);
            Toast.makeText(this, "Item deleted", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    private void saveItem(){
        String name=editName.getText().toString().trim();
        String unit=editUnit.getText().toString().trim();
        String quantString=editQuantity.getText().toString().trim();
        String expiry=editExpiry.getText().toString().trim();

        if (name.isEmpty()){
            Toast.makeText(this,"Enter a name!",Toast.LENGTH_SHORT).show();
            return;
        }
        int quantity=0;
        if (!quantString.isEmpty()){
            try{
                quantity=Integer.parseInt(quantString);
            } catch (NumberFormatException e){
                Toast.makeText(this,"Quantity must be a number!",Toast.LENGTH_SHORT).show();
                return;
            }
        }
        PantryItem item= new PantryItem(name,quantity,unit,expiry);
        if (currentItemId== -1){
            repository.insertItem(item);
            Toast.makeText(this, "Item saved", Toast.LENGTH_SHORT).show();
        }else{
            item.setId(currentItemId);
            repository.updateItem(item);
            Toast.makeText(this, "Item updated", Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}
