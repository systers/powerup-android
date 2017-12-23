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
         * The expected Intent is SinkToSwinEndActivity.
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
         * The Y value of the pointer is expected to be 100 when 
         * bringPointerAndBoatToInitial() runs.
         * This tests if the pointer Y value is at 100.
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
         * The Y value of the boat is expected to be -50 when 
         * bringPointerAndBoatToInitial() runs.
         * This tests if the boat Y value is at -50.
         */
        assertEquals(expectedPosition, actualPosition, 0f);
    }

    @Test
    public void setButtonsEnabled() {
        boolean expected = false;

        activity.setButtonsEnabled(false);

        /**
         * When the function setButtonsEnabled(false) runs the 
         * skip, true, and false buttons are NOT Clickable.
         * This tests if this is true
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
         * When the function setButtonsEnabled(true) runs the 
         * skip, true, and false buttons are Clickable
         * This tests if this is true
         */
        assertEquals(expected, activity.skipOption.isClickable());
        assertEquals(expected, activity.trueOption.isClickable());
        assertEquals(expected, activity.falseOption.isClickable());
    }

    @Test
    public void TimerPausesCorrectly() {
        activity.onPause();
        /**
         * When the app is paused, the timer should be canceled(it can't be paused)
         * This tests if this is true by checking if the countDownTimer is null
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
         * This function puts the pointer Y at 199 because bringPointerAndAvatarUp() checks if
         * the pointer Y value is less than the activity height * 0.2, which is 200.
         * If the if statement is true, the position of the pointer does not change
         * This tests if the actual position of the pointer for Y is 199.
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
         * This function puts the pointer Y at 199 because bringPointerAndAvatarUp() checks if
         * the pointer Y value is less than the activity height * 0.2 = 200.
         * If the if statement is true, the position of the boat does not change
         * This tests if the actual position of the boat for Y is the same as before.
         * The avatar location is the same as the boat.
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
         * This function puts the pointer Y at 201 because bringPointerAndAvatarUp() checks if
         * the pointer Y value is less than the activity height * 0.2 = 200.
         * If the if statement is false, the position of the pointer Y decreases by the 
         * activity height * 0.1= 100. This makes the expected position 201-100=101.
         * This tests if the actual position of the pointer for Y is 101.
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
         * This function puts the pointer Y at 201 because bringPointerAndAvatarUp() checks if
         * the pointer Y value is less than the activity height * 0.2 = 200.
         * If the if statement is false, the position of the boat Y decreases by the
         * activity height * 0.01 *0.66 = 66. 
         * This tests if the actual position of the boat for Y is 66 less than before.
         * The avatar location is the same as the boat location.
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
         * This function runs bringPointerAndAvatarDown(),
         * which changes the pointer position by  height * 0.01 * speed
         * Since the height is 1000 and the speed is 2, the position changes by +20
         * This tests if the actual position of the pointer for Y is 20 more than before.
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
         * This function runs bringPointerAndAvatarDown(),
         * which changes the boat position by  height * 0.01 * speed * 0.66
         * Since the height is 1000 and the speed is 3, the position is changed by +20
         * This tests if the actual boat of the pointer for Y is around 20 more than before.
         * The avatar location is the same as the boat location.
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
         * This fuction sets curQuestion to equal the number of questions(SWIM_SINK_QUESTION_ANSWERS.length - 1)
         * Then runs showNetQuestion() to see if gameEnd() will run
         * gameEnd() should go to start SinkToSwimEndActivity
         * This tests if this happens
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
         * On the first question the answer is false
         * When answerChosen runs with the "false" option, the answer would be correct
         * Since this is the first question, and since the score is 0, the new score should be 1
         * This tests if the ScoreView is "Score: 1"
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
         * On the first question the answer is false
         * When answerChosen runs with the "true" option, the answer would be wrong
         * Since this is the first question, and since the score is 0, the new score should be 0
         * This tests if the ScoreView is "Score: 0"
         */
        assertEquals("Score: 0", activity.scoreView.getText().toString());
    }

    @Test
    public void nextQuestionShowsUpOnAnswering() {
        activity.curQuestion = 0;

        activity.answerChosen(activity.findViewById(R.id.true_option));

         /**
         * This tests that when answerChosen() runs, showNextQuestion() runs
         * And that since this is the first question, the second question would display
         * This tests if the QuestionView is the scond question from SWIM_SINK_QUESTION_ANSWERS.
         */
        assertEquals(PowerUpUtils.SWIM_SINK_QUESTION_ANSWERS[1][0], activity.questionView.getText().toString());
    }

}
