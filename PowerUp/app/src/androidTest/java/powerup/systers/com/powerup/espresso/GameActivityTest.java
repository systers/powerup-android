package powerup.systers.com.powerup.espresso;

import android.content.pm.ActivityInfo;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import powerup.systers.com.GameActivity;
import powerup.systers.com.R;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

/**
 * Created by opticod(anupam) on 6/2/16.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class GameActivityTest {
    @Rule
    public ActivityTestRule<GameActivity> activityRule = new ActivityTestRule<>(
            GameActivity.class);


    public void viewTest() {

        onView(withId(R.id.scenarioNameEditText)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        onView(withId(R.id.scenarioNameEditText)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        onView(withId(R.id.mainListView)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        onView(withId(R.id.mainListView)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        onView(withId(R.id.continueButtonGoesToMap)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        onView(withId(R.id.continueButtonGoesToMap)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        onView(withId(R.id.redoButton)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        onView(withId(R.id.redoButton)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        onView(withId(R.id.askerImageView)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        onView(withId(R.id.askerImageView)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        onView(withId(R.id.powerBarView)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        onView(withId(R.id.powerBarView)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        onView(withId(R.id.powerbarHealing)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        onView(withId(R.id.powerbarHealing)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        onView(withId(R.id.powerbarStrength)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        onView(withId(R.id.powerbarStrength)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        onView(withId(R.id.powerbarTelepathy)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        onView(withId(R.id.powerbarTelepathy)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        onView(withId(R.id.powerbarInvisibility)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        onView(withId(R.id.powerbarInvisibility)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        onView(withId(R.id.questionView)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        onView(withId(R.id.questionView)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        onView(withId(R.id.faceImageView)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        onView(withId(R.id.faceImageView)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        onView(withId(R.id.eyeImageView)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        onView(withId(R.id.eyeImageView)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        onView(withId(R.id.hairImageView)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        onView(withId(R.id.hairImageView)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        onView(withId(R.id.clothImageView)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        onView(withId(R.id.clothImageView)).check(matches(isDisplayed()));
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    @Test
    public void clickEvents() {

        onData(is(instanceOf(java.lang.String.class)))
                .inAdapterView(withId(R.id.mainListView))
                .atPosition(0)
                .check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.redoButton)).perform(click());
        onView(withId(R.id.continueButtonGoesToMap)).perform(click());

    }

}
