package com.eshabudram.smartpantry;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.eshabudram.smartpantry.data.PantryRepository;
import com.eshabudram.smartpantry.data.PantryItem;

public class AddEditItem extends AppCompatActivity{
    private EditText editName,editCategory,editQuantity,editUnit,editExpiry;
    private PantryRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit_item);

        repository=new PantryRepository(this);
        editName=findViewById(R.id.editName);
        editCategory=findViewById(R.id.editCategory);
        editQuantity=findViewById(R.id.editQuantity);
        editUnit=findViewById(R.id.editUnit);
        editExpiry=findViewById(R.id.editExpiry);

        Button saveButton=findViewById(R.id.saveButton);
        saveButton.setOnClickListener(v->saveItem());
    }

    private void saveItem(){
        String name=editName.getText().toString().trim();
        String category=editCategory.getText().toString().trim();
        String unit=editUnit.getText().toString().trim();
        String quantString=editQuantity.getText().toString().trim();
        String expiry=editExpiry.getText().toString().trim();

        if (name.isEmpty()){
            Toast.makeText(this,"Enter a name!",Toast.LENGTH_SHORT).show();
            return;
        }
        int quantity=0;
        if (!quantString.isEmpty()){
            quantity=Integer.parseInt(quantString);
        }
        PantryItem item= new PantryItem(name,category,quantity,unit,expiry);
        repository.insertItem(item);
        Toast.makeText(this,"Item is saved!",Toast.LENGTH_SHORT).show();
        finish();

    }
}
