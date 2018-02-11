package powerup.systers.com.powerup.test;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.widget.Button;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowApplication;
import org.robolectric.shadows.ShadowDialog;

import powerup.systers.com.AboutActivity;
import powerup.systers.com.AvatarRoomActivity;
import powerup.systers.com.BuildConfig;
import powerup.systers.com.R;
import powerup.systers.com.StartActivity;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by Mu'aaz Kasker on 12/23/2017.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class NewGameButtonTest {

    @Test
    public void check(){
        StartActivity startActivity = Robolectric.setupActivity(StartActivity.class);
        startActivity.findViewById(R.id.newUserButtonFirstPage).performClick();

        AlertDialog dialog = (AlertDialog) ShadowDialog.getLatestDialog();
        assertTrue(dialog.isShowing());

        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).performClick();
        assertFalse(dialog.isShowing());

        startActivity.findViewById(R.id.newUserButtonFirstPage).performClick();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).performClick();

        Intent expectedIntent = new Intent(startActivity, AvatarRoomActivity.class);
        Intent actual = ShadowApplication.getInstance().getNextStartedActivity();
        assertEquals(expectedIntent.getComponent(), actual.getComponent());

    }

}
