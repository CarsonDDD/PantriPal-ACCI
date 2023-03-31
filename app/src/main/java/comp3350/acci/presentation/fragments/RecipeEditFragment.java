package comp3350.acci.presentation.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import comp3350.acci.R;
import comp3350.acci.application.Services;
import comp3350.acci.objects.Recipe;
import comp3350.acci.presentation.MainActivity;

public class RecipeEditFragment extends Fragment {
    private final Recipe RECIPE;

    public RecipeEditFragment(Recipe recipe){
        RECIPE = recipe;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the view for this fragment (ie. show it on the screen)
        View view =  inflater.inflate(R.layout.fragment_recipe_insert, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle("Edit Recipe");
        // Add toolbar + menu to app
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity)getActivity()).showNavigationBar(true);

        // Setup fields with recipe data
        EditText recipeText = view.findViewById(R.id.et_title);
        Spinner difficultyText = view.findViewById(R.id.sr_difficulty);
        EditText instructionText = view.findViewById(R.id.et_instructions);
        CheckBox privacySwitch = view.findViewById(R.id.cb_private);

        recipeText.setText(RECIPE.getName());
        difficultyText.setSelection(0);// TODO: start on actual difficulty
        instructionText.setText(RECIPE.getInstructions());
        privacySwitch.setChecked(RECIPE.getIsPrivate());

        // Create button + logic
        Button btnPublish = view.findViewById(R.id.btn_publish);
        btnPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get user inputted text fields
                String title = ((EditText)view.findViewById(R.id.et_title)).getText().toString();
                String difficulty = difficultyText.getSelectedItem().toString();
                String instruction = ((EditText)view.findViewById(R.id.et_instructions)).getText().toString();
                boolean isPrivate = ((CheckBox)view.findViewById(R.id.cb_private)).isChecked();

                boolean isValid = !(title.isEmpty() || difficulty.isEmpty() || instruction.isEmpty());

                if(isValid) {
                    Services.getRecipeManager().modify(RECIPE, title, instruction, isPrivate, difficulty);

                    Toast.makeText(getContext(), "Recipe Edited!", Toast.LENGTH_SHORT).show();
                    ((MainActivity)getActivity()).undoFragment();
                }else {
                    Toast.makeText(getContext(), "Some fields were empty\nPlease fix them and try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}
