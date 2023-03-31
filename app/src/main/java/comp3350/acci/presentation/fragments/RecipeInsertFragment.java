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
import comp3350.acci.business.interfaces.RecipeManager;
import comp3350.acci.business.interfaces.UserManager;
import comp3350.acci.objects.Recipe;
import comp3350.acci.presentation.MainActivity;

public class RecipeInsertFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the view for this fragment (ie. show it on the screen)
        View view =  inflater.inflate(R.layout.fragment_recipe_insert, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        // Add toolbar + menu to app
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        ((MainActivity)getActivity()).showNavigationBar(true);

        // Create button + logic
        Button btnPublish = view.findViewById(R.id.btn_publish);
        btnPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get user inputted text fields
                EditText recipeText = view.findViewById(R.id.et_title);
                Spinner difficultyText = view.findViewById(R.id.sr_difficulty);
                EditText instructionText = view.findViewById(R.id.et_instructions);
                CheckBox privacySwitch = view.findViewById(R.id.cb_private);

                // TODO: DO SOMETHING WITH IMAGES

                //convert text fields to strings
                String recipeName = recipeText.getText().toString();
                String difficulty = difficultyText.getSelectedItem().toString();
                String instructions = instructionText.getText().toString();
                Boolean isPrivate = privacySwitch.isChecked();

                //get the manager from services
                RecipeManager manager = Services.getRecipeManager();
                UserManager userManager = Services.getUserManager();

                Recipe addedRecipe = manager.createRecipe(userManager.getCurrUser(), recipeName,instructions, isPrivate, difficulty);
                if(addedRecipe != null) {
                    Toast.makeText(getContext(), "Recipe added successfully!", Toast.LENGTH_SHORT).show();
                    ((MainActivity)getActivity()).changeFragment(new ProfileViewFragment(Services.getUserManager().getCurrUser()));
                }else {
                    Toast.makeText(getContext(), "Some fields were empty\nPlease fix them and try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    // TODO: HANDLE IMAGE BUTTONS
}