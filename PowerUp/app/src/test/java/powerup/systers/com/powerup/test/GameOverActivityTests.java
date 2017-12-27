package powerup.systers.com.powerup.test;

import android.os.Build;
import android.widget.Button;
import android.content.Intent;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import powerup.systers.com.BuildConfig;
import powerup.systers.com.GameOverActivity;
import powerup.systers.com.MapActivity;
import powerup.systers.com.R;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by yuyuanluo on 12/27/17.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)

public class GameOverActivityTests {
    GameOverActivity activity;
    Button backToMap;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(GameOverActivity.class)
                .create()
                .resume()
                .get();
        backToMap = (Button) activity.findViewById(R.id.ContinueButtonMap);
    }

    @Test
    public void shouldNotBeNull() throws Exception {
        assertNotNull(activity);
    }

    @Test
    public void backToMap() throws Exception{
        Intent expectedIntent = new Intent(activity, MapActivity.class);

        backToMap.callOnClick();
        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();

        assertTrue(expectedIntent.filterEquals(actualIntent));
    }
}
