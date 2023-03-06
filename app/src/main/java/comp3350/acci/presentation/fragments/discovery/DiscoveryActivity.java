package comp3350.acci.presentation.fragments.discovery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import comp3350.acci.R;
import comp3350.acci.business.implementation.RecipeCreator;
import comp3350.acci.business.interfaces.RecipeManager;
import comp3350.acci.business.listeners.RecipeClickListener;
import comp3350.acci.objects.Recipe;
import comp3350.acci.presentation.fragments.ACCIFragment;
import comp3350.acci.presentation.MainActivity;
import comp3350.acci.presentation.fragments.RecipeViewActivity;

public class DiscoveryActivity extends ACCIFragment {

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
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(),1));

        RecipeManager rm = new RecipeCreator();

        List<Recipe> recipeList = rm.getRecipes();

        recipeAdapter = new RecipeAdapter(this.getContext(), recipeList, recipeClickListener);
        recyclerView.setAdapter(recipeAdapter);
    }

    private RecipeClickListener recipeClickListener = new RecipeClickListener() {
        @Override
        public void onRecipeClick(Recipe recipe) {
            getAppCompact().changeFragment(new RecipeViewActivity(getAppCompact(), recipe));
        }
    };

}