package comp3350.acci.presentation;

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
import comp3350.acci.business.RecipeManager;

/**
    This class is the linked Java class to the activity_insert_recipe.xml layout file
    Where the layout decides what is shown/parameters, this file decides logical operations that can be performed

    Last Updated: Caelan Myskiw 02/11/2023
 */
public class InsertRecipeActivity extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InsertRecipeActivity() {
        // Required empty public constructor
    }

    private MainActivity mainActivity;
    public InsertRecipeActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InsertRecipeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InsertRecipeActivity newInstance(String param1, String param2) {
        InsertRecipeActivity fragment = new InsertRecipeActivity();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

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
        //create a manager
        RecipeManager manager = new RecipeManager();


        manager.createRecipe(authorName, recipeName,instructions, isPrivate, difficulty);

        //make the screen look like we did something (cuz we did)
        confirmationText.setText("Recipe was added successfully!\nPlease add next recipe");
        recipeText.getText().clear();
        authorText.getText().clear();
        difficultyText.getText().clear();
        instructionText.getText().clear();
        privacySwitch.setChecked(false);

    }
}