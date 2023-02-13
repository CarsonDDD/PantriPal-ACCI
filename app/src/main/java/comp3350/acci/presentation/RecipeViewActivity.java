package comp3350.acci.presentation;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import comp3350.acci.R;
import comp3350.acci.objects.Recipe;

public class RecipeViewActivity extends Fragment {

    private Recipe recipe;
    private MainActivity mainActivity;

    public RecipeViewActivity(MainActivity mainActivity,Recipe recipe){
        this.mainActivity = mainActivity;
        this.recipe = recipe;
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