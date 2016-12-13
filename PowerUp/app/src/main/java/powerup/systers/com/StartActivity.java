package powerup.systers.com;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;

public class StartActivity extends Activity {

    /**
     * The first activity called when app is opened.
     */

    private SharedPreferences preferences;
    private boolean hasPreviouslyStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        hasPreviouslyStarted = preferences.getBoolean(getString(R.string.preferences_has_previously_started), false);
        ImageButton newUserButton = (ImageButton) findViewById(R.id.newUserButtonFirstPage);
        newUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create new Avatar.
                startActivityForResult(new Intent(StartActivity.this, AvatarRoomActivity.class), 0);
            }
        });

        ImageButton startButton = (ImageButton) findViewById(R.id.startButtonMain);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If the app was played before, go directly to MapActivity, else open AvatarRoomActivity to create new Avatar.
                if (hasPreviouslyStarted) {
                    startActivity(new Intent(StartActivity.this, MapActivity.class));
                } else {
                    startActivity(new Intent(StartActivity.this, AvatarRoomActivity.class));
                }

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        hasPreviouslyStarted = preferences.getBoolean(getString(R.string.preferences_has_previously_started), false);
    }
}
