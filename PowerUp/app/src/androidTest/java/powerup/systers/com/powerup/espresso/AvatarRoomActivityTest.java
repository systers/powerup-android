package powerup.systers.com.powerup.espresso;

import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.widget.ImageView;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import powerup.systers.com.AvatarRoomActivity;
import powerup.systers.com.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.v4.content.res.ResourcesCompat.getDrawable;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AvatarRoomActivityTest {
    @Rule
    public ActivityTestRule<AvatarRoomActivity> activityRule = new ActivityTestRule<>(
            AvatarRoomActivity.class);

    @Test
    public void viewTest() {
        onView(withId(R.id.eyes)).check(matches(isDisplayed()));
        onView(withId(R.id.hair)).check(matches(isDisplayed()));
        onView(withId(R.id.face)).check(matches(isDisplayed()));
        onView(withId(R.id.clothes)).check(matches(isDisplayed()));
        onView(withId(R.id.eyeLeft)).check(matches(isDisplayed()));
        onView(withId(R.id.eyeRight)).check(matches(isDisplayed()));
        onView(withId(R.id.hairLeft)).check(matches(isDisplayed()));
        onView(withId(R.id.hairRight)).check(matches(isDisplayed()));
        onView(withId(R.id.faceLeft)).check(matches(isDisplayed()));
        onView(withId(R.id.faceRight)).check(matches(isDisplayed()));
        onView(withId(R.id.clotheLeft)).check(matches(isDisplayed()));
        onView(withId(R.id.clotheRight)).check(matches(isDisplayed()));
        onView(withId(R.id.continueButtonAvatar)).check(matches(isDisplayed()));
    }

    @Test
    public void checkIntent() {
        onView(withId(R.id.continueButtonAvatar)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.continueButton)).check(matches(isDisplayed()));
    }

    @Test
    public void clickEvents() {
        String[] eyeList = new String[]{"eye3", "eye2", "eye1"};
        String[] faceList = new String[]{"face3", "face2", "face1"};
        String[] clothList = new String[]{"cloth4", "cloth3", "cloth2", "cloth1"};
        String[] hairList = new String[]{"hair1"};

        for (int i = 0; i < eyeList.length; i++) {
            activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            onView(withId(R.id.eyeLeft)).perform(click());
            R.drawable ourRID = new R.drawable();
            java.lang.reflect.Field photoNameField;
            try {
                photoNameField = ourRID.getClass().getField(eyeList[i]);
                onView(withId(R.id.eyes)).check(matches(new DrawableMatcher(photoNameField.getInt(ourRID))));
                onView(withId(R.id.eyeLeft)).perform(click());
                onView(withId(R.id.eyeRight)).perform(click());
                onView(withId(R.id.eyes)).check(matches(new DrawableMatcher(photoNameField.getInt(ourRID))));
                activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                onView(withId(R.id.eyes)).check(matches(new DrawableMatcher(photoNameField.getInt(ourRID))));
            } catch (NoSuchFieldException | IllegalAccessException
                    | IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < faceList.length; i++) {
            activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            onView(withId(R.id.faceLeft)).perform(click());
            R.drawable ourRID = new R.drawable();
            java.lang.reflect.Field photoNameField;
            try {
                photoNameField = ourRID.getClass().getField(faceList[i]);
                onView(withId(R.id.face)).check(matches(new DrawableMatcher(photoNameField.getInt(ourRID))));
                onView(withId(R.id.faceLeft)).perform(click());
                onView(withId(R.id.faceRight)).perform(click());
                onView(withId(R.id.face)).check(matches(new DrawableMatcher(photoNameField.getInt(ourRID))));
                activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                onView(withId(R.id.face)).check(matches(new DrawableMatcher(photoNameField.getInt(ourRID))));
            } catch (NoSuchFieldException | IllegalAccessException
                    | IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < clothList.length; i++) {
            activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            onView(withId(R.id.clotheLeft)).perform(click());
            R.drawable ourRID = new R.drawable();
            java.lang.reflect.Field photoNameField;
            try {
                photoNameField = ourRID.getClass().getField(clothList[i]);
                onView(withId(R.id.clothes)).check(matches(new DrawableMatcher(photoNameField.getInt(ourRID))));
                onView(withId(R.id.clotheLeft)).perform(click());
                onView(withId(R.id.clotheRight)).perform(click());
                onView(withId(R.id.clothes)).check(matches(new DrawableMatcher(photoNameField.getInt(ourRID))));
                activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                onView(withId(R.id.clothes)).check(matches(new DrawableMatcher(photoNameField.getInt(ourRID))));
            } catch (NoSuchFieldException | IllegalAccessException
                    | IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < hairList.length; i++) {
            activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            onView(withId(R.id.hairLeft)).perform(click());
            R.drawable ourRID = new R.drawable();
            java.lang.reflect.Field photoNameField;
            try {
                photoNameField = ourRID.getClass().getField(hairList[i]);
                onView(withId(R.id.hair)).check(matches(new DrawableMatcher(photoNameField.getInt(ourRID))));
                onView(withId(R.id.hairLeft)).perform(click());
                onView(withId(R.id.hairRight)).perform(click());
                onView(withId(R.id.hair)).check(matches(new DrawableMatcher(photoNameField.getInt(ourRID))));
                activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                onView(withId(R.id.hair)).check(matches(new DrawableMatcher(photoNameField.getInt(ourRID))));
            } catch (NoSuchFieldException | IllegalAccessException
                    | IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    public class DrawableMatcher extends TypeSafeMatcher<View> {

        private final int expectedId;
        private String resourceName;

        public DrawableMatcher(int expectedId) {
            super(View.class);
            this.expectedId = expectedId;
        }

        @Override
        protected boolean matchesSafely(View target) {
            if (!(target instanceof ImageView)) {
                return false;
            }
            ImageView imageView = (ImageView) target;
            if (expectedId < 0) {
                return imageView.getDrawable() == null;
            }
            Resources resources = target.getContext().getResources();
            Drawable expectedDrawable = getDrawable(resources, expectedId, target.getContext().getTheme());
            resourceName = resources.getResourceEntryName(expectedId);
            if (expectedDrawable == null) {
                return false;
            }
            Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            Bitmap expectedBitmap = ((BitmapDrawable) expectedDrawable).getBitmap();
            return bitmap.sameAs(expectedBitmap);
        }

        @Override
        public void describeTo(Description description) {
            description.appendValue(expectedId);
            description.appendText(":");
            if (resourceName != null) {
                description.appendText(resourceName);
            }
        }
    }
}