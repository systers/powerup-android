package powerup.systers.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import powerup.systers.com.db.DatabaseHandler;

/**
 * The main part of the UI - responsible for displaying the map
 */
public class MapActivity extends Activity {

    private DatabaseHandler mDbHandler;

    /**
     * The callback to be invoked when a location on the map has been clicked
     */
    private OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Button b = (Button) v;
            if (getmDbHandler().setSessionId(b.getText().toString())) { // if the scene isn't completed
                startActivityForResult(new Intent(MapActivity.this, GameActivity.class), 0);
            } else {
                startActivityForResult(new Intent(MapActivity.this, CompletedSceneActivity.class), 0);
            }
        }
    };

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setmDbHandler(new DatabaseHandler(this));
        getmDbHandler().open();
        setContentView(R.layout.gamemap);

        // Set OnClickListeners for each of the different locations on the map

        Button house = (Button) findViewById(R.id.HouseButton);
        house.setOnClickListener(onClickListener);

        Button boyfriend = (Button) findViewById(R.id.BoyfriendButton);
        boyfriend.setOnClickListener(onClickListener);

        Button hospital = (Button) findViewById(R.id.HospitalButton);
        hospital.setOnClickListener(onClickListener);

        Button school = (Button) findViewById(R.id.SchoolButton);
        school.setOnClickListener(onClickListener);

        Button storeButton = (Button) findViewById(R.id.storeButton);
        storeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapActivity.this, DressingRoomActivity.class));
            }
        });

        Button homeButton = (Button) findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(MapActivity.this, StartActivity.class));
            }
        });

    }

    public DatabaseHandler getmDbHandler() {
        return mDbHandler;
    }

    public void setmDbHandler(DatabaseHandler mDbHandler) {
        this.mDbHandler = mDbHandler;
    }
}
