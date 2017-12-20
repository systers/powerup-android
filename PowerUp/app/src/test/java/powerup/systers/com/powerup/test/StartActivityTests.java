package powerup.systers.com.powerup.test;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowAlertDialog;

import powerup.systers.com.AvatarRoomActivity;
import powerup.systers.com.BuildConfig;
import powerup.systers.com.R;
import powerup.systers.com.StartActivity;

import static org.junit.Assert.assertTrue;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class StartActivityTests {
    private StartActivity activity;

    @Before
    public void setUp() {
        activity = Robolectric.setupActivity(StartActivity.class);
    }

    @Test
    public void testNewGameButton() {
        activity.findViewById(R.id.newUserButtonFirstPage).performClick();
        AlertDialog alertDialog = (AlertDialog) ShadowAlertDialog.getLatestDialog();
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).performClick();
        Intent expected = new Intent(activity, AvatarRoomActivity.class);
        ShadowActivity shadowActivity = shadowOf(activity);
        ShadowActivity.IntentForResult actual = shadowActivity.getNextStartedActivityForResult();
        assertTrue(actual.intent.filterEquals(expected));
    }
}
