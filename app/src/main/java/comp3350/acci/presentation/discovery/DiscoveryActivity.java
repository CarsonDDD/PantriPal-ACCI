package comp3350.acci.presentation.discovery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import comp3350.acci.R;
import comp3350.acci.objects.Recipe;

public class DiscoveryActivity extends Fragment {

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
        return inflater.inflate(R.layout.activity_discovery, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_recipe);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(),1));

        List<Recipe> recipeList = new ArrayList<>();
        recipeList.add(new Recipe());
        recipeList.add(new Recipe());
        recipeList.add(new Recipe());
        recipeList.add(new Recipe());

        recipeAdapter = new RecipeAdapter(this.getContext(), recipeList);
        recyclerView.setAdapter(recipeAdapter);
    }

}