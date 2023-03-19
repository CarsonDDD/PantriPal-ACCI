package comp3350.acci.presentation;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.MenuRes;
import androidx.appcompat.widget.Toolbar;
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
import comp3350.acci.application.Services;
import comp3350.acci.presentation.fragments.ProfileViewFragment;
import comp3350.acci.presentation.fragments.DiscoveryViewFragment;
import comp3350.acci.presentation.fragments.RecipeInsertFragment;

// This class acts as the engine which runs/controls the fragment interactions
public class MainActivity extends AppCompatActivity {

    private FragmentNavigator fragmentNavigator;
    private @MenuRes int currentToolbarMenu = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        copyDatabaseToDevice();

        // Starting conditions for back button and ActionBar.
        //getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        // Set starting fragment
        fragmentNavigator = new FragmentNavigator(this.getSupportFragmentManager());
        fragmentNavigator.setFragment(new DiscoveryViewFragment());

        BottomNavigationView navigation = findViewById(R.id.navigation_bar);
        navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_discovery:
                        changeFragment(new DiscoveryViewFragment());
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
                fragmentNavigator.undoFragment();// Possible to edit this function to not update the display, then set it using the local function here to isolate code
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    // Public function to be used outside this class without needing to touch its caller
    public boolean changeFragment(Fragment f){
        // reset nav bar. this makes fragments need to give the flag only for false without lingering effects

        showNavigationBar(true);
        boolean hasChanged = fragmentNavigator.setFragment(f);
        return hasChanged;
    }

    public void showNavigationBar(boolean showBar){
        BottomNavigationView nav = findViewById(R.id.navigation_bar);
        if(showBar)
            nav.setVisibility(View.VISIBLE);
        else
            nav.setVisibility(View.GONE);
    }

    // Used from other fragments to populate apps toolbar with its own
    // while keeping the standard/cross fragment toolbar features (hamburger menu)
    // Passing -1 as menu will remove it
    public void setToolbar(Toolbar toolbar){
        setToolbar(toolbar, false);
    }

    public void setToolbar(Toolbar toolbar, boolean showBack){
        setToolbar(toolbar, -1, showBack);
    }

    public void setToolbar(Toolbar toolbar, @MenuRes int menu){
        setToolbar(toolbar, menu, false);
    }

    public void setToolbar(Toolbar toolbar, @MenuRes int menu, boolean showDrawer) {
        // Set toolbar
        setSupportActionBar(toolbar);

        // Set current toolbar. I dont know the proper way to do this. However InvalidateOptionsMenu() will recall onCreateOptionsMenu() with the new currentToolBarMenu
        currentToolbarMenu = menu;

        invalidateOptionsMenu(); // Might need to call this after showDrawer check. The internet says this calls onCreateOptionsMenu, wonder what else?
    }
}
