package powerup.systers.com.powerup.test;

import android.content.Intent;
import android.os.Build;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import powerup.systers.com.BuildConfig;
import powerup.systers.com.R;
import powerup.systers.com.ScenarioOverActivity;
import powerup.systers.com.minesweeper.MinesweeperGameActivity;
import powerup.systers.com.minesweeper.MinesweeperSessionManager;
import powerup.systers.com.minesweeper.ProsAndConsActivity;
import powerup.systers.com.powerup.PowerUpUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by sachinaggarwal on 02/07/17.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class ProsConsTests {

    ProsAndConsActivity activity;

    //Setting Up ProsAndConsActivity
    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(ProsAndConsActivity.class)
                .create()
                .resume()
                .get();
    }

    //Checking for Null Exceptions in ProsAndConsActivity
    @Test
    public void shouldNotBeNull() throws Exception {
        assertNotNull(activity);
    }

    //Testing the text of Pro(1)
    @Test
    public void setPro1TextProperly() {
        activity.completedRounds = 2;
        activity.setTexts();
        TextView textView = (TextView) activity.findViewById(R.id.pro_one);
        //Comparing the expected Pro(1) vs actual Pro(1)
        assertEquals(textView.getText().toString(), PowerUpUtils.ROUNDS_PROS_CONS[1][0]);
    }

    //Testing the text of Con(1)
    @Test
    public void setCon1TextProperly() {
        activity.completedRounds = 2;
        activity.setTexts();
        TextView textView = (TextView) activity.findViewById(R.id.con_one);
        //Comapring teh expected Con(1) vs actual Con(1)
        assertEquals(textView.getText().toString(), PowerUpUtils.ROUNDS_PROS_CONS[1][2]);
    }

    //Testing the text of Pro(1)
    @Test
    public void setPro2TextProperly() {
        activity.completedRounds = 2;
        activity.setTexts();
        TextView textView = (TextView) activity.findViewById(R.id.pro_two);
        //Comparing the expected Pro(1) vs actual Pro(1)
        assertEquals(textView.getText().toString(), PowerUpUtils.ROUNDS_PROS_CONS[1][1]);
    }

    //Testing correct action of the continueButton for MineActivity
    @Test
    public void continueshouldLaunchMineActivity() {
        activity.completedRounds = PowerUpUtils.NUMBER_OF_ROUNDS - 1;
        Class Minesweeper = MinesweeperGameActivity.class;
        Intent expectedIntent = new Intent(activity, Minesweeper);

        activity.findViewById(R.id.continue_button).callOnClick();
        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();

        //Comparing actual intent vs the generated intent
        assertTrue(expectedIntent.filterEquals(actualIntent));
    }

    //Testing correct action of the continueButton for GameActivity
    @Test
    public void continueshouldLaunchGameActivity() {
        activity.completedRounds = PowerUpUtils.NUMBER_OF_ROUNDS;
        Class ScenarioOver = ScenarioOverActivity.class;
        Intent expectedIntent = new Intent(activity, ScenarioOver);

        activity.findViewById(R.id.continue_button).callOnClick();
        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();

        //Comparing actual intent vs the generated intent
        assertTrue(expectedIntent.filterEquals(actualIntent));
    }

    //Testing action of continueButton to update the Game state
    @Test
    public void continueshouldUpdateGameOpenedState() {
        activity.completedRounds = PowerUpUtils.NUMBER_OF_ROUNDS;

        activity.findViewById(R.id.continue_button).callOnClick();

        //Comparing values from SessionManager for the ProAndConsActivity
        assertEquals(false, new MinesweeperSessionManager(activity).isMinesweeperOpened());
    }

    //Testing the completion of Rounds
    @Test
    public void checkCompletedRounds() {
        activity.completedRounds = PowerUpUtils.NUMBER_OF_ROUNDS; //should fail for PowerUpUtils.NUMBER_OF_ROUNDS + 1

        //Comparing completed rounds with Number of Rounds value
        assertTrue(activity.completedRounds <= PowerUpUtils.NUMBER_OF_ROUNDS);
    }
}
