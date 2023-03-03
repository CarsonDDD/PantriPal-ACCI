package comp3350.acci.presentation.fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import comp3350.acci.R;
import comp3350.acci.presentation.MainActivity;
import comp3350.acci.presentation.fragments.ACCIFragment;

public class ProfileActivity extends ACCIFragment {


    public ProfileActivity(MainActivity mainActivity) {
        super(mainActivity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_profile, container, false);
    }
}