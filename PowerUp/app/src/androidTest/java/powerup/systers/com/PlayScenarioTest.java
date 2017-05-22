package powerup.systers.com;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
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
public class PlayScenarioTest {

    @Rule
    public ActivityTestRule<StartActivity> mActivityTestRule = new ActivityTestRule<>(StartActivity.class);

    @Test
    public void playScenarioTest() {
        ViewInteraction imageButton = onView(
                allOf(withId(R.id.startButtonMain), isDisplayed()));
        imageButton.perform(click());

        ViewInteraction button = onView(
                allOf(withId(R.id.continueButtonAvatar), isDisplayed()));
        button.perform(click());

        ViewInteraction button2 = onView(
                allOf(withId(R.id.continueButton), isDisplayed()));
        button2.perform(click());

        ViewInteraction button3 = onView(
                allOf(withId(R.id.BoyfriendButton), withText("Boyfriend"), isDisplayed()));
        button3.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.rowTextView), withText("Maybe another time."),
                        childAtPosition(
                                withId(R.id.mainListView),
                                0),
                        isDisplayed()));
        textView.perform(click());

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.rowTextView), withText("Returns Home"),
                        childAtPosition(
                                withId(R.id.mainListView),
                                0),
                        isDisplayed()));
        textView2.perform(click());

        ViewInteraction imageButton2 = onView(
                allOf(withId(R.id.continueButton), isDisplayed()));
        imageButton2.perform(click());

        ViewInteraction button4 = onView(
                allOf(withId(R.id.continueButtonGoesToMap), isDisplayed()));
        button4.perform(click());

        ViewInteraction button5 = onView(
                allOf(withId(R.id.SchoolButton), withText("School"), isDisplayed()));
        button5.perform(click());

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.rowTextView), withText("Sure"),
                        childAtPosition(
                                withId(R.id.mainListView),
                                1),
                        isDisplayed()));
        textView3.perform(click());

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.rowTextView), withText("Can we talk about this?"),
                        childAtPosition(
                                withId(R.id.mainListView),
                                0),
                        isDisplayed()));
        textView4.perform(click());

        ViewInteraction imageButton3 = onView(
                allOf(withId(R.id.continueButton), isDisplayed()));
        imageButton3.perform(click());

        ViewInteraction button6 = onView(
                allOf(withId(R.id.continueButtonGoesToMap), isDisplayed()));
        button6.perform(click());

        ViewInteraction button7 = onView(
                allOf(withId(R.id.HospitalButton), withText("Hospital"), isDisplayed()));
        button7.perform(click());

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.rowTextView), withText("Sure"),
                        childAtPosition(
                                withId(R.id.mainListView),
                                1),
                        isDisplayed()));
        textView5.perform(click());

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.rowTextView), withText("Sure"),
                        childAtPosition(
                                withId(R.id.mainListView),
                                1),
                        isDisplayed()));
        textView6.perform(click());

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.rowTextView), withText("Do you have condoms?"),
                        childAtPosition(
                                withId(R.id.mainListView),
                                0),
                        isDisplayed()));
        textView7.perform(click());

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.rowTextView), withText("Let’s go to the store and buy some."),
                        childAtPosition(
                                withId(R.id.mainListView),
                                0),
                        isDisplayed()));
        textView8.perform(click());

        ViewInteraction textView9 = onView(
                allOf(withId(R.id.rowTextView), withText("\"Ok I am going home\""),
                        childAtPosition(
                                withId(R.id.mainListView),
                                0),
                        isDisplayed()));
        textView9.perform(click());

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
