package powerup.systers.com.powerup.test;

import android.content.Intent;
import android.os.Build;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import powerup.systers.com.AboutActivity;
import powerup.systers.com.BuildConfig;
import powerup.systers.com.R;
import powerup.systers.com.StartActivity;

import static junit.framework.Assert.assertEquals;

/**
 * Created by nihar on 23/12/17.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class AboutButtonTest {
    @Test
    public void testAboutButton() {
        StartActivity activity = Robolectric.setupActivity(StartActivity.class);
        activity.findViewById(R.id.aboutButtonMain).performClick();

        Intent expectedIntent = new Intent(activity, AboutActivity.class);
        Intent actual = ShadowApplication.getInstance().getNextStartedActivity();
        assertEquals(expectedIntent.getComponent(), actual.getComponent());
    }
}
