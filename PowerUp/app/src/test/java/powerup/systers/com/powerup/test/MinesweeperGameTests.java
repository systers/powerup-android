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

    //Initializing Activity objects
    MinesweeperGameActivity activity;
    MinesweeperSessionManager sessionManager;

    //Setting Up MinesweeperGameActvity
    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(MinesweeperGameActivity.class)
                .create()
                .resume()
                .get();
    }

    //Checking for Null Exceptions in MinesweeperGameActivity
    @Test
    public void shouldNotBeNull() throws Exception {
        assertNotNull(activity);
    }

    /*
    * Note for Beginners in Testing
    * Examining the first two tests below should provide you
    * with ample instinct to be able to understand or get a
    * fair glimpse of how robolectric testing works
    */

    //Testing the correct correct function of continueButton
    @Test
    public void continueButtonShouldLaunchProsAndCons() throws Exception {
        //Creating ProsAndCons class object
        Class ProsAndCons = ProsAndConsActivity.class;
        //Checking Intent for expected behaviour
        Intent expectedIntent = new Intent(activity, ProsAndCons);

        //Clicking on continueButton
        activity.continueButton.callOnClick();
        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();

        //Comparing the actualIntent with expectedIntent
        assertTrue(expectedIntent.filterEquals(actualIntent));
    }

    //Testing RedBanner Correct generation
    @Test
    public void showsCorrectRedBanner() throws Exception {
        //Getting resource value for RED_BANNER
        int type = PowerUpUtils.RED_BANNER;
        //Initializing ImageView
        ImageView imageView = (ImageView) activity.findViewById(R.id.banner);
        //Initializing the expected resource value with id of failure_banner
        int expected = R.drawable.failure_banner;

        //Showing banner in the MineSweeperGameActivity
        activity.showBanner(type);

        //Generating a Shadow drawable to examine the actual banner drawable
        ShadowDrawable shadowDrawable = Shadows.shadowOf(imageView.getDrawable());
        //Testing for the correctness of expectedBanner vs Created Banner
        assertEquals(expected, shadowDrawable.getCreatedFromResId());
    }

    //Testing the correct appearance of the created GreenBanner in the Activity
    @Test
    public void showsCorrectGreenBanner() throws Exception {
        int type = PowerUpUtils.GREEN_BANNER;
        ImageView imageView = (ImageView) activity.findViewById(R.id.banner);
        int expected = R.drawable.success_banner;

        activity.showBanner(type);

        ShadowDrawable shadowDrawable = Shadows.shadowOf(imageView.getDrawable());
        assertEquals(expected, shadowDrawable.getCreatedFromResId());
    }

    //Testing if mines are disabled after banner appearance or not, with Green Banner
    @Test
    public void minesDisabledAfterBannerAppearance1() throws Exception {
        int type = PowerUpUtils.GREEN_BANNER;

        activity.showBanner(type);

        //Testing for each mine using loop
        for (int id : PowerUpUtils.minesViews)
            assertTrue(!activity.findViewById(id).isEnabled());
    }

    //Testing if mines are disabled after banner appearance or not, with Red Banner
    @Test
    public void minesDisabledAfterBannerAppearance2() throws Exception {
        int type = PowerUpUtils.RED_BANNER;

        activity.showBanner(type);

        //Testing for each mine using loop
        for (int id : PowerUpUtils.minesViews)
            assertTrue(!activity.findViewById(id).isEnabled());
    }

    //Testing updation of Score
    @Test
    public void scoreGetsUpdated() throws Exception {
        int sampleScore = 3;
        activity.score = sampleScore;
        int expectedScore = 4;
        String expectedText = "Score: " + expectedScore;

        activity.openedGreenMine();

        String actualText = activity.scoreTextView.getText().toString();
        //Comparing actualText vs expectedText i.e Score
        assertEquals(expectedText, actualText);
    }

    //Testing the number of chances correct decrementation
    @Test
    public void chacesNumberDecrementsCorrectly() throws Exception {
        int sampleChancesLeft = 3;
        activity.numSelectionsLeft = sampleChancesLeft;
        int expectedChances = 2;

        activity.openedGreenMine();

        //Comparing sampleChancesLeft and expectedChances after decrement
        assertEquals(expectedChances, activity.numSelectionsLeft);
    }

    //Testing the initialization of mines at the start of the game
    @Test
    public void minesEnabledOnGameStart() throws Exception {
        activity.setUpGame();

        //Checking each mine initialization
        for (int id : PowerUpUtils.minesViews)
            assertTrue(activity.findViewById(id).isEnabled());
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    //Testing slight tint in Mine color towards Grey with a little fade
    @Test
    public void greyOutMines() throws Exception {
        activity.setUpGame();
        ImageView greenMineImageView = (ImageView) activity.findViewById(R.id.imageView5);
        activity.mines.remove(PowerUpUtils.ID_REFERENCE + 5);

        activity.showOriginalMines();

        ShadowDrawable shadowDrawable = Shadows.shadowOf(greenMineImageView.getDrawable());
        //Testing the display of the correct drawable
        assertEquals(R.drawable.green_star, shadowDrawable.getCreatedFromResId());
        //Comparison using Alpha values
        assertEquals(0.8f, greenMineImageView.getAlpha(), 0.005f);
        ShadowPorterDuffColorFilter actualColor = Shadows.shadowOf(activity.filter);
        //Comparing Expected vs Actual Color
        assertEquals(Color.GRAY, actualColor.getColor());
        //Comparing color mode for colors
        assertEquals(PorterDuff.Mode.MULTIPLY, actualColor.getMode());
    }

    //Testing the disappearance of continueButton
    @Test
    public void continueHiddenOnRoundStart() throws Exception {
        ImageView continueButton = (ImageView) activity.findViewById(R.id.continue_button);
        float expectedAplha = 0f;

        activity.setUpGame();


        //Comparison using the Alpha values
        assertEquals(expectedAplha, continueButton.getAlpha(), 0.005);
    }

    //Testing the disappearance of banner at the starting of the round
    @Test
    public void bannerHiddenOnRoundStart() throws Exception {
        ImageView banner = (ImageView) activity.findViewById(R.id.banner);
        float expectedAplha = 0f;

        activity.setUpGame();

        //Comparison using the Alpha values
        assertEquals(expectedAplha, banner.getAlpha(), 0.005);
    }

    //Testing disabling of continueButton on Game initialization
    @Test
    public void continueButtonNotClickableOnGameStart() throws Exception {
        ImageView continueButton = (ImageView) activity.findViewById(R.id.continue_button);

        activity.setUpGame();

        //Expecting the button to be unClickable
        assertTrue(!continueButton.isClickable());
    }

    //Testing the clickable action for continueButton after banner appearance
    @Test
    public void continueButtonClickableAfterBanner() {
        ImageView continueButton = (ImageView) activity.findViewById(R.id.continue_button);

        activity.showBanner(PowerUpUtils.RED_BANNER);

        //Expecting the button to be Clickable
        assertTrue(continueButton.isClickable());
    }

    //Testing increment of score correctly, at GreenMine
    @Test
    public void scoreIncrementCorrectly1() {
        int sampleScore = 4;
        activity.score = sampleScore;

        activity.openedGreenMine();

        //Comparing expected score to actual score
        assertEquals(sampleScore + 1, activity.score);
    }

    //Testing increment of score correctly, at RedMine
    @Test
    public void scoreIncrementCorrectly2() {
        int sampleScore = 4;
        activity.score = sampleScore;

        activity.openedRedMine();

        //Comparing expected score to actual score
        assertEquals(sampleScore, activity.score);
    }

    //Testing correct display of RedMines
    @Test
    public void shouldShowRedMineCorrectly() throws Exception {
        activity.setUpGame(); //call this function to initialise mines Hashset
        ImageView redMineImageView = (ImageView) activity.findViewById(R.id.imageView5);
        activity.mines.add(PowerUpUtils.ID_REF + 5);
        int expectedDrawable = R.drawable.red_star; //since this imageview have red mine

        PowerUpUtils.sPauseTest = true;
        redMineImageView.callOnClick();
        while ((PowerUpUtils.sPauseTest == true)) {
            Thread.sleep(100);
        }

        int drawableResId = Shadows.shadowOf(redMineImageView.getDrawable()).getCreatedFromResId();
        //Comparing expected drawble to the actual drawable
        assertEquals(expectedDrawable, drawableResId);
    }

    //Testing correct display of GreenMines
    @Test
    public void shouldShowGreenMineCorrectly() throws Exception {
        activity.setUpGame(); //call this function to initialise mines Hashset
        ImageView greenMineImageView = (ImageView) activity.findViewById(R.id.imageView6);
        activity.mines.remove(PowerUpUtils.ID_REF + 6);
        int expectedDrawable = R.drawable.green_star; //since this imageview have red mine

        PowerUpUtils.sPauseTest = true;
        greenMineImageView.callOnClick();
        while ((PowerUpUtils.sPauseTest == true)) {
            Thread.sleep(100);
        }

        int drawableResId = Shadows.shadowOf(greenMineImageView.getDrawable()).getCreatedFromResId();
        //Comparing expected drawable to the actual drawable
        assertEquals(expectedDrawable, drawableResId);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    //Asserting the finish of Round when Chances finished, with GreenMine
    @Test
    public void roundCompletesWhenChancesFinished1() throws InterruptedException {
        activity.numSelectionsLeft = 1;
        PowerUpUtils.sPauseTest = true;

        activity.openedGreenMine();
        while ((PowerUpUtils.sPauseTest == true)) {
            Thread.sleep(100);
        }

        //Testing clickable action of continueButton
        assertTrue(activity.continueButton.isClickable());
        //Testing the visibility of the banner using Alpha value
        assertEquals(0.95f, activity.banner.getAlpha(), 0.005f);
        //Testing the visibilty of continueButton using Alpha value
        assertEquals(1f, activity.continueButton.getAlpha(), 0.005f);

    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    //Asserting the finish of Round when Chances finished again, with RedMine
    @Test
    public void roundCompletesWhenChancesFinished2() throws InterruptedException {
        activity.numSelectionsLeft = 1;
        PowerUpUtils.sPauseTest = true;

        activity.openedRedMine();
        while ((PowerUpUtils.sPauseTest == true)) {
            Thread.sleep(100);
        }

        //Testing clickable action of continueButton
        assertTrue(activity.continueButton.isClickable());
        //Testing the visibility of the banner using Alpha value
        assertEquals(0.95f, activity.banner.getAlpha(), 0.005f);
        //Testing the visibility of continueButton using Alpha value
        assertEquals(1f, activity.continueButton.getAlpha(), 0.005f);
    }

    //Testing the score of session manager
    @Test
    public void testSessionManagerScore() {
        sessionManager = new MinesweeperSessionManager(activity);
        int expectedScore = 5;

        sessionManager.saveData(expectedScore, PowerUpUtils.NUMBER_OF_ROUNDS);

        //Comparing expected vs actual score
        assertEquals(expectedScore, sessionManager.getScore());
    }

    //Testing the score of sessionManager at rounds completed
    @Test
    public void testSessionManagerCompletedRounds() {
        sessionManager = new MinesweeperSessionManager(activity);
        int expectedRounds = PowerUpUtils.NUMBER_OF_ROUNDS - 1;

        sessionManager.saveData(5, expectedRounds);

        //Comparing expected vs actual score
        assertEquals(expectedRounds, sessionManager.getCompletedRounds());
    }

    //Checking whether score is not negative
    @Test
    public void scoreNotNegative() {
        sessionManager = new MinesweeperSessionManager(activity);

        //Checking the value of sessionManager.score
        assertTrue(sessionManager.getScore() >= 0);
    }

    //Checking whether score of sessionManager at rounds completed
    @Test
    public void roundsCompletedNotNegative() {
        sessionManager = new MinesweeperSessionManager(activity);

        //Comparing the value of sessionManager.CompletedRounds score
        assertTrue(sessionManager.getCompletedRounds() >= 0);
    }

}
