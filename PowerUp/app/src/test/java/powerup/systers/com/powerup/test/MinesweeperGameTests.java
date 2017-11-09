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
        assertNotNull(activity);
    }


    @Test
    public void continueButtonShouldLaunchProsAndCons() throws Exception {
        Class ProsAndCons = ProsAndConsActivity.class;
        Intent expectedIntent = new Intent(activity, ProsAndCons);

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

        activity.showBanner(type);

        ShadowDrawable shadowDrawable = Shadows.shadowOf(imageView.getDrawable());
        assertEquals(expected, shadowDrawable.getCreatedFromResId());
    }

    @Test
    public void showsCorrectGreenBanner() throws Exception {
        int type = PowerUpUtils.GREEN_BANNER;
        ImageView imageView = (ImageView) activity.findViewById(R.id.banner);
        int expected = R.drawable.success_banner;

        activity.showBanner(type);

        ShadowDrawable shadowDrawable = Shadows.shadowOf(imageView.getDrawable());
        assertEquals(expected, shadowDrawable.getCreatedFromResId());
    }

    @Test
    public void minesDisabledAfterBannerAppearance1() throws Exception {
        int type = PowerUpUtils.GREEN_BANNER;

        activity.showBanner(type);

        for (int id : PowerUpUtils.minesViews)
            assertTrue(!activity.findViewById(id).isEnabled());
    }

    @Test
    public void minesDisabledAfterBannerAppearance2() throws Exception {
        int type = PowerUpUtils.RED_BANNER;

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

        activity.openedGreenMine();

        String actualText = activity.scoreTextView.getText().toString();
        assertEquals(expectedText, actualText);
    }

    @Test
    public void chacesNumberDecrementsCorrectly() throws Exception {
        int sampleChancesLeft = 3;
        activity.numSelectionsLeft = sampleChancesLeft;
        int expectedChances = 2;

        activity.openedGreenMine();

        assertEquals(expectedChances, activity.numSelectionsLeft);
    }

    @Test
    public void minesEnabledOnGameStart() throws Exception {
        activity.setUpGame();

        for (int id : PowerUpUtils.minesViews)
            assertTrue(activity.findViewById(id).isEnabled());
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Test
    public void greyOutMines() throws Exception {
        activity.setUpGame();
        ImageView greenMineImageView = (ImageView) activity.findViewById(R.id.imageView5);
        activity.mines.remove(PowerUpUtils.ID_REFERENCE + 5);

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

        activity.setUpGame();


        assertEquals(expectedAplha, continueButton.getAlpha(), 0.005);
    }

    @Test
    public void bannerHiddenOnRoundStart() throws Exception {
        ImageView banner = (ImageView) activity.findViewById(R.id.banner);
        float expectedAplha = 0f;

        activity.setUpGame();

        assertEquals(expectedAplha, banner.getAlpha(), 0.005);
    }

    @Test
    public void continueButtonNotClickableOnGameStart() throws Exception {
        ImageView continueButton = (ImageView) activity.findViewById(R.id.continue_button);

        activity.setUpGame();

        assertTrue(!continueButton.isClickable());
    }

    @Test
    public void continueButtonClickableAfterBanner() {
        ImageView continueButton = (ImageView) activity.findViewById(R.id.continue_button);

        activity.showBanner(PowerUpUtils.RED_BANNER);

        assertTrue(continueButton.isClickable());
    }

    @Test
    public void scoreIncrementCorrectly1() {
        int sampleScore = 4;
        activity.score = sampleScore;

        activity.openedGreenMine();

        assertEquals(sampleScore + 1, activity.score);
    }

    @Test
    public void scoreIncrementCorrectly2() {
        int sampleScore = 4;
        activity.score = sampleScore;

        activity.openedRedMine();

        assertEquals(sampleScore, activity.score);
    }

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
        assertEquals(expectedDrawable, drawableResId);
    }

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
        assertEquals(expectedDrawable, drawableResId);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Test
    public void roundCompletesWhenChancesFinished1() throws InterruptedException {
        activity.numSelectionsLeft = 1;
        PowerUpUtils.sPauseTest = true;

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

        sessionManager.saveData(expectedScore, PowerUpUtils.NUMBER_OF_ROUNDS);

        assertEquals(expectedScore, sessionManager.getScore());
    }

    @Test
    public void testSessionManagerCompletedRounds() {
        sessionManager = new MinesweeperSessionManager(activity);
        int expectedRounds = PowerUpUtils.NUMBER_OF_ROUNDS - 1;

        sessionManager.saveData(5, expectedRounds);

        assertEquals(expectedRounds, sessionManager.getCompletedRounds());
    }

    @Test
    public void scoreNotNegative() {
        sessionManager = new MinesweeperSessionManager(activity);

        assertTrue(sessionManager.getScore() >= 0);
    }

    @Test
    public void roundsCompletedNotNegative() {
        sessionManager = new MinesweeperSessionManager(activity);

        assertTrue(sessionManager.getCompletedRounds() >= 0);
    }

}
