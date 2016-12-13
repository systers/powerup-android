package powerup.systers.com;

import android.app.Activity;
import android.os.Bundle;

public class GameOverActivity extends Activity {

    /**
     * This activity is shown when all scenarios are over.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.completed_game);
    }
}
