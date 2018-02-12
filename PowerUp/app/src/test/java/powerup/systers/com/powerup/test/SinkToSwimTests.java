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

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.setupActivity(SinkToSwimGame.class);
    }

    @Test
    public void shouldNotBeNull() throws Exception {
        /**
         * Null activity can cause the app to crash.
         * This tests if this activity is null.
         */
        assertNotNull(activity);
    }

    @Test
    public void gameEndStartsSinkToSwimEndActivity() {
        Class SinkToSwimEnd = SinkToSwimEndActivity.class;
        Intent expectedIntent = new Intent(activity, SinkToSwimEnd);

        activity.gameEnd();

        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        /**
         * This tests if the SinkToSwinEndActivity is the next activity.
         */
        assertTrue(expectedIntent.filterEquals(actualIntent));
    }

    @Test
    public void bringsPointerToInitialPosition() {
        activity.height = 1000;
        float expectedPosition = 100;

        activity.bringPointerAndBoatToInitial();

        float actualPosition = activity.pointer.getY();
        /**
         * This tests chekcs if the pointer Y value of the pointer
         * is at 100 after runnng bringPointerAndBoatToInitial().
         */
        assertEquals(expectedPosition, actualPosition, 0f);
    }

    @Test
    public void bringsAvatarToInitialPosition() {
        activity.height = 1000;
        float expectedPosition = -50;

        activity.bringPointerAndBoatToInitial();

        float actualPosition = activity.boat.getY();
        /**
         * This tests chekcs if the pointer Y value of the pointer
         * is at -50 after runnng bringPointerAndBoatToInitial().
         */
        assertEquals(expectedPosition, actualPosition, 0f);
    }

    @Test
    public void setButtonsEnabled() {
        boolean expected = false;

        activity.setButtonsEnabled(false);

        /**
         * SetButtonsEnabled(false) makes buttons NOT Clickable.
         * This tests if this is happens.
         */
        assertEquals(expected, activity.skipOption.isClickable());
        assertEquals(expected, activity.trueOption.isClickable());
        assertEquals(expected, activity.falseOption.isClickable());
    }

    @Test
    public void setButtonsEnabled2() {
        boolean expected = true;

        activity.setButtonsEnabled(true);

        /**
         * SetButtonsEnabled(true) makes buttons Clickable.
         * This tests if this is happens.
         */
        assertEquals(expected, activity.skipOption.isClickable());
        assertEquals(expected, activity.trueOption.isClickable());
        assertEquals(expected, activity.falseOption.isClickable());
    }

    @Test
    public void TimerPausesCorrectly() {
        activity.onPause();
        /**
         * When the app is paused, the timer should stop.
         * This tested by checking if countDownTimer is null.
         */
        assertEquals(null, activity.countDownTimer);
    }

    @Test
    public void bringsPointerUp1() {
        activity.height = 1000;
        float expectedPosition = 199;

        activity.pointer.setY(199);
        activity.animator = activity.boat.animate().setDuration(1000);
        activity.bringPointerAndAvatarUp();

        float actualPosition = activity.pointer.getY();
        /**
         * This function sets variables so that when bringPointerAndAvatarUp() 
         * runs, the Y position of the pointer does not change.
         * This tests if the actual Y pointer position stays at 199.
         */
        assertEquals(expectedPosition, actualPosition, 0f);
    }

    @Test
    public void bringsAvatarUp1() {
        activity.height = 1000;
        float expectedPosition = activity.boat.getY();

        activity.pointer.setY(199);
        activity.animator = activity.pointer.animate().setDuration(1000);
        activity.bringPointerAndAvatarUp();

        float actualPosition = activity.boat.getY();
        /**
         * This function sets variables so that when bringPointerAndAvatarUp() 
         * runs, the Y position of the boat, where the avatar is, does not change.
         * This tests if the actual Y avatar position stays the same.
         */
        assertEquals(expectedPosition, actualPosition, 0f);
    }

    @Test
    public void bringsPointerUp2() {
        activity.height = 1000;
        float expectedPosition = 101;

        activity.pointer.setY(201);
        activity.animator = activity.pointer.animate().setDuration(1000);
        activity.bringPointerAndAvatarUp();

        float actualPosition = activity.pointer.getY();
        /**
         * This function sets variables so that when bringPointerAndAvatarUp() 
         * runs, the Y position of the pointer decreases by 100.
         * This tests if the actual Y pointer position is 201-100=101.
         */
        assertEquals(expectedPosition, actualPosition, 0f);
    }

    @Test
    public void bringsAvatarUp2() {
        activity.height = 1000;
        float expectedPosition = activity.boat.getY() - 66;

        activity.pointer.setY(201);
        activity.animator = activity.pointer.animate().setDuration(1000);
        activity.bringPointerAndAvatarUp();

        float actualPosition = activity.boat.getY();
        /**
         * This function sets variables so that when bringPointerAndAvatarUp() 
         * runs, the Y position of the boat, where the avatar is, decreases by 66.
         * This tests if the actual Y avatar position is 66 less than before.
         */
        assertEquals(expectedPosition, actualPosition, 1f);
    }

    @Test
    public void bringsPointerDown() {
        activity.height = 1000;
        activity.speed = 2;
        float expectedPosition = activity.pointer.getY() + 20;

        activity.bringPointerAndAvatarDown();

        float actualPosition = activity.pointer.getY();
        /**
         * This function sets variables so that when bringPointerAndAvatarDown() 
         * runs, the Y position of the pointer increases by 20.
         * This tests if the actual Y pointer position is 20 more than before.
         */
        assertEquals(expectedPosition, actualPosition, 1f);
    }

    @Test
    public void bringsAvatarDown() {
        activity.height = 1000;
        activity.speed = 3;
        float expectedPosition = activity.boat.getY() + 20;

        activity.bringPointerAndAvatarDown();

        float actualPosition = activity.boat.getY();
        /**
         * This function sets variables so that when bringPointerAndAvatarDown() 
         * runs, the Y position of the boat, where the avatar is, increases by 20.
         * This tests if the actual Y avatar position is 20 more than before.
         */
        assertEquals(expectedPosition, actualPosition, 1f);
    }

    @Test
    public void gameOverOnQuestionsFinished() {
        activity.curQuestion = PowerUpUtils.SWIM_SINK_QUESTION_ANSWERS.length - 1;
        Class SinkToSwimEnd = SinkToSwimEndActivity.class;
        Intent expectedIntent = new Intent(activity, SinkToSwimEnd);

        activity.showNextQuestion();

        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        /**
         * This fuction sets curQuestion so when showNextQuestion() runs,
         * gameEnd() runs are starts SinkToSwimEndActivity.
         * This tests if this happens.
         */
        assertTrue(expectedIntent.filterEquals(actualIntent));
    }

    @Test
    public void setScoreIncrementsForCorrectAnswer() {
        activity.curQuestion = 0;
        activity.score = 0;
        String correctAnswer = "F";

        activity.answerChosen(activity.findViewById(R.id.false_option));

        /**
         * This funciton sets variables so when answerChosen(false)
         * runs, the new score should be 1.
         * This tests if the ScoreView is "Score: 1".
         */
        assertEquals("Score: 1", activity.scoreView.getText().toString());
    }

    @Test
    public void scoreNotIncrementsOnWrongAnswer() {
        activity.curQuestion = 0;
        activity.score = 0;
        String correctAnswer = "F";

        activity.answerChosen(activity.findViewById(R.id.true_option));

         /**
         * This funciton sets variables so when answerChosen(true)
         * runs, the new score should be 0.
         * This tests if the ScoreView is "Score: 0".
         */
        assertEquals("Score: 0", activity.scoreView.getText().toString());
    }

    @Test
    public void nextQuestionShowsUpOnAnswering() {
        activity.curQuestion = 0;

        activity.answerChosen(activity.findViewById(R.id.true_option));

         /**
         * This function runs answerChosen() which runs showNextQuestion(). 
         * This tests if the QuestionView is the second/ next Sink To Swim question.
         */
        assertEquals(PowerUpUtils.SWIM_SINK_QUESTION_ANSWERS[1][0], activity.questionView.getText().toString());
    }

}
