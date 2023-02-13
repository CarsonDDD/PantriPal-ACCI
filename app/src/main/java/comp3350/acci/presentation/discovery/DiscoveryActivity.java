package comp3350.acci.presentation.discovery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import comp3350.acci.R;
import comp3350.acci.business.RecipeManager;
import comp3350.acci.business.listeners.RecipeClickListener;
import comp3350.acci.objects.Recipe;
import comp3350.acci.presentation.MainActivity;
import comp3350.acci.presentation.RecipeViewActivity;

public class DiscoveryActivity extends Fragment {

    private RecipeAdapter recipeAdapter;
    private RecyclerView recyclerView;

    private MainActivity mainActivity;

    public DiscoveryActivity(MainActivity mainActivity){
        this.mainActivity = mainActivity;
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
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(),1));

        RecipeManager rm = new RecipeManager();

        List<Recipe> recipeList = rm.getRecipes();


        recipeAdapter = new RecipeAdapter(this.getContext(), recipeList, recipeClickListener);
        recyclerView.setAdapter(recipeAdapter);
    }

    private RecipeClickListener recipeClickListener = new RecipeClickListener() {
        @Override
        public void onRecipeClick(Recipe recipe) {
            //Toast.makeText(getContext(), "Hello World", Toast.LENGTH_SHORT).show();
            // Open recipe view with recipe
            // open view, not switch view.
            // no nav bar

            mainActivity.replaceFragment(new RecipeViewActivity(mainActivity, recipe));

        }
    };

}