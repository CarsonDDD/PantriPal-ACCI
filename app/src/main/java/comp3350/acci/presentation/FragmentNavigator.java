package comp3350.acci.presentation;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Stack;

import comp3350.acci.R;

public class FragmentNavigator {

    private Stack<Fragment> fragmentHistory = new Stack<>();

    private FragmentManager manager;

    public FragmentNavigator(FragmentManager supportFragmentManger){
        manager = supportFragmentManger;
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

    public Fragment currentFragment(){
        return fragmentHistory.peek();
    }

    // returns if fragment changed
    public boolean setFragment(Fragment f){
        fragmentHistory.push(f);
        return updateManager();
    }

    // returns if fragment changed
    public boolean undoFragment(){
        if(fragmentHistory.size() > 1){
            fragmentHistory.pop();
            return updateManager();
        }

        return false;
    }

    public void clear(){
        fragmentHistory.clear();
    }


}
