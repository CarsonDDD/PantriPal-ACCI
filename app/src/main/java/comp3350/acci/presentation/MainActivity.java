package comp3350.acci.presentation;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.MenuRes;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
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
import comp3350.acci.presentation.fragments.ProfileViewFragment;
import comp3350.acci.presentation.fragments.DiscoveryViewFragment;
import comp3350.acci.presentation.fragments.ACCIFragment;
import comp3350.acci.presentation.fragments.RecipeInsertFragment;

// This class acts as the engine which runs/controls the fragment interactions
public class MainActivity extends AppCompatActivity {

    private FragmentNavigator fragmentNavigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        copyDatabaseToDevice();

        // Starting conditions for back button and ActionBar.
        //getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        // Set starting fragment
        fragmentNavigator = new FragmentNavigator(this.getSupportFragmentManager());
        fragmentNavigator.setFragment(new DiscoveryViewFragment(this));
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

    // Public function to be used outside this class without needing to touch its caller
    public boolean changeFragment(ACCIFragment f){
        boolean hasChanged = fragmentNavigator.setFragment(f);
        //adjustLayoutToCurrentFragment();
        return hasChanged;
    }

    public void setNavigationBar(boolean showBar){
        BottomNavigationView nav = findViewById(R.id.navigation_bar);
        if(showBar)
            nav.setVisibility(View.VISIBLE);
        else
            nav.setVisibility(View.GONE);
    }
}
