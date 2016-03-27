package powerup.systers.com;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SplashActivity extends Activity {

    private static int SPLASH_TIME_OUT = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        int c1 = getResources().getColor(R.color.pink);
        int c2 = getResources().getColor(R.color.yellow);
        RelativeLayout r=(RelativeLayout) findViewById(R.id.relativeLayoutSplashActivity);
        ObjectAnimator anim = ObjectAnimator.ofInt(r, "backgroundColor", c1, c2);
        anim.setDuration(3000);
        anim.setEvaluator(new ArgbEvaluator());
        anim.setRepeatCount(ValueAnimator.INFINITE);
        anim.setRepeatMode(ValueAnimator.REVERSE);
        anim.start();

        TextView txtViewTagLine = (TextView) findViewById(R.id.txtViewTagLine);
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/font2.ttf");
        txtViewTagLine.setTypeface(tf);

        TextView headingtextView = (TextView) findViewById(R.id.headingtextView);
        Typeface tf2 = Typeface.createFromAsset(getAssets(),
                "fonts/font1.ttf");
        headingtextView.setTypeface(tf2);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashActivity.this, StartActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);

        StartAnimations();
    }

    private void StartAnimations() {
//        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
//        anim.reset();

//        l.clearAnimation();
//        l.startAnimation(anim);

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.logo);
        iv.clearAnimation();
        iv.startAnimation(anim);

        TextView txtViewTagLine = (TextView) findViewById(R.id.txtViewTagLine);
        Animation anim2 = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim2.reset();
        txtViewTagLine.clearAnimation();
        txtViewTagLine.startAnimation(anim2);


    }
}
