package comp3350.acci.presentation.fragments;

import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import comp3350.acci.R;
import comp3350.acci.application.Services;
import comp3350.acci.business.interfaces.UserManager;
import comp3350.acci.objects.Recipe;
import comp3350.acci.objects.User;
import comp3350.acci.presentation.ImageAdapter;
import comp3350.acci.presentation.MainActivity;

public class RecipeViewFragment extends Fragment {

    private final Recipe RECIPE;

    public RecipeViewFragment(Recipe recipe){
        RECIPE = recipe;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        if(Services.getRecipeManager().getUsersRecipes(Services.getUserManager().getCurrUser()).contains(RECIPE)){
            // User recipe. show edit button
            inflater.inflate(R.menu.menu_recipe_current, menu);
        }
        else{
            // non user recipe. Show like button
            inflater.inflate(R.menu.menu_recipe_other, menu);

            // Logic for determining filled state
            // Starting condition for save icon
            UserManager userManager = Services.getUserManager();
            if(userManager.isSaved(userManager.getCurrUser(), RECIPE)){
                MenuItem myMenuItem = menu.findItem(R.id.action_save_recipe);
                myMenuItem.setIcon(R.drawable.ic_star_filled);
                myMenuItem.setChecked(true);
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_recipe_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){

        // Toolbar/menu settings
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity)getActivity()).showNavigationBar(true);

        //Get the required textviews from the layout
        //TextView titleView = (TextView) getView().findViewById(R.id.tv);
        TextView authorView = view.findViewById(R.id.tv_author);
        TextView instructionView = view.findViewById(R.id.tv_instructions);
        TextView difficultyView = view.findViewById(R.id.tv_difficulty);
        TextView updatedView = view.findViewById(R.id.tv_date);
        TextView titleView = view.findViewById(R.id.tv_recipe_name);
        RecyclerView rvImages = view.findViewById(R.id.rv_images);

        // ------------- Fill Fields -------------------
        // Set up recycler view
        rvImages.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));

        // TODO: remove hardcode images
        List<Image> displayImages = new ArrayList<>();
        // add harcoded images
        ImageAdapter imageAdapter = new ImageAdapter(displayImages);
        rvImages.setAdapter(imageAdapter);

        //set the textviews to the values from the recipe:
        toolbar.setTitle("");
        titleView.setText(RECIPE.getName());
        authorView.setText(RECIPE.getAuthor().getUserName());
        difficultyView.setText(RECIPE.getDifficulty());
        instructionView.setText(RECIPE.getInstructions().replace("\\n", "\n"));
        updatedView.setText(RECIPE.getUpdated().toString());

        if(authorView.getText().length() > 10){
            authorView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 28 - authorView.getText().length());
        }

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_edit_recipe:
                        ((MainActivity)getActivity()).changeFragment(new RecipeEditFragment(RECIPE));
                        Toast.makeText(getContext(), "Edit Recipe!", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_save_recipe:

                        UserManager userManager = Services.getUserManager();
                        User currentUser = userManager.getCurrUser();
                        boolean isSaved = userManager.toggleSaved(currentUser, RECIPE);
                        item.setChecked(isSaved);
                        if(isSaved) {
                            item.setIcon(R.drawable.ic_star_filled);
                            Toast.makeText(getContext(), "Recipe Saved!", Toast.LENGTH_SHORT).show();
                        }else {
                            item.setIcon(R.drawable.ic_star_border);
                            Toast.makeText(getContext(), "Recipe Unsaved!", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
                return true;
            }
        });

        LinearLayout authorBox = view.findViewById(R.id.ll_author);
        authorBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: make sure this doesnt show private info
                ((MainActivity)getActivity()).changeFragment(new ProfileViewFragment(RECIPE.getAuthor()));
            }
        });
    }
}


/*
* case R.id.action_save:
                        //Close your eyes
                        Event currentEvent = event;
                        item.setChecked(!item.isChecked());
                        if (item.isChecked()) {
                            item.setIcon(R.drawable.ic_star_filled);
                            Toast.makeText(getContext(), "Bookmarked Event!", Toast.LENGTH_SHORT).show();

                            ((MainActivity)getActivity()).getActiveUser().bookMark(currentEvent);
                            // save the event
                        } else {
                            item.setIcon(R.drawable.ic_star_border);
                            Toast.makeText(getContext(), "Removed Bookmark.", Toast.LENGTH_SHORT).show();
                            ((MainActivity)getActivity()).getActiveUser().removeBookMark(currentEvent);
                            // remove the saved event
                        }
                        break;
*
* */