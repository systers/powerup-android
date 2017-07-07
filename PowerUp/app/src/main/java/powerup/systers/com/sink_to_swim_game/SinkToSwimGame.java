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
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import powerup.systers.com.GameActivity;
import powerup.systers.com.R;
import powerup.systers.com.datamodel.Score;
import powerup.systers.com.powerup.PowerUpUtils;

public class SinkToSwimGame extends AppCompatActivity {
    //make session manager for this
    //handle case of interruption..timer lifecycle
    //addd animations at last - score, boat, fade in, checkmark, timer,
    //do formatting
    //add tests + other Power Up work like setting up scenarios
    //make the sceanrio content flowcharts
    //include this mini game into the main power up game

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
        boat.startAnimation(mAnimation);
        trueOption = (Button) findViewById(R.id.true_option);
        skipOption = (Button) findViewById(R.id.skip_option);
        falseOption = (Button) findViewById(R.id.false_option);

        questionView = (TextView) findViewById(R.id.questionView);
        scoreView = (TextView) findViewById(R.id.swim_score);
        timer = (TextView) findViewById(R.id.time);
        initialSetUp();
    }

    public void initialSetUp() {
        bringPointerToCenter();
        score = 0;
        speed = 2;
        curQuestion = -1;
        millisleft = 60000; //=30 sec
        questionStartTime = millisleft;
        trueOption.setClickable(false);
        falseOption.setClickable(false);
        skipOption.setClickable(false);
        boat.setTranslationY(-height*0.05f);
        showNextQuestion();
        countDownTimer = new CountDownTimer(millisleft, 1000) {

            public void onTick(long millisUntilFinished) {
                millisleft = millisUntilFinished;
                timer.setText("" + millisleft / 1000);
                bringPointerAndAvatarDown();
                if (getMargin() > height * 0.75){ // edit this with actual value
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

        trueOption.setClickable(true);
        falseOption.setClickable(true);
        skipOption.setClickable(true);
    }

    public void answerChosen(View view) {
        trueOption.setClickable(false);
        falseOption.setClickable(false);
        skipOption.setClickable(false);
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

    private void bringPointerToCenter() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        pointer = (ImageView) findViewById(R.id.pointer);

        lp = new FrameLayout.LayoutParams(pointer.getLayoutParams());
//        double initial_height = height * 0.50;
        double initial_height = height * 0.10;
        lp.setMargins(0, (int) initial_height, 0, 0);
        pointer.setLayoutParams(lp);
    }

    public void increaseonePointerAvatarUpOneLevel() {
        if (getMargin() < height * 0.19)
            return;
        lp = new FrameLayout.LayoutParams(pointer.getLayoutParams());
        double pixels = height * 0.1;
        int total = getMargin() - (int) pixels;
        lp.setMargins(0, total, 0, 0);
        pointer.setLayoutParams(lp);
        boat.animate().translationYBy(-(height*0.1f*0.66f));

    }
    public void bringPointerAndAvatarDown() {
        lp = new FrameLayout.LayoutParams(pointer.getLayoutParams());
        double pixels = height * 0.01 * speed;
        int total = getMargin() + (int) pixels;
        lp.setMargins(0, total, 0, 0);
        pointer.setLayoutParams(lp);
        boat.animate().translationYBy(height*0.01f*speed*0.66f);
    }

    public int getMargin() {
        FrameLayout.LayoutParams newparams = (FrameLayout.LayoutParams) pointer.getLayoutParams();
        return newparams.topMargin;
    }


}
