package powerup.systers.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import powerup.systers.com.db.DatabaseHandler;

public class MapActivity extends Activity {

    private DatabaseHandler mDbHandler;
    private OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Button b = (Button) v;
            if (mDbHandler.setSessionId(b.getText().toString())) {
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
        mDbHandler = new DatabaseHandler(this);
        mDbHandler.open();
        setContentView(R.layout.gamemap);

        Button house = (Button) findViewById(R.id.HouseButton);
        house.setOnClickListener(mOnClickListener);

        Button boyfriend = (Button) findViewById(R.id.BoyfriendButton);
        boyfriend.setOnClickListener(mOnClickListener);

        Button hospital = (Button) findViewById(R.id.HospitalButton);
        hospital.setOnClickListener(mOnClickListener);

        Button school = (Button) findViewById(R.id.SchoolButton);
        school.setOnClickListener(mOnClickListener);

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
}
