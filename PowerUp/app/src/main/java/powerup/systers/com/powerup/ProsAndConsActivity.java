package powerup.systers.com.powerup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import powerup.systers.com.MapActivity;
import powerup.systers.com.MinesweeperGameActivity;
import powerup.systers.com.PowerUpUtils;
import powerup.systers.com.R;

public class ProsAndConsActivity extends AppCompatActivity {

    int completedRounds;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minesweeper_pros_cons);
        TextView proOne = (TextView) findViewById(R.id.pro_one);
        TextView proTwo = (TextView) findViewById(R.id.pro_two);
        TextView conThree = (TextView) findViewById(R.id.con_one);
        MinesweeperSessionManager sessionManager = new MinesweeperSessionManager(this);
        completedRounds = sessionManager.getCompletedRounds();
        proOne.setText(PowerUpUtils.ROUNDS_PROS_CONS[completedRounds-1][0]);
        proTwo.setText(PowerUpUtils.ROUNDS_PROS_CONS[completedRounds-1][1]);
        conThree.setText(PowerUpUtils.ROUNDS_PROS_CONS[completedRounds-1][2]);

    }
    public void continuePressedProsAndCons(View v){
        if (completedRounds < PowerUpUtils.NUMBER_OF_ROUNDS) {
            startActivity(new Intent(ProsAndConsActivity.this, MinesweeperGameActivity.class).putExtra(PowerUpUtils.CALLED_BY,false));
        }
        else {
            new MinesweeperSessionManager(this).saveMinesweeperOpenedStatus(false);
            startActivity(new Intent(ProsAndConsActivity.this,MapActivity.class));
        }


    }
}
