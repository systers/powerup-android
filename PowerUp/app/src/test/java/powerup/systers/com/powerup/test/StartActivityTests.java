package powerup.systers.com.powerup.test;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.CalendarContract;
import android.widget.Button;

import org.junit.Before;
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
import powerup.systers.com.MapActivity;
import powerup.systers.com.R;
import powerup.systers.com.StartActivity;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.robolectric.RuntimeEnvironment.application;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by Aryaman21 on 24-12-2017.
 */

@RunWith(RobolectricTestRunner.class)
@Config(manifest = "AndroidManifest.xml",constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class StartActivityTests {

    private StartActivity startActivity = Robolectric.buildActivity(StartActivity.class).create().get();
    private SharedPreferences preferences;
    private boolean hasPreviouslyStarted;
    private Button startButton;
    private Button newUserButton;
    private Button aboutButton;
    Context context;

    @Before
    public void SetUP(){
        //Setting Up SharedPreferences to check the value of hasPreviouslyStarted or not
        context = startActivity;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        hasPreviouslyStarted = preferences.getBoolean("has_previously_started", false);
    }
    /*
    * Test 1 The program checks the value of hasPreviouslyStarted, in this case its false.
    * Thus the program is directed towards AvatarRoomActivity and thus we check correct intent
    * specific to that in the noSavedGame() function.
    */
    @Test
    public void load_button_test1(){
        if (hasPreviouslyStarted){
            hasSavedGame();
        }else {
            noSavedGame();
        }
    }
    /*
    * Test 2 The program checks the value of hasPreviouslyStarted, in this case its true.
    * Thus the program is directed towards MapActivity and thus we check correct intent
    * specific to that in the hasSavedGame() function.
    */
    @Test
    public void load_button_test2(){
        if (hasPreviouslyStarted){
            hasSavedGame();
        }else {
            noSavedGame();
        }
    }
    public void hasSavedGame(){
        //Simulating Load Game Button
        startActivity.findViewById(R.id.startButtonMain).performClick();
        //Defining the expected Intent to be generated
        Intent expectedIntent = new Intent(startActivity, MapActivity.class);
        //Getting the actual Intent generated
        Intent actual = ShadowApplication.getInstance().getNextStartedActivity();
        //Comparing the expected vs the actual intent
        assertEquals(expectedIntent.getComponent(), actual.getComponent());
    }

    public void noSavedGame(){
        //Simulating Load Game Button
        startActivity.findViewById(R.id.startButtonMain).performClick();
        //Defining the expected Intent to be generated
        Intent expectedIntent = new Intent(startActivity, AvatarRoomActivity.class);
        //Getting the actual Intent generated
        Intent actual = ShadowApplication.getInstance().getNextStartedActivity();
        //Comparing the expected vs the actual intent
        assertEquals(expectedIntent.getComponent(), actual.getComponent());
    }
}

