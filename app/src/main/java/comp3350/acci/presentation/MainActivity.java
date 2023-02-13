package comp3350.acci.presentation;

import android.app.ActionBar;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import comp3350.acci.R;
import comp3350.acci.databinding.ActivityMainBinding;
import comp3350.acci.presentation.discovery.DiscoveryActivity;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new DiscoveryActivity(this));


        binding.navigationBar.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.menu_discovery:
                    replaceFragment(new DiscoveryActivity(this));
                    break;
                case R.id.menu_insert_recipe:
                    replaceFragment(new InsertRecipeActivity(this));
                    break;
                /*case R.id.menu_profile:
                    replaceFragment(new ProfileActivity());
                    break;*/
            }
            return true;
        });

    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.current_fragment, fragment);
        fragmentTransaction.commit();
    }


}
