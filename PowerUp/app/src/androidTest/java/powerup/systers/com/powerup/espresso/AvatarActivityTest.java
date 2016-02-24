package powerup.systers.com.powerup.espresso;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import powerup.systers.com.AvatarActivity;
import powerup.systers.com.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AvatarActivityTest {
    @Rule
    public ActivityTestRule<AvatarActivity> activityRule = new ActivityTestRule<>(
            AvatarActivity.class);

    @Test
    public void viewTest() {
        onView(withId(R.id.faceView)).check(matches(isDisplayed()));
        onView(withId(R.id.eyeView)).check(matches(isDisplayed()));
        onView(withId(R.id.hairView)).check(matches(isDisplayed()));
        onView(withId(R.id.clothView)).check(matches(isDisplayed()));
        onView(withId(R.id.continueButton)).check(matches(isDisplayed()));
    }

    @Test
    public void clickEvents() {
        onView(withId(R.id.continueButton)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.HouseButton)).check(matches(isDisplayed()));
        onView(withId(R.id.HospitalButton)).check(matches(isDisplayed()));
        onView(withId(R.id.SchoolButton)).check(matches(isDisplayed()));
        onView(withId(R.id.BoyfriendButton)).check(matches(isDisplayed()));
    }
}
