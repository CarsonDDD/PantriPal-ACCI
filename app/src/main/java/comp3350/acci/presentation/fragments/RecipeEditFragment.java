package comp3350.acci.presentation.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
        ((MainActivity)getActivity()).showNavigationBar(false);

        // Setup fields with recipe data
        EditText recipeText = view.findViewById(R.id.et_title);
        EditText difficultyText = view.findViewById(R.id.et_difficulty);
        EditText instructionText = view.findViewById(R.id.et_instructions);
        ToggleButton privacySwitch = view.findViewById(R.id.tb_public);

        recipeText.setText(RECIPE.getName());
        difficultyText.setText(RECIPE.getDifficulty());
        instructionText.setText(RECIPE.getInstructions());
        privacySwitch.setChecked(!RECIPE.getIsPrivate());

        // Create button + logic
        Button btnPublish = view.findViewById(R.id.btn_publish);
        btnPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get user inputted text fields
                String title = ((EditText)view.findViewById(R.id.et_title)).getText().toString();
                String difficulty = ((EditText)view.findViewById(R.id.et_difficulty)).getText().toString();
                String instruction = ((EditText)view.findViewById(R.id.et_instructions)).getText().toString();
                boolean isPublic = ((ToggleButton)view.findViewById(R.id.tb_public)).isChecked();

                boolean isValid = !(title.isEmpty() || difficulty.isEmpty() || instruction.isEmpty());

                if(isValid) {
                    Services.getRecipeManager().modify(RECIPE, title, instruction, !isPublic, difficulty);

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
