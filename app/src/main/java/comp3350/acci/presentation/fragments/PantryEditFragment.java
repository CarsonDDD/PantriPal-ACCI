package comp3350.acci.presentation.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import comp3350.acci.R;
import comp3350.acci.application.Services;
import comp3350.acci.business.interfaces.PantryManager;
import comp3350.acci.business.interfaces.UserManager;
import comp3350.acci.objects.Ingredient;
import comp3350.acci.objects.Pantry;
import comp3350.acci.presentation.MainActivity;

public class PantryEditFragment extends Fragment {

    private final Pantry pantry;

    public PantryEditFragment(Pantry pantry){
        this.pantry = pantry;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_pantry_edit, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        TextView ingredientText = view.findViewById(R.id.tv_ingredient);
        EditText quantityText = view.findViewById(R.id.et_quantity);
        EditText unitText = view.findViewById(R.id.et_unit);

        ingredientText.setText(pantry.getIngredient().getName());
        quantityText.setText("" + pantry.getQuantity());
        unitText.setText(pantry.getUnit());

        Button submit = view.findViewById(R.id.iv_submit);
        Button delete = view.findViewById(R.id.iv_delete);

        PantryManager pantryManager = Services.getPantryManager();
        UserManager userManager = Services.getUserManager();

        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                TextView ingredientText = view.findViewById(R.id.tv_ingredient);
                EditText quantityText = view.findViewById(R.id.et_quantity);
                EditText unitText = view.findViewById(R.id.et_unit);

                String ingredient = ingredientText.getText().toString();
                Double quantity = 0.0;
                String unit = unitText.getText().toString();

                try{
                    quantity = Double.parseDouble(quantityText.getText().toString());
                }catch(Exception e){

                }

                Pantry editedPantry = pantryManager.updatePantry(userManager.getCurrUser(), new Ingredient(ingredient), quantity, unit);
                if(editedPantry != null) {
                    Toast.makeText(getContext(), "Ingredient edit submitted!", Toast.LENGTH_SHORT).show();
                    ((MainActivity)getActivity()).changeFragment(new PantryFragment());
                }
                else {
                    Toast.makeText(getContext(), "Some fields were invalid\nPlease fix them and try again", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pantryManager.deletePantry(pantry);
                ((MainActivity)getActivity()).changeFragment(new PantryFragment());
            }
        });
    }
}
