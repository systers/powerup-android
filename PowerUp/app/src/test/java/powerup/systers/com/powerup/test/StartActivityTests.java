package powerup.systers.com.powerup.test;

import android.content.Intent;
import android.os.Build;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import powerup.systers.com.AboutActivity;
import powerup.systers.com.BuildConfig;
import powerup.systers.com.R;
import powerup.systers.com.StartActivity;
import static junit.framework.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class StartActivityTests {
    private StartActivity activity;

    @Before
    public void setUp() {
        activity = Robolectric.setupActivity(StartActivity.class);
    }

    @Test
    public void aboutActivityStartedOnAboutClick() {
        activity.findViewById(R.id.aboutButtonMain).performClick();
        Intent expectedIntent = new Intent(activity, AboutActivity.class);
        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        assertTrue(actualIntent.filterEquals(expectedIntent));
    }
}