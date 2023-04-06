package comp3350.acci.presentation.fragments;

import android.os.Bundle;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import comp3350.acci.R;
import comp3350.acci.application.Services;
import comp3350.acci.business.listeners.RecipeClickListener;
import comp3350.acci.objects.Recipe;
import comp3350.acci.objects.User;
import comp3350.acci.presentation.MainActivity;
import comp3350.acci.presentation.RecipeAdapter;
import comp3350.acci.presentation.UserAdapter;

public class ProfileViewFragment extends Fragment {

    private RecyclerView savedRecipesView;
    private RecyclerView userRecipesView;
    private User user;
    private boolean isCurrentUser = false;

    // global layout elements. These are used to hide/show the edit profile feature
    Toolbar toolbar;
    TextView tv_bio;
    EditText et_bio;
    EditText et_name;
    LinearLayout ll_edit;


    public ProfileViewFragment(User user) {
        this.user = user;
        isCurrentUser = user.getUserID() == Services.getUserManager().getCurrUser().getUserID();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    // inflate toolbar with menu
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        // Only show menu options if its the current users profile.
        if(isCurrentUser){
            inflater.inflate(R.menu.menu_profile, menu);
        }
        else{
            // show back button on non current
            ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        // Set Name
        toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle(user.getUserName());
        // Add toolbar + menu to app
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        ((MainActivity)getActivity()).showNavigationBar(true);

        // Set bio
        tv_bio = view.findViewById(R.id.bio);
        tv_bio.setVisibility(View.VISIBLE);
        tv_bio.setText(user.getBio());
        tv_bio.setMovementMethod(new ScrollingMovementMethod());

        // Get and hide edit fields
        ll_edit = view.findViewById(R.id.ll_edit);
        ll_edit.setVisibility(View.GONE);

        et_bio = view.findViewById(R.id.et_edit_bio);
        et_bio.setVisibility(View.GONE);

        et_name = view.findViewById(R.id.et_edit_name);
        et_name.setVisibility(View.GONE);

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

        // Edit profile button
        Button btnEditProfile = view.findViewById(R.id.btn_edit_profile);
        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View button) {
                // Update user
                // TODO: might cause bugs with newlines?
                // Probably dont need to edit the actual user object here
                // Only update the database if needed!
                if(!user.getBio().equals(et_bio.getText().toString())){
                    user.setBio(et_bio.getText().toString());
                    Services.getUserManager().setBio(user.getUserID(),user.getBio());
                }

                if(!user.getUserName().equals(et_name.getText().toString())){
                    user.setUserName(et_name.getText().toString());
                    Services.getUserManager().setUsername(user.getUserID(),user.getUserName());
                }

                // Reset visibility
                hideEditProfile();

                // Update display
                toolbar.setTitle(et_name.getText());
                tv_bio.setText(et_bio.getText());

                // Confirmation message
                Toast.makeText(getContext(), "Edit Saved!", Toast.LENGTH_SHORT).show();
            }
        });

        // Cancel edit
        Button btnCancel = view.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideEditProfile();
            }
        });

        // Edit profile button. Disabled until Iteration 3. Make sure to readd back into xml
        //action.inflateMenu(R.menu.menu_profile);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_add_recipe:
                        ((MainActivity)getActivity()).changeFragment(new RecipeInsertFragment());
                        break;
                    case R.id.action_pantry:
                        Toast.makeText(getContext(), "View Pantry!", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_edit_profile:
                        // Hide/show visability to edit fields
                        showEditProfile();
                        //Toast.makeText(getContext(), "Edit Profile!", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_login:
                        showSwitchUserDialog();
                        Toast.makeText(getContext(), "Login as!", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

    private void showSwitchUserDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialogue_switch_user, null);
        builder.setView(dialogView);

        AlertDialog alertDialog = builder.create();

        List<User> users = Services.getUserManager().getUsers();

        RecyclerView rv_users = dialogView.findViewById(R.id.user_list);
        rv_users.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rv_users.setAdapter(new UserAdapter(users, new UserAdapter.UserClickListener() {
            @Override
            public void onClick(User selectedUser) {
                // Change user and udpate display
                Services.getUserManager().setCurrUser(selectedUser);
                ((MainActivity)getActivity()).changeFragment(new ProfileViewFragment(selectedUser));
                alertDialog.dismiss();
            }
        }));

        alertDialog.show();
    }


    private void showEditProfile(){
        et_bio.setVisibility(View.VISIBLE);
        et_name.setVisibility(View.VISIBLE);
        ll_edit.setVisibility(View.VISIBLE);
        toolbar.setTitle("");
        tv_bio.setVisibility(View.GONE);

        et_name.setText(user.getUserName());
        et_bio.setText(user.getBio());
    }

    private void hideEditProfile(){
        et_bio.setVisibility(View.GONE);
        et_name.setVisibility(View.GONE);
        ll_edit.setVisibility(View.GONE);
        tv_bio.setVisibility(View.VISIBLE);
        toolbar.setTitle(user.getUserName());
    }

    public boolean isCurrentUser(){
        return isCurrentUser;
    }
}