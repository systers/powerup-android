/**
 * @desc if the user has already completed a scenario, she can go
 * to the store or go back to the map.
 */

package powerup.systers.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CompletedSceneActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.completed_scene);
        Button backToMap = (Button) findViewById(R.id.ContinueButtonMap);
        backToMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompletedSceneActivity.this,
                        MapActivity.class);
                finish();
                startActivityForResult(intent, 0);
            }
        });
        Button storeButton = (Button) findViewById(R.id.store_button);
        storeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompletedSceneActivity.this, DressingRoomActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onBackPressed() {
        backToHome();
    }

    private void backToHome() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }
}
