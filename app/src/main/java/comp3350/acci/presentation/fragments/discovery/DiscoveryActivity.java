package comp3350.acci.presentation.fragments.discovery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import comp3350.acci.R;
import comp3350.acci.business.implementation.RecipeCreator;
import comp3350.acci.business.interfaces.RecipeManager;
import comp3350.acci.business.listeners.RecipeClickListener;
import comp3350.acci.objects.Recipe;
import comp3350.acci.presentation.fragments.ACCIFragment;
import comp3350.acci.presentation.MainActivity;
import comp3350.acci.presentation.fragments.RecipeViewActivity;

public class DiscoveryActivity extends ACCIFragment{

    private RecipeAdapter recipeAdapter;
    private RecyclerView recyclerView;


    public DiscoveryActivity(MainActivity mainActivity){
        super(mainActivity);
        this.hasNavigationBar = true;
        this.hasBackButton = false;
        this.hasActionBar = false;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_discovery, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_recipe);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        RecipeManager rm = new RecipeCreator();

        List<Recipe> recipeList = rm.getRecipes();

        recipeAdapter = new RecipeAdapter(R.layout.recipe_card, recipeList,recipeClickListener);
        recyclerView.setAdapter(recipeAdapter);

        SearchView searchView = view.findViewById(R.id.search_view);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                List<Recipe> filtered = new ArrayList<Recipe>();

                for(Recipe recipe : recipeAdapter.recipes){
                    if (recipe.getName().toLowerCase().contains(newText)) {
                        filtered.add(recipe);
                    }
                }

                RecipeAdapter filteredAdapter = new RecipeAdapter(R.layout.recipe_card, filtered, recipeClickListener);
                recyclerView.setAdapter(filteredAdapter);
                return true;
            }
        });
    }

    private RecipeClickListener recipeClickListener = new RecipeClickListener() {
        @Override
        public void onRecipeClick(Recipe recipe) {
            getAppCompact().changeFragment(new RecipeViewActivity(getAppCompact(), recipe));
        }
    };
}