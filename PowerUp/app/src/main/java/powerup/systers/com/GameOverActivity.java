package powerup.systers.com;

import android.app.Activity;
import android.os.Bundle;

public class GameOverActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) { // Sets the application to completed_game seen.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.completed_game);
    }
}
