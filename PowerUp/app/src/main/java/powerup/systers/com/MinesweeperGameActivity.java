package powerup.systers.com;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Random;

import powerup.systers.com.powerup.MinesweeperSessionManager;
import powerup.systers.com.powerup.ProsAndConsActivity;


public class MinesweeperGameActivity extends AppCompatActivity {

    private HashSet<String> mines;
    private int score = 0;
    private int gameRound = 0;
    private int numRedMines;
    private int numSelectionsLeft;
    private TextView scoreTextView;
    private ImageView banner;
    private ImageView continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minesweeper_game);
        scoreTextView = (TextView) findViewById(R.id.minesweeper_score);
        banner = (ImageView) findViewById(R.id.banner);
        continueButton = (ImageView) findViewById(R.id.continue_button);
        mines = new HashSet<>();
        setUpGame();

    }

    private void setUpGame() {


        boolean calledByGameActivity = getIntent().getBooleanExtra(PowerUpUtils.CALLED_BY,false);
        numSelectionsLeft = PowerUpUtils.MAXIMUM_FLIPS_ALLOWED;
        if(!calledByGameActivity){
        MinesweeperSessionManager session = new MinesweeperSessionManager(this);
        score = session.getScore();
            Log.e("sachin",""+session.getScore()+" "+session.getCompletedRounds());
        scoreTextView.setText("Score: "+score);
        gameRound = session.getCompletedRounds();
           ImageView background = (ImageView) findViewById(R.id.mine_background);
            background.setImageDrawable(getResources().getDrawable(PowerUpUtils.ROUND_BACKGROUNDS[gameRound]));
        mines.clear();
        }
        gameRound++;
        numRedMines = (PowerUpUtils.ROUNDS_FAILURE_PERCENTAGES[gameRound - 1] * PowerUpUtils.NUMBER_OF_CELLS) / 100;
        while (mines.size() != numRedMines) {
            Random random = new Random();
            mines.add(PowerUpUtils.ID_REFERENCE + Math.abs(random.nextInt() % 25));
    }
    }

    public void openMine(final View view) {

        final ImageView imageView = (ImageView) view;
        imageView.setRotationY(0f);
        imageView.animate().setDuration(100).rotationY(90f).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (mines.contains(view.getResources().getResourceName(view.getId()))) {
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.red_star));
                    openedRedMine();
                } else {
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.green_star));
                    openedGreenMine();
                }

                imageView.setRotationY(270f);
                imageView.animate().rotationY(360f).setListener(null);
                imageView.setClickable(false);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });

    }

    private void openedRedMine(){
        showBanner(1);
    }

    private void openedGreenMine(){

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.animator.zoom_in);
        scoreTextView.startAnimation(animation);
        score ++;
        scoreTextView.setText("Score: "+score);
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.animator.zoom_out);
        scoreTextView.startAnimation(animation);
        numSelectionsLeft--;
        if (numSelectionsLeft == 0){
            showBanner(0);
        }

    }
    public void showBanner(int type){

        banner = (ImageView) findViewById(R.id.banner);

        Drawable drawable = type == 1 ? getResources().getDrawable(R.drawable.failure_banner):getResources().getDrawable(R.drawable.success_banner);
        banner.setImageDrawable(drawable);
        banner.animate().setDuration(1500).alpha(0.95f).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                continueButton.setAlpha(1f);
                continueButton.setClickable(true);


            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });


        ImageView mine;
        for (int id : PowerUpUtils.minesViews){
            mine = (ImageView) findViewById(id);
            if (mines.contains(getResources().getResourceName(id))){
               drawable = getResources().getDrawable(R.drawable.red_star);
            }
            else {
                drawable = getResources().getDrawable(R.drawable.green_star);
                setImageButtonEnabled(this,false,mine,drawable);
            }
            mine.setImageDrawable(drawable);
            mine.setEnabled(false);

            }

        }

    public static void setImageButtonEnabled(Context ctxt, boolean enabled, ImageView item, Drawable originalIcon) {
        item.setAlpha(0.8f);
        Drawable res = originalIcon.mutate();
        if (enabled)
            res.setColorFilter(null);
        else
            res.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
    }

    public void continuePressed(View view){


        MinesweeperSessionManager session = new MinesweeperSessionManager(this);
        session.saveData(score,gameRound);
        Log.e("sachin",""+session.getScore()+" "+session.getCompletedRounds());
        finish();
        startActivity(new Intent(MinesweeperGameActivity.this,ProsAndConsActivity.class));
        overridePendingTransition(R.animator.fade_in,R.animator.fade_out);


    }



    }




