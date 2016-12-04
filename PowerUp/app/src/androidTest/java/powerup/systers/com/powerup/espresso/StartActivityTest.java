package powerup.systers.com.powerup.espresso;

import android.content.pm.ActivityInfo;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import powerup.systers.com.R;
import powerup.systers.com.StartActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


/**
 * Created by opticod(anupam) on 7/2/16.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class StartActivityTest {
    @Rule
    public ActivityTestRule<StartActivity> activityRule = new ActivityTestRule<>(
            StartActivity.class);


    public void viewTest() {

        onView(withId(R.id.powerupTextView)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        onView(withId(R.id.powerupTextView)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        onView(withId(R.id.imageView1)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        onView(withId(R.id.imageView1)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        onView(withId(R.id.startButtonMain)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        onView(withId(R.id.startButtonMain)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        onView(withId(R.id.newUserButtonFirstPage)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        onView(withId(R.id.newUserButtonFirstPage)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        onView(withId(R.id.aboutButtonMain)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        onView(withId(R.id.aboutButtonMain)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    @Test
    public void clickStart() {
        onView(withId(R.id.startButtonMain)).check(matches(isDisplayed())).perform(click());
    }

    @Test
    public void clickNewUser() {
        onView(withId(R.id.newUserButtonFirstPage)).check(matches(isDisplayed())).perform(click());
    }

    @Test
    public void clickAbout() {
        onView(withId(R.id.aboutButtonMain)).check(matches(isDisplayed())).perform(click());
    }

}
