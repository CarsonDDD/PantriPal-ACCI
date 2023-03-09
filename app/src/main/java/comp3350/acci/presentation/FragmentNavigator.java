package comp3350.acci.presentation;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Stack;

import comp3350.acci.R;
import comp3350.acci.presentation.fragments.ACCIFragment;

public class FragmentNavigator {

    private Stack<ACCIFragment> fragmentHistory = new Stack<>();

    private FragmentManager manager;

    public FragmentNavigator(FragmentManager supportFragmentManger){
        manager = supportFragmentManger;
    }

    public ACCIFragment currentFragment(){
        return fragmentHistory.peek();
    }

    // returns if fragment changed
    public boolean setFragment(ACCIFragment f){
        fragmentHistory.push(f);
        return updateManager();
    }

    // returns if fragment changed
    public boolean undoFragment(){
        // Stack should ALWAYS contain a single element, if there are 0 elements there is no screen!
        if(fragmentHistory.size() > 1){
            fragmentHistory.pop();
            return updateManager();
        }

        return false;
    }

    public void clear(){
        fragmentHistory.clear();
    }

    // Updates the FragmentManager with the current fragment
    // returns if fragment changed
    private boolean updateManager(){
        if(fragmentHistory.size() > 0){
            FragmentTransaction fragmentTransaction = manager.beginTransaction();
            fragmentTransaction.replace(R.id.current_fragment, currentFragment());
            fragmentTransaction.commit();
            return true;
        }
        return false;
    }


}