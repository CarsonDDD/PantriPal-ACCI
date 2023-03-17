package comp3350.acci.presentation.fragments;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import comp3350.acci.R;
import comp3350.acci.objects.Recipe;
import comp3350.acci.presentation.ImageAdapter;
import comp3350.acci.presentation.MainActivity;

public class RecipeViewFragment extends ACCIFragment {

    private Recipe recipe;

    public RecipeViewFragment(MainActivity mainActivity, Recipe recipe){
        super(mainActivity);
        this.recipe = recipe;
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

        // TODO: do call back to set menu and back bar here!

        //Get the required textviews from the layout
        //TextView titleView = (TextView) getView().findViewById(R.id.tv);
        TextView authorView = view.findViewById(R.id.tv_author);
        TextView instructionView = view.findViewById(R.id.tv_instructions);
        TextView difficultyView = view.findViewById(R.id.tv_difficulty);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        RecyclerView rvImages = view.findViewById(R.id.rv_images);

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
        instructionView.setText("Instructions:\n" + recipe.getInstructions().replace("\\n", "\n"));

    }
}