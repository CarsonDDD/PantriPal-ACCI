package SystemTests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressBack;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;

import androidx.recyclerview.widget.RecyclerView;
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
        This class contains the save recipe acceptance tests for running with Espresso
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
public class SaveRecipeTests {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);
    @Test
    public void saveRecipes() {
        //This will break if the item "Peanut Butter and Jelly" does not exist or is created by the current user
        //save the recipe (if this is not a different user's recipe this test will not work
        onView(withId((R.id.nav_discovery))).perform(click());
        onView(withId(R.id.rv_recipelist)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Peanut Butter and Jelly"))));
        onView(withId(R.id.rv_recipelist)).perform(RecyclerViewActions.actionOnItem(hasDescendant(withText("Peanut Butter and Jelly")), click()));

        //make sure it's someone elses recipe
        onView(withId(R.id.tv_author)).check(matches(not("GuestUser")));

        //save recipe
        onView(withId(R.id.action_save_recipe)).perform(click());


        //check that it got saved
        onView(withId((R.id.nav_profile))).perform(click());

        onView(withText("SAVED RECIPES")).perform(click());
        //sometimes this crashes the test, presumably the reason is the app is lagging on the emulated device and is struggling to keep up with espresso's inputs (atleast in my experience) it DOES work most of the time (when espresso works)
        onView(withId(R.id.saved_recipes_recycler)).perform(RecyclerViewActions.actionOnItem(hasDescendant(withText("Peanut Butter and Jelly")), click()));

        //unsave the recipe
        onView(withId(R.id.action_save_recipe)).perform(click());
        //make sure the recipe is no longer present in our saved recipes (is unsaved)
        onView(withId(R.id.toolbar)).perform(pressBack());
        onView(withText("SAVED RECIPES")).perform(click());
        onView(withId(R.id.saved_recipes_recycler)).check(matches(not(hasDescendant(withText("Peanut Butter and Jelly")))));

    }

}
