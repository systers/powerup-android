package powerup.systers.com.powerup.test;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowCountDownTimer;

import powerup.systers.com.BuildConfig;
import powerup.systers.com.R;
import powerup.systers.com.powerup.PowerUpUtils;
import powerup.systers.com.sink_to_swim_game.SinkToSwimEndActivity;
import powerup.systers.com.sink_to_swim_game.SinkToSwimGame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by sachinaggarwal on 02/07/17.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class SinkToSwimTests {

    SinkToSwimGame activity;

    //Setting Up SinkToSwimGame
    @Before
    public void setUp() throws Exception {
        activity = Robolectric.setupActivity(SinkToSwimGame.class);
    }

    //Checking for Null Exceptions in ProsAndConsActivity
    @Test
    public void shouldNotBeNull() throws Exception {
        assertNotNull(activity);
    }

    //Testing transition to SinkToSwinEndActivity
    @Test
    public void gameEndStartsSinkToSwimEndActivity() {
        Class SinkToSwimEnd = SinkToSwimEndActivity.class;
        Intent expectedIntent = new Intent(activity, SinkToSwimEnd);

        activity.gameEnd();

        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        //Comparing actual vs expected intent
        assertTrue(expectedIntent.filterEquals(actualIntent));
    }

    //Checking the correct pointer location at the mean position
    @Test
    public void bringsPointerToInitialPosition() {
        activity.height = 1000;
        float expectedPosition = 100;

        activity.bringPointerAndBoatToInitial();

        float actualPosition = activity.pointer.getY();
        //Comparing expected position vs actual position
        assertEquals(expectedPosition, actualPosition, 0f);
    }

    //Checking the correct Avatar location
    @Test
    public void bringsAvatarToInitialPosition() {
        activity.height = 1000;
        float expectedPosition = -50;

        activity.bringPointerAndBoatToInitial();

        float actualPosition = activity.boat.getY();
        //Comparing expected position vs actual position
        assertEquals(expectedPosition, actualPosition, 0f);
    }

    //Testing the clickable action of the Skip, True and False buttons
    @Test
    public void setButtonsEnabled() {
        boolean expected = false;

        activity.setButtonsEnabled(false);

        //Checking the clickable action for the three options
        assertEquals(expected, activity.skipOption.isClickable());
        assertEquals(expected, activity.trueOption.isClickable());
        assertEquals(expected, activity.falseOption.isClickable());
    }

    //Testing the clickable action of the Skip, True and False buttons again
    @Test
    public void setButtonsEnabled2() {
        boolean expected = true;

        activity.setButtonsEnabled(true);

        //Checking the clickable action for the three options again
        assertEquals(expected, activity.skipOption.isClickable());
        assertEquals(expected, activity.trueOption.isClickable());
        assertEquals(expected, activity.falseOption.isClickable());
    }

    //Testing Time Pause action
    @Test
    public void TimerPausesCorrectly() {
        activity.onPause();
        assertEquals(null, activity.countDownTimer);
    }

    //Testing the change(up) in pointer location
    @Test
    public void bringsPointerUp1() {
        activity.height = 1000;
        float expectedPosition = 199;

        activity.pointer.setY(199);
        activity.animator = activity.boat.animate().setDuration(1000);
        activity.bringPointerAndAvatarUp();

        float actualPosition = activity.pointer.getY();
        //Comparing the expected vs the actual location
        assertEquals(expectedPosition, actualPosition, 0f);
    }

    //Testing the change(up) in Avatar location
    @Test
    public void bringsAvatarUp1() {
        activity.height = 1000;
        float expectedPosition = activity.boat.getY();

        activity.pointer.setY(199);
        activity.animator = activity.pointer.animate().setDuration(1000);
        activity.bringPointerAndAvatarUp();

        float actualPosition = activity.boat.getY();
        //Comparing the expected vs the actual location
        assertEquals(expectedPosition, actualPosition, 0f);
    }

    //Testing the change(up) in pointer location again
    @Test
    public void bringsPointerUp2() {
        activity.height = 1000;
        float expectedPosition = 101;

        activity.pointer.setY(201);
        activity.animator = activity.pointer.animate().setDuration(1000);
        activity.bringPointerAndAvatarUp();

        float actualPosition = activity.pointer.getY();
        //Comparing the expected vs the actual location again
        assertEquals(expectedPosition, actualPosition, 0f);
    }

    //Testing the change(up) in Avatar location again
    @Test
    public void bringsAvatarUp2() {
        activity.height = 1000;
        float expectedPosition = activity.boat.getY() - 66;

        activity.pointer.setY(201);
        activity.animator = activity.pointer.animate().setDuration(1000);
        activity.bringPointerAndAvatarUp();

        float actualPosition = activity.boat.getY();
        //Comparing the expected vs the actual location again
        assertEquals(expectedPosition, actualPosition, 1f);
    }

    //Testing the change(down) in pointer location
    @Test
    public void bringsPointerDown() {
        activity.height = 1000;
        activity.speed = 2;
        float expectedPosition = activity.pointer.getY() + 20;

        activity.bringPointerAndAvatarDown();

        float actualPosition = activity.pointer.getY();
        //Comparing the expected vs the actual location
        assertEquals(expectedPosition, actualPosition, 1f);
    }

    //Testing the change(down) in Avatar location
    @Test
    public void bringsAvatarDown() {
        activity.height = 1000;
        activity.speed = 3;
        float expectedPosition = activity.boat.getY() + 20;

        activity.bringPointerAndAvatarDown();

        float actualPosition = activity.boat.getY();
        //Comparing the expected vs the actual location
        assertEquals(expectedPosition, actualPosition, 1f);
    }

    //Testing the transition to SinkToSwimEnd Activity on GameOver
    @Test
    public void gameOverOnQuestionsFinished() {
        activity.curQuestion = PowerUpUtils.SWIM_SINK_QUESTION_ANSWERS.length - 1;
        Class SinkToSwimEnd = SinkToSwimEndActivity.class;
        Intent expectedIntent = new Intent(activity, SinkToSwimEnd);

        activity.showNextQuestion();

        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        //Comparing the expected vs the actual intent
        assertTrue(expectedIntent.filterEquals(actualIntent));
    }

    //Testing the correct increment of the score at correct answer
    @Test
    public void setScoreIncrementsForCorrectAnswer() {
        activity.curQuestion = 0;
        activity.score = 0;
        String correctAnswer = "F";

        activity.answerChosen(activity.findViewById(R.id.false_option));

        //Comparing the expected vs the actual value
        assertEquals("Score: 1", activity.scoreView.getText().toString());
    }

    //Testing no increment of score at wrong answer
    @Test
    public void scoreNotIncrementsOnWrongAnswer() {
        activity.curQuestion = 0;
        activity.score = 0;
        String correctAnswer = "F";

        activity.answerChosen(activity.findViewById(R.id.true_option));

        //Comparing the expected vs the actual value
        assertEquals("Score: 0", activity.scoreView.getText().toString());
    }

    //Testing the switching to the next the question
    @Test
    public void nextQuestionShowsUpOnAnswering() {
        activity.curQuestion = 0;

        activity.answerChosen(activity.findViewById(R.id.true_option));

        //Comparing the expected vs the actual question
        assertEquals(PowerUpUtils.SWIM_SINK_QUESTION_ANSWERS[1][0], activity.questionView.getText().toString());
    }

}
