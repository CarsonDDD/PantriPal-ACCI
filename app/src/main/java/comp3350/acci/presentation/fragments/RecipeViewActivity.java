package comp3350.acci.presentation.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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

    }


}