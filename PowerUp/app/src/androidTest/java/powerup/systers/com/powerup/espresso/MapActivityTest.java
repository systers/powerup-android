package powerup.systers.com.powerup.espresso;

import android.content.pm.ActivityInfo;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import powerup.systers.com.MapActivity;
import powerup.systers.com.R;

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
public class MapActivityTest {
    @Rule
    public ActivityTestRule<MapActivity> activityRule = new ActivityTestRule<>(
            MapActivity.class);


    public void viewTest() {

        onView(withId(R.id.HouseButton)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        onView(withId(R.id.HouseButton)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        onView(withId(R.id.HospitalButton)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        onView(withId(R.id.HospitalButton)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        onView(withId(R.id.SchoolButton)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        onView(withId(R.id.SchoolButton)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        onView(withId(R.id.BoyfriendButton)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        onView(withId(R.id.BoyfriendButton)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    @Test
    public void clickHouse() {
        onView(withId(R.id.HouseButton)).check(matches(isDisplayed())).perform(click());
    }

    @Test
    public void clickBoyFriend() {
        onView(withId(R.id.BoyfriendButton)).check(matches(isDisplayed())).perform(click());
    }

    @Test
    public void clickHospital() {
        onView(withId(R.id.HospitalButton)).check(matches(isDisplayed())).perform(click());
    }

    @Test
    public void clickSchool() {
        onView(withId(R.id.SchoolButton)).check(matches(isDisplayed())).perform(click());
    }

}
