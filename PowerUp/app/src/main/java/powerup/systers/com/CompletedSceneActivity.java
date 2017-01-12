/**
 * @desc if the user has already completed a scenario, she can go
 * to the store or go back to the map.
 */

package powerup.systers.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CompletedSceneActivity extends Activity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.completed_scene);

        findViewById(R.id.ContinueButtonMap).setOnClickListener(this);
        findViewById(R.id.store_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ContinueButtonMap:
                Intent myIntent = new Intent(CompletedSceneActivity.this,
                        MapActivity.class);
                finish();
                startActivityForResult(myIntent, 0);
                break;

            case R.id.store_button:
                Intent intent = new Intent(CompletedSceneActivity.this, DressingRoomActivity.class);
                startActivity(intent);
                break;
        }
    }

}
