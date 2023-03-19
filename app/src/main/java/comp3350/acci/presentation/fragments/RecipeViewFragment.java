package comp3350.acci.presentation.fragments;

import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import comp3350.acci.objects.Recipe;
import comp3350.acci.presentation.ImageAdapter;
import comp3350.acci.presentation.MainActivity;

public class RecipeViewFragment extends Fragment {

    private Recipe recipe;

    private MenuProvider menuProvider;

    public RecipeViewFragment(Recipe recipe){
        this.recipe = recipe;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create a new MenuProvider for this fragment
        //menuProvider = new MenuProvider(requireActivity(), R.menu.menu_recipe_current);
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
        ((MainActivity)getActivity()).setToolbar(toolbar,R.menu.menu_recipe_current);
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity)getActivity()).showNavigationBar(true);

        //Get the required textviews from the layout
        //TextView titleView = (TextView) getView().findViewById(R.id.tv);
        TextView authorView = view.findViewById(R.id.tv_author);
        TextView instructionView = view.findViewById(R.id.tv_instructions);
        TextView difficultyView = view.findViewById(R.id.tv_difficulty);
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
        toolbar.setTitle(recipe.getName());
        authorView.setText(recipe.getAuthor().getUserName());
        difficultyView.setText(recipe.getDifficulty());
        instructionView.setText(recipe.getInstructions().replace("\\n", "\n"));

        if(authorView.getText().length() > 10){
            authorView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 28 - authorView.getText().length());
        }


    }
}