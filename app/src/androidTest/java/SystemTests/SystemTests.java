package SystemTests;


import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.anything;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;


import comp3350.acci.R;
import comp3350.acci.presentation.MainActivity;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SystemTests {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void discoverRecipes() {
        System.out.println("\n\nStarting System tests\n\n");

        //Insert a Recipe
        onView(withId(R.id.nav_insert_recipe)).perform(click());
        onView(withId(R.id.et_title)).perform(typeText("Toast"));
        onView(withId(R.id.et_instructions)).perform(typeText("Put bread in toaster"));
        onView(withId(R.id.sr_difficulty)).perform(click());
        onView(withText("Hard")).perform(click());

        closeSoftKeyboard();

        //Make sure recipe was added
        onView(withId((R.id.nav_discovery))).perform(click());
        onData(anything()).inAdapterView(withId(R.id.rv_recipelist)).atPosition(0).perform(click());

//        onView(withId(R.id.rv_recipelist)).perform(RecyclerViewAction.scrollTo(hasDescendant(withText("Toast"))));


    }
}
