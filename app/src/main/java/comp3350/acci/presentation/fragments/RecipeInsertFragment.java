package comp3350.acci.presentation.fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
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

/**
 /**
 This class is the linked Java class to the activity_insert_recipe.xml layout file
 Where the layout decides what is shown/parameters, this file decides logical operations that can be performed

 Last Updated: Carson Dickinson 03/02/2023
 */
public class RecipeInsertFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the view for this fragment (ie. show it on the screen)
        View view =  inflater.inflate(R.layout.fragment_recipe_insert, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        // Add toolbar + menu to app
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);

        Button btnPublish = view.findViewById(R.id.btn_publish);
        btnPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get user inputted text fields
                EditText recipeText = view.findViewById(R.id.et_title);
                EditText difficultyText = view.findViewById(R.id.et_difficulty);
                EditText instructionText = view.findViewById(R.id.et_instructions);
                ToggleButton privacySwitch = view.findViewById(R.id.tb_public);

                // TODO: DO SOMETHING WITH IMAGES


                //convert text fields to strings
                String recipeName = recipeText.getText().toString();
                String difficulty = difficultyText.getText().toString();
                String instructions = instructionText.getText().toString();
                Boolean isPublic = privacySwitch.isChecked();
                //get the manager from services
                RecipeManager manager = Services.getRecipeManager();
                UserManager userManager = Services.getUserManager();

                Recipe addedRecipe = manager.createRecipe(userManager.getCurrUser(), recipeName,instructions, !isPublic, difficulty);
                if(addedRecipe != null) {
                    //make the screen look like we did something (cuz we did)
                    Toast.makeText(getContext(), "Recipe added successfully!", Toast.LENGTH_SHORT).show();
                    ((MainActivity)getActivity()).changeFragment(new ProfileViewFragment(Services.getUserManager().getCurrUser()));
                    // Reset is not needed if we change views
                    // TODO: CHANGE VIEWS
                    /*recipeText.getText().clear();
                    difficultyText.getText().clear();
                    instructionText.getText().clear();
                    privacySwitch.setChecked(false);*/
                }else {
                    Toast.makeText(getContext(), "Some fields were empty\nPlease fix them and try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    // TODO: HANDLE IMAGE BUTTONS
}