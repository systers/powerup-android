/**
 * @desc brings user to map if previous session is being opened. Otherwise,
 * a new user will be brought to the “Avatar Room” to customize avatar
 * upon starting the app.
 */

package powerup.systers.com;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

public class StartActivity extends Activity implements View.OnClickListener {

    private SharedPreferences mPreferences;
    private boolean mHasPreviouslyStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        mHasPreviouslyStarted = mPreferences.getBoolean(getString(R.string.preferences_has_previously_started), false);

        findViewById(R.id.newUserButtonFirstPage).setOnClickListener(this);
        findViewById(R.id.startButtonMain).setOnClickListener(this);
        findViewById(R.id.aboutButtonMain).setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHasPreviouslyStarted = mPreferences.getBoolean(getString(R.string.preferences_has_previously_started), false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.newUserButtonFirstPage:
                startActivityForResult(new Intent(StartActivity.this, AvatarRoomActivity.class), 0);
                break;
            case R.id.startButtonMain:
                if (mHasPreviouslyStarted) {
                    startActivity(new Intent(StartActivity.this, MapActivity.class));
                } else {
                    startActivity(new Intent(StartActivity.this, AvatarRoomActivity.class));
                }
                break;
            case R.id.aboutButtonMain:
                startActivity(new Intent(StartActivity.this, AboutActivity.class));
                break;
        }
    }
  
}
