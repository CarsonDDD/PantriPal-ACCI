package comp3350.acci.presentation.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import comp3350.acci.R;
import comp3350.acci.application.Services;
import comp3350.acci.business.interfaces.RecipeManager;
import comp3350.acci.business.interfaces.UserManager;
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
        UserManager um = Services.getUserManager();
        List<Recipe> recipeList = rm.getUserAndPublicRecipes(um.getCurrUser());
        recipeAdapter = new RecipeAdapter(R.layout.recipe_card, recipeList, ((MainActivity)getActivity()).CLICK_RECIPE);
        recyclerView.setAdapter(recipeAdapter);

        // Set up searchbar
        SearchView searchView = view.findViewById(R.id.search_view);

        // Searching it self
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                List<Recipe> filtered = new ArrayList<Recipe>();

                for(Recipe recipe : recipeAdapter.getRecipes()){
                    if (recipe.getName().toLowerCase().contains(newText)) {
                        filtered.add(recipe);
                    }
                }

                RecipeAdapter filteredAdapter = new RecipeAdapter(R.layout.recipe_card, filtered, ((MainActivity)getActivity()).CLICK_RECIPE);
                recyclerView.setAdapter(filteredAdapter);
                return true;
            }
        });

        // Display background
        // Set background on open
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.lightorange_rounded));
            }
        });

        // remove background on close
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                searchView.setBackground(null);// Yes this is valid @~@
                return false;
            }
        });

    }
}