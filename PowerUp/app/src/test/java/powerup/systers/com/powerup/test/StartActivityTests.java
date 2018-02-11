package powerup.systers.com.powerup.test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import powerup.systers.com.AvatarRoomActivity;
import powerup.systers.com.BuildConfig;
import powerup.systers.com.MapActivity;
import powerup.systers.com.R;
import powerup.systers.com.StartActivity;

import static junit.framework.Assert.assertEquals;


@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class StartActivityTests {
    private static final String IS_STARTED_KEY = "has_previously_started";
    private ActivityController<StartActivity> controller;
    private StartActivity activity;
    private SharedPreferences preferences;

    @Before
    public void setUp() {
        controller = Robolectric.buildActivity(StartActivity.class);
        preferences = PreferenceManager.getDefaultSharedPreferences(controller.get());
    }

    @Test
    public void shouldStartMap() {
        setPreviouslyStarted(true); // simulate that game has already been started

        activity = controller.create().get();

        activity.findViewById(R.id.startButtonMain).performClick();

        Intent excepted = new Intent(activity, MapActivity.class);
        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent actual = shadowActivity.getNextStartedActivity();

        assertEquals(excepted.getComponent(), actual.getComponent());
    }

    @Test
    public void shouldStartAvatarRoom() {
        setPreviouslyStarted(false); //  simulate that game has not already been started

        activity = controller.create().get();

        activity.findViewById(R.id.startButtonMain).performClick();

        Intent excepted = new Intent(activity, AvatarRoomActivity.class);
        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent actual = shadowActivity.getNextStartedActivity();

        assertEquals(excepted.getComponent(), actual.getComponent());
    }

    private void setPreviouslyStarted(boolean isStarted) {
        preferences.edit()
                .putBoolean(IS_STARTED_KEY, isStarted)
                .commit();
    }
}
