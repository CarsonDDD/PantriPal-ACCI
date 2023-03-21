package comp3350.acci.presentation.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import comp3350.acci.R;
import comp3350.acci.application.Services;
import comp3350.acci.business.interfaces.RecipeManager;
import comp3350.acci.objects.Recipe;
import comp3350.acci.presentation.MainActivity;
import comp3350.acci.presentation.RecipeAdapter;

public class DiscoveryViewFragment extends Fragment {

    private RecipeAdapter recipeAdapter;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discovery, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        // Add toolbar + menu to app
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        ((MainActivity)getActivity()).showNavigationBar(true);

        recyclerView = view.findViewById(R.id.rv_recipelist);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        // Populate RecyclerView from database
        RecipeManager rm = Services.getRecipeManager();
        List<Recipe> recipeList = rm.getRecipes();
        recipeAdapter = new RecipeAdapter(R.layout.recipe_card, recipeList, ((MainActivity)getActivity()).CLICK_RECIPE);
        recyclerView.setAdapter(recipeAdapter);
    }
}