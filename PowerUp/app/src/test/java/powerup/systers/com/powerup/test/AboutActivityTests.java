package powerup.systers.com.powerup.test;

import org.junit.runner.RunWith;
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
import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = "AndroidManifest.xml",constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class AboutActivityTests {
    private StartActivity activity1 = Robolectric.buildActivity(StartActivity.class).create().get();
    @Test
    public void clickingAbout_shouldStartAboutActivity() {
        activity1.findViewById(R.id.aboutButtonMain).performClick();
        Intent expectedIntent = new Intent(activity1, AboutActivity.class);
        Intent actual = ShadowApplication.getInstance().getNextStartedActivity();
        assertEquals(expectedIntent.getComponent(), actual.getComponent());
    }
}
