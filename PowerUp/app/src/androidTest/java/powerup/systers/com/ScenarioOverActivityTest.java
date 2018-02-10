package powerup.systers.com;


import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

import powerup.systers.com.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ScenarioOverActivityTest {

    @Rule
    public ActivityTestRule<SplashActivity> mActivityTestRule = new ActivityTestRule<>(SplashActivity.class);

    @Test
    public void scenarioOverActivityTest() {
         // Added a sleep statement to match the app's execution delay.
 // The recommended way to handle such scenarios is to use Espresso idling resources:
  // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
try {
 Thread.sleep(1000);
 } catch (InterruptedException e) {
 e.printStackTrace();
 }
        
        ViewInteraction button = onView(
allOf(withId(R.id.newUserButtonFirstPage),
childAtPosition(
childAtPosition(
withClassName(is("android.widget.LinearLayout")),
2),
0),
isDisplayed()));
        button.perform(click());
        
        ViewInteraction appCompatButton = onView(
allOf(withId(android.R.id.button1), withText("Create new Avatar"),
childAtPosition(
childAtPosition(
withId(R.id.buttonPanel),
0),
3)));
        appCompatButton.perform(scrollTo(), click());
        
        ViewInteraction imageView = onView(
allOf(withId(R.id.continueButtonAvatar),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
1),
isDisplayed()));
        imageView.perform(click());
        
        ViewInteraction imageView2 = onView(
allOf(withId(R.id.house),
childAtPosition(
childAtPosition(
withClassName(is("android.widget.LinearLayout")),
0),
1),
isDisplayed()));
        imageView2.perform(click());
        
        DataInteraction textView = onData(anything())
.inAdapterView(allOf(withId(R.id.mainListView),
childAtPosition(
withId(R.id.root),
4)))
.atPosition(0);
        textView.perform(click());
        
        DataInteraction textView2 = onData(anything())
.inAdapterView(allOf(withId(R.id.mainListView),
childAtPosition(
withId(R.id.root),
4)))
.atPosition(0);
        textView2.perform(click());
        
        DataInteraction textView3 = onData(anything())
.inAdapterView(allOf(withId(R.id.mainListView),
childAtPosition(
withId(R.id.root),
4)))
.atPosition(0);
        textView3.perform(click());
        
        DataInteraction textView4 = onData(anything())
.inAdapterView(allOf(withId(R.id.mainListView),
childAtPosition(
withId(R.id.root),
4)))
.atPosition(0);
        textView4.perform(click());
        
        ViewInteraction textView5 = onView(
allOf(withId(R.id.karmaPoints), withText("8"),
childAtPosition(
allOf(withId(R.id.root),
childAtPosition(
withId(android.R.id.content),
0)),
1),
isDisplayed()));
        textView5.check(matches(withText("8")));
        
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
                        && view.equals(((ViewGroup)parent).getChildAt(position));
            }
        };
    }
    }
