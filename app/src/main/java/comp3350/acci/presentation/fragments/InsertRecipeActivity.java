package comp3350.acci.presentation.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import comp3350.acci.R;
import comp3350.acci.application.Services;
import comp3350.acci.business.interfaces.RecipeManager;
import comp3350.acci.objects.Recipe;
import comp3350.acci.presentation.MainActivity;
import comp3350.acci.presentation.fragments.ACCIFragment;

/**
 /**
 This class is the linked Java class to the activity_insert_recipe.xml layout file
 Where the layout decides what is shown/parameters, this file decides logical operations that can be performed

 Last Updated: Carson Dickinson 03/02/2023
 */
public class InsertRecipeActivity extends ACCIFragment implements View.OnClickListener {

    public InsertRecipeActivity(MainActivity mainActivity) {
        super(mainActivity);

        this.hasNavigationBar = true;
        this.hasBackButton = false;
        hasActionBar = false;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the view for this fragment (ie. show it on the screen)
        View view =  inflater.inflate(R.layout.activity_insert_recipe, container, false);

        //make sure this class knows it's the on listening for this button to be pressed
        Button addRecipe = (Button) view.findViewById(R.id.insert_recipe_btnadd);
        addRecipe.setOnClickListener(this);

        return view;
    }


    //add the recipe to the persistence (with a manager)
    @Override
    public void onClick(View view) {
        Button addRecipeButton = (Button) getView().findViewById(R.id.insert_recipe_btnadd);

        //Get user inputted text fields
        EditText recipeText = (EditText) getView().findViewById(R.id.insert_recipe_txtname);
        EditText authorText = (EditText) getView().findViewById(R.id.insert_recipe_txtauthor);
        EditText difficultyText = (EditText) getView().findViewById(R.id.insert_recipe_txtdifficulty);
        EditText instructionText = (EditText) getView().findViewById(R.id.insert_recipe_txtInstructions);

        Switch privacySwitch = (Switch) getView().findViewById(R.id.insert_recipe_swtchPrivacy);

        TextView confirmationText = (TextView) getView().findViewById(R.id.insert_recipe_confirmation);

        //convert text fields to strings
        String recipeName = recipeText.getText().toString();
        String authorName = authorText.getText().toString();
        String difficulty = difficultyText.getText().toString();
        String instructions = instructionText.getText().toString();
        Boolean isPrivate = privacySwitch.isChecked();
        //get the manager from services
        RecipeManager manager = Services.getRecipeManager();


        Recipe addedRecipe = manager.createRecipe(authorName, recipeName,instructions, isPrivate, difficulty);
        if(addedRecipe != null) {
            //make the screen look like we did something (cuz we did)
            confirmationText.setText("Recipe was added successfully!\nPlease add next recipe");
            recipeText.getText().clear();
            authorText.getText().clear();
            difficultyText.getText().clear();
            instructionText.getText().clear();
            privacySwitch.setChecked(false);
        }else {
            confirmationText.setText("Some issue was found in recipe fields\nPlease fix them and try again");
        }
    }
}