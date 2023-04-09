package SystemTests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.Menu;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.OrderWith;
import org.junit.runner.RunWith;

import comp3350.acci.R;
import comp3350.acci.presentation.MainActivity;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EditProfileTests {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void editProfileName() {
        onView(withId(R.id.nav_profile)).perform(click());
        //open the options menu
        openActionBarOverflowOrOptionsMenu(ApplicationProvider.getApplicationContext());

        onView(withText("Edit Profile")).perform(click());
        //test the name change
        onView(withId(R.id.et_edit_name)).perform(replaceText("TestUser"));
        onView(withId(R.id.btn_edit_profile)).perform(click());

        onView(withId(R.id.toolbar)).check(matches(withChild(withText("TestUser"))));
        resetChanges();
    }
    @Test
    public void editProfileBio() {
        onView(withId(R.id.nav_profile)).perform(click());
        //test the bio change
        openActionBarOverflowOrOptionsMenu(ApplicationProvider.getApplicationContext());

        onView(withText("Edit Profile")).perform(click());
        //test the name change
        onView(withId(R.id.et_edit_bio)).perform(replaceText("This is a test bio!"));
        onView(withId(R.id.btn_edit_profile)).perform(click());

        onView(withChild(withText("This is a test bio!"))).check(matches(isDisplayed()));
        resetChanges();
    }


    @Test
    public void testCancel() {
        onView(withId(R.id.nav_profile)).perform(click());
        //check cancel button
        openActionBarOverflowOrOptionsMenu(ApplicationProvider.getApplicationContext());

        onView(withText("Edit Profile")).perform(click());
        //test the name change
        onView(withId(R.id.et_edit_name)).perform(replaceText("TestUser"));
        onView(withId(R.id.et_edit_bio)).perform(replaceText("This is a test bio!"));
        onView(withId(R.id.btn_cancel)).perform(click());

        onView(withId(R.id.toolbar)).check(matches(withChild(withText("GuestUser"))));
        onView(withChild(withText("This is the biography section for GuestUser"))).check(matches(isDisplayed()));
    }

    private void resetChanges() {
        //reset the changes
        openActionBarOverflowOrOptionsMenu(ApplicationProvider.getApplicationContext());
        onView(withText("Edit Profile")).perform(click());
        //test the name change
        onView(withId(R.id.et_edit_bio)).perform(replaceText("This is the biography section for GuestUser"));
        onView(withId(R.id.et_edit_name)).perform(replaceText("GuestUser"));
        onView(withId(R.id.btn_edit_profile)).perform(click());

        onView(withId(R.id.toolbar)).check(matches(withChild(withText("GuestUser"))));
        onView(withChild(withText("This is the biography section for GuestUser"))).check(matches(isDisplayed()));
    }
}
