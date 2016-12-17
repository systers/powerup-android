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
    public void onCreate(Bundle savedInstanceState) { // Creation of this window.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.completed_scene);
        Button backToMap = (Button) findViewById(R.id.ContinueButtonMap); // Creates a button called backtoMap from the asset ContinueButtonMap
        backToMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(CompletedSceneActivity.this,
                        MapActivity.class);
                finish();
                startActivityForResult(myIntent, 0);
            }
        });
        Button storeButton = (Button) findViewById(R.id.store_button); // Creates a button called Storebutton from the asset store_button
        storeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompletedSceneActivity.this, DressingRoomActivity.class);
                startActivity(intent);
            }
        });
    }

}