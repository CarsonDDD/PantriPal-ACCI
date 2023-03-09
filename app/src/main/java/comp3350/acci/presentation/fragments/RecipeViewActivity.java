package comp3350.acci.presentation.fragments;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import comp3350.acci.R;
import comp3350.acci.objects.Recipe;
import comp3350.acci.presentation.MainActivity;

public class RecipeViewActivity extends ACCIFragment {

    private Recipe recipe;

    public RecipeViewActivity(MainActivity mainActivity, Recipe recipe){
        super(mainActivity);
        this.recipe = recipe;

        hasNavigationBar = false;
        hasBackButton = true;
        hasActionBar = true;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_recipeview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){


        //Get the required textviews from the layout
        TextView titleView = (TextView) getView().findViewById(R.id.recipe_title);
        TextView authorView = (TextView) getView().findViewById(R.id.recipe_author);
        TextView instructionView = (TextView) getView().findViewById(R.id.recipe_instructions);
        TextView difficultyView = (TextView) getView().findViewById(R.id.recipe_difficulty);

        //set the textviews to the values from the recipe:
        titleView.setText(recipe.getName());
        authorView.setText("By " + recipe.getAuthor().getUserName());
        difficultyView.setText("Difficulty: " + recipe.getDifficulty());
        instructionView.setText("Instructions:\n" + recipe.getInstructions().replace("\\n", "\n"));

    }
}