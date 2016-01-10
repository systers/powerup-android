package powerup.systers.com;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;


public class StartActivity extends Activity {

    boolean previouslyStarted;
    SharedPreferences prefs;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        previouslyStarted = prefs.getBoolean(getString(R.string.pref_previously_started), false);
		ImageButton new_user = (ImageButton) findViewById(R.id.newUserButtonFirstPage);
		new_user.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(StartActivity.this,
						AvatarRoomActivity.class);
				startActivityForResult(myIntent, 0);
			}
		});


        ImageButton start = (ImageButton) findViewById(R.id.startButtonMain);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!previouslyStarted) {
                    startActivity(new Intent(StartActivity.this, AvatarRoomActivity.class));
                } else {
                    startActivity(new Intent(StartActivity.this, MapActivity.class));
                }

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        previouslyStarted = prefs.getBoolean(getString(R.string.pref_previously_started), false);
    }
}
