package comp3350.acci.presentation.fragments;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import comp3350.acci.R;
import comp3350.acci.application.Services;
import comp3350.acci.business.interfaces.PantryManager;
import comp3350.acci.objects.Pantry;
import comp3350.acci.presentation.MainActivity;
import comp3350.acci.presentation.PantryAdapter;

public class PantryFragment extends Fragment {

    private RecyclerView recyclerView;

    private PantryAdapter pantryAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.menu_pantry_add, menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pantry, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = view.findViewById(R.id.pantry_toolbar);

        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        ((MainActivity)getActivity()).showNavigationBar(true);

        recyclerView = view.findViewById(R.id.rv_pantries);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        PantryManager pantryManager = Services.getPantryManager();
        List<Pantry> pantryList = pantryManager.getPantrys();
        pantryAdapter = new PantryAdapter(pantryList, ((MainActivity)getActivity()).CLICK_EDIT_PANTRY);
        recyclerView.setAdapter(pantryAdapter);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                ((MainActivity)getActivity()).changeFragment(new PantryInsertFragment());
                return true;
            }
        });
    }

}