package powerup.systers.com.powerup.test;


import android.content.Intent;
import android.os.Build;
import android.widget.ImageView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import powerup.systers.com.BuildConfig;
import powerup.systers.com.GameOverActivity;
import powerup.systers.com.R;
import powerup.systers.com.powerup.PowerUpUtils;
import powerup.systers.com.sink_to_swim_game.SinkToSwimEndActivity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class SinkToSwimEndTests {

    private SinkToSwimEndActivity activity;

    @Before
    public void setUp() {
        Robolectric.buildActivity(SinkToSwimEndActivity.class);
    }

    private void createWithIntent(int extra, String type) {
        Intent intent = new Intent(RuntimeEnvironment.application, SinkToSwimEndActivity.class);
        if ("score".equals(type)) {
            intent.putExtra(PowerUpUtils.SCORE, extra);
        } else if ("correct".equals(type)) {
            intent.putExtra(PowerUpUtils.CORRECT_ANSWERS, extra);
        } else if ("wrong".equals(type)) {
            intent.putExtra(PowerUpUtils.WRONG_ANSWER, extra);
        }
        activity = Robolectric.buildActivity(SinkToSwimEndActivity.class, intent)
                .create()
                .start()
                .resume()
                .visible()
                .get();
    }

    @Test
    public void createsAndDestroysActivity() {
        createWithIntent(12, "score");
    }

    @Test
    public void shouldNotBeNull() throws Exception {
        createWithIntent(12, "score");

        assertNotNull(activity);
    }

    @Test
    public void scoreDisplayedCorrectly(){
        int expectedScore = 3;
        createWithIntent(expectedScore, "score");

        String actualScore = activity.scoreView.getText().toString();
        assertEquals(""+expectedScore,actualScore);
    }

    @Test
    public void correctAnswerDisplayedCorrectly(){
        int expectedCorrect = 3;
        createWithIntent(expectedCorrect, "correct");

        String actualCorrect = activity.correctView.getText().toString();
        assertEquals(""+expectedCorrect, actualCorrect);
    }

    @Test
    public void wrongAnswerDisplayedCorrectly(){
        int expectedWrong = 2;
        createWithIntent(expectedWrong, "wrong");

        String actualWrong = activity.wrongView.getText().toString();
        assertEquals(""+expectedWrong,actualWrong);
    }


    @Test
    public void launchesNextActivityCorrectly(){
        createWithIntent(5, "score");
        Class GameOver = GameOverActivity.class;
        Intent expectedIntent = new Intent(activity, GameOver);

        ImageView continueButton = (ImageView) activity.findViewById(R.id.continue_button);
        continueButton.callOnClick();

        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        assertTrue(expectedIntent.filterEquals(actualIntent));
    }
}
