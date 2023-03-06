package comp3350.acci.presentation.fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import comp3350.acci.R;
import comp3350.acci.presentation.MainActivity;

public class ProfileActivity extends ACCIFragment {


    public ProfileActivity(MainActivity mainActivity) {
        super(mainActivity);
        this.hasNavigationBar = true;
        this.hasBackButton = false;
        hasActionBar = true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_userprofile, container, false);
    }
}