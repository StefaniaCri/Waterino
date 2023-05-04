package com.example.waterreminder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class NewDrinkActivity extends AppCompatActivity {

    private EditText editTextTitle;
    private EditText editTextDescription;
    private NumberPicker numberPickerQuantity;
    public static final String userRef = "123";
    public  static final String SHARED_PREFS = "sharedPrefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_drink);
getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
setTitle("Add a drink");
        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        numberPickerQuantity = findViewById(R.id.number_quantity_picker);

        numberPickerQuantity.setMinValue(50);
        numberPickerQuantity.setMaxValue(1000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.new_drink_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_drink:
                saveDrink();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void saveDrink(){
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        int priority = numberPickerQuantity.getValue();

        if(title.trim().isEmpty() || description.trim().isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Please insert a title and description",Toast.LENGTH_SHORT);
            return;
        }
        CollectionReference drinkRef = FirebaseFirestore.getInstance().collection("Drinks");
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String userRefrence = sharedPreferences.getString(userRef,"");

        drinkRef.add(new Drink(title,description,priority, userRefrence));
        Toast.makeText(getApplicationContext(),"Note added",Toast.LENGTH_SHORT);
        finish();
    }
}