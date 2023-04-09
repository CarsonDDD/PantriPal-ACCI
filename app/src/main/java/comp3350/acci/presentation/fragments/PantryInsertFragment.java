package comp3350.acci.presentation.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import comp3350.acci.R;
import comp3350.acci.application.Services;
import comp3350.acci.business.interfaces.PantryManager;
import comp3350.acci.business.interfaces.UserManager;
import comp3350.acci.objects.Ingredient;
import comp3350.acci.objects.Pantry;
import comp3350.acci.presentation.MainActivity;

public class PantryInsertFragment extends Fragment {

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_pantry_insert, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        PantryManager pantryManager = Services.getPantryManager();
        UserManager userManager = Services.getUserManager();

        Button submit = view.findViewById(R.id.iv_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                EditText ingredientText = view.findViewById(R.id.et_ingredient);
                EditText quantityText = view.findViewById(R.id.et_quantity);
                EditText unitText = view.findViewById(R.id.et_unit);

                String ingredient = ingredientText.getText().toString();
                Double quantity = 0.0;
                String unit = unitText.getText().toString();

                try{
                    quantity = Double.parseDouble(quantityText.getText().toString());
                }catch(Exception e){

                }

                Pantry addedPantry = pantryManager.insertPantry(userManager.getCurrUser(), new Ingredient(ingredient), quantity, unit);
                if(addedPantry != null) {
                    Toast.makeText(getContext(), "Ingredient added to pantry!", Toast.LENGTH_SHORT).show();
                    ((MainActivity)getActivity()).changeFragment(new PantryFragment());
                }
                else {
                    Toast.makeText(getContext(), "Some fields were invalid\nPlease fix them and try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
