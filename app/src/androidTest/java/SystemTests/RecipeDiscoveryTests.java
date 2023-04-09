package SystemTests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import comp3350.acci.R;
import comp3350.acci.presentation.MainActivity;


/*
    Author: Caelan Myskiw
    Date: 04/08/2023
    Purpose:
        This class contains the discovery system tests for running with Espresso
    Notes:
        These tests DO NOT WORK if you don't have animations turned off in the developer options
        These can be turned off by going to: settings > system > advanced > developer options (only available with the device in developer mode (tap software version 7 times))
        From here you can disable window animation scale, transition animation scale, animator duration scale (turn to animation off)
        This is necessary because RecyclerViewActions do not work with animations (they enjoy teleporting the app)


        These tests work best with a "Clean Slate" app.
        This means that you should clear the storage of the app before you run the system tests.
        This is because espresso can be buggy with duplicate Id's and things that are not exactly how it expects.

 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RecipeDiscoveryTests {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);
    @Test
    public void discoverRecipes() {
        onView(withId((R.id.nav_discovery))).perform(click());
        onView(withId(R.id.rv_recipelist)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }
}
