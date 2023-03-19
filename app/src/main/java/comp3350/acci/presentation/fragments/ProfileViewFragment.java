package comp3350.acci.presentation.fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

import comp3350.acci.R;
import comp3350.acci.application.Services;
import comp3350.acci.business.listeners.RecipeClickListener;
import comp3350.acci.objects.Recipe;
import comp3350.acci.objects.User;
import comp3350.acci.presentation.MainActivity;
import comp3350.acci.presentation.RecipeAdapter;

public class ProfileViewFragment extends Fragment {

    private RecyclerView savedRecipesView;
    private RecyclerView userRecipesView;
    private User user;

    public ProfileViewFragment(User user) {
        this.user = user;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Tell the system that this fragment has an options menu
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        // Set Name
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle(user.getUserName());
        // Add toolbar + menu to app
        ((MainActivity)getActivity()).setToolbar(toolbar,R.menu.menu_profile);

        // Set bio
        TextView bio = view.findViewById(R.id.bio);
        bio.setText(user.getBio());

        // set up TabLayout
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        savedRecipesView = view.findViewById(R.id.saved_recipes_recycler);
        userRecipesView = view.findViewById(R.id.user_recipes_recycler);
        tabLayout.addTab(tabLayout.newTab().setText("Saved Recipes"));
        tabLayout.addTab(tabLayout.newTab().setText("User Recipes"));// Possible change this to <username>
        tabLayout.getTabAt(1).select();


        // set up RecyclerViews
        List<Recipe> userRecipes =  Services.getRecipeManager().getUsersRecipes(user);
        List<Recipe> savedRecipes = Services.getUserManager().getUsersSavedRecipes(user);

        savedRecipesView.setAdapter(new RecipeAdapter(R.layout.recipe_card_small, savedRecipes, ((MainActivity)getActivity()).CLICK_RECIPE));
        savedRecipesView.setLayoutManager(new GridLayoutManager(this.getContext(), 3));
        savedRecipesView.setHasFixedSize(true);

        userRecipesView.setAdapter(new RecipeAdapter(R.layout.recipe_card_small, userRecipes, ((MainActivity)getActivity()).CLICK_RECIPE));
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

        // Edit profile button. Disabled until Iteration 3. Make sure to readd back into xml
        //action.inflateMenu(R.menu.menu_profile);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_pantry:
                        Toast.makeText(getContext(), "View Pantry!", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_edit_profile:
                        Toast.makeText(getContext(), "Edit Profile!", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_login:
                        Toast.makeText(getContext(), "Login as!", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_recipe_current, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit_recipe:
                Toast.makeText(getContext(), "Edit!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_save_recipe:
                Toast.makeText(getContext(), "Like!", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}