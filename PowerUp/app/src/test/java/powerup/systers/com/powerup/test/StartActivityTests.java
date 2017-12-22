package powerup.systers.com.powerup.test;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;
import android.provider.CalendarContract;
import android.widget.Button;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadow.api.Shadow;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowAlertDialog;
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
import static org.robolectric.RuntimeEnvironment.application;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by Aryaman21 on 22-12-2017.
 */

@RunWith(RobolectricTestRunner.class)
@Config(manifest = "AndroidManifest.xml",constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
    public class StartActivityTests {

        private StartActivity startActivity = Robolectric.buildActivity(StartActivity.class).create().get();
        @Test
        public void clickingAbout_shouldStartAboutActivity(){
            //Simulating New Game Button
            startActivity.findViewById(R.id.newUserButtonFirstPage).performClick();
            AlertDialog alertDialog = new AlertDialog.Builder(startActivity).create();
            alertDialog.show();
            //Checking if the alertDialog is generated or not
            assertTrue(alertDialog.isShowing());
            //Simulating the confirm button
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).performClick();
            alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).performClick();
            //Checking that startActivityForResult is invoke
            ShadowActivity shadowActivity = shadowOf(startActivity);
            ShadowActivity.IntentForResult intentForResult = shadowActivity.getNextStartedActivityForResult();
            Intent actual = ShadowApplication.getInstance().getNextStartedActivity();
            //Asserting that the right Intent request is sent
            assertEquals(actual, intentForResult);
        }
        @Test
        public void dismiss_Dialog(){
            startActivity.findViewById(R.id.newUserButtonFirstPage).performClick();
            AlertDialog alertDialog = new AlertDialog.Builder(startActivity).create();
            alertDialog.show();
            //Checking if the alertDialog is generated or not
            assertTrue(alertDialog.isShowing());
            //Simulating the confirm button
            alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).performClick();
            //Checking if the dialog closed or not
            assertFalse(alertDialog.isShowing());

        }
    }

