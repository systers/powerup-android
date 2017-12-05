package powerup.systers.com.sink_to_swim_game;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import powerup.systers.com.GameOverActivity;
import powerup.systers.com.R;
import powerup.systers.com.powerup.PowerUpUtils;

public class SinkToSwimEndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sink_to_swim_end);
        Intent intent = getIntent();
        int wrongAnswers= intent.getExtras().getInt(PowerUpUtils.WRONG_ANSWER);
        int correctAnswers= intent.getExtras().getInt(PowerUpUtils.CORRECT_ANSWERS);
        int score = intent.getExtras().getInt(PowerUpUtils.SCORE);
        TextView  scoreView = (TextView) findViewById(R.id.swim_score);
        TextView  correctView = (TextView) findViewById(R.id.correct);
        TextView  wrongView = (TextView) findViewById(R.id.wrong);
        scoreView.setText(""+score);
        correctView.setText(""+correctAnswers);
        wrongView.setText(""+wrongAnswers);
    }

    public void continuePressed(View view){
        Intent intent = new Intent(SinkToSwimEndActivity.this, GameOverActivity.class);
        finish();
        startActivityForResult(intent, 0);
    }

    @Override
    public void onBackPressed() {
        backToHome();
    }

    private void backToHome() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }
}
