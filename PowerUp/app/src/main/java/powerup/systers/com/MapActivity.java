/**
 * @desc sets up the map screen.
 */

package powerup.systers.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import powerup.systers.com.db.DatabaseHandler;

public class MapActivity extends Activity implements OnClickListener {

    private DatabaseHandler mDbHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamemap);

        mDbHandler = new DatabaseHandler(this);
        mDbHandler.open();

        setClickListeners();
    }

    private void setClickListeners() {
        findViewById(R.id.HouseButton).setOnClickListener(this);
        findViewById(R.id.BoyfriendButton).setOnClickListener(this);
        findViewById(R.id.HospitalButton).setOnClickListener(this);
        findViewById(R.id.SchoolButton).setOnClickListener(this);
        findViewById(R.id.storeButton).setOnClickListener(this);
        findViewById(R.id.homeButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.HouseButton:
            case R.id.BoyfriendButton:
            case R.id.HospitalButton:
            case R.id.SchoolButton:
                Button b = (Button) v;
                if (mDbHandler.setSessionId(b.getText().toString())) {
                    startActivityForResult(new Intent(MapActivity.this, GameActivity.class), 0);
                } else {
                    startActivityForResult(new Intent(MapActivity.this, CompletedSceneActivity.class), 0);
                }
                break;

            case R.id.storeButton:
                startActivity(new Intent(MapActivity.this, DressingRoomActivity.class));
                break;

            case R.id.homeButton:
                finish();
                startActivity(new Intent(MapActivity.this, StartActivity.class));
                break;
        }
    }

}
