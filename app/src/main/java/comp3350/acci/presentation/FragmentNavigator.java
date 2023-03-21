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

    public Fragment currentFragment(){
        return fragmentHistory.peek();
    }

    // returns if fragment changed
    public void setFragment(Fragment fragment){
        /*fragmentHistory.push(f);
        updateManager();*/
        manager.beginTransaction()
                .replace(R.id.current_fragment,fragment)
                .addToBackStack(null)
                .commit();
    }

    public void undoFragment() {
        // Stack should ALWAYS contain a single element, if there are 0 elements there is no screen!
        if(manager.getBackStackEntryCount() > 1){
            /*fragmentHistory.pop();
            updateManager();*/
            manager.popBackStack();
        }
    }

    public void clear(){
        fragmentHistory.clear();
    }

    // Updates the FragmentManager with the current fragment
    // returns if fragment changed
    private void updateManager(){
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.current_fragment, currentFragment());
        fragmentTransaction.commit();
    }


}
