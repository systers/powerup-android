package powerup.systers.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import powerup.systers.com.db.DatabaseHandler;

public class MapActivity extends Activity {

    private DatabaseHandler mDbHandler;
    private OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Button b = (Button) v;
            if (getmDbHandler().setSessionId(b.getText().toString())) {
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
    boolean text = false;

    public void showText(View v) {
        final TextView houset = (TextView) findViewById(R.id.houset);
        final TextView hospitalt = (TextView) findViewById(R.id.hospitalt);
        final TextView schoolt = (TextView) findViewById(R.id.schoolt);
        final TextView boyfriendt = (TextView) findViewById(R.id.boyfriendt);
        final TextView storet = (TextView) findViewById(R.id.storet);

        if (text == true) {
            text = false;
            houset.setVisibility(View.VISIBLE);
            hospitalt.setVisibility(View.VISIBLE);
            schoolt.setVisibility(View.VISIBLE);
            boyfriendt.setVisibility(View.VISIBLE);
            storet.setVisibility(View.VISIBLE);
        } else {
            text = true;
            houset.setVisibility(View.INVISIBLE);
            hospitalt.setVisibility(View.INVISIBLE);
            schoolt.setVisibility(View.INVISIBLE);
            boyfriendt.setVisibility(View.INVISIBLE);
            storet.setVisibility(View.INVISIBLE);
        }
    }
}
