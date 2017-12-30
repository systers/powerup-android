package powerup.systers.com.vocab_match_game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import powerup.systers.com.MapActivity;
import powerup.systers.com.R;
import powerup.systers.com.powerup.PowerUpUtils;

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
                    Intent intent = new Intent(VocabMatchTutorials.this,VocabMatchGameActivity.class);
                    finish();
                    startActivity(intent);
                }else {
                    tutorialView.setImageDrawable(getResources().getDrawable(PowerUpUtils.VOCAB_MATCH_TUTS[curTutorialImage]));
                    curTutorialImage++;
                }
            }
        });
    }
    public void goToMap(){

        Intent intent = new Intent(this, MapActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
    //function to execute when back button is pressed
    @Override
    public void onBackPressed() {
        goToMap();
    }
}
