package powerup.systers.com.sink_to_swim_game;

import android.animation.Animator;
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

import powerup.systers.com.R;
import powerup.systers.com.powerup.PowerUpUtils;

public class SinkToSwimGame extends AppCompatActivity {
    //make session manager for this
    //handle case of interruption..timer lifecycle
    //addd animations at last
    //do formatting
    //add tests + other Power Up work like setting up scenarios

    FrameLayout.LayoutParams lp;
    ImageView pointer, boat;
    int height;
    private Animation mAnimation;
    int score, curQuestion, timerValue;
    SinkSwimGameTimeCounter counter;
    Button trueOption, falseOption, skipOption;
    TextView questionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sink_to_swim_game);

        mAnimation = AnimationUtils.loadAnimation(this, R.animator.boat_animation);
        mAnimation.setInterpolator(new LinearInterpolator());
        boat = (ImageView) findViewById(R.id.boat);
        trueOption = (Button) findViewById(R.id.true_option);
        skipOption = (Button) findViewById(R.id.skip_option);
        falseOption = (Button) findViewById(R.id.false_option);
        counter = new SinkSwimGameTimeCounter(300,300);
        questionView = (TextView) findViewById(R.id.questionView);
        initialSetUp();
    }

    public void initialSetUp() {
        bringPointerToCenter();
        score = 0;
        curQuestion = -1;
        timerValue = 30; //sec
        trueOption.setClickable(false);
        falseOption.setClickable(false);
        skipOption.setClickable(false);
        showNextQuestion();
//        counter.start();
    }

    public void answerChosen(View view){
        trueOption.setClickable(false);
        falseOption.setClickable(false);
        skipOption.setClickable(false);
        if (view == findViewById(R.id.true_option)){
            if(PowerUpUtils.SWIM_SINK_QUESTION_ANSWERS[curQuestion][1]=="T"){
                
            }

        }else if (view == findViewById(R.id.false_option)){
            if(PowerUpUtils.SWIM_SINK_QUESTION_ANSWERS[curQuestion][1]=="F"){

            }
        }else {

        }

        //first disable all three buttons - true false ?
        //check if true or false or no guess
        //fetch answer of question from, if correct +1, otherwise -1 and reflect it on
        //if correct => +1 and shot him up by one level, wrong, shoot him down by 1 point
        //
    }

    private void bringPointerToCenter() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height =  displayMetrics.heightPixels;
        pointer = (ImageView) findViewById(R.id.pointer);

        lp = new FrameLayout.LayoutParams(pointer.getLayoutParams());
        double initial_height =  height* 0.50;
        lp.setMargins(0,(int) initial_height, 0, 0);
        pointer.setLayoutParams(lp);
    }

    public void decreaseonePointerDownOneLevel(){
        lp = new FrameLayout.LayoutParams(pointer.getLayoutParams());
        double pixels = height * 0.1;
        int total = (int) pixels + getMargin();
        lp.setMargins(0,total,0,0);
        pointer.setLayoutParams(lp);
    }
    public void increaseonePointerUpOneLevel(){
        lp = new FrameLayout.LayoutParams(pointer.getLayoutParams());
        double pixels = height * 0.1;
        int total = getMargin() - (int) pixels;
        lp.setMargins(0,total,0,0);
        pointer.setLayoutParams(lp);
    }

    private void showNextQuestion() {
        curQuestion ++;
        questionView.setText(PowerUpUtils.SWIM_SINK_QUESTION_ANSWERS[curQuestion][0]);
        trueOption.setClickable(true);
        falseOption.setClickable(true);
        skipOption.setClickable(true);
//        questionView.animate().alpha(0).setDuration(30).setListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animator) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animator) {
//
//                questionView.animate().alpha(1).setDuration(30).start();
//
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animator) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animator) {
//
//            }
//        });
    }

    public int getMargin() {
        FrameLayout.LayoutParams newparams =  (FrameLayout.LayoutParams) pointer.getLayoutParams();
        return newparams.topMargin;
    }

    @Override
    protected void onResume() {

        boat.startAnimation(mAnimation);

        super.onResume();
    }


    public class SinkSwimGameTimeCounter extends CountDownTimer {

        public SinkSwimGameTimeCounter(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            //change score
            //change timer text
            //change
        }

        @Override
        public void onFinish() {

        }


    }

}
