package powerup.systers.com.sink_to_swim_game;


import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import powerup.systers.com.GameActivity;
import powerup.systers.com.R;
import powerup.systers.com.powerup.PowerUpUtils;

public class SinkToSwimGame extends AppCompatActivity {
    //make session manager for this
    //handle case of interruption..timer lifecycle
    //addd animations at last - score, boat, fade in, checkmark, timer,
    //do formatting
    //add tests + other Power Up work like setting up scenarios
    //make the sceanrio content flowcharts
    //include this mini game into the main power up game
    //boat animation make better, reduce extra code + refract + proper names, score textview improve, sometimes upward not work + downward motion not completely smooth , checkmark, fade animations, score animation

    FrameLayout.LayoutParams lp;
    ImageView pointer, boat;
    int height;
    private Animation mAnimation;
    int score, curQuestion;
    Button trueOption, falseOption, skipOption;
    TextView questionView, timer, scoreView;
    long questionStartTime;
    long millisleft;
    CountDownTimer countDownTimer;
    int speed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sink_to_swim_game);

        mAnimation = AnimationUtils.loadAnimation(this, R.animator.boat_animation); // Now it should work smoothy as hanging problem is solved now
        mAnimation.setInterpolator(new LinearInterpolator());
        boat = (ImageView) findViewById(R.id.boat);
        trueOption = (Button) findViewById(R.id.true_option);
        skipOption = (Button) findViewById(R.id.skip_option);
        falseOption = (Button) findViewById(R.id.false_option);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;

        questionView = (TextView) findViewById(R.id.questionView);
        scoreView = (TextView) findViewById(R.id.swim_score);
        timer = (TextView) findViewById(R.id.time);

        pointer = (ImageView) findViewById(R.id.pointer);
        initialSetUp();
    }

    public void initialSetUp() {
        bringPointerToTop();
        boat.startAnimation(mAnimation);
        score = 0;
        speed = 2;
        curQuestion = -1;
        millisleft = 60000; //=30 sec
        questionStartTime = millisleft;
        setButtonsEnabled(false);
        boat.setTranslationY(-height*0.05f);
        bringPointerAndAvatarDownContinously();
        showNextQuestion();
        countDownTimer = new CountDownTimer(millisleft, 1000) {

            public void onTick(long millisUntilFinished) {
                millisleft = millisUntilFinished;
                timer.setText("" + millisleft / 1000);
                if (pointer.getTranslationY() > height * 0.75){ // edit this with actual value
                    gameEnd();
                }
            }

            public void onFinish() {
                gameEnd();
            }
        };
        countDownTimer.start();


    }

    private void gameEnd() {
        countDownTimer.cancel();
        startActivity(new Intent(SinkToSwimGame.this, GameActivity.class));
        finish();
    }

    private void showNextQuestion() {
        questionStartTime = millisleft;
        curQuestion++;
        if (curQuestion == PowerUpUtils.SWIM_SINK_QUESTION_ANSWERS.length)
            curQuestion = 0;
        new Runnable() {
            @Override
            public void run() {
                questionView.setText(PowerUpUtils.SWIM_SINK_QUESTION_ANSWERS[curQuestion][0]);
            }
        }.run();
setButtonsEnabled(true);
    }

    public void answerChosen(View view) {
        setButtonsEnabled(false);
        //replace this by switch case and ?: ternary operator
        if (view == findViewById(R.id.true_option)) {
            if (PowerUpUtils.SWIM_SINK_QUESTION_ANSWERS[curQuestion][1] == "T") {
                score += 1 + questionStartTime / 1000 - millisleft / 1000;
                increaseonePointerAvatarUpOneLevel();
            } else {
                score += questionStartTime / 1000 - millisleft / 1000 - 1;
            }
        } else if (view == findViewById(R.id.false_option)) {
            if (PowerUpUtils.SWIM_SINK_QUESTION_ANSWERS[curQuestion][1] == "F") {
                score += 1 + questionStartTime / 1000 - millisleft / 1000;
                increaseonePointerAvatarUpOneLevel();
            } else {
                score += questionStartTime / 1000 - millisleft / 1000 - 1;
            }
        } else {
        }
        showNextQuestion();
        scoreView.setText("Score: " + score);
    }

    private void bringPointerToTop() {
        float initial_height = height * 0.10f;
       pointer.setTranslationY(initial_height);
    }

    public void increaseonePointerAvatarUpOneLevel() {
        Log.e("sachin",""+pointer.getTranslationY());
        if (pointer.getTranslationY() < height * 0.2)
            return;
        float pixels = height * 0.1f;
        pointer.animate().translationYBy(-pixels).start();
        boat.animate().translationYBy(-(height*0.1f*0.66f));

    }
    public void bringPointerAndAvatarDownContinously() {
        float pixels = height * 0.01f * speed;
        pointer.animate().translationYBy(pixels).setDuration(1000).start();
        boat.animate().translationYBy(height* 0.01f* speed* 0.66f).setDuration(1000);
    }
    public void setButtonsEnabled(boolean bool){
        trueOption.setClickable(bool);
        falseOption.setClickable(bool);
        skipOption.setClickable(bool);
    }
}
