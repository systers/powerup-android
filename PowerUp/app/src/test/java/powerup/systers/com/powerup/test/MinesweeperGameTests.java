package powerup.systers.com.powerup.test;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.widget.ImageView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadow.api.Shadow;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowDrawable;
import org.robolectric.shadows.ShadowPorterDuffColorFilter;

import java.util.Collections;

import powerup.systers.com.BuildConfig;
import powerup.systers.com.R;
import powerup.systers.com.minesweeper.MinesweeperGameActivity;
import powerup.systers.com.minesweeper.MinesweeperSessionManager;
import powerup.systers.com.minesweeper.ProsAndConsActivity;
import powerup.systers.com.powerup.PowerUpUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by sachinaggarwal on 01/07/17.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)

public class MinesweeperGameTests {

    MinesweeperGameActivity activity;
    MinesweeperSessionManager sessionManager;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(MinesweeperGameActivity.class)
                .create()
                .resume()
                .get();
    }

    @Test
    public void shouldNotBeNull() throws Exception {
        /* If activity is null then all the test will fail
        *  Test to make sure it is not null. */
        assertNotNull(activity);
    }


    @Test
    public void continueButtonShouldLaunchProsAndCons() throws Exception {
        Class ProsAndCons = ProsAndConsActivity.class;
        Intent expectedIntent = new Intent(activity, ProsAndCons);

        /* When the continueButton is pressed the continuePressed function
        *  is called which launches the ProsAndConsActivity.
        *  Test this happens. */

        activity.continueButton.callOnClick();
        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();

        assertTrue(expectedIntent.filterEquals(actualIntent));
    }

    @Test
    public void showsCorrectRedBanner() throws Exception {
        int type = PowerUpUtils.RED_BANNER;
        ImageView imageView = (ImageView) activity.findViewById(R.id.banner);
        int expected = R.drawable.failure_banner;

        /* Pass a value of one (PowerUpUtils.RED_BANNER) to the showBanner
        *  function results in the failure_banner being displayed.
        *  Test this happens. */

        activity.showBanner(type);

        ShadowDrawable shadowDrawable = Shadows.shadowOf(imageView.getDrawable());
        assertEquals(expected, shadowDrawable.getCreatedFromResId());
    }

    @Test
    public void showsCorrectGreenBanner() throws Exception {
        int type = PowerUpUtils.GREEN_BANNER;
        ImageView imageView = (ImageView) activity.findViewById(R.id.banner);
        int expected = R.drawable.success_banner;

        /* Pass a value of zero (PowerUpUtils.GREEN_BANNER) to the showBanner
        *  function results in the success_banner being displayed.
        *  Test this happens. */

        activity.showBanner(type);

        ShadowDrawable shadowDrawable = Shadows.shadowOf(imageView.getDrawable());
        assertEquals(expected, shadowDrawable.getCreatedFromResId());
    }

    @Test
    public void minesDisabledAfterBannerAppearance1() throws Exception {
        int type = PowerUpUtils.GREEN_BANNER;

        /* showBanner calls the function showOriginalMines and this function
        *  sets enabled to false for each mine view.
        *  Test that each of the mines in minesViews is not enabled after
        *  the showBanner function is run. */

        activity.showBanner(type);

        for (int id : PowerUpUtils.minesViews)
            assertTrue(!activity.findViewById(id).isEnabled());
    }

    @Test
    public void minesDisabledAfterBannerAppearance2() throws Exception {
        int type = PowerUpUtils.RED_BANNER;

        /* showBanner calls the function showOriginalMines and this function
        *  sets enabled to false for each mine view.
        *  Test that each of the mines in minesViews is not enabled after
        *  the showBanner function is run. */

        activity.showBanner(type);

        for (int id : PowerUpUtils.minesViews)
            assertTrue(!activity.findViewById(id).isEnabled());
    }

    @Test
    public void scoreGetsUpdated() throws Exception {
        int sampleScore = 3;
        activity.score = sampleScore;
        int expectedScore = 4;
        String expectedText = "Score: " + expectedScore;

        /* openedGreenMine will increase the score by 1.
        *  Test that when we start with a score of three and open a green
        *  mine we we have a score of four. This is formatted into a text
        *  string by openedGreenMine.
        *  Test to see if the text is correctly formed with the score. */

        activity.openedGreenMine();

        String actualText = activity.scoreTextView.getText().toString();
        assertEquals(expectedText, actualText);
    }

    @Test
    public void chancesNumberDecrementsCorrectly() throws Exception {
        int sampleChancesLeft = 3;
        activity.numSelectionsLeft = sampleChancesLeft;
        int expectedChances = 2;

        /* openedGreenMine will reduce the number of chances left by 1.
        *  Test that when we start with three chances and open a green
        *  mine we are only left with two chances. */

        activity.openedGreenMine();

        assertEquals(expectedChances, activity.numSelectionsLeft);
    }

    @Test
    public void minesEnabledOnGameStart() throws Exception {
        activity.setUpGame();

        /* Tests that each of the imageView are enabled by default */

        for (int id : PowerUpUtils.minesViews)
            assertTrue(activity.findViewById(id).isEnabled());
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Test
    public void greyOutMines() throws Exception {
        activity.setUpGame();
        ImageView greenMineImageView = (ImageView) activity.findViewById(R.id.imageView5);
        activity.mines.remove(PowerUpUtils.ID_REFERENCE + 5);

        /* showOriginalMines will display all the imageViews with the red stars coloured red
        *  and the green stars filtered to appear grey. imageView5 is removed from mines so
        *  it will appear greyed out.
        *  Test that this happens. */

        activity.showOriginalMines();

        ShadowDrawable shadowDrawable = Shadows.shadowOf(greenMineImageView.getDrawable());
        assertEquals(R.drawable.green_star, shadowDrawable.getCreatedFromResId());
        assertEquals(0.8f, greenMineImageView.getAlpha(), 0.005f);
        ShadowPorterDuffColorFilter actualColor = Shadows.shadowOf(activity.filter);
        assertEquals(Color.GRAY, actualColor.getColor());
        assertEquals(PorterDuff.Mode.MULTIPLY, actualColor.getMode());
    }

    @Test
    public void continueHiddenOnRoundStart() throws Exception {
        ImageView continueButton = (ImageView) activity.findViewById(R.id.continue_button);
        float expectedAplha = 0f;

        /* The resource definition of R.id.continue_button has alpha set to 0 in
        *  activity_minesweeper_game.xml - which is its initial alpha value.
        *  test the setUpGame function does not change this. */

        activity.setUpGame();


        assertEquals(expectedAplha, continueButton.getAlpha(), 0.005);
    }

    @Test
    public void bannerHiddenOnRoundStart() throws Exception {
        ImageView banner = (ImageView) activity.findViewById(R.id.banner);
        float expectedAplha = 0f;

        /* The resource definition of R.id.banner has alpha set to 0 in
        *  activity_minesweeper_game.xml - which is its initial alpha value.
        *  test the setUpGame function does not change this. */

        activity.setUpGame();

        assertEquals(expectedAplha, banner.getAlpha(), 0.005);
    }

    @Test
    public void continueButtonNotClickableOnGameStart() throws Exception {
        ImageView continueButton = (ImageView) activity.findViewById(R.id.continue_button);

        /* When the MinesweeperGameActivity is created the continueButton has setClickable(false)
        *  Test that setUpGame does not change this to true. */

        activity.setUpGame();

        assertTrue(!continueButton.isClickable());
    }

    @Test
    public void continueButtonClickableAfterBanner() {
        ImageView continueButton = (ImageView) activity.findViewById(R.id.continue_button);

        /* When the showBanner function is run the continueButton is set to clickable. */

        activity.showBanner(PowerUpUtils.RED_BANNER);

        assertTrue(continueButton.isClickable());
    }

    @Test
    public void scoreIncrementCorrectly1() {
        int sampleScore = 4;
        activity.score = sampleScore;
        /* Test that the openedGreenMine function increases the activity.score by 1. */

        activity.openedGreenMine();

        assertEquals(sampleScore + 1, activity.score);
    }

    @Test
    public void scoreIncrementCorrectly2() {
        int sampleScore = 4;
        activity.score = sampleScore;

        /* Test that the openedRedMine function does not alter the activity.score. */

        activity.openedRedMine();

        assertEquals(sampleScore, activity.score);
    }

    @Test
    public void shouldShowRedMineCorrectly() throws Exception {
        activity.setUpGame(); //call this function to initialise mines Hashset
        ImageView redMineImageView = (ImageView) activity.findViewById(R.id.imageView5);
        activity.mines.add(PowerUpUtils.ID_REF + 5);
        int expectedDrawable = R.drawable.red_star; //since this imageview have red mine

        /* Ensuring that star 5 is added to the mines which means it will be a red mine
        *  when clicked.
        *  Test that the imageView drawable is set to a red star when the mine is opened. */

        PowerUpUtils.sPauseTest = true;
        redMineImageView.callOnClick();
        while ((PowerUpUtils.sPauseTest == true)) {
            Thread.sleep(100);
        }

        int drawableResId = Shadows.shadowOf(redMineImageView.getDrawable()).getCreatedFromResId();
        assertEquals(expectedDrawable, drawableResId);
    }

    @Test
    public void shouldShowGreenMineCorrectly() throws Exception {
        activity.setUpGame(); //call this function to initialise mines Hashset
        ImageView greenMineImageView = (ImageView) activity.findViewById(R.id.imageView6);
        activity.mines.remove(PowerUpUtils.ID_REF + 6);
        int expectedDrawable = R.drawable.green_star; //since this imageview have red mine

        /* The behaviour of a green mine is tested to ensure mine 6 is not a red mine by
        *  removing it from mines.
        *  When clicked The image view drawable should be set to a green star. */

        PowerUpUtils.sPauseTest = true;
        greenMineImageView.callOnClick();
        while ((PowerUpUtils.sPauseTest == true)) {
            Thread.sleep(100);
        }

        int drawableResId = Shadows.shadowOf(greenMineImageView.getDrawable()).getCreatedFromResId();
        assertEquals(expectedDrawable, drawableResId);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Test
    public void roundCompletesWhenChancesFinished1() throws InterruptedException {
        activity.numSelectionsLeft = 1;
        PowerUpUtils.sPauseTest = true;

        /* openedGreenMine causes a banner to animate. At the end of the animation
        *  the continueButton is set to clickable and has an alpha value of 1.
        *  The banner itself has an alpha of 0.95.
        *  Test that these conditions are true. */

        activity.openedGreenMine();
        while ((PowerUpUtils.sPauseTest == true)) {
            Thread.sleep(100);
        }

        assertTrue(activity.continueButton.isClickable());
        assertEquals(0.95f, activity.banner.getAlpha(), 0.005f);
        assertEquals(1f, activity.continueButton.getAlpha(), 0.005f);

    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Test
    public void roundCompletesWhenChancesFinished2() throws InterruptedException {
        activity.numSelectionsLeft = 1;
        PowerUpUtils.sPauseTest = true;

        /* openedRedMine causes a banner to animate. At the end of the animation
        *  the continueButton is set to clickable and has an alpha value of 1.
        *  The banner itself has an alpha of 0.95.
        *  Test that these conditions are true. */


        activity.openedRedMine();
        while ((PowerUpUtils.sPauseTest == true)) {
            Thread.sleep(100);
        }


        assertTrue(activity.continueButton.isClickable());
        assertEquals(0.95f, activity.banner.getAlpha(), 0.005f);
        assertEquals(1f, activity.continueButton.getAlpha(), 0.005f);
    }

    @Test
    public void testSessionManagerScore() {
        sessionManager = new MinesweeperSessionManager(activity);
        int expectedScore = 5;

        /* saveData stores a SCORE and a ROUNDS_COMPLETED in a
        *  key value store. getScore retrieves the
        *  value associated with SCORE.
        *  Test that when we store a value of 5 using saveData it
        *  is subsequently retrieved using the function getScore. */

        sessionManager.saveData(expectedScore, PowerUpUtils.NUMBER_OF_ROUNDS);

        assertEquals(expectedScore, sessionManager.getScore());
    }

    @Test
    public void testSessionManagerCompletedRounds() {
        sessionManager = new MinesweeperSessionManagR.id.imageView12er(activity);
        int expectedRounds = PowerUpUtils.NUMBER_OF_ROUNDS - 1;

        /* saveData stores a SCORE and a ROUNDS_COMPLETED in a
        *  key value store. getCompletedRounds retrieves the
        *  value associated with ROUNDS_COMPLETED.
        *  Test that when we store a value of 0 using saveData it
        *  is subsequently retrieved using the function getCompletedRounds. */

        sessionManager.saveData(5, expectedRounds);

        assertEquals(expectedRounds, sessionManager.getCompletedRounds());
    }

    @Test
    public void scoreNotNegative() {
        sessionManager = new MinesweeperSessionManager(activity);

        /* When a new MinesweeperSessionManager is created, a pref
        *  value for SCORE will not be set, a call to getScore
        *  will return a default value of 0.
        *  Test that the score is not negative. */

        assertTrue(sessionManager.getScore() >= 0);
    }

    @Test
    public void roundsCompletedNotNegative() {
        sessionManager = new MinesweeperSessionManager(activity);

        /* when a new MinesweeperSessionManager is created,
        *  a pref value for ROUNDS_COMPLETED will not be set, a call to
        *  getCompletedRounds will return a default value of 1.
        *  test that the number of rounds completed is not negative. */

        assertTrue(sessionManager.getCompletedRounds() >= 0);
    }

}
