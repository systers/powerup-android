package powerup.systers.com.powerup.test;

import android.content.Intent;
import android.os.Build;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.annotation.Config;

import powerup.systers.com.BuildConfig;
import powerup.systers.com.GameActivity;
import powerup.systers.com.MapActivity;
import powerup.systers.com.R;
import powerup.systers.com.ScenarioOverActivity;
import powerup.systers.com.datamodel.SessionHistory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by yuyuanluo on 12/27/17.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)

public class ScenarioOverActivityTests {
    ScenarioOverActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(ScenarioOverActivity.class)
                .create()
                .resume()
                .get();
    }

    @Test
    public void shouldNotBeNull() throws Exception {
        assertNotNull(activity);
    }

    @Test
    public void mapButtonShouldLaunchMap() throws Exception {
        Intent expectedIntent = new Intent(activity, MapActivity.class);

        Button mapButton = (Button) activity.findViewById(R.id.mapButton);
        mapButton.callOnClick();

        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();

        assertTrue(expectedIntent.filterEquals(actualIntent));
    }

    @Test
    public void karmaPointsUpdated(){
        SessionHistory.totalPoints += 50;
        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        TextView karmaPoints = (TextView) shadowActivity.findViewById(R.id.karmaPoints);
        karmaPoints.setText(String.valueOf(SessionHistory.totalPoints));

        assertEquals(karmaPoints.getText().toString(),String.valueOf(SessionHistory.totalPoints));

        SessionHistory.totalPoints -= 50;
    }

    @Test
    public void continueButtonWorks(){
        Intent expectedIntent = new Intent(activity, GameActivity.class);

        ImageView continueButton = (ImageView) activity.findViewById(R.id.continueButton);
        continueButton.callOnClick();

        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();

        assertTrue(expectedIntent.filterEquals(actualIntent));
    }

    @Test
    public void replayButtonWorks(){
        ImageView replayButton = (ImageView) activity.findViewById(R.id.replayButton);
        replayButton.callOnClick();
        assertEquals(SessionHistory.currSessionID, SessionHistory.prevSessionID);
        assertEquals(SessionHistory.totalPoints, SessionHistory.totalPoints - SessionHistory.currScenePoints);
        assertEquals(SessionHistory.currScenePoints, 0);
        assertEquals(activity.scenarioActivityDone, 0);
        new GameActivity().gameActivityInstance.isFinishing();
        activity.scenarioOverActivityInstance.isFinishing();

        Intent expectedIntent = new Intent(activity, GameActivity.class);
        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();

        assertTrue(expectedIntent.filterEquals(actualIntent));
    }

    @Test
    public void shouldCloseActivity() throws Exception{
        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        activity.onBackPressed();
        assertTrue(shadowActivity.isFinishing());
    }
}
