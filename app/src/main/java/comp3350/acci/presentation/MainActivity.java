package comp3350.acci.presentation;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import comp3350.acci.R;
import comp3350.acci.presentation.fragments.PantryFragment;
import comp3350.acci.presentation.fragments.RecipeEditFragment;
import comp3350.acci.presentation.fragments.SearchViewFragment;
import comp3350.acci.application.Services;
import comp3350.acci.business.listeners.RecipeClickListener;
import comp3350.acci.objects.Recipe;
import comp3350.acci.presentation.fragments.ProfileViewFragment;
import comp3350.acci.presentation.fragments.DiscoveryViewFragment;
import comp3350.acci.presentation.fragments.RecipeInsertFragment;
import comp3350.acci.presentation.fragments.RecipeViewFragment;

// This class acts as the engine which runs/controls the fragment interactions
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // Lock screen orientation

        copyDatabaseToDevice();

        changeFragment(new DiscoveryViewFragment());

        BottomNavigationView navigation = findViewById(R.id.navigation_bar);
        navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_discovery:
                        changeFragment(new DiscoveryViewFragment());
                        break;
                    case R.id.nav_search:
                        changeFragment(new SearchViewFragment());
                        break;
                    case R.id.nav_pantry:
                        Toast.makeText(MainActivity.super.getBaseContext(), "View Pantry!", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_insert_recipe:
                        changeFragment(new RecipeInsertFragment());
                        break;
                    case R.id.nav_profile:
                        changeFragment(new ProfileViewFragment(Services.getUserManager().getCurrUser()));
                        break;
                }
                return true;
            }
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
                undoFragment();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    // Public function to be used outside this class without needing to touch its caller
    public void changeFragment(Fragment fragment){
        this.getSupportFragmentManager().beginTransaction()
                .replace(R.id.current_fragment,fragment)
                .addToBackStack(null)
                .commit();
    }

    public void undoFragment(){
        if(getSupportFragmentManager().getBackStackEntryCount() > 1){
            getSupportFragmentManager().popBackStack();
            updateNavigationBar();
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        undoFragment();
    }

    // Make sure the navigation bar has the correct fragment checked
    private void updateNavigationBar(){
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.current_fragment);
        BottomNavigationView navbar = findViewById(R.id.navigation_bar);

        // hmmmmmm. I believe .setSelectedItemId calls the above fragment switching code. This effectively modifies the stack in a way where nav buttons cannot be back swiped.
        // This was the old behaviour before using the manager stack. Writing this code, I did not intent for this to work like this, nonetheless I am pleasantly pleased.
        if(currentFragment instanceof DiscoveryViewFragment){
            navbar.setSelectedItemId(R.id.nav_discovery);
        }
        else if(currentFragment instanceof SearchViewFragment){
            navbar.setSelectedItemId(R.id.nav_search);
        }
        else if(currentFragment instanceof PantryFragment){
            navbar.setSelectedItemId(R.id.nav_pantry);
        }
        else if(currentFragment instanceof RecipeInsertFragment){
            navbar.setSelectedItemId(R.id.nav_insert_recipe);
        }
        else if(currentFragment instanceof ProfileViewFragment && ((ProfileViewFragment)currentFragment).isCurrentUser()){
            navbar.setSelectedItemId(R.id.nav_profile);
        }

        // else.... nothing.
    }

    public void showNavigationBar(boolean showBar){
        BottomNavigationView nav = findViewById(R.id.navigation_bar);
        if(showBar)
            nav.setVisibility(View.VISIBLE);
        else
            nav.setVisibility(View.GONE);
    }

    public final RecipeClickListener CLICK_RECIPE = new RecipeClickListener() {
        @Override
        public void onRecipeClick(Recipe recipe) {
            //getAppCompact().changeFragment(new RecipeViewFragment(getAppCompact(), recipe));
            changeFragment(new RecipeViewFragment(recipe));
        }
    };
}
