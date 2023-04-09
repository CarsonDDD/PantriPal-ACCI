package SystemTests;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotSelected;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.not;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;


import java.util.Random;

import comp3350.acci.R;
import comp3350.acci.presentation.MainActivity;


/*
    Author: Caelan Myskiw
    Date: 04/08/2023
    Purpose:
        This class contains the Recipe Creation system tests for running with Espresso
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
public class RecipeInsertionTests {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void insertRecipes() {
        //NOTE: This is to ensure you can just keep running the tests back and forth because espresso crashes if you try to scrollTo on the recycler view and there are multiple things with the same name
        Random rd = new Random();
        String title = "Toast " + rd.nextInt();
        //Insert a Recipe
        onView(withId(R.id.nav_insert_recipe)).perform(click());
        onView(withId(R.id.et_title)).perform(typeText(title));
        onView(withId(R.id.et_instructions)).perform(typeText("Put bread in toaster"));
        closeSoftKeyboard();
        onView(withId(R.id.sr_difficulty)).perform(click());
        onView(withText("Hard")).perform(click());

        onView(withId(R.id.btn_publish)).perform(click());


        //Make sure recipe was added
        onView(withId((R.id.nav_discovery))).perform(click());
        //see note above
        onView(withId(R.id.rv_recipelist)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText(title))));
        onView(withId(R.id.rv_recipelist)).perform(RecyclerViewActions.actionOnItem(hasDescendant(withText(title)), click()));
        //check that the recipe has the correct fields
        onView(withText(title)).check(matches(isDisplayed()));
        onView(withText("Put bread in toaster")).check(matches(isDisplayed()));
        onView(withText("Hard")).check(matches(isDisplayed()));
    }
}
