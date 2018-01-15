/**
 * @desc finishes the game and shows a screen telling the user that the game
 * has completed.
 */

package powerup.systers.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import powerup.systers.com.StartActivity;
public class GameOverActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.completed_game);
        Button backToMap = (Button) findViewById(R.id.ContinueButtonMap);
        backToMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameOverActivity.this,
                        MapActivity.class);
                finish();
                startActivityForResult(intent, 0);
            }
        });
    }
	 //function to go back to map
    public void goToStart(){
        new GameActivity().gameActivityInstance.finish();
        Intent intent = new Intent(this, StartActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goToMap()
	}
}
