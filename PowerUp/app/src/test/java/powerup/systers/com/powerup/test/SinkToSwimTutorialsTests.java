package powerup.systers.com.powerup.test;

import android.content.Intent;
import android.os.Build;
import android.widget.ImageView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowDrawable;

import powerup.systers.com.BuildConfig;
import powerup.systers.com.R;
import powerup.systers.com.powerup.PowerUpUtils;
import powerup.systers.com.sink_to_swim_game.SinkToSwimGame;
import powerup.systers.com.sink_to_swim_game.SinkToSwimTutorials;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class SinkToSwimTutorialsTests {

    private SinkToSwimTutorials activity;
    private ImageView tutorialView, startButton;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.setupActivity(SinkToSwimTutorials.class);
        tutorialView = (ImageView) activity.findViewById(R.id.tut);
        startButton = (ImageView) activity.findViewById(R.id.start_button);
    }

    @Test
    public void shouldNotBeNull() throws Exception {
        assertNotNull(activity);
    }

    @Test
    public void displaysCorrectTutorial() throws Exception {
        ShadowDrawable shadowDrawable = Shadows.shadowOf(tutorialView.getDrawable());
        for (int i = 0; i < PowerUpUtils.SWIM_TUTS.length; i++) {
            // If it is the last tutorial screen, check that the start button is enabled
            if (i == PowerUpUtils.SWIM_TUTS.length - 1) {
                assertTrue(startButton.isClickable());
            }
            assertEquals(PowerUpUtils.SWIM_TUTS[i], shadowDrawable.getCreatedFromResId());
            tutorialView.performClick();
            shadowDrawable = Shadows.shadowOf(tutorialView.getDrawable());
        }
    }

    @Test
    public void launchesNextActivityCorrectly() throws Exception {
        // Go to the last tutorial screen before the activity changes
        for (int i = 0; i < PowerUpUtils.SWIM_TUTS.length; i++) {
            tutorialView.performClick();
        }

        ImageView startButton = (ImageView) activity.findViewById(R.id.start_button);

        Class SinkToSwim = SinkToSwimGame.class;
        Intent expectedIntent = new Intent(activity, SinkToSwim);
        startButton.performClick();

        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        assertTrue(expectedIntent.filterEquals(actualIntent));
    }

    @Test
    public void startButtonNotClickableOnGameStart() throws Exception {
        assertFalse(startButton.isEnabled());
    }

    @Test
    public void startButtonHiddenOnStart() throws Exception {
        assertFalse(startButton.isOpaque());
    }
}
