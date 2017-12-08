/**
 * @desc finishes the game and shows a screen telling the user that the game
 * has completed.
 */

package powerup.systers.com;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class GameOverActivity extends AppCompatActivity {

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
