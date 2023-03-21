package comp3350.acci.presentation.fragments;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import comp3350.acci.R;
import comp3350.acci.objects.Recipe;
import comp3350.acci.presentation.MainActivity;
import comp3350.acci.presentation.RecipeAdapter;


public class SearchViewFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        ((MainActivity)getActivity()).showNavigationBar(true);

        // Set up searchbar
        SearchView searchView = view.findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                /*List<Recipe> filtered = new ArrayList<Recipe>();

                for(Recipe recipe : recipeAdapter.getRecipes()){
                    if (recipe.getName().toLowerCase().contains(newText)) {
                        filtered.add(recipe);
                    }
                }

                RecipeAdapter filteredAdapter = new RecipeAdapter(R.layout.recipe_card, filtered, ((MainActivity)getActivity()).CLICK_RECIPE);
                recyclerView.setAdapter(filteredAdapter);*/
                return true;
            }
        });

        return view;
    }
}