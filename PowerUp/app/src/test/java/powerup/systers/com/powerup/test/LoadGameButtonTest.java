package powerup.systers.com.powerup.test;

/**
 * Created by yuyuanluo on 12/26/17.
 */
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.widget.Button;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.RuntimeEnvironment;

import powerup.systers.com.AvatarRoomActivity;
import powerup.systers.com.BuildConfig;
import powerup.systers.com.MapActivity;
import powerup.systers.com.R;
import powerup.systers.com.StartActivity;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)

public class LoadGameButtonTest {

    StartActivity activity;
    Context context;
    SharedPreferences.Editor editor;
    Button loadGame;

    @Before
    public void setup() throws Exception{
        activity = Robolectric.buildActivity(StartActivity.class)
                .create()
                .resume()
                .get();
        context = RuntimeEnvironment.application.getApplicationContext();
        editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        loadGame = (Button) activity.findViewById(R.id.startButtonMain);

    }

    @Test
    public void shouldNotBeNull() throws Exception {
        assertNotNull(activity);
    }

    @Test
    public void loadGameShouldLaunchMapActivity() throws Exception{
        editor.putBoolean(context.getString(R.string.preferences_has_previously_started), Boolean.TRUE);
        editor.apply();

        loadGame.callOnClick();

        Intent expectedIntent = new Intent(activity, AvatarRoomActivity.class);
        ShadowActivity shadowActivity=Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();

        assertTrue(expectedIntent.filterEquals(actualIntent));
    }

    @Test
    public void loadGameShouldLaunchAvatarRoomActivity() throws Exception{
        editor.putBoolean(context.getString(R.string.preferences_has_previously_started), Boolean.FALSE);
        editor.apply();

        loadGame.callOnClick();

        Intent expectedIntent = new Intent(activity, AvatarRoomActivity.class);
        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();

        assertTrue(expectedIntent.filterEquals(actualIntent));
    }
}
