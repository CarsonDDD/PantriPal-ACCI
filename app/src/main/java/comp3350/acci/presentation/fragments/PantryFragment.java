package comp3350.acci.presentation.fragments;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import comp3350.acci.R;
import comp3350.acci.presentation.IngredientModel;
import comp3350.acci.presentation.MainActivity_pantry;
import comp3350.acci.presentation.RecyclerIngredientAdapter;

public class PantryFragment extends Fragment {
    ArrayList<IngredientModel> arrayIngredients = new ArrayList<>();
    RecyclerIngredientAdapter adapter;
    Button btnOpenDialog;
    RecyclerView recyclerView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_pantry, container, false);
        recyclerView = view.findViewById(R.id.recyclerIngredient);
        btnOpenDialog = view.findViewById(R.id.add_items);


        btnOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getContext());
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
                            Toast.makeText(getContext(), "Please enter Ingredient Name", Toast.LENGTH_SHORT).show();
                        }
                        if (!editquantity.getText().toString().equals("")) {
                            quantity = editquantity.getText().toString();
                        } else {
                            Toast.makeText(getContext(), "Please enter Ingredient quantity", Toast.LENGTH_SHORT).show();
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

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        arrayIngredients.add(new IngredientModel(R.drawable.lighorange_rounded, "A", "9876463236"));
        arrayIngredients.add(new IngredientModel(R.drawable.lighorange_rounded, "B", "9876463236"));
        arrayIngredients.add(new IngredientModel(R.drawable.lighorange_rounded, "C", "9876463236"));
        arrayIngredients.add(new IngredientModel(R.drawable.lighorange_rounded, "D", "9876463236"));
        arrayIngredients.add(new IngredientModel(R.drawable.lighorange_rounded, "E", "9876463236"));

        RecyclerIngredientAdapter adapter = new RecyclerIngredientAdapter(getContext(), arrayIngredients);
        recyclerView.setAdapter(adapter);


        return view;
    }
}