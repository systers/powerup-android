/**
 * @desc instruct the user on how to play Vocab Match before starting the mini game
 */

package powerup.systers.com.vocab_match_game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import powerup.systers.com.MapActivity;
import powerup.systers.com.R;
import powerup.systers.com.powerup.PowerUpUtils;
import powerup.systers.com.sink_to_swim_game.SinkToSwimGame;

public class VocabMatchTutorials extends AppCompatActivity {

    ImageView tutorialView;
    int curTutorialImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocab_match_tutorials);
        tutorialView = (ImageView) findViewById(R.id.tut);
        curTutorialImage = 1;
        tutorialView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(curTutorialImage == PowerUpUtils.VOCAB_MATCH_TUTS.length){
                    Intent intent = new Intent(VocabMatchTutorials.this,VocabMatchGameActivity.class)
                            .putExtra(PowerUpUtils.CALLED_BY, true);
                    finish();
                    startActivity(intent);
                    overridePendingTransition(R.animator.fade_in_custom, R.animator.fade_out_custom);
                }else {
                    tutorialView.setImageDrawable(getResources().getDrawable(PowerUpUtils.VOCAB_MATCH_TUTS[curTutorialImage]));
                    curTutorialImage++;
                }
            }
        });
    }

    /**
     * Goes back to the map when user presses back button
     */
    @Override
    public void onBackPressed(){
        // The flag FLAG_ACTIVITY_CLEAR_TOP checks if an instance of the activity is present and it
        // clears the activities that were created after the found instance of the required activity
        startActivity(new Intent(VocabMatchTutorials.this, MapActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        finish();
    }
}
