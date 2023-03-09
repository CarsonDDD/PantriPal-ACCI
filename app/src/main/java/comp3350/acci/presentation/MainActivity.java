package comp3350.acci.presentation;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.FragmentContainerView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import comp3350.acci.R;
import comp3350.acci.application.Services;
import comp3350.acci.databinding.ActivityMainBinding;
import comp3350.acci.presentation.fragments.ProfileActivity;
import comp3350.acci.presentation.fragments.discovery.DiscoveryActivity;
import comp3350.acci.presentation.fragments.ACCIFragment;
import comp3350.acci.presentation.fragments.InsertRecipeActivity;

// This class acts as the engine which runs/controls the fragment interactions
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FragmentNavigator fragmentNavigator;

    private boolean isShowingNavigationBar;
    private boolean isShowingBackButton;
    private boolean isShowingActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());// Set app display to this file

        copyDatabaseToDevice();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Starting conditions for back button and ActionBar.
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        hideActionBar();

        // Set starting fragment
        fragmentNavigator = new FragmentNavigator(this.getSupportFragmentManager());
        fragmentNavigator.setFragment(new DiscoveryActivity(this));

        // init layout variables to the starting fragment.
        isShowingBackButton = fragmentNavigator.currentFragment().hasBackButton();
        isShowingNavigationBar = fragmentNavigator.currentFragment().hasNavigationBar();
        isShowingActionBar = fragmentNavigator.currentFragment().hasActionBar();

        //adjustCurrentFragment();

        // Event Handler for bottom nav menu
        binding.navigationBar.setOnItemSelectedListener(item -> {
            fragmentNavigator.clear();// Clear navigation history. This is a design choice to not have a back button on a "main" menu
            switch (item.getItemId()){
                case R.id.menu_discovery:
                    changeFragment(new DiscoveryActivity(this));
                    break;
                case R.id.menu_insert_recipe:
                    changeFragment(new InsertRecipeActivity(this));
                    break;
                case R.id.menu_profile:
                    changeFragment(new ProfileActivity(this,Services.getUserManager().getCurrUser()));
                    break;
            }
            //adjustCurrentFragment();
            return true;
        });

    }

    private void copyDatabaseToDevice() {
        final String DB_PATH = "db";

        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try {

            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++) {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }

            copyAssetsToDirectory(assetNames, dataDirectory);
            System.out.println(assetNames.toString());

            Services.setDBPathName(dataDirectory.toString() + "/" + Services.getDBPathName());

        } catch (final IOException ioe) {
            System.out.println("Unable to access application data: " + ioe.getMessage());
        }
    }

    public void copyAssetsToDirectory(String[] assets, File directory) throws IOException {
        AssetManager assetManager = getAssets();

        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];
            System.out.println(copyPath);
            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists()) {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }


    // Gets called when the back button is pressed.
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        // Pop stack, switching between THESE menus should clear the history as the fragmentNavigator is used to handle submenus/subfragments
        // Make current top, current fragment
        switch (item.getItemId()) {
            case android.R.id.home:
                //Toast.makeText(this, "Back Button!", Toast.LENGTH_SHORT).show();
                fragmentNavigator.undoFragment();// Possible to edit this function to not update the display, then set it using the local function here to isolate code
                adjustCurrentFragment();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Public function to be used outside this class without needing to touch its caller
    public boolean changeFragment(ACCIFragment f){
        boolean hasChanged = fragmentNavigator.setFragment(f);
        adjustCurrentFragment();
        return hasChanged;
    }

    // Update layout variables, only if they are different.
    // This function needs to be called everytime a fragment is changed (so the main components update)
    // Probably a better way around this, but that's for later.
    public void adjustCurrentFragment(){
        ACCIFragment currentFragment = fragmentNavigator.currentFragment();

        // Only change if a change is required
        boolean curBack = currentFragment.hasBackButton();
        boolean curNavbar = currentFragment.hasNavigationBar();
        boolean curAction = currentFragment.hasActionBar();

        if(curBack != isShowingBackButton){
            getSupportActionBar().setDisplayHomeAsUpEnabled(curBack);
            isShowingBackButton = curBack;
        }
        if(curNavbar != isShowingNavigationBar) {
            setNavigationBar(curNavbar);
            isShowingNavigationBar = curNavbar;
        }
        if(curAction != isShowingActionBar){
            if(curAction){
                //getSupportActionBar().show();
                showActionBar();
            }
            else{
                //getSupportActionBar().hide();
                hideActionBar();
            }

            isShowingActionBar = curAction;
        }
    }

    public float convertDpToPixel(float dp){
        return dp * ((float) getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public void setNavigationBar(boolean showBar){
        if(showBar)
            showNavigationBar();
        else
            hideNavigationBar();
    }

    // To hide the nav bar in a constraint layout, we need to do 2 things
    // 1. Make the navigation bar invisible: set the height to 0.
    // 2. Make the fragment fullscreen/take its place: re-wire the constraint; tie the fragment to the bottom of the screen (instead of the top of the nav bar.)
    private void hideNavigationBar(){
        FragmentContainerView fragment = binding.currentFragment;
        BottomNavigationView bar = binding.navigationBar;
        ConstraintLayout layout = binding.container;

        bar.getLayoutParams().height = 0;

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(layout);
        constraintSet.connect(fragment.getId(),ConstraintSet.BOTTOM,layout.getId(),ConstraintSet.BOTTOM,0);
        constraintSet.applyTo(layout);
    }

    // Reset the height and constraints.
    private void showNavigationBar(){
        FragmentContainerView fragment = binding.currentFragment;
        BottomNavigationView bar = binding.navigationBar;
        ConstraintLayout layout = binding.container;

        bar.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(layout);
        constraintSet.connect(fragment.getId(),ConstraintSet.BOTTOM, bar.getId(),ConstraintSet.TOP,(int)convertDpToPixel(55));
        constraintSet.applyTo(layout);
    }

    private void hideActionBar(){
        getSupportActionBar().hide();

        FragmentContainerView fragment = binding.currentFragment;
        ConstraintLayout layout = binding.container;

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(layout);
        constraintSet.connect(fragment.getId(),ConstraintSet.TOP,layout.getId(),ConstraintSet.TOP,0);
        constraintSet.applyTo(layout);
    }

    // Reset the height and constraints.
    private void showActionBar(){
        getSupportActionBar().show();

        FragmentContainerView fragment = binding.currentFragment;
        ConstraintLayout layout = binding.container;

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(layout);
        constraintSet.connect(fragment.getId(),ConstraintSet.TOP,layout.getId(),ConstraintSet.TOP,(int)convertDpToPixel(55));
        constraintSet.applyTo(layout);
    }


}
