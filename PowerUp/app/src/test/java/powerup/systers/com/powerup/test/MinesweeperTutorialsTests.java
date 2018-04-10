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
import powerup.systers.com.minesweeper.MinesweeperGameActivity;
import powerup.systers.com.minesweeper.MinesweeperTutorials;
import powerup.systers.com.powerup.PowerUpUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class MinesweeperTutorialsTests {

    private MinesweeperTutorials activity;
    private ImageView tutorialView;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.setupActivity(MinesweeperTutorials.class);
        tutorialView = (ImageView) activity.findViewById(R.id.tut);
    }

    @Test
    public void shouldNotBeNull() throws Exception {
        assertNotNull(activity);
    }

    @Test
    public void displaysCorrectTutorial() throws Exception {
        ShadowDrawable shadowDrawable = Shadows.shadowOf(tutorialView.getDrawable());
        for (int i = 0; i < PowerUpUtils.MINES_TUTS.length; i++) {
            assertEquals(PowerUpUtils.MINES_TUTS[i], shadowDrawable.getCreatedFromResId());
            tutorialView.performClick();
            shadowDrawable = Shadows.shadowOf(tutorialView.getDrawable());
        }
    }

    @Test
    public void launchesNextActivityCorrectly() throws Exception {
        // Go to the last tutorial screen before the activity changes
        for (int i = 0; i < PowerUpUtils.MINES_TUTS.length; i++) {
            tutorialView.performClick();
        }

        Class Minesweeper = MinesweeperGameActivity.class;
        Intent expectedIntent = new Intent(activity, Minesweeper);
        // Perform the last click that will launch the next activity
        tutorialView.performClick();
        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        assertTrue(expectedIntent.filterEquals(actualIntent));
    }
}
