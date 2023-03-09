package comp3350.acci.presentation.fragments;

import androidx.fragment.app.Fragment;

import comp3350.acci.presentation.MainActivity;

public abstract class ACCIFragment extends Fragment implements ACCIFragmentable {

    // If a fragment should display the bottom navigation bar
    protected boolean hasNavigationBar = true;

    // If a fragment should display the back button to navigate between fragments
    protected boolean hasBackButton = true;

    // If a fragment should display the ActionBar, setting this to false will hide the back button
    // and any elements inside the ActionBar
    protected boolean hasActionBar = true;

    // Reference to the app itself for 2 way communication between the fragment and the holder
    private final MainActivity mainActivity;

    public ACCIFragment(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    public boolean hasNavigationBar() {
        return hasNavigationBar;
    }

    public boolean hasBackButton() {
        return hasBackButton;
    }

    public boolean hasActionBar(){return hasActionBar;}

    // Returns the AppCompact holding the fragment
    public MainActivity getAppCompact(){
        return mainActivity;
    }
}