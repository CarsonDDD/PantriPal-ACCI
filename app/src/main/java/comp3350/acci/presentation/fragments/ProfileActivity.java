package comp3350.acci.presentation.fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

import comp3350.acci.R;
import comp3350.acci.business.implementation.RecipeCreator;
import comp3350.acci.business.interfaces.RecipeManager;
import comp3350.acci.business.listeners.RecipeClickListener;
import comp3350.acci.objects.Recipe;
import comp3350.acci.presentation.MainActivity;
import comp3350.acci.presentation.fragments.discovery.RecipeAdapter;

public class ProfileActivity extends ACCIFragment {

    private RecyclerView savedRecipesView;
    private RecyclerView userRecipesView;

    // TODO: This should also take a user as a parameter and inflate the view with its data
    public ProfileActivity(MainActivity mainActivity) {
        super(mainActivity);
        this.hasNavigationBar = true;
        this.hasBackButton = false;
        hasActionBar = false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_userprofile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        savedRecipesView = view.findViewById(R.id.saved_recipes_recycler);
        userRecipesView = view.findViewById(R.id.user_recipes_recycler);

        // set up TabLayout. This is done programmatically for no specific reasons (its what the tutorial had.)
        tabLayout.addTab(tabLayout.newTab().setText("Saved Recipes"));
        tabLayout.addTab(tabLayout.newTab().setText("User Recipes"));// Possible change this to <username>


        // set up RecyclerViews
        // TODO: use correct recipe lists
        RecipeManager rm = new RecipeCreator();
        List<Recipe> recipeList = rm.getRecipes();

        List<Recipe> userRecipes = recipeList;
        List<Recipe> savedRecipes = recipeList;

        savedRecipesView.setAdapter(new RecipeAdapter(R.layout.recipe_card_small, savedRecipes, recipeClickListener));
        savedRecipesView.setLayoutManager(new GridLayoutManager(this.getContext(), 3));
        savedRecipesView.setHasFixedSize(true);

        userRecipesView.setAdapter(new RecipeAdapter(R.layout.recipe_card_small, userRecipes, recipeClickListener));
        userRecipesView.setLayoutManager(new GridLayoutManager(this.getContext(), 3));
        savedRecipesView.setHasFixedSize(false);

        // Tab switcher, show correct recycler, hide incorrect one
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        savedRecipesView.setVisibility(View.VISIBLE);
                        userRecipesView.setVisibility(View.GONE);
                        break;
                    case 1:
                        savedRecipesView.setVisibility(View.GONE);
                        userRecipesView.setVisibility(View.VISIBLE);
                        break;
                }
            }

            // interface leftovers
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        Toolbar action = view.findViewById(R.id.profile_menu);
        //action.inflateMenu(R.menu.menu_profile);
        action.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_edit_profile:
                        Toast.makeText(getAppCompact(), "Edit Profile!", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

    // This function is copypaste from DiscoveryActivity, as clicking on the card should have the same
    // effect. I dont know to make it properly/automatic where I dont need to copy paste this function.
    // One option would be to move this listener into the RecipeAdapter itself, however, that means we would
    // need to pass a reference to the AppCompact (since we change the display on click). Passing it in
    // feels too dirty (even for me ;] ) and probably breaks something mentioned in the lectures; for now it stays here.
    // TODO: make this function not need to be hardcoded into the classes which use a RecipeAdapter. All RecipeAdapters
    //  should(?) have the same functionality when clicking on a recipe card.
    private RecipeClickListener recipeClickListener = new RecipeClickListener() {
        @Override
        public void onRecipeClick(Recipe recipe) {
            getAppCompact().changeFragment(new RecipeViewActivity(getAppCompact(), recipe));
        }
    };
}