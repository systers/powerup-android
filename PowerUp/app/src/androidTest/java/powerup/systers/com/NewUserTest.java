package powerup.systers.com;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class NewUserTest {

    @Rule
    public ActivityTestRule<StartActivity> mActivityTestRule = new ActivityTestRule<>(StartActivity.class);

    @Test
    public void newUserTest() {
        ViewInteraction imageButton = onView(
                allOf(withId(R.id.newUserButtonFirstPage), isDisplayed()));
        imageButton.perform(click());

        ViewInteraction imageButton2 = onView(
                allOf(withId(R.id.eyeRight), isDisplayed()));
        imageButton2.perform(click());

        ViewInteraction imageButton3 = onView(
                allOf(withId(R.id.eyeRight), isDisplayed()));
        imageButton3.perform(click());

        ViewInteraction imageButton4 = onView(
                allOf(withId(R.id.eyeLeft), isDisplayed()));
        imageButton4.perform(click());

        ViewInteraction imageButton5 = onView(
                allOf(withId(R.id.hairRight), isDisplayed()));
        imageButton5.perform(click());

        ViewInteraction imageButton6 = onView(
                allOf(withId(R.id.faceRight), isDisplayed()));
        imageButton6.perform(click());

        ViewInteraction imageButton7 = onView(
                allOf(withId(R.id.faceRight), isDisplayed()));
        imageButton7.perform(click());

        ViewInteraction imageButton8 = onView(
                allOf(withId(R.id.faceLeft), isDisplayed()));
        imageButton8.perform(click());

        ViewInteraction imageButton9 = onView(
                allOf(withId(R.id.clotheLeft), isDisplayed()));
        imageButton9.perform(click());

        ViewInteraction imageButton10 = onView(
                allOf(withId(R.id.clotheRight), isDisplayed()));
        imageButton10.perform(click());

        ViewInteraction imageButton11 = onView(
                allOf(withId(R.id.clotheRight), isDisplayed()));
        imageButton11.perform(click());

        ViewInteraction button = onView(
                allOf(withId(R.id.continueButtonAvatar), isDisplayed()));
        button.perform(click());

        ViewInteraction button2 = onView(
                allOf(withId(R.id.backButton), withText("Back"), isDisplayed()));
        button2.perform(click());

        ViewInteraction imageButton12 = onView(
                allOf(withId(R.id.hairLeft), isDisplayed()));
        imageButton12.perform(click());

        ViewInteraction imageButton13 = onView(
                allOf(withId(R.id.hairLeft), isDisplayed()));
        imageButton13.perform(click());

        ViewInteraction button3 = onView(
                allOf(withId(R.id.continueButtonAvatar), isDisplayed()));
        button3.perform(click());

        ViewInteraction button4 = onView(
                allOf(withId(R.id.continueButton), isDisplayed()));
        button4.perform(click());

    }

}
