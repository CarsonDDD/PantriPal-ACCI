package comp3350.acci.presentation.fragments;

import androidx.fragment.app.Fragment;

import comp3350.acci.presentation.MainActivity;

public abstract class ACCIFragment extends Fragment {

    // Reference to the app itself for 2 way communication between the fragment and the holder
    private final MainActivity mainActivity;

    public ACCIFragment(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    // Returns the AppCompact holding the fragment
    public MainActivity getAppCompact(){
        return mainActivity;
    }
}
