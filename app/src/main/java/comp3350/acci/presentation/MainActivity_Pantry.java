package comp3350.acci.presentation;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import comp3350.acci.R;

public class MainActivity_Pantry extends AppCompatActivity {
    ArrayList<IngredientModel> arrayIngredients = new ArrayList<>();
    RecyclerIngredientAdapter adapter;
    Button btnOpenDialog;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry);

        recyclerView = findViewById(R.id.recyclerIngredient);
        btnOpenDialog = findViewById(R.id.add_items);
        
        
        btnOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MainActivity_Pantry.this);
                dialog.setContentView(R.layout.dialog_add_item);
                EditText editName = dialog.findViewById(R.id.edtIngredient);
                EditText editquantity = dialog.findViewById(R.id.edtQuantity);
                Button btnAction = dialog.findViewById(R.id.btnAction);

                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = "", quantity = "";
                        if (!editName.getText().toString().equals("")) {
                            name = editName.getText().toString();
                        } else {
                            Toast.makeText(MainActivity_Pantry.this, "Please enter Ingredient Name", Toast.LENGTH_SHORT).show();
                        }
                        if (!editquantity.getText().toString().equals("")) {
                            quantity = editquantity.getText().toString();
                        } else {
                            Toast.makeText(MainActivity_Pantry.this, "Please enter Ingredient quantity", Toast.LENGTH_SHORT).show();
                        }
                        arrayIngredients.add(new IngredientModel(name, quantity));
                       // adapter.notifyItemInserted(arrayIngredients.size()-1);

                        recyclerView.scrollToPosition(arrayIngredients.size()-1);
                        dialog.dismiss();
                    }
                });
                dialog.show();

           }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        arrayIngredients.add(new IngredientModel(R.drawable.lighorange_rounded, "A", "9876463236"));
        arrayIngredients.add(new IngredientModel(R.drawable.lighorange_rounded, "B", "9876463236"));
        arrayIngredients.add(new IngredientModel(R.drawable.lighorange_rounded, "C", "9876463236"));
        arrayIngredients.add(new IngredientModel(R.drawable.lighorange_rounded, "D", "9876463236"));
        arrayIngredients.add(new IngredientModel(R.drawable.lighorange_rounded, "E", "9876463236"));

        RecyclerIngredientAdapter adapter = new RecyclerIngredientAdapter(this, arrayIngredients);
        recyclerView.setAdapter(adapter);
    }
}



