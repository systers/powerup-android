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
public class AboutActivityTest {

    @Rule
    public ActivityTestRule<SplashActivity> mActivityTestRule = new ActivityTestRule<>(SplashActivity.class);

    @Test
    public void aboutActivityTest() {
        ViewInteraction button = onView(
allOf(withId(powerup.systers.com.R.id.aboutButtonMain),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
0),
isDisplayed()));
        button.perform(click());

        ViewInteraction button2 = onView(
allOf(withId(powerup.systers.com.R.id.aboutButtonMain),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
0),
isDisplayed()));
        button2.perform(click());

        ViewInteraction textView = onView(
allOf(withId(powerup.systers.com.R.id.about_title), withText("About"),
childAtPosition(
childAtPosition(
IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class),
0),
0),
isDisplayed()));
        textView.check(matches(withText("About")));

        ViewInteraction button3 = onView(
allOf(withId(powerup.systers.com.R.id.homeButton),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
1),
isDisplayed()));
        button3.check(matches(isDisplayed()));

        ViewInteraction textView2 = onView(
allOf(withText("> The Game"),
childAtPosition(
childAtPosition(
IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
0),
0),
isDisplayed()));
        textView2.check(matches(withText("> The Game")));

        ViewInteraction textView3 = onView(
allOf(withText("> Why this App is needed"),
childAtPosition(
childAtPosition(
IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
0),
1),
isDisplayed()));
        textView3.check(matches(withText("> Why this App is needed")));

        ViewInteraction textView4 = onView(
allOf(withText("> How does PowerUp help teenagers?"),
childAtPosition(
childAtPosition(
IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
0),
2),
isDisplayed()));
        textView4.check(matches(withText("> How does PowerUp help teenagers?")));

        ViewInteraction textView5 = onView(
allOf(withText("> The Game"),
childAtPosition(
childAtPosition(
withClassName(is("android.widget.ScrollView")),
0),
0)));
        textView5.perform(scrollTo(), click());

        ViewInteraction textView6 = onView(
allOf(withId(powerup.systers.com.R.id.about_the_game), withText("PowerUp is an educational choose-your-own-adventure game for girls between the age of 12 and 14. The game consists of different scenarios in which the user can choose between multiple answers and thereby lead the conversation into different directions. The user can create a personalized avatar by choosing clothes, hair, eye and skin color. Later on the avatar can be further customized and other accessories can be bought using points earned in the scenarios. Additional mini games contain key information surrounding reproductive health and provide a break from the storyline."),
childAtPosition(
childAtPosition(
IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
0),
1),
isDisplayed()));
        textView6.check(matches(isDisplayed()));

        ViewInteraction textView7 = onView(
allOf(withText("> The Game"),
childAtPosition(
childAtPosition(
withClassName(is("android.widget.ScrollView")),
0),
0)));
        textView7.perform(scrollTo(), click());

        ViewInteraction textView8 = onView(
allOf(withText("> Why this App is needed"),
childAtPosition(
childAtPosition(
withClassName(is("android.widget.ScrollView")),
0),
2)));
        textView8.perform(scrollTo(), click());

        ViewInteraction textView9 = onView(
allOf(withId(powerup.systers.com.R.id.about_the_urgency), withText("Increased exposure among preadolescents aged 12-14 to sexualized media has normalized engagement in sexual activity at younger ages. However information regarding sex is not very accessible to preadolescents. Only 65% of schools provide sex education and even less explain condom use. Having the talk at home is usually very awkward. Preadolescent girls need better options for reproductive health and self-esteem education. They are at risk for possible pregnancy and spread of sexually transmitted infection (STI), yet very little information reaches preadolescents about risk and responsibility associated with sex. Additionally, preadolescents struggle with self-esteem, which makes them vulnerable to peer and media influence. Current trends in sexual activity among preadolescents impact the economy, hurt family cohesion, affect female education, and burden healthcare systems."),
childAtPosition(
childAtPosition(
IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
0),
2),
isDisplayed()));
        textView9.check(matches(isDisplayed()));

        ViewInteraction textView10 = onView(
allOf(withText("> Why this App is needed"),
childAtPosition(
childAtPosition(
withClassName(is("android.widget.ScrollView")),
0),
2)));
        textView10.perform(scrollTo(), click());

        ViewInteraction textView11 = onView(
allOf(withText("> How does PowerUp help teenagers?"),
childAtPosition(
childAtPosition(
withClassName(is("android.widget.ScrollView")),
0),
4)));
        textView11.perform(scrollTo(), click());

        ViewInteraction textView12 = onView(
allOf(withId(powerup.systers.com.R.id.about_helping_by), withText("The app uses Social and Emotional Learning (SEL) to empower middle school girls to take ownership of their health and sexual activity. It is designed to increase access to reproductive health and self-esteem education by incorporating quality and relevant information surrounding those topics and it helps concretize corresponding concepts through interactive activities. The Content is based on SEL Core Competencies (Self-awareness, Self-management, Social awareness, Relationship skills, Decision making)."),
childAtPosition(
childAtPosition(
IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
0),
3),
isDisplayed()));
        textView12.check(matches(isDisplayed()));

        ViewInteraction textView13 = onView(
allOf(withText("> How does PowerUp help teenagers?"),
childAtPosition(
childAtPosition(
withClassName(is("android.widget.ScrollView")),
0),
4)));
        textView13.perform(scrollTo(), click());

        ViewInteraction button4 = onView(
allOf(withId(powerup.systers.com.R.id.homeButton),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
0),
isDisplayed()));
        button4.perform(click());

        ViewInteraction button5 = onView(
allOf(withId(powerup.systers.com.R.id.homeButton),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
0),
isDisplayed()));
        button5.perform(click());

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
